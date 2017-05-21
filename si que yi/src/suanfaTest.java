import junit.framework.TestCase;


public class suanfaTest extends TestCase {
	double money=2000,time=12,rate=12,fee=1,income;
	public void testFulijisuan() {
		
		//月利息=本金x月利率
		double month=money*(rate/12);
		double income=month*time-money*fee/12+money;
		
		assertEquals(25833.333333333332,income);
	}

	public void testDengebenxi() {
		double month = money/time+money*(rate/12);
		income=month*time-money*(fee/12)*time;
	
		assertEquals(24000.0,income);
	}

	public void testDengebenjin() {
		for(int i=1;i<=time;i++){
			
				double sum=0;
				double a=money/time;
				double b=a*(i-1);
				sum=sum+b;
				double month;
				month=(money/time)+(money-sum)*rate/12;
			    double income=0;
			    income=income+money+(money-sum)*rate/12;
			   
		        assertEquals(2166,income);
			
		}
		
	}

	public void testBenxi() {
		double income=money*(rate/100/12)*(1-fee/100)+money;
	
		assertEquals(2019.8,income);
	}

}
