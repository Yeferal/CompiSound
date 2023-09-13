#!/bin/bash

SET JAVA_HOME="C:\Program Files\Java\jdk-11.0.12\bin"
SET PATH=%JAVA_HOME%;PATH%
SET CLASSPATH=%JAVA_HOME%;
cd C:\Users\Usuario\Documents\AndroidStudioProjects\app\src\main\java\com\example\reproductor\gramm\comunication
java -jar C:\Users\Usuario\Desktop\Compi2\projects\Project1\CompiSound\DesktopReproductor\libraries\JFlex.jar LexCom.jflex
pause