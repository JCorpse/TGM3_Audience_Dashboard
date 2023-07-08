#
# Build stage
#
FROM maven:3.9.3-sapmachine-17 AS build
LABEL authors="Juu"

ENV Discord_TOKEN=${Discord_TOKEN}
ENV Twitch_OAUTH=${Twitch_OAUTH}
ENV sendbot=${sendbot}


COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM amazoncorretto:17
COPY --from=build /home/app/target/TGM3_Audience_Dashboard-0.0.1-SNAPSHOT.jar /usr/local/lib/TGM3_Audience_Dashboard.jar

ENV Discord_TOKEN=${Discord_TOKEN}
ENV Twitch_OAUTH=${Twitch_OAUTH}
ENV sendbot=${sendbot}


ENTRYPOINT ["java","-jar","/usr/local/lib/TGM3_Audience_Dashboard.jar"]

EXPOSE 8080



