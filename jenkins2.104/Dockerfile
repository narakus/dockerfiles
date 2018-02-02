FROM registry.zeasn.local:5000/base/tomcat:7.0.55

WORKDIR /usr/local/

COPY jenkins.war /usr/local/tomcat7/webapps/

#install maven
COPY apache-maven-3.5.2-bin.tar.gz apache-maven-3.5.2-bin.tar.gz

RUN tar zxf apache-maven-3.5.2-bin.tar.gz && rm -f apache-maven-3.5.2-bin.tar.gz && \
    rm -f apache-maven-3.5.2/conf/settings.xml

COPY settings.xml apache-maven-3.5.2/conf/

ENV M2_HOME /usr/local/apache-maven-3.5.2
ENV PATH $PATH:$M2_HOME/bin 

VOLUME ["/root.jenkins","/usr/local/tomcat7/logs"]

EXPOSE 8080 50000

ENTRYPOINT ["/usr/local/tomcat7/bin/catalina.sh","run"]
