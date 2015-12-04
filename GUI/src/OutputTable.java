/* Author: Alexander Masterson
 * This method takes a boolean String that represents the logical operation of the simulated circuit
 * and then builds a table that will show the input and output data for every possible input for that
 * circuit. Calculation of outputs is accomplished by running the String through a series of filters
 * set to a specific input sequence until one character representing the output is found.
 */
public class OutputTable 
{
	//These are the input states for different numbers of variable inputs
	static String[] WStates = {"0","1"};
	static String[] WXStates = {"00","01","10","11"};
	static String[] WXYStates = {"000","001","010","011","100","101","110","111"};
	static String[] WXYZStates = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
	static String[][] States = {WStates,WXStates,WXYStates,WXYZStates};
	
	/* This method builds the output table and populates it with the data that will occupy the table seen
	 * by the user when they simulate their circuit.
	 */
	static String[][] buildTable(String boolStr, int varCount)
	{
		int x = 0;
		
		// Table for no variable inputs
		if (varCount == 0)
		{
			String[][] outputTable = new String[2][1];
			
			outputTable[0][0] = "Out";
			
			outputTable[1][0] = parseBool(boolStr,States[0][0]);
			return outputTable;
		}
		
		// Table for one variable input
		else if (varCount == 1)
		{
			String[][]outputTable = new String[3][2];
			
			outputTable[0][0] = "W";
			outputTable[0][1] = "Out";
			
			for (int i=0; i<2; i++)
			{
				outputTable[i+1][0] = States[varCount-1][i];
				//System.out.println(outputTable[i]);
			}
			
			for (int i=0; i<2; i++)
			{
				outputTable[i+1][1] = parseBool(boolStr,States[varCount-1][i]);
				//System.out.println(outputTable[i]);
			}
			
			return outputTable;
		}
		
		// Table for two variable inputs
		else if (varCount == 2)
		{
			String[][]outputTable = new String[5][3];
			
			outputTable[0][0] = "W";
			outputTable[0][1] = "X";
			outputTable[0][2] = "Out";
			
			for(int i=0; i<4; i++)
			{
				for (int j=0; j<2; j++)
				{
					String state = States[varCount-1][i];
					outputTable[i+1][j] = Character.toString(state.charAt(j));
					//System.out.println(outputTable[i]);
				}
			}
			
			for (int i=0; i<4; i++)
			{
				outputTable[i+1][2] = parseBool(boolStr,States[varCount-1][x]);
				x++;
				//System.out.println(outputTable[i]);
			}
			
			return outputTable;
		}
		
		// Table for three variable inputs
		else if (varCount == 3)
		{
			String[][]outputTable = new String[9][4];
			
			outputTable[0][0] = "W";
			outputTable[0][1] = "X";
			outputTable[0][2] = "Y";
			outputTable[0][3] = "Out";
			
			for(int i=0; i<8; i++)
			{
				for (int j=0; j<3; j++)
				{
					String state = States[varCount-1][i];
					outputTable[i+1][j] = Character.toString(state.charAt(j));
					//System.out.println(outputTable[i]);
				}
			}
			
			for (int i=0; i<8; i++)
			{
				outputTable[i+1][3] = parseBool(boolStr,States[varCount-1][x]);
				x++;
				//System.out.println(outputTable[i]);
			}
			
			return outputTable;
		}
		
		// Table for four variable inputs
		else
		{
			String[][]outputTable = new String[17][5];
			
			outputTable[0][0] = "W";
			outputTable[0][1] = "X";
			outputTable[0][2] = "Y";
			outputTable[0][3] = "Z";
			outputTable[0][4] = "Out";
			
			for(int i=0; i<16; i++)
			{
				for (int j=0; j<4; j++)
				{
					String state = States[varCount-1][i];
					outputTable[i+1][j] = Character.toString(state.charAt(j));
					//System.out.println(outputTable[i]);
				}
			}
			
			for (int i=0; i<16; i++)
			{
				outputTable[i+1][4] = parseBool(boolStr,States[varCount-1][x]);
				x++;
				//System.out.println(outputTable[i]);
			}
			
			return outputTable;
		}
	}
	
	/* This method takes the boolean statement provided to it and the state with which to process
	 * the statement, and calculates the output of the boolean statement at the given state.
	 */
	static String parseBool(String boolStr, String state)
	{
		String output = firstPass(boolStr, state);
		
		while(output.length() != 1)
		{
				output = termReduce(output);
				output = parenReduce(output);
		}
		return output;
	}

	/* This method runs a passover of the boolStr and replaces every variable input with the correct
	 * state value for the current input state.
	 */
	static String firstPass(String boolStr, String state)
	{				
		if((boolStr == null) || (boolStr.length() == 0))
			return boolStr;
		
		if(boolStr.charAt(0) == 'w')							//is the current first char a W?
			return state.charAt(0) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else if(boolStr.charAt(0) == 'x')						//is the current first char an X?
			return state.charAt(1) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else if(boolStr.charAt(0) == 'y')						//is the current first char a Y?
			return state.charAt(2) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else if(boolStr.charAt(0) == 'z')						//is the current first char a Z?
			return state.charAt(3) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else										//not an a,b,c, or d
			return boolStr.charAt(0) + firstPass(boolStr.substring(1), state);	//continue to next char
	}
	
	/* This method runs a passover of the boolStr and replaces every easy to process operation (ie. ~1 or (1*1))
	 * and replaces it with the result of that operation
	 */
	static String termReduce(String boolStr)
	{
		if((boolStr == null) || (boolStr.length() == 0))
			return boolStr;
		
		if(boolStr.charAt(0) == '~' && (boolStr.charAt(1) == '1' || boolStr.charAt(1) == '0'))
			return Gates.NOT(boolStr.charAt(1)) + termReduce(boolStr.substring(2));
		
		else if((boolStr.length()>=3) && (boolStr.charAt(1) == '*') && (boolStr.charAt(0) == '1' || boolStr.charAt(0) == '0') && (boolStr.charAt(2) == '1' || boolStr.charAt(2) == '0'))
			return Gates.AND(boolStr.charAt(0), boolStr.charAt(2)) + termReduce(boolStr.substring(3));
		
		else if((boolStr.length()>=3) && (boolStr.charAt(1) == '+') && (boolStr.charAt(0) == '1' || boolStr.charAt(0) == '0') && (boolStr.charAt(2) == '1' || boolStr.charAt(2) == '0'))
			return Gates.OR(boolStr.charAt(0), boolStr.charAt(2)) + termReduce(boolStr.substring(3));

		else if((boolStr.length()>=3) && (boolStr.charAt(1) == '^') && (boolStr.charAt(0) == '1' || boolStr.charAt(0) == '0') && (boolStr.charAt(2) == '1' || boolStr.charAt(2) == '0'))
			return Gates.XOR(boolStr.charAt(0), boolStr.charAt(2)) + termReduce(boolStr.substring(3));

		else
			return boolStr.charAt(0) + termReduce(boolStr.substring(1));
	}

	/* This method runs a passover of the boolStr and deletes every pair of parenthesis that has a single character
	 * encapsulated.
	 */
	static String parenReduce(String boolStr)
	{
		if((boolStr == null) || (boolStr.length() == 0))
			return boolStr;
		
		if(boolStr.charAt(0) == '(' && boolStr.charAt(2) == ')')
			return boolStr.charAt(1) + parenReduce(boolStr.substring(3));
		
		else
			return boolStr.charAt(0) + parenReduce(boolStr.substring(1));		
	}
}
