# Harbor Install



## https自签名证书部署

**默认的 OpenSSL 生成的签名请求只适用于生成时填写的域名，即 Common Name 填的是哪个域名，证书就只能应用于哪个域名，但是一般内网都是以 IP 方式部署，所以需要添加 SAN(Subject Alternative Name) 扩展信息，以支持多域名和IP** 



<!--redhat系列openssl配置文件在/etc/pki/tls/openssl.cnf,debian系列配置文件在/etc/ssl/openssl.cnf-->

```bash
# 首先 cp 一份 openssl 配置
cp /etc/ssl/openssl.cnf .
# 主要修改 内容如下
[ req ]
# 上面的内容省略，主要增加这个属性(默认在最后一行被注释了，解开即可)
req_extensions = v3_req
[ v3_req ]
# 修改 subjectAltName
subjectAltName = @alt_names
[ alt_names ]
# 此节点[ alt_names ]为新增的，内容如下
IP.1=192.168.1.204   # 扩展IP(私服所在服务器IP)
DNS.1=*.youdomain.com  # 可添加多个扩展域名和IP
```



##### 完整的配置文件如下 :

```shell
[ req ]
default_bits            = 2048
default_keyfile         = privkey.pem
distinguished_name      = req_distinguished_name
attributes              = req_attributes
x509_extensions = v3_ca # The extentions to add to the self signed cert

# Passwords for private keys if not present they will be prompted for
# input_password = secret
# output_password = secret

# This sets a mask for permitted string types. There are several options.
# default: PrintableString, T61String, BMPString.
# pkix   : PrintableString, BMPString (PKIX recommendation before 2004)
# utf8only: only UTF8Strings (PKIX recommendation after 2004).
# nombstr : PrintableString, T61String (no BMPStrings or UTF8Strings).
# MASK:XXXX a literal mask value.
# WARNING: ancient versions of Netscape crash on BMPStrings or UTF8Strings.
string_mask = utf8only

req_extensions = v3_req # The extensions to add to a certificate request
[ v3_req ]

# Extensions to add to a certificate request

basicConstraints = CA:FALSE
keyUsage = nonRepudiation, digitalSignature, keyEncipherment
subjectAltName = @alt_names

[ alt_names ]
IP.1=192.168.1.204
DNS.1=*.advance.net
```



### 创建 CA 及自签名

```shell
# 创建工作目录
mkdir -p /data/cert;cd /data/cert/
# 创建 CA 工作目录
mkdir -p demoCA/{private,certs,crl,newcerts}
# 创建 CA 私钥
(umask 077; openssl genrsa -out demoCA/private/cakey.pem 2048)
# 执行自签名(信息不要乱填，参考下面截图)
openssl req -new -x509 -key demoCA/private/cakey.pem -days 3655 -out demoCA/cacert.pem
# 初始化相关文件
touch demoCA/{index.txt,serial,crlnumber}
# 初始化序列号
echo "01" > demoCA/serial
```



### 创建证书并通过 CA 签名

```shell
# 证书存放目录
mkdir dockercrt
# 创建私钥
openssl genrsa -out dockercrt/docker.key 2048
# 生成带有 SAN 的证书请求
openssl req -new -key dockercrt/docker.key -out dockercrt/docker.csr -config /etc/ssl/openssl.cnf
# 签名带有 SAN 的证书
openssl ca -in dockercrt/docker.csr -out dockercrt/docker.crt -config /etc/ssl/openssl.cnf -extensions v3_req
```



## Install Harbor



### Download & Install

```shell
# 安装docker-ce
curl -s https://raw.githubusercontent.com/narakus/script/master/install_ali_docker_ce.sh  | bash

# 安装docker-compose
curl -L https://get.daocloud.io/docker/compose/releases/download/1.22.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose;chmod a+x /usr/local/bin/docker-compose

# 下载离线安装包
wget https://storage.googleapis.com/harbor-releases/harbor-offline-installer-v1.5.3.tgz

# 解压
tar xf harbor-offline-installer-v1.5.3.tgz -C /data/

# 修改配置文件harbor.cfg的以下内容
hostname = 192.168.1.204
ui_url_protocol = https
ssl_cert = /data/cert/dockercrt/docker.crt      
ssl_cert_key = /data/cert/dockercrt/docker.key 

# 启动
cd /data/harbor/;
./install.sh

# 若修改配置后重新启动，可执行以下
./prepare
docker-compose down
docker-compose up -d 
```



### 设置客户端信任证书

<!--解决：x509: certificate signed by unknown authority-->

```shell
# Debian系列
cat /data/cert/demoCA/cacert.pem >> /etc/ssl/certs/ca-certificates.crt

# redhat系列
cat /data/cert/demoCA/cacert.pem >> /etc/pki/tls/certs/ca-bundle.crt

# 重启docker
systemctl restart docker

```



[漠然blog]: https://mritd.me/2016/07/03/Harbor-%E4%BC%81%E4%B8%9A%E7%BA%A7-Docker-Registry-HTTPS%E9%85%8D%E7%BD%AE/
[官方文档]: https://github.com/goharbor/harbor/blob/master/docs/installation_guide.md

