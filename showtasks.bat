echo starting... showtasks.bat
echo running... runcrud.bat
call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startbrowser
echo There was a problem with runcrud.bat
goto fail

:startbrowser
START chrome "https://www.onet.pl"
if "%ERRORLEVEL%" == "0" goto startlocalhost
echo There was a problem with openning a web browser
goto fail

:startlocalhost
TIMEOUT /T 30
START chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end

:fail
echo There were some problems - abort execution

:end
echo ending the showtasks

