#!/bin/sh
cd $TRRuSST_HOME
mvn clean compile test-compile install
cd  -