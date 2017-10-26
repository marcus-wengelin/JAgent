#!/bin/sh

# Compiles the loader

javac -cp /usr/lib/jvm/java-8-openjdk-amd64/lib/tools.jar Loader.java

# Compiles the agent

javac JAgent.java
