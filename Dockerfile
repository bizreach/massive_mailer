FROM gradle:jdk8

USER root
WORKDIR /home
COPY . /home
CMD ["gradle", "test"]
# RUN ./gradlew test --debug
