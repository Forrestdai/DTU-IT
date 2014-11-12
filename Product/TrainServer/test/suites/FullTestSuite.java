/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import trafficrouting.DatabaseHandlerTest;
import trafficrouting.DijkstraTraversalTest;
import trafficrouting.FibonacciHeapTest;
import trafficrouting.GraphTest;
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
            TestServer.class,
            DijkstraTraversalTest.class,
            FibonacciHeapTest.class,
            DatabaseHandlerTest.class,
            GraphTest.class
        })
public class FullTestSuite
{
    /*
     @BeforeClass
     public static void Compile() throws FileNotFoundException
     {
     String sep = java.io.File.separator;
     ArrayList<String> filesToCompile = new ArrayList<>();
     filesToCompile.add("test" + sep + "transmission" + sep + "TestClientConnection.java");
     filesToCompile.add("test" + sep + "transmission" + sep + "TestProtocol.java");
     filesToCompile.add("test" + sep + "transmission" + sep + "TestUDPBroadcast.java");
     filesToCompile.add("test" + sep + "transmission" + sep + "TestServer.java");
        
     filesToCompile.add("test" + sep + "transmission" + sep + "ClientsUDPSetup.java");
     filesToCompile.add("test" + sep + "transmission" + sep + "TestingClients.java");

     JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

     for (String file : filesToCompile)
     {
     OutputStream outStream = new FileOutputStream("build" + sep + "test" + sep + "classes" + sep + "transmission" + sep + file.substring(0, 5) + ".class");
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
     */
}
