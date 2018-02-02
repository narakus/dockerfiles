FROM registry.zeasn.local:5000/base/centos:7.1

MAINTAINER eli <liu11011241@msn.cn>

WORKDIR /usr/local/

COPY jdk1.8.0_101-x64.tar.gz jdk1.8.0_101-x64.tar.gz

RUN tar -zxf jdk1.8.0_101-x64.tar.gz
RUN rm -f jdk1.8.0_101-x64.tar.gz

ENV JAVA_VERSION 1.8.0  
ENV JAVA_HOME /usr/local/jdk1.8.0_101
ENV JRE_HOME $JAVA_HOME/jre  
ENV CLASSPATH .:$JAVA_HOME/lib:$JRE_HOME/lib  
ENV PATH $PATH:$JAVA_HOME/bin  
  
