package org.jmock;

public abstract class MockObjectTestCase extends AbstractMockObjectTestCase
{
   private MockTester mockTester;

   public MockObjectTestCase()
   {
      super();
      mockTester = new DefaultMockTester();
   }

   public MockObjectTestCase( String name )
   {
      super( name );
      mockTester = new DefaultMockTester();
   }

   protected final MockTester getMockTester()
   {
      return mockTester;
   }
}
