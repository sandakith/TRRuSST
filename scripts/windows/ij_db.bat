::Default DB Path
::SET DB_PATH=%TruSST_ROOT%\DroidDataSet

::Weather DB Path
SET DB_PATH=%TruSST_ROOT%\DroidDataSet\derbyDB\weatherAppDB


cd %DB_PATH%
start %JAVA_HOME%\bin\java -jar %DERBY_INSTALL%\lib\derbyrun.jar ij  
cd %TruSST_ROOT%\scripts\windows
