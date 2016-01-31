# MixITeam
Repository de l'equipe MixITeam pour les 24h du code 2016
http://www.les24hducode.fr/2016/

Exploration et visualisation des données ouvertes (OpenData) concernant les événements et lieux culturels de la Sarthe.
http://24hc16.haum.org/

## description app

## installation

### pré-requis 

Java 1.7.0_79
maven 3

### installation tomcat

dans setup, unzip apache-tomcat-7.0.67.zip
copier tomcat-users.xml dans votre installation
bin/startup.sh

### maven settings

ajout de la definition du serveur tomcat dans le settings.xml de maven pour le deploiement automatique
setup/server-idtomcat-maven-settings.xml

### build appli wsOpenDataSartheDev 

cd wsOpenDataSartheDev
mvn clean verify 

### build appli opendata

cp opendata $TOMCAT_HOME/webapps/ROOT

### build OpenDataReconnaissanceVocale

cd OpenDataReconnaissanceVocale
mvn clean verify

## usage

webapp -> http://localhost:8080/opendata/

app OpenDataReconnaissanceVocale -> java -cp ... opendata.Main

