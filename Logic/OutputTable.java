public class OutputTable 
{
	static String[] AStates = {"0","1"};
	static String[] ABStates = {"00","01","10","11"};
	static String[] ABCStates = {"000","001","010","011","100","101","110","111"};
	static String[] ABCDStates = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
	//static String[] AStates = {"0","1"};
	//static String[] ABStates = {"00","01","11","10"};
	//static String[] ABCStates = {"000","001","011","010","100","101","111","110"};
	//static String[] ABCDStates = {"0000","0001","0011","0010","0100","0101","0111","0110","1000","1001","1011","1010","1100","1101","1111","1110"};
	static String[][] States = {AStates,ABStates,ABCStates,ABCDStates};
	
	static String[] buildTable(String boolStr, int varCount)
	{
		if (varCount == 0)
		{
			String[] outputTable = new String[1];
			outputTable[0] = "";
			return outputTable;
		}
		else
		{
			String[]outputTable = new String[(int) Math.pow(2, varCount)];
			for(int i=0; i<outputTable.length; i++)
			{
				outputTable[i] = parseBool(boolStr,States[varCount-1][i]);
				//System.out.println(outputTable[i]);
			}
			
			return outputTable;
		}
	}
	
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

	static String firstPass(String boolStr, String state)
	{				
		if((boolStr == null) || (boolStr.length() == 0))
			return boolStr;
		
		if(boolStr.charAt(0) == 'a')											//is the current first char an a?
			return state.charAt(0) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else if(boolStr.charAt(0) == 'b')										//is the current first char a b?
			return state.charAt(1) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else if(boolStr.charAt(0) == 'c')										//is the current first char a c?
			return state.charAt(2) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else if(boolStr.charAt(0) == 'd')										//is the current first char a d?
			return state.charAt(3) + firstPass(boolStr.substring(1), state);	//recursive return necessary number and string starting at next char
		
		else																	//not an a,b,c, or d
			return boolStr.charAt(0) + firstPass(boolStr.substring(1), state);	//continue to next char
	}
	
/*
	static String secondPass(String boolStr)
	{
		if((boolStr == null) || (boolStr.length() == 0))
			return boolStr;
		if(boolStr.charAt(0) == '~' && boolStr.charAt(1) == '0')											//is the current first char an a?
			return '1' + secondPass(boolStr.substring(2));	//recursive return necessary number and string starting at next char
		else if(boolStr.charAt(0) == '~' && boolStr.charAt(1) == '1')											//is the current first char an a?
			return '0' + secondPass(boolStr.substring(2));	//recursive return necessary number and string starting at next char
		else																	//not an a,b,c, or d
			return boolStr.charAt(0) + secondPass(boolStr.substring(1));	//continue to next char
	}
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
