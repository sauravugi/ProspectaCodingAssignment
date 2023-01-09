package Assignment2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String data = sc.nextLine();
		
		calculateCSV(data);
	}
	
	public static void calculateCSV(String data) {
	
		String[] strArray = data.split(", ");
		
		if(strArray.length==0) {
			System.out.println("No Data Available");
			return;
		}
		
		Map<String,String> cells = new LinkedHashMap<>();
		
		try {
			for(String str:strArray) {
				//It contains pairs of cell and value
				String[] values = str.split(": ");

				if(values.length!=2) {
					System.out.println("Not Proper Data");
					return;
				}
				
				String cellNumber = values[0];
				String cellvalue = values[1];
				
				
				if(isValidCell(cellNumber)) {
					if(isNum(cellvalue)) {
						cells.put(cellNumber, cellvalue);
					}else {
						if(isValidExpression(cellvalue)) {
							String exp = cellvalue.substring(1);
							 
							String[] expArray = exp.split("[+*/-]");
							
							boolean addSign = false;
							boolean subSign = false;
							boolean multiplySign = false;
							boolean divideSign = false;
							
							if(exp.contains("+")) addSign = true;
							if(exp.contains("-")) subSign = true;
							if(exp.contains("*")) multiplySign = true;
							if(exp.contains("/")) divideSign = true;
							
							Integer val1 = null;
							Integer val2 = null;
							
							if(isValidCell(expArray[0])) {
								if(cells.containsKey(expArray[0])) {
									val1 = Integer.parseInt(cells.get(expArray[0]));
								}else {
									System.out.println("Not Proper Value");
									return;
								}
							}
							if(isValidCell(expArray[1])) {
								if(cells.containsKey(expArray[1])) {
									val2 = Integer.parseInt(cells.get(expArray[1]));
								}else {
									System.out.println("Invalid Input");
									return;
								}
							}
							
				
							if(isNum(expArray[0]) && isNum(expArray[1])) {
								Integer result = null; 
								Integer n1 =  Integer.parseInt(expArray[0]);
								Integer n2 =  Integer.parseInt(expArray[1]);
								if(addSign) {
									result = add(n1, n2);
								}
								if(subSign) {
									result = subtract(n1,n2);
								}
								if(multiplySign) {
									result = multiply(n1, n2);
								}
								if(divideSign) {
									result = divide(n1,n2);
								}
								cells.put(cellNumber, "" +result);
							}else if(val1!=null && val2 !=null){
					            Integer result = null;
					            if(addSign) {
									result = add(val1, val2);
								}
								if(subSign) {
									result = subtract(val1, val2);
								}
								if(multiplySign) {
									result = multiply(val1, val2);
								}
								if(divideSign) {
									result = divide(val1, val2);
								}
								cells.put(cellNumber, "" +result);
							}else if(val1!=null && val2 == null) {
								Integer result = null; 
								Integer n1 =  val1;
								Integer n2 =  Integer.parseInt(expArray[1]);
								if(addSign) {
									result = add(n1, n2);
								}
								if(subSign) {
									result = subtract(n1,n2);
								}
								if(multiplySign) {
									result = multiply(n1, n2);
								}
								if(divideSign) {
									result = divide(n1,n2);
								}
								cells.put(cellNumber, "" +result);
							}else if(val1==null && val2 != null) {
								Integer result = null; 
								Integer n1 =  Integer.parseInt(expArray[0]);
								Integer n2 = val2;
								if(addSign) {
									result = add(n1, n2);
								}
								if(subSign) {
									result = subtract(n1,n2);
								}
								if(multiplySign) {
									result = multiply(n1, n2);
								}
								if(divideSign) {
									result = divide(n1,n2);
								}
								cells.put(cellNumber, "" +result);
							
							}else {
								System.out.println("invalid input");
								return;
							}
						}else {
							System.out.println("Invalid Input");
							return;
						}
					}
				}else {
					System.out.println("Invalid cell=" + cellNumber);
					return;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input");
			return;
		}
		//printing output
		System.out.println(cells);
	}
	
	//checks whether proper cell or not
	public static boolean isValidCell(String str) {
		return Pattern.matches("^[A-Z]{1,2}[1-9]{1}[0-9]{0,4}$", str);
	}
	
	//checks whether number or not
	public static boolean isNum(String str) {
		return Pattern.matches("^[0-9]+$", str);
	}
	
	//checks whether valid expression or not
	public static boolean isValidExpression(String str) {
		return Pattern.matches("^=[A-Z0-9]+[+*/-][A-Z0-9]+$", str);
	}
	
	public static int add(int n1,int n2) {
		return n1 + n2;
	}
	
	public static int subtract(int n1,int n2) {
		return n1 - n2;
	}
	
	public static int multiply(int n1,int n2) {
		return n1 * n2;
	}
	
	public static int divide(int n1,int n2) {
		return n1/n2;
	}


}
