@ECHO OFF
SET DIR=%~dp0
SET APP_BASE_NAME=%~n0
SET CLASSPATH=%DIR%gradle\wrapper\gradle-wrapper.jar
IF NOT "%JAVA_HOME%" == "" (
  "%JAVA_HOME%\bin\java.exe" -Dorg.gradle.appname=%APP_BASE_NAME% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
) ELSE (
  java -Dorg.gradle.appname=%APP_BASE_NAME% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
)
