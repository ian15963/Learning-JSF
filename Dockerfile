FROM jboss/wildfly:latest

WORKDIR /opt/jboss/wildfly

COPY target/empresas.war standalone/deployments/

EXPOSE 8080