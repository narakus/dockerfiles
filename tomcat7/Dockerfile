FROM registry.zeasn.local:5000/base/jdk:1.8

MAINTAINER eli <liu11011241@msn.cn>

WORKDIR /usr/local/

COPY apache-tomcat-7.0.55.tar.gz apache-tomcat-7.0.55.tar.gz

RUN tar -zxf apache-tomcat-7.0.55.tar.gz && rm -f apache-tomcat-7.0.55.tar.gz && \
    mv apache-tomcat-7.0.55 tomcat7


#ENTRYPOINT ['/usr/local/tomcat7/bin/catalina.sh','run'] 

#VOLUME ['/usr/local/tomcat7/logs']

#EXPOSE 8080
