/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import transmission.TestClientConnection;
import transmission.TestProtocol;

/**
 *
 * @author JamesFoxes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            TestClientConnection.class,
            TestProtocol.class
        })
public class FullTestSuite
{

}
