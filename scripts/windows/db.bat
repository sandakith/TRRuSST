::Default DB Path
::SET DB_PATH=%TRRuSST_ROOT%\DroidDataSet

::Weather DB Path
SET DB_PATH=%TRRuSST_ROOT%\DroidDataSet\derbyDB\weatherAppDB


cd %DB_PATH%
start java -jar %DERBY_ROOT%\lib\derbyrun.jar server start
cd %TRRuSST_ROOT%\scripts\windows
