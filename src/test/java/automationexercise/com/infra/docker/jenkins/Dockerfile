FROM jenkins/jenkins:lts

USER root

RUN apt-get update && apt-get install -y \
    curl \
    wget \
    git \
    gnupg \
    ca-certificates \
    unzip \
    && rm -rf /var/lib/apt/lists/*

RUN echo "deb http://deb.debian.org/debian bookworm main" >> /etc/apt/sources.list \
    && apt-get update \
    && apt-get install -y openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

RUN wget https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip -O /tmp/maven.zip \
    && unzip /tmp/maven.zip -d /opt \
    && mv /opt/apache-maven-3.9.6 /opt/maven \
    && rm /tmp/maven.zip

ENV MAVEN_HOME=/opt/maven
ENV PATH="${MAVEN_HOME}/bin:${PATH}"

RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs

RUN npm install -g playwright \
    && playwright install --with-deps chromium

RUN chown -R jenkins:jenkins /var/jenkins_home

USER jenkins
