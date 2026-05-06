FROM gradle:jdk21-ubi-minimal@sha256:41607448ecfb9d51c68f4f5db84590aef1009cc196aff12a4c20eae273c50d74 AS build
WORKDIR /app

RUN groupadd -r -g 10001 builder && \
    useradd  -r -u 10000 -g builder -M builder && \
    chown -R builder:builder /app
USER builder:builder

ENV GRADLE_USER_HOME=/app/.gradle
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.parallel=true -Dorg.gradle.caching=true -Dorg.gradle.configuration-cache=true"

COPY gradle  /app/gradle/
COPY --chown=builder:builder settings.gradle /app/
COPY --chown=builder:builder build.gradle   /app/

RUN --mount=type=cache,target=/app/.gradle,uid=10000,gid=10001 \
    gradle dependencies --no-daemon || true

COPY --chown=builder:builder src /app/src

RUN --mount=type=cache,target=/app/.gradle,uid=10000,gid=10001 \
    gradle clean bootJar --no-daemon --build-cache --parallel --no-scan --no-watch-fs

FROM eclipse-temurin:21-jre-alpine@sha256:8728e354e012e18310faa7f364d00185277dec741f4f6d593af6c61fc0eb15fd
WORKDIR /app

RUN addgroup -g 10001 -S spring && \
    adduser  -u 10000 -S -G spring spring && \
    chown -R spring:spring /app
USER spring:spring

COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/app.jar

EXPOSE 10200

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+UseZGC -XX:+AlwaysActAsServerClassMachine"
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-local}
ENV SERVER_PORT=${SERVER_PORT:-10200}

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Xmx512m", "-jar", "app.jar"]