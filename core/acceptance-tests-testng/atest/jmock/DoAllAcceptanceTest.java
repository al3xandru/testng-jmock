package atest.jmock;

import java.util.ArrayList;
import java.util.Collection;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.core.Invocation;
import org.jmock.core.Stub;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;
import static org.jmock.core.stub.Stubs.*;

public class DoAllAcceptanceTest {
    public interface Collector {
        void addThingsTo(Collection collection);
    }
    
    private MockTester mockTester;

    @BeforeMethod
    public void init() {
        mockTester = new DefaultMockTester();
    }

    @AfterMethod
    public void verify() {
        mockTester.verify();
    }

    @Test
    public void canSpecifyMultipleStubsForOneInvocation() {
        Mock mockCollector = mockTester.mock(Collector.class);
        Collector collector = (Collector) mockCollector.proxy();
        ArrayList list = new ArrayList();
        
        mockCollector.expects(once()).method("addThingsTo").with(same(list))
        	.will(doAll(addElement("1"), addElement("2"), addElement("3"), addElement("4")));
        
        collector.addThingsTo(list);
        
        assertEquals("list length", 4, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals("element "+(i+1), Integer.toString(i+1), list.get(i));
        }
    }
    
    private Stub addElement(String newElement) {
        return new AddElementStub(newElement);
    }
    
    public static class AddElementStub implements Stub {
        private final String newElement;

        public AddElementStub(String newElement) {
            this.newElement = newElement;
        }

        public Object invoke(Invocation invocation) throws Throwable {
            ((Collection)invocation.parameterValues.get(0)).add(newElement);
            return null;
        }
        
        public StringBuffer describeTo(StringBuffer buffer) {
            throw new UnsupportedOperationException("AddElementStub.describeTo not implemented");
        }
    }
}
