FROM jenkins/jenkins:lts

USER root

# Install required dependencies and download Allure
RUN apt-get update && \
    apt-get install -y \
        openjdk-17-jdk \
        libxkbcommon0 \
        libgbm1 \
        wget \
        unzip && \
    wget https://github.com/allure-framework/allure2/releases/download/2.20.0/allure-2.20.0.zip -O /tmp/allure.zip && \
    unzip /tmp/allure.zip -d /opt/ && \
    ln -s /opt/allure-2.20.0/bin/allure /usr/bin/allure && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/allure.zip

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"

USER jenkins
