package atest.jmock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jmock.DefaultMockTester;
import org.jmock.Mock;
import org.jmock.MockTester;
import org.jmock.core.DynamicMockError;
import org.testng.annotations.*;
import static org.jmock.Assert.*;
import static org.jmock.core.constraint.Constraints.*;
import static org.jmock.core.matcher.InvocationMatchers.*;

public class CollectionsAcceptanceTest{
    private Mock mockT;
    private T t;

    public interface T {
        void withArray(Object[] a);
        void withList(List l);
        void withSet(Set s);
        void withMap(Map m);
        void withObject(Object o);
    }

    private MockTester mockTester;
    
    @BeforeMethod
    public void init() {
        mockTester = new DefaultMockTester();
        mockT = mockTester.mock(T.class);
        t = (T)mockT.proxy();
    }

    @AfterMethod
    public void finish() {
        mockTester.verify();
    }
    
    @Test
    public void canMatchArrayContainingEqualElement() {
        mockT.expects(once()).method("withArray").with(arrayContaining("a"));

        t.withArray(new String[]{"A","a","Aa"});

        try {
            t.withArray(new String[]{"B","b","Bb"});
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchArrayContainingMatchingElement() {
        mockT.expects(atLeastOnce()).method("withArray").with(arrayContaining(eq("a")));

        t.withArray(new String[]{"A","a","Aa"});

        try {
            t.withArray(new String[]{"B","b","Bb"});
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchElementOfArray() {
        mockT.expects(atLeastOnce()).method("withObject").with(isIn(new String[]{"A","a","Aa"}));
        
        t.withObject("a");
        
        try {
            t.withObject("not an element");
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchListContainingEqualElement() {
        mockT.expects(once()).method("withList").with(collectionContaining("a"));
        
        List list = new ArrayList();
        list.add("A");
        list.add("a");
        list.add("Aa");
        
        t.withList(list);
    }
    
    @Test
    public void canMatchSetContainingEqualElement() {
        mockT.expects(once()).method("withSet").with(collectionContaining("a"));
        
        Set set = new HashSet();
        set.add("A");
        set.add("a");
        set.add("Aa");
        
        t.withSet(set);
    }
    
    @Test
    public void canMatchCollectionContainingMatchingElement() {
        String string = "string";
        String equalString = new String(string);
        
        mockT.expects(atLeastOnce()).method("withList").with(collectionContaining(same(string)));
        
        t.withList(Collections.singletonList(string));
        
        try {
            t.withList(Collections.singletonList(equalString));
            fail("expected DynamicMockError");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchElementOfCollection() {
        mockT.expects(atLeastOnce()).method("withObject").with(isIn(Arrays.asList(new String[]{"A","a","Aa"})));
        
        t.withObject("a");
        
        try {
            t.withObject("not an element");
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchMapContainingEntryValues() {
        mockT.expects(atLeastOnce()).method("withMap").with(mapContaining("a","A"));
        
        Map goodMap = new HashMap();
        goodMap.put("a", "A");
        
        Map badMap =  new HashMap();
        badMap.put("a", "B");
        
        t.withMap(goodMap);
        
        try {
            t.withMap(badMap);
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchMapContainingEntryMatchingConstraints() {
        mockT.expects(atLeastOnce()).method("withMap").with(mapContaining(eq("a"),eq("A")));
        
        Map goodMap = new HashMap();
        goodMap.put("a", "A");
        
        Map badMap =  new HashMap();
        badMap.put("a", "B");
        
        t.withMap(goodMap);

        try {
            t.withMap(badMap);
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchMapContainingEqualKey() {
        mockT.expects(atLeastOnce()).method("withMap").with(mapWithKey("a"));
        
        Map goodMap = new HashMap();
        goodMap.put("a", "A");
        
        Map badMap =  new HashMap();
        badMap.put("b", "B");
        
        t.withMap(goodMap);
        
        try {
            t.withMap(badMap);
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchMapContainingMatchingKey() {
        mockT.expects(atLeastOnce()).method("withMap").with(mapWithKey(eq("a")));
        
        Map goodMap = new HashMap();
        goodMap.put("a", "A");
        
        Map badMap =  new HashMap();
        badMap.put("b", "B");
        
        t.withMap(goodMap);

        try {
            t.withMap(badMap);
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchMapContainingEqualValue() {
        mockT.expects(atLeastOnce()).method("withMap").with(mapWithValue("A"));
        
        Map goodMap = new HashMap();
        goodMap.put("a", "A");
        
        Map badMap =  new HashMap();
        badMap.put("b", "B");
        
        t.withMap(goodMap);
        
        try {
            t.withMap(badMap);
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
    
    @Test
    public void canMatchMapContainingMatchingValue() {
        mockT.expects(atLeastOnce()).method("withMap").with(mapWithValue(eq("A")));
        
        Map goodMap = new HashMap();
        goodMap.put("a", "A");
        
        Map badMap =  new HashMap();
        badMap.put("b", "B");
        
        t.withMap(goodMap);

        try {
            t.withMap(badMap);
            fail("should not have been expected");
        }
        catch( Error err ) { 
            if (err instanceof DynamicMockError) {
					 /* expected */ 
				} else {
                throw err;
				}
		  }
    }
}
