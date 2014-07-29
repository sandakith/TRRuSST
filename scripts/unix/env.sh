@echo off 

export M2_REPO=/home/sandakith/.m2/repository
export TRRuSST_ROOT=~/REPO/TRRuSST
export JAVA_HOME=~/software/jdk1.7.0_04
export MAVEN_HOME=~/software/apache-maven-3.1.0
export DERBY_INSTALL=~/software/db-derby-10.8.2.2-bin

PATH=$JAVA_HOME/bin:$PATH
PATH=$MAVEN_HOME/bin:$PATH
PATH=$DERBY_INSTALL/lib/:$PATH
