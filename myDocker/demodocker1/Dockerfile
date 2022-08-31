#openjdk 버전 : docker hub에 openjdk검색하여 들어가서 정보봐보기
FROM openjdk:8 
#ARG : 프로젝트 우클릭 - RUN AS - MAVEN INSTALL을 통한 압출jar파일의 이름 
ARG JAR_FILE=target/demodocker1-0.0.1-SNAPSHOT.jar 
#COPY : 압출파일의 복제본
COPY ${JAR_FILE} docker1.jar
ENTRYPOINT ["java","-Xms1024m","-Xmx1024m","-jar","/docker1.jar"]
