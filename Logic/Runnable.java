import java.util.Arrays;


public class Runnable {

	public static void main(String[] args) 
	{
		String bool1 = "(~a+d+b+c)^b*c*1*d*d";
//		String bool2 = "~(~(a*~b*(~b^c))+~(~(a*~b*(~b^c))*(~b^c)))+~(~(a*~b*(~b^c))*(~b^c))";
//		String bool3 = "~(~(a*~a*(~a^b))+~(~(a*~a*(~a^b))*b))+~(~(a*~a*(~a^b))*b)";
//		String bool4 = "~(~(a*~a*(~a^a))+~(~(a*~a*(~a^a))*a))+~(~(a*~a*(~a^a))*a)";
		
		System.out.println(Arrays.toString(OutputTable.buildTable(bool1,4)));
		//System.out.println(Arrays.toString(OutputTable.buildTable(bool2,3)));
		//System.out.println(Arrays.toString(OutputTable.buildTable(bool3,2)));
		//System.out.println(Arrays.toString(OutputTable.buildTable(bool4,1)));
		
	}

}
