/**
 * LogOutput.java
 * @author smitha
 * date 10/24/2018
 * LogOutput is a class which contains all the different variables and the operators
 */
 public class LogOutput{
	double d1,d2,d3;
	String s1,s2,s3;
	
	public LogOutput(Double d1,String s1,Double d2,String s2,Double d3){
		this.d1 = d1;
		this.s1=s1;
		this.d2=d2;
		this.s2=s2;
		this.d3=d3;
		
	}
	public LogOutput(String s1, String of, Double d1,String s2, Double d3){
		
		this.s1=s1;
		this.s3=of;
		this.d1=d1;
		this.s2=s2;
		this.d3=d3;
	}
	public LogOutput(Double d1, String pow, String of,Double d2,String equal,Double d3){
		
		this.d1=d1;
		this.s1=pow;
		this.s3 = of;
		this.d2=d2;
		this.s2 = equal;
		this.d3=d3;
	}
	@Override
	public String toString(){
		return String.format("%.2f %s %.2f %s %.2f", this.d1,this.s1,this.d2,this.s2,this.d3);
	}

	
}