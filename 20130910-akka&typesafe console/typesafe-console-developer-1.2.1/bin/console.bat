@echo off
set BIN_DIR=%~dp0
set ATMOS_HOME=%BIN_DIR%\..
set CONSOLE_CLASSPATH=%ATMOS_HOME%\conf\console;%ATMOS_HOME%\lib\console\console.jar
set JAVA_OPTS=-server -Xms512M -Xmx512M -XX:MaxPermSize=256M
set HTTP_PORT=9900

REM We move all generated directories and such into the user TMP directory.
set VAR_DIR=%TMP%\atmos\var
if not exist %VAR_DIR%\nul mkdir %VAR_DIR%

java %JAVA_OPTS% -cp "%CONSOLE_CLASSPATH%" "-Duser.dir=%VAR_DIR%" "-Dhttp.port=%HTTP_PORT%" "-Dlogger.resource=/logback-console.xml" play.core.server.NettyServer
