import java.util.*;

public class LogicGate {
	int numInputs;								//2-input, 3-input, or 4-input?
	ArrayList<LogicGate> inputConnections;		//list of gates connected to the input of this gate
	ArrayList<LogicGate> outputConnections;		//list of gates connected to the output of this gate
	ArrayList<String> varInputs;				//List of var/hardcoded inputs, implies it's a leaf node
	String operation;							//List of possibilities (all caps)
												//AND, NAND, OR, NOR, XOR, XNOR, NOT
	String output = "";							//Should be a 0 (false), 1 (true), or variable
												//initially null to specify the operation hasn't been performed
	int stateOutput = -1;						//Should be only 0 or 1
	boolean finalOutput = false;						//Probably don't need this anymore since we can tell by the level of the gate

	String gateName;
	String [] inputs;
	int level;
	
	//default constructor
	public LogicGate(){}
	
	//Simple constructor method. 
	//Inputs: numInput: the # of inputs the gate will have
	//		  operation: what kind of gate is this (list is above)
	public LogicGate(int numInputs, String operation) {
		this.numInputs = numInputs;
		this.operation = operation;
		this.inputConnections = new ArrayList<LogicGate>();
		this.outputConnections = new ArrayList<LogicGate>();
	}
	
	//method will be overridden by subclasses
	public void performOperation() {}	
}
