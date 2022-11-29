FROM openjdk@sha256:0d66ca8d9f5bb7ec86daf28f84bebc732f71a94ecb26be7209770b475d189f23 AS builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
# RUN ./gradlew bootRun
RUN ./gradlew bootJAR

FROM openjdk@sha256:0d66ca8d9f5bb7ec86daf28f84bebc732f71a94ecb26be7209770b475d189f23
COPY --from=builder build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]