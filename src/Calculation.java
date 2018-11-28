import java.lang.*;
import java.util.*;

/**Calculation.java
 * @author Smitha 
 * This class contains all the all methods required for performing the calculations
 * 
 */

public class Calculation {
	
	/**
	 * @param
	 * tempVar : used to hold the double value after checking and validating and returned to the calling method 
	 * isDouble,isOperator : a boolean which is used as a flag 
	 * reUseNumber : a static variable which is used to hold the last saved result and is assigned to tempVar as an input when the user enters the string mem.
	 * printResult : a stack used for displaying the last saved result when the user press MEM 
	 * saveResult : a queue used for logging the last result when the user press SAVE
	 */
	
	Double tempVar;
	static Double reUseNumber = 0.0;
	boolean isDouble,isOperator ;
	String tempOperator; 
	static Stack <Double> printResult = new Stack <> ();
	static Queue <Double> saveResult = new LinkedList <Double> ();
	static Queue <LogOutput> logOutputToQueue = new LinkedList <LogOutput> ();

	/**
	 * @method
	 * checkInput() :  a method accepting user inputs and validates if the user has entered the desired input and returns a double.
	 * @return double -- result of validated input 
	 **/
	public double checkInput() {

		Scanner scanner = new Scanner (System.in);
		do {
			isDouble= false;
			try{
				tempVar = scanner.nextDouble();
				isDouble = true;
				break;
			} catch (InputMismatchException exception) {
				String temp = scanner.next();
				if (temp.equalsIgnoreCase("pi")){
					tempVar = 3.14;
					isDouble = true;
				}
				else if (temp.equalsIgnoreCase("e")) {
					tempVar = 2.718;
					isDouble = true;
				}
				else if (temp.equalsIgnoreCase("mem")){
					tempVar = reUseNumber;
					isDouble = true;
				}
				else{		 
					System.out.println(exception.toString());
					System.out.println("Input entered is not a number, try again \n");		
				}

			}
		} while (isDouble == false);
		return tempVar;
	}

	public String checkOperator(){

		do {
			Scanner newOperator = new Scanner(System.in);
			tempOperator = newOperator.next();
			isOperator = false;
			if(!(tempOperator.equals("+") || tempOperator.equals("-") ||tempOperator.equals("*") || tempOperator.equals("/")|| tempOperator.equals("%")||tempOperator.equals("sqrt")||tempOperator.equals("pow"))){
				System.out.println("Undefined Operator, Please enter operator again \n");
			} else 
				isOperator = true;
		} while (isOperator == false);
		return tempOperator;
	}
	/**
	 * @param
	 * logOutputToQueue : is a queue which holds the instances of the LogOutput class
	 * @method
	 * performCalculation() : which accepts firstInput,secondInput and the operator as arguments and performs the the calculations 
	 * based on the operator entered by the user.
	 * @return 
	 * returns a Queue.
	 **/
	public Queue <LogOutput > performCalculation (double firstNumber, double secondNumber, String operator ) {	

		/**
		 * logOutput1,logOutput2, logOutput3 etc : are the instances of the logOutput class. 
		 * In which each instance has the log of operations performed.
		 */
		String equal = "=";
		Double result;
		String of = "of";

		switch(operator) { 

		case "+" :  
			result = firstNumber+secondNumber;
			saveResult.add (result);
			LogOutput logOutput = new LogOutput (firstNumber,operator,secondNumber,equal,result);
			System.out.println (logOutput.toString());
			logOutputToQueue.add (logOutput);
			break;

		case "-" : 
			result = firstNumber-secondNumber;
			saveResult.add (firstNumber - secondNumber);
			LogOutput logOutput1 = new LogOutput (firstNumber,operator,secondNumber,equal,result);
			System.out.println (logOutput1.toString());
			logOutputToQueue.add (logOutput1);
			break;

		case "*" : 
			result = firstNumber * secondNumber;
			saveResult.add (firstNumber * secondNumber);
			LogOutput logOutput2 = new LogOutput (firstNumber,operator,secondNumber,equal,result);
			System.out.println (logOutput2.toString());
			logOutputToQueue.add (logOutput2);
			break;

		case "/" :
			result = firstNumber / secondNumber;
			try {
					saveResult.add (firstNumber / secondNumber);
					LogOutput logOutput3 = new  LogOutput (firstNumber,operator,secondNumber,equal,result);
					System.out.println (logOutput3.toString());
					logOutputToQueue.add (logOutput3);
				} catch (ArithmeticException e) {
					System.out.println("Divide by zero is not possible, Please enter a non zero number \n");
				}
			break;

		case "%" : 
			result = firstNumber % secondNumber;
			LogOutput logOutput4 = new LogOutput (firstNumber , operator , secondNumber , equal, result);
			System.out.println (logOutput4.toString());
			logOutputToQueue.add (logOutput4);
			saveResult.add (firstNumber % secondNumber);
			break;

		case "sqrt" :
			if (firstNumber < 0) {
				String imaginaryNumber = "i";
				Double tempResult =  Math.sqrt(Math.abs (firstNumber));
				System.out.println ("The sqrt of " +firstNumber+ " = " +Math.sqrt(Math.abs(firstNumber))+imaginaryNumber);
				saveResult.add (tempResult);
				LogOutput logOutput5 = new LogOutput (operator,of,firstNumber,equal,tempResult);
				logOutputToQueue.add (logOutput5);
			}
			else if (firstNumber >= 0){
				System.out.println ("The sqrt of " +firstNumber+ " = " + Math.sqrt(firstNumber));
				saveResult.add (Math.sqrt(firstNumber));
				LogOutput logOutput6 = new LogOutput (operator, of, firstNumber,equal,Math.sqrt(firstNumber));
				logOutputToQueue.add (logOutput6);
			}
			break;

		case "pow" :
			String pow = "pow";
			result = Math.pow (firstNumber, secondNumber);
			System.out.println (firstNumber+ " raised to power of " +secondNumber+ " = " +Math.pow(firstNumber, secondNumber) ); 			
			saveResult.add(Math.pow (firstNumber,secondNumber));
			LogOutput logOutput7 = new LogOutput(firstNumber, pow, of,secondNumber,equal,result); 
			logOutputToQueue.add (logOutput7);
			break;

		default : 
			System.out.println ("Undefined Operator, Please enter operator again \n");
			break;
		}
		return logOutputToQueue;
	}

	/** 
	 * @param
	 * queueToArray : a variable which holds the array of results. The queue saveResult is casted into a ArrayList called queueToArray.This casting is done in order to  				retrieve the last element of the array.(which cant be done using a queue)
	 * @method
	 * logResults() : used to save the last performed calculation result
	 * @return type
	 *  void 
	 */
	public void logResults(){
		ArrayList <Double> queueToArray = new ArrayList<> (saveResult);
		try {
				int i = queueToArray.size() - 1;
				queueToArray.get(i);
				printResult.push (queueToArray.get(i));
				System.out.println ("Saved Result");
				reUseNumber = queueToArray.get(i);
				System.out.println (reUseNumber);
			}catch (ArrayIndexOutOfBoundsException e) {
				
				System.out.println ("No results to save");
				System.out.println (e.toString());
			}
	}
	/** 
	 * @method
	 *  getResults():used to display the last saved result
	 *  @return type
	 *  void 
	 */
	public void getResults(){
		try{ 
			System.out.println (printResult.pop());
			System.out.println ("Retreiving Last saved result ");
			} catch (EmptyStackException e){
				System.out.println ("No more results to retrieve");
				System.out.println (e.toString());
			}
		}
}
