/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamiccompiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 *
 * @author JamesFoxes
 */
public class NewClass
{

    public NewClass()
    {
        String classpath = "%JAVA_HOME%\\bin"; // make sure tools.jar is in this path 
String sourcepath = "src" + java.io.File.separator + "dynamiccompiler" + java.io.File.separator + "DynamicCompiler.java"; // path to your sources
String putputpath = "src" + java.io.File.separator + "dynamiccompiler" + java.io.File.separator + "test" + java.io.File.separator; // directory for generated class files
//String filepath = ...; // file path the file you want to compile

String[] args = new String[]
        {
            "-classpath", classpath,
            "-sourcepath", sourcepath,
            "-d", putputpath,
            //filePath
        };
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            int compilationResult = compiler.run(null, null, null, args);
//com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();
  //      int compileStatus = javac.compile(args);
    }

}
