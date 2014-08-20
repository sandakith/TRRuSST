#!/bin/sh
# Default DB Path
# SET DB_PATH=TRRuSST_HOME\TrDataSets

# Derby DB Path
export $DB_PATH=$TRRuSST_HOME/TrDataSets/AmazonDB/DerbyDB 


cd $DB_PATH
$JAVA_HOME/bin/java -jar $DERBY_HOME/lib/derbyrun.jar server start
cd $TRRuSST_HOME/scripts/unix
