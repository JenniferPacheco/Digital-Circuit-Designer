
public class XORGate extends LogicGate{
	public XORGate(int numInputs) {
		super(numInputs, "XOR");
	}
	
	@Override
	public void performOperation() {
		//XOR calculation
		//Since implementation will be like an odd-parity checker, simply run
		//through the inputConnections and count up how many true outputs there are.
		//If there are an odd # of trues, set the output variable to true.
		//If there are an even # of trues, set the output variable to false.
		for (int i = 0; i < inputConnections.size(); i++) {
			LogicGate testGate = inputConnections.get(i);
			
			//check to make sure the testGate has a defined output
			if (testGate.output == "") {
				testGate.performOperation();
			}
			
			if (this.output == "") {
				this.output = "(" + testGate.output;
			} else if (i + 1 == inputConnections.size()){
				this.output += "^" + testGate.output + ")";
			} else {
				this.output += "^" + testGate.output;
			}
		}
	}
}
