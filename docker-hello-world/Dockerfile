FROM registry.zeasn.local:5000/base/tomcat:7.0.55

WORKDIR /usr/local/

COPY hello.war /usr/local/tomcat7/webapps/

VOLUME /usr/local/tomcat7/logs

EXPOSE 8080 30000

ENTRYPOINT ["/usr/local/tomcat7/bin/catalina.sh","run"]
