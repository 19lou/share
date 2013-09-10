@echo off
set BIN_DIR=%~dp0
set ATMOS_HOME=%BIN_DIR%\..
set ATMOS_CLASSPATH=%ATMOS_HOME%\conf\atmos;%ATMOS_HOME%\lib\atmos\atmos.jar
set JAVA_OPTS=-server -Xms512M -Xmx512M -XX:MaxPermSize=256M
set HTTP_PORT=8660

REM We move all generated directories and such into the user TMP directory.
set VAR_DIR=%TMP%\atmos\var
if not exist %VAR_DIR%\nul mkdir %VAR_DIR%

java %JAVA_OPTS% -cp "%ATMOS_CLASSPATH%" "-Duser.dir=%VAR_DIR%" "-Dquery.http.port=%HTTP_PORT%" com.typesafe.atmos.AtmosDev
