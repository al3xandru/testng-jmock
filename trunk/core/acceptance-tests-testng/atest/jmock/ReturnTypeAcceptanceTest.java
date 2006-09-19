/*  Copyright (c) 2000-2004 jMock.org
 */
package atest.jmock;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;
import static org.jmock.core.stub.Stubs.*;


public class ReturnTypeAcceptanceTest {
    public interface ReturnTypes {
        String returnString();
        boolean returnBoolean();
        byte returnByte();
        char returnChar();
        short returnShort();
        int returnInt();
        long returnLong();
        float returnFloat();
        double returnDouble();
    }

    private Mock mock;
    private ReturnTypes proxy;

    private MockTester mockTester;

    @BeforeMethod
    public void init() {
        mockTester = new DefaultMockTester();
        mock = mockTester.mock(ReturnTypes.class, "theMock");
        proxy = (ReturnTypes)mock.proxy();
    }

    @AfterMethod
    public void verify() {
        mockTester.verify();
    }

    @Test
    public void canReturnObjectReferences() {
        String result = new String("RESULT");

        mock.stubs().method("returnString").will(returnValue(result));

        assertSame("should be same result", result, proxy.returnString());
    }
    
    @Test
    public void canReturnNullObjectReferences() {
        mock.stubs().method("returnString").will(returnValue(null));
        
        assertNull("should be null", proxy.returnString());
    }
    
    @Test
    public void canReturnBooleanValues() {
        mock.stubs().method("returnBoolean").will(returnValue(true));
        assertTrue("should be true", proxy.returnBoolean());

        mock.stubs().method("returnBoolean").will(returnValue(false));
        assertFalse("should be false", proxy.returnBoolean());
    }

    @Test
    public void canReturnByteValues() {
        byte result = 123;

        mock.stubs().method("returnByte").will(returnValue(result));

        assertEquals("should be same result", result, proxy.returnByte());
    }

    @Test
    public void canReturnCharValues() {
        char result = '\u1234';

        mock.stubs().method("returnChar").will(returnValue(result));

        assertEquals("should be same result", result, proxy.returnChar());
    }

    @Test
    public void canReturnShortValues() {
        short result = 12345;

        mock.stubs().method("returnShort").will(returnValue(result));

        assertEquals("should be same result", result, proxy.returnShort());
    }

    @Test
    public void canReturnIntValues() {
        int result = 1234567890;

        mock.stubs().method("returnInt").will(returnValue(result));

        assertEquals("should be same result", result, proxy.returnInt());
    }

    @Test
    public void canReturnLongValues() {
        long result = 1234567890124356789L;

        mock.stubs().method("returnLong").will(returnValue(result));

        assertEquals("should be same result", result, proxy.returnLong());
    }

    @Test
    public void canReturnFloatValues() {
        float result = 12345.67890f;

        mock.stubs().method("returnFloat").will(returnValue(result));

        assertEquals("should be same result", result, proxy.returnFloat(), 0.0);
    }

    @Test
    public void canReturnDoubleValues() {
        double result = 1234567890.1234567890;

        mock.stubs().method("returnDouble").will(returnValue(result));

        assertEquals("should be same result", result, proxy.returnDouble(), 0.0);
    }
}
