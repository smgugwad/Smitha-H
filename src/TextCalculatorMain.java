/**@author smitha
 * @date 10/24/18
 * @ProjectName Text Calculator
 * @description Midterm project
 *    This project starts by asking the user to enter ON,HELP,MEM,PRINT,SAVE,OFF from the keyboard.Prompts the user to enter two inputs and the basic 
 *    mathematical operation to be performed. It also checks if the user has entered a non numeric value and asks the user to enter a valid input.
 *    The user can enter mathematical constants like e or pi. After the operation is performed the output is displayed. 
 * @class
 *  TextCalculatorMain : it has the main method and all the methods of Calculator class have been called here using an instance.
 * @variables 
 *  firstInput,secondInput: variable which accepts inputs(double) from the user
 *  operator : variable which accepts operator (string) from the user
 *  PI= 3.14 (constant)
 *  logOutputToQueueFinal: is a queue used to log all the operations performed
 * @method
 * checkOperator() : used to accept the operator from the user and validates it and returns a string.
 */

import java.awt.List;
import java.util.*;
import java.util.EmptyStackException;

public class TextCalculatorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double firstInput,secondInput=0;
		String operator = "";
		boolean flag = false;
		Queue <LogOutput> logOutputToQueueFinal = new LinkedList <LogOutput> ();
		System.out.println("\t\t\tTEXT CALCULATOR");
		System.out.println("=================================================================================\n");
		System.out .println("Welcome ! ");
		boolean onOrOffFlag = false;
		do{
		System.out.println("Press ON to turn on the Calculator \n");
		System.out.println("OFF to turn Off  \n");
	    Scanner onOrOff = new Scanner(System.in);
		String tempScanner1 = onOrOff.next();
		if(tempScanner1.equalsIgnoreCase("on")){
			do {
				System.out.println("\nPlease select any one of the following \n");
				System.out.println("START\tHELP\tSAVE\tMEM\tPRINT\tOFF\n ");
				Scanner scanner = new Scanner (System.in);
				String tempScanner = scanner.next();
				if (tempScanner.equalsIgnoreCase("start")) {
					Calculation calculation = new Calculation();
					System.out.println("Enter the first input ");
					firstInput = calculation.checkInput();
					System.out.println("Enter the operator u want to perform ");
					System.out.println("---------------------------------------------");
					System.out.println("Addition : \t\t+ \n\nSubtraction : \t\t-\n\nMultiplication : \t*\n\nDivision : \t\t/\n\nModulus: \t\t%\n\nExponential power: \tpow\n\nSquare Root : \t\tsqrt\n\n"); 
					operator = calculation.checkOperator();
					if (operator.equalsIgnoreCase ("sqrt")) {
							logOutputToQueueFinal = calculation.performCalculation(firstInput, secondInput,operator);
					}else {
						System.out.println("Enter the second input ");
						secondInput = calculation.checkInput();
						logOutputToQueueFinal = calculation.performCalculation(firstInput, secondInput,operator);
					}
				}	
				else if (tempScanner.equalsIgnoreCase("Help")){
					System.out.println("Press ON to start the Calculator.Then the user is asked to enter two inputs and the operation to be performed. The inputs can be a double or a  pi or e. The user can also enter the last saved result as the input ");
					System.out.println("If any other non numeric keys are entered the user is asked to enter the inputs again");
					System.out.println("Press SAVE to log all the results after the calculations are performed.If no operations are performed before then the message printed on the screen would be no results to save " );
					System.out.println("Press MEM to retrieve the last saved result");
					System.out.println("Press PRINT to display all the operations performed like 2+3 =5 or 5 *10 = 50");
					System.out.println("Press HELP to dispaly the Help option");
					System.out.println("Press OFF to end the calculator");

				}
				else if (tempScanner.equalsIgnoreCase("save")){
					Calculation cal1 = new Calculation();
					try{
						cal1.logResults();
					}catch(Exception e ){
						System.out.println("nothing is saved!");
					}
				}
				else if (tempScanner.equalsIgnoreCase("mem")){
					Calculation cal1 = new Calculation(); 
					cal1.getResults();
				}
				else if (tempScanner.equalsIgnoreCase("print")){
					if (logOutputToQueueFinal.isEmpty())
						System.out.println("No log of operations to print ");
					else {
						System.out.println("Displaying all the operations");
						for (LogOutput display : logOutputToQueueFinal)
							System.out.println(display);
					}
				}
				else if (tempScanner.equalsIgnoreCase("off")){
					System.out.println("END OF TEXT CALCULATOR"); 
					flag = true;
					onOrOffFlag = true; 
			}
			else{
					System.out.println("please enter a valid option");
				}
			} while (flag == false);
		}
		else if (tempScanner1.equalsIgnoreCase("off")){
				System.out.println("END OF TEXT CALCULATOR"); 
				onOrOffFlag = true;
		}
		else {
			System.out.println("Please enter a valid option/n");
		}
		}while (onOrOffFlag == false);			
	}
}
