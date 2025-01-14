FROM jenkins/jenkins:lts

USER root

# Install required dependencies
RUN apt-get update && \
    apt-get install -y \
        openjdk-17-jdk \
        libxkbcommon0 \
        libgbm1 \
        allure && \
    apt-get clean

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"

USER jenkins
