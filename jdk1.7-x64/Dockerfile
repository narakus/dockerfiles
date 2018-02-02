FROM registry.zeasn.local:5000/base/centos:7.1

MAINTAINER eli <liu11011241@msn.cn>

WORKDIR /usr/local/

COPY jdk-7u79-linux-x64.tar.gz jdk-7u79-linux-x64.tar.gz

RUN tar -zxf jdk-7u79-linux-x64.tar.gz
RUN rm -f jdk-7u79-linux-x64.tar.gz

ENV JAVA_VERSION 1.7  
ENV JAVA_HOME /usr/local/jdk1.7.0_79
ENV JRE_HOME $JAVA_HOME/jre  
ENV CLASSPATH .:$JAVA_HOME/lib:$JRE_HOME/lib  
ENV PATH $PATH:$JAVA_HOME/bin  
  
