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


public class DynamicMockExample
{
    public interface Market {
        String[] listStocks();

        int getPrice( String ticker );

        void buyStock( String ticker, int quantity );
    }

    public class Agent
    {
        Market market;

        public Agent( Market market ) {
            this.market = market;
        }

        public void buyLowestPriceStock( int cost ) {
            String[] stocks = market.listStocks();
            int cheapestPrice = Integer.MAX_VALUE;
            String cheapestStock = null;

            for (int i = 0; i < stocks.length; ++i) {
                int price = market.getPrice(stocks[i]);
                if (price < cheapestPrice) {
                    cheapestPrice = price;
                    cheapestStock = stocks[i];
                }
            }
            market.buyStock(cheapestStock, cost / cheapestPrice);
        }
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
    public void builderExample() {
        Mock market = mockTester.mock(Market.class);
        Agent agent = new Agent((Market)market.proxy());

        market.stubs().method("listStocks").withNoArguments()
                .will(returnValue(new String[]{"IBM", "ORCL"}));
        market.expects(atLeastOnce()).method("getPrice").with(eq("IBM"))
                .will(returnValue(10));
        market.expects(atLeastOnce()).method("getPrice").with(eq("ORCL"))
                .will(returnValue(25));
        market.expects(once()).method("buyStock").with(eq("IBM"), eq(2));

        agent.buyLowestPriceStock(20);
    }

    public void xtestDynaMockExample() {
        Mock mockMarket = mockTester.mock(Market.class);
        Agent agent = new Agent((Market)mockMarket.proxy());
//	
//	 
//	    mockMarket.invokedMethod("buyStock", "MSFT", new Integer(10)).void();
//
//		mockMarket.invokedMethod("buyStock", "MSFT", new Integer(10)).returns(true)
//			.expectOnce();
//			//.expectNever();
//			//.addMatcher(new MyExpectation());
//			
//		mockMarket.invokedMethod("listStocks").alwaysReturns(new Vector("MSFT", "ORCL"));
//		mockMarket.invokedMethod("getPrice", "MSFT").alwaysReturns(10);
//		mockMarket.invokedMethod("getPrice", "ORCL").alwaysReturns(50);
//		
//		mockMarket.invokedMethod(C.equal("buyStock"), C.eq(1)).
//			
//		mockMarket.methodName("listStocks").noParams()
//			.alwaysReturns("MSFT");
//
//		InvocationHandler listInvocation = mockMarket.methodName("listStocks").noParams()
//			.returns("MSFT")
//			.returns("ORCL")
//			.throwsException(new ....);
//
//		mockMarket.methodName("buyStock").params("MSFT", 10).returns(900)
//			.calledOnce()
//			.before(listInvocation);
//		mockMarket.methodName("buyStock").params("ORCL", 2).returns(100)
//			.calledOnce()
//			.before(listInvocation);
//
//		mockMarket.newInvocationHandler().addMatcher( new NameMatcher(new IsEqual("buyStock"))
//			.addMatcher( new ActualParameterMatcher( new Constraint[] { new IsEqual("MSFT"), new IsEqual(new Integer(10)})))
//			.addStub( new ReturnStub( new Integer(900) )));
//			
//
        agent.buyLowestPriceStock(1000);
    }
}
