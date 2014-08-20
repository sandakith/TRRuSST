::Default DB Path
::SET DB_PATH=%TRRuSST_HOME%\TrDataSets

::Derby DB Path
SET DB_PATH=%TRRuSST_HOME%\TrDataSets\AmazonDB\DerbyDB


cd %DB_PATH%
start %JAVA_HOME%\bin\java -jar %DERBY_HOME%\lib\derbyrun.jar ij  
cd %TRRuSST_HOME%\scripts\windows
