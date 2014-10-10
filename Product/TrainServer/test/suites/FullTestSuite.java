/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suites;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import transmission.TestClientConnection;
import transmission.TestProtocol;
import transmission.TestServer;
import transmission.TestUDPBroadcast;

/**
 *
 * @author JamesFoxes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            TestClientConnection.class,
            TestProtocol.class,
            TestUDPBroadcast.class,
            TestServer.class
        })
public class FullTestSuite
{

    @BeforeClass
    public static void Compile() throws FileNotFoundException
    {
        ArrayList<String> filesToCompile = new ArrayList<>();
        filesToCompile.add("test" + java.io.File.separator + "transmission" + java.io.File.separator + "TestClientConnection.java");
        filesToCompile.add("test" + java.io.File.separator + "transmission" + java.io.File.separator + "TestProtocol.java");
        filesToCompile.add("test" + java.io.File.separator + "transmission" + java.io.File.separator + "TestUDPBroadcast.java");
        filesToCompile.add("test" + java.io.File.separator + "transmission" + java.io.File.separator + "TestServer.java");
        
        filesToCompile.add("test" + java.io.File.separator + "transmission" + java.io.File.separator + "ClientsUDPSetup.java");
        filesToCompile.add("test" + java.io.File.separator + "transmission" + java.io.File.separator + "TestingClients.java");

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        for (String file : filesToCompile)
        {
            OutputStream outStream = new FileOutputStream("build" + java.io.File.separator + "test" + java.io.File.separator + "classes" + java.io.File.separator + file.substring(0, 4) + ".class");
            int compilationResult = compiler.run(null, outStream, null, file);
            if (compilationResult == 0)
            {
                System.out.println("Compilation is successful");
            } else
            {
                System.out.println("Compilation Failed");
            }
        }
    }
}
