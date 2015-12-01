
public class XNORGate extends LogicGate {
	public XNORGate(int numInputs) {
		super(numInputs, "XNOR");
	}
	
	@Override
	public void performOperation() {
		//XNOR calculation
		//Similar to XOR but instead this gate acts as an even-parity checker
		//If there are an odd # of trues, set the output to false
		//If there are an even # of trues, set the output to true
		for (int i = 0; i < inputConnections.size(); i++) {
			LogicGate testGate = inputConnections.get(i);
			
			if (testGate.output == "") {
				testGate.performOperation();
			}
			
			if (this.output == "") {
				this.output = "~(" + testGate.output;
			} else if (i + 1 == inputConnections.size()) {
				this.output += "^" + testGate.output + ")";
			} else {
				this.output += "^" + testGate.output;
			}
		}
	}
}
