# 常用docker命令记录

下载私有仓库
docker pull registry

运行容器
docker run -d --name registry-server -p 5000:5000 --restart=always -v /data/registry/:/var/lib/registry/ registry:latest

通过dockerfile创建容器
docker build -t  registry.docker.local:5000/base/jdk:1.7 .

删除images
docker rmi cee56dbfcdb9

停止容器
docker stop 4b1bd55fa137

创建标签
docker tag jdk:1.7  registry.docker.local:5000/base/jdk:1.7

上传至私有仓库
docker push registry.docker.local:5000/base/jdk:1.7
