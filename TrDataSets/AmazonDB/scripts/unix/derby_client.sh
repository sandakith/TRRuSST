#!/bin/sh

# Derby DB Path
export DB_PATH=$TRRuSST_ROOT\TrDataSets\AmazonDB\DerbyDB

cd $DB_PATH
$JAVA_HOME\bin\java -jar $DERBY_INSTALL\lib\derbyrun.jar ij  
cd $TRRuSST_ROOT\scripts\windows
