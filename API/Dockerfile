# FROM openjdk@sha256:0d66ca8d9f5bb7ec86daf28f84bebc732f71a94ecb26be7209770b475d189f23 AS builder

# COPY gradlew .
# COPY gradle gradle
# COPY build.gradle .
# COPY settings.gradle .
# COPY src src
# RUN ./gradlew bootJAR --stacktrace

# FROM openjdk@sha256:0d66ca8d9f5bb7ec86daf28f84bebc732f71a94ecb26be7209770b475d189f23
# COPY --from=builder build/libs/*.jar app.jar
# ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk@sha256:0d66ca8d9f5bb7ec86daf28f84bebc732f71a94ecb26be7209770b475d189f23 AS builder

COPY API/gradlew .
COPY API/gradle gradle
COPY API/build.gradle .
COPY API/settings.gradle .
COPY API/src src
COPY codeRuntime.proto src/main/proto/codeRuntime.proto
RUN ./gradlew bootJAR --stacktrace

FROM openjdk@sha256:0d66ca8d9f5bb7ec86daf28f84bebc732f71a94ecb26be7209770b475d189f23
COPY --from=builder build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]