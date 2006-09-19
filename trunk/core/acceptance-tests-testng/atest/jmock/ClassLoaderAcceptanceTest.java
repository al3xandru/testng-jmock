/*  Copyright (c) 2000-2004 jMock.org
 */
package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.MockTester;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Constants;
import org.testng.annotations.*;
import static org.jmock.Assert.*;


public class ClassLoaderAcceptanceTest
{

    static class EmptyInterfaceCreator extends ClassLoader
    {
        protected Class findClass( String name ) {
            ClassWriter writer = new ClassWriter(true);
            writer.visit(Constants.ACC_PUBLIC | Constants.ACC_INTERFACE,
                         name.replace('.', '/'),
                         "java/lang/Object",
                         null, /* interfaces */
                         null /* source file */);

            byte[] b = writer.toByteArray();

            return defineClass(name, b, 0, b.length);
        }
    }

    private MockTester mockTester;

    @BeforeMethod
    public void init(){
       mockTester = new DefaultMockTester();
    }
    
    @Test
    public void mockingTypeFromOtherClassLoader() throws ClassNotFoundException {
        ClassLoader interfaceClassLoader = new EmptyInterfaceCreator();
        Class interfaceClass = interfaceClassLoader.loadClass("$UniqueTypeName$");

        mockTester.mock(interfaceClass); // Should not throw an exception
    }
}
