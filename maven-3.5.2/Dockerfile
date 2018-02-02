FROM registry.zeasn.local:5000/base/jdk:1.8

WORKDIR /usr/local

ENV MAVEN_VERSION 3.5.2
ENV MAVEN_HOME /usr/local/maven

# Install maven
COPY apache-maven-3.5.2-bin.tar.gz apache-maven-3.5.2-bin.tar.gz

RUN tar xzf apache-maven-3.5.2-bin.tar.gz && \
    mv apache-maven-${MAVEN_VERSION} maven  && \
    ln -s /usr/local/maven/bin/mvn /usr/bin/mvn

RUN rm -f apache-maven-3.5.2-bin.tar.gz

COPY settings.xml /usr/local/maven/conf/settings.xml

#test
#ADD hello /hello
#RUN cd /hello && \
#    mvn clean install -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
