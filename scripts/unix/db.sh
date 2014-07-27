# Default DB Path
# SET DB_PATH=TRRuSST_ROOT\DroidDataSet

# Weather DB Path
export $DB_PATH=$TRRuSST_ROOT/DroidDataSet/derbyDB/weatherAppDB


cd $DB_PATH
$JAVA_HOME/bin/java -jar $DERBY_INSTALL/lib/derbyrun.jar server start
cd $TRRuSST_ROOT/scripts/unix
