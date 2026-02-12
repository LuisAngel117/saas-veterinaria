@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.

@echo off
setlocal

set BASE_DIR=%~dp0
if "%BASE_DIR:~-1%"=="\" set BASE_DIR=%BASE_DIR:~0,-1%

if "%JAVA_HOME%"=="" (
  for /f "delims=" %%i in ('where java 2^>NUL') do set JAVA_CMD=%%i
) else (
  set JAVA_CMD=%JAVA_HOME%\bin\java.exe
)

if "%JAVA_CMD%"=="" (
  echo ERROR: JAVA_HOME is not defined correctly.
  exit /b 1
)

if not exist "%JAVA_CMD%" (
  echo ERROR: JAVA_HOME is not defined correctly.
  exit /b 1
)

set WRAPPER_JAR=%BASE_DIR%\.mvn\wrapper\maven-wrapper.jar
set WRAPPER_PROPERTIES=%BASE_DIR%\.mvn\wrapper\maven-wrapper.properties

"%JAVA_CMD%" -classpath "%WRAPPER_JAR%" -Dmaven.multiModuleProjectDirectory="%BASE_DIR%" -Dwrapper.properties="%WRAPPER_PROPERTIES%" org.apache.maven.wrapper.MavenWrapperMain %*
endlocal
