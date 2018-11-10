echo starting... showtasks.bat
echo running... runcrud.bat
call runcrud.bat

if "%ERRORLEVEL%" == "0" goto startbrowser
echo There was a problem with runcrud.bat
goto end

:startbrowser
START chrome "https://www.onet.pl"
echo openining webbrowser successfull

:end
echo ending the showtasks

