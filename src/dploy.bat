set PROJECT_HOME=E:\Projects\StockWatcher
set PROJECT_NAME=StockWatcher
cd /d %CATALINA_HOME%\\bin
call shutdown.bat 
cd /d %PROJECT_HOME%
call ant war
copy %PROJECT_HOME%\\%PROJECT_NAME%.war %CATALINA_HOME%\\webapps
cd /d %CATALINA_HOME%\\bin
call startup.bat 
timeout 5
start http://localhost:8080/%PROJECT_NAME%
exit

 
