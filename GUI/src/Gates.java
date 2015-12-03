/* Author: Alexander Masterson
 * This is a helper class for the OutputTable class. This class provides Logical Gate
 * functions for the text analysis program in the OutputTable class. String characters
 * are passed to this class, and this class helps reduce terms in OutputTable.
 */
public class Gates 
{
	//This is a String Based NOT gate
	static String NOT(char input1)
	{
    	if(input1 == '1')
    	{
        	return "0";
    	}
    	else
    	{
        	return "1";
    	}
	}
	
	//This is a String Based AND gate
	static String AND(char input1, char input2)
	{    	
    	if(input1 == '1' && input2 == '1')
    	{
        	return "1";
    	}
    	else
    	{
        	return "0";
    	}
	}
	
	//This is a String Based OR gate
	static String OR(char input1, char input2)
	{    	
    	if(input1 == '1' || input2 == '1')
    	{
        	return "1";
    	}
    	else
    	{
        	return "0";
    	}
	}
	
	//This is a String Based XOR gate
	static String XOR(char input1, char input2)
	{
    	if(input1 != input2)
    	{
        	return "1";
    	}
    	else
    	{
        	return "0";
    	}
	}
}
