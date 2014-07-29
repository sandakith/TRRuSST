# Default DB Path
# SET DB_PATH=%TruSST_ROOT%\DroidDataSet

# Weather DB Path
export DB_PATH=$TRRuSST_ROOT\DroidDataSet\derbyDB\weatherAppDB


cd $DB_PATH
$JAVA_HOME\bin\java -jar $DERBY_INSTALL\lib\derbyrun.jar ij  
cd $TRRuSST_ROOT\scripts\windows
