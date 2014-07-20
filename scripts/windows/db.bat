::Default DB Path
::SET DB_PATH=%TruSST_ROOT%\DroidDataSet

::Weather DB Path
SET DB_PATH=%TruSST_ROOT%\DroidDataSet\derbyDB\weatherAppDB


cd %DB_PATH%
start java -jar %DERBY_INSTALL%\lib\derbyrun.jar server start
cd %TruSST_ROOT%\scripts\windows
