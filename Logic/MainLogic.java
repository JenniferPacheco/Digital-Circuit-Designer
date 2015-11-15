import java.util.*;

public class MainLogic {
	ArrayList<LogicGate> nodeList;
	String[] oneVarInput = {"0", "1"};
	String[] twoVarInput = {"00", "01", "10", "11"};
	String[] threeVarInput = {"000", "001", "010", "011", "100", "101", "110", "111"};
	String[] fourVarInput = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
	
	//Method for going through and calculating the output of the circuit
	public String DFSRunThrough() {
		nodeList.get(0).performOperation();
		return "";
	}
	
	//Method for determining the K-Map
	//need to determine what it returns exactly. Picture?
	public void generateKMap() {
		//determine the number of variables being used
		//and send the correct blank kmap to the GUI
		
		//pick a state of the kmap
		//process that state
		//send answer to GUI to fill in that part of the kmap
		//repeat until the kmap is filled
		
		//done generating the kmap
	}
	
	//Method for determining the boolean expression
	public String generateBoolExp() {
		return "";
	}
	
	//Method for connecting two logic gates
	public void makeConnection(LogicGate outputGate, LogicGate inputGate) {
		//add the inputGate to the "outputConnections" of the outputGate
		outputGate.outputConnections.add(inputGate);
		
		//add the outputGate to the "inputConnections" of the inputGate
		inputGate.inputConnections.add(outputGate);
	}
}
