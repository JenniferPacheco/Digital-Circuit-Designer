import java.util.*;

public class MainLogic {
	ArrayList<LogicGate> nodeList;
	OutputTable tableOutput;
	
	public MainLogic() {
		nodeList = new ArrayList<LogicGate>();
		tableOutput = new OutputTable();
	}
	
	//Method for going through and calculating the output of the circuit
	//creates the unsimplified boolean expression
	public String DFSRunThrough() {
		LogicGate startingNode = new NOTGate();
		for (LogicGate gate : nodeList) {
			if (gate.finalOutput) {
				startingNode = gate;
				break;
			}
		}
		
		if (startingNode.output == "") {
			startingNode.performOperation();
		}
		
		return startingNode.output;
	}
	
	public void createGate(String operation, int numInputs) {
		LogicGate newGate = new NOTGate();
		
		if (operation == "AND") {
			newGate = new ANDGate(numInputs);
		} else if (operation == "NAND") {
			newGate = new NANDGate(numInputs);
		} else if (operation == "OR") {
			newGate = new ORGate(numInputs);
		} else if (operation == "NOR") {
			newGate = new NORGate(numInputs);
		} else if (operation == "XOR") {
			newGate = new XORGate(numInputs);
		} else if (operation == "XNOR") {
			newGate = new XNORGate(numInputs);
		} else if (operation == "NOT") {
			newGate = new NOTGate();
		}
		
		nodeList.add(newGate);
	}
	
	public void createGate(String operation, int numInputs, boolean finalOutput) {
		LogicGate newGate = new NOTGate();
		
		if (operation == "AND") {
			newGate = new ANDGate(numInputs);
		} else if (operation == "NAND") {
			newGate = new NANDGate(numInputs);
		} else if (operation == "OR") {
			newGate = new ORGate(numInputs);
		} else if (operation == "NOR") {
			newGate = new NORGate(numInputs);
		} else if (operation == "XOR") {
			newGate = new XORGate(numInputs);
		} else if (operation == "XNOR") {
			newGate = new XNORGate(numInputs);
		} else if (operation == "NOT") {
			newGate = new NOTGate();
		}
		
		newGate.finalOutput = true;
		nodeList.add(newGate);
	}
	
	public LogicGate createGate(String gateName) {
		varGate newGate = new varGate(gateName);
		//nodeList.add(newGate);
		return newGate;
	}
	
	//Method for determining the K-Map
	//creates output table ;)
	//Not using this method anymore
//	public void generateKMap() {
//		//determine the number of variables being used
//		//and send the correct blank kmap to the GUI
//		
//		//pick a state of the kmap
//		//process that state
//		//send answer to GUI to fill in that part of the kmap
//		//repeat until the kmap is filled
//		
//		//done generating the kmap
//	}
	
	
	//Method for connecting two logic gates
	public void makeConnection(LogicGate outputGate, LogicGate inputGate) {
		//add the inputGate to the "outputConnections" of the outputGate
		outputGate.outputConnections.add(inputGate);
		
		//add the outputGate to the "inputConnections" of the inputGate
		inputGate.inputConnections.add(outputGate);
	}
}
