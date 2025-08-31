FROM openjdk:21-jdk
ARG JAR_PATH=build/libs/vinllage-0.0.1-SNAPSHOT.jar
ARG PORT=4000
COPY ${JAR_PATH} app.jar
RUN mkdir uploads

ENV SPRING_PROFILES_ACTIVE=default,prod
ENV DB_URL=**
ENV DB_PASSWORD=**
ENV DB_USERNAME=**

ENV DDL_AUTO=**

ENV FILE_PATH=/uploads
ENV FILE_URL=/uploads

ENV MAIL_USERNAME=**
ENV MAIL_PASSWORD=**

ENV REDIS_HOST=**
ENV REDIS_PORT=**

ENV JWT_SECRET=**
ENV JWT_VALIDTIME=**

ENTRYPOINT ["java", "-Ddb.url=${DB_URL}", "-Ddb.username=${DB_USERNAME}", "-Ddb.password=${DB_PASSWORD}", "-Dspring.jpa.hibernate.ddl-auto=${DDL_AUTO}", "-Dfile.path=${FILE_PATH}", "-Dfile.url=${FILE_URL}", "-Dmail.username=${MAIL_USERNAME}", "-Dmail.password=${MAIL_PASSWORD}", "-Dredis.host=${REDIS_HOST}", "-Dredis.port=${REDIS_PORT}", "-Djwt.secret=${JWT_SECRET}", "-Djwt.validTime=${JWT_VALIDTIME}", "-Dapi.url=${API_URL}", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]

EXPOSE ${PORT}