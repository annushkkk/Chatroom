FROM gradle:8.2-jdk17-alpine AS builder
WORKDIR /app
COPY . /app
RUN gradle bootJar

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/chatroom-0.0.1-SNAPSHOT.jar /app/rt.jar

ENTRYPOINT ["java"]
CMD ["-jar", "rt.jar"]