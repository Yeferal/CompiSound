SET JAVA_HOME="C:\Program Files\Java\jdk-11.0.12\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
cd C:\Users\Usuario\Desktop\Compi2\projects\Project1\CompiSound\DesktopReproductor\src\main\java\com\yeferal\desktopreproductor\gramm\main
java -jar C:\Users\Usuario\Desktop\Compi2\projects\Project1\CompiSound\DesktopReproductor\libraries\java-cup-11b.jar -parser SyntaxAnalyzerMain -symbols SymbolMainCode SyntaxMain.cup
pause