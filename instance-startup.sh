#!/bin/sh

# Set the metadata server to the get projct id
PROJECTID=$(curl -s "http://metadata.google.internal/computeMetadata/v1/project/project-id" -H "Metadata-Flavor: Google")
BUCKET=$(curl -s "http://metadata.google.internal/computeMetadata/v1/instance/attributes/BUCKET" -H "Metadata-Flavor: Google")

echo "Project ID: ${PROJECTID} Bucket: ${BUCKET}"

# Get the files we need
gsutil cp gs://"${BUCKET}"/notes-0.0.1.jar .

# Install dependencies
apt-get update
#apt-get -y --force-yes install openjdk-8-jdk
apt-get -y --force-yes install openjdk-11-jre openjdk-11-jdk

# Install mongodb as a dependency
apt-get -y --force-yes install mongodb

# Make Java 8 default
update-alternatives --set java /usr/lib/jvm/java-11-openjdk-amd64/bin/java

# Start server
java -jar notes-0.0.1.jar
