
::Derby DB Path
SET DB_PATH=%TRRuSST_HOME%\TrDataSets\AmazonDB\DerbyDB

cd %DB_PATH%
start java -jar %DERBY_HOME%\lib\derbyrun.jar server start
cd %TRRuSST_HOME%\scripts\windows
