#!/bin/bash

SET JAVA_HOME="C:\Program Files\Java\jdk-11.0.12\bin"
SET PATH=%JAVA_HOME%;PATH%
SET CLASSPATH=%JAVA_HOME%;
cd C:\Users\Usuario\Desktop\Compi2\projects\Project1\CompiSound\DesktopReproductor\src\main\java\com\yeferal\desktopreproductor\gramm\list
java -jar C:\Users\Usuario\Desktop\Compi2\projects\Project1\CompiSound\DesktopReproductor\libraries\JFlex.jar LexList.jflex
pause