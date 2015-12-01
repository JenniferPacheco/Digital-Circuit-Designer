import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class UserInterface extends JPanel implements ActionListener, ItemListener, ListSelectionListener{

	private static final long serialVersionUID = 1L;

	//array of logic gates, 13 is max number of gates
	//4 for each of the first 3 levels and 1 for the last level
	LogicGate [] userGates = new LogicGate [13]; 
	
	ImageIcon image;
	
	//initialize counting variables for the number of gates
	int numGates = 0, numAnds = 0, numOrs = 0, numXors = 0, numXnors = 0, numNors = 0, numNots = 0, numNands = 0;
	
	//flags to know what variables are being used
	boolean flagW = false, flagX = false, flagY = false, flagZ = false;
	
	//array to hold the number of gates in each level
	//to ensure that we don't go over the max
	int [] numGatesInLevel = new int[4];

	//menu items
	JMenuItem newFile, open, save, manual;
	JMenuItem input1, input2, input3, zeroInput, oneInput;
	JMenuItem and2, and3, and4;
	JMenuItem or2, or3, or4;
	JMenuItem nand2, nand3, nand4;
	JMenuItem nor2, nor3, nor4;
	JMenuItem xor2, xor3, xor4;
	JMenuItem xnor2, xnor3, xnor4;
	JMenuItem not;
	
	//array of JPanels for drawing area
	JPanel[] drawLevels = new JPanel[4];
	
	//JPanel to place the output on
	JPanel output;	
	JButton simulate, clear, connections;
	JFrame frame, newMenu;
	
	//items for the create logic gate new menu that comes
	//up whenever one of the gates is chosen
	JList<String> inputList;
	DefaultListModel<String> inputs;
	DefaultComboBoxModel<String> gates;
	JButton showInputs, submit;
	JComboBox<String> selectGate;
	
	//connection to the main logic class
	MainLogic theMainLogic = new MainLogic();
	
	void CreateUI() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		
		//initialize 0 gates in each level
		for (int i = 0; i < 4; i++) {
			numGatesInLevel[i] = 0;
		}
		
		//create the main frame
		frame = new JFrame("Digital Circuit Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(900, 720));
		
		//create the menu bar
		JMenuBar menuBar = new JMenuBar();
        //menuBar.setBackground(new Color(154, 165, 127));
        menuBar.setPreferredSize(new Dimension(1080, 40));
        
        //and gate menu
        JMenu andMenu = new JMenu("AND Gates");
        menuBar.add(andMenu);
        and2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputAND.png"));
        and2.addActionListener(this);
        andMenu.add(and2);
        and3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputAND.png"));
        and3.addActionListener(this);
        andMenu.add(and3);
        and4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputAND.png"));
        and4.addActionListener(this);
        andMenu.add(and4);
        
        //or gate menu
        JMenu orMenu = new JMenu("OR Gates");
        menuBar.add(orMenu);
        or2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputOR.png"));
        or2.addActionListener(this);
        orMenu.add(or2);
        or3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputOR.png"));
        or3.addActionListener(this);
        orMenu.add(or3);
        or4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputOR.png"));
        or4.addActionListener(this);
        orMenu.add(or4);
        
        //nand gate menu
        JMenu nandMenu = new JMenu("NAND Gates");
        menuBar.add(nandMenu);
        nand2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputNAND.png"));
        nand2.addActionListener(this);
        nandMenu.add(nand2);
        nand3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputNAND.png"));
        nand3.addActionListener(this);
        nandMenu.add(nand3);
        nand4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputNAND.png"));
        nand4.addActionListener(this);
        nandMenu.add(nand4);
        
        //nor gate menu
        JMenu norMenu = new JMenu("NOR Gates");
        menuBar.add(norMenu);
        nor2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputNOR.png"));
        nor2.addActionListener(this);
        norMenu.add(nor2);
        nor3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputNOR.png"));
        nor3.addActionListener(this);
        norMenu.add(nor3);
        nor4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputNOR.png"));
        nor4.addActionListener(this);
        norMenu.add(nor4);
        
        //xor gate menu
        JMenu xorMenu = new JMenu("XOR Gates");
        menuBar.add(xorMenu);
        xor2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputXOR.png"));
        xor2.addActionListener(this);
        xorMenu.add(xor2);
        xor3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputXOR.png"));
        xor3.addActionListener(this);
        xorMenu.add(xor3);
        xor4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputXOR.png"));
        xor4.addActionListener(this);
        xorMenu.add(xor4);
        
        //xnor gate menu
        JMenu xnorMenu = new JMenu("XNOR Gates");
        menuBar.add(xnorMenu);
        xnor2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputXNOR.png"));
        xnor2.addActionListener(this);
        xnorMenu.add(xnor2);
        xnor3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputXNOR.png"));
        xnor3.addActionListener(this);
        xnorMenu.add(xnor3);
        xnor4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputXNOR.png"));
        xnor4.addActionListener(this);
        xnorMenu.add(xnor4);
        
        //not gate menu
        JMenu notMenu = new JMenu("NOT Gate");
        menuBar.add(notMenu);
        not = new JMenuItem("NOT", new ImageIcon("images/NOT.png"));
        not.addActionListener(this);
        notMenu.add(not);
        
        //help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        manual = new JMenuItem("User Manual");
        manual.addActionListener(this);
        helpMenu.add(manual);
        
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int panelWidth = ((int)d.getWidth() - 160)/4;
        
        //add the 4 drawing panels
        for(int i = 0; i<4; i++){
        	//array so that you can index the individual panels while drawing the gates
        	drawLevels[i] = new JPanel();
        	drawLevels[i].setLayout(new BoxLayout(drawLevels[i], BoxLayout.Y_AXIS));
        	frame.add(drawLevels[i]);
        	drawLevels[i].setBounds(80 + panelWidth*i, 20, panelWidth, 290);
        	
        }
        
        //simulate button
        connections = new JButton("Connect");
        frame.add(connections);
        connections.setBounds(490, 325, 100, 25);
        connections.addActionListener(this);
        
        //simulate button
        simulate = new JButton("Simulate");
        frame.add(simulate);
        simulate.setBounds(615, 325, 100, 25);
        simulate.addActionListener(this);
        
        //clear button
        clear = new JButton("Clear");
        frame.add(clear);
        clear.setBounds(740, 325, 100, 25);
        clear.addActionListener(this);
        
        //output JPanel
        output = new JPanel();
        output.setLayout(new BoxLayout(output, BoxLayout.Y_AXIS));
        frame.add(output);
        output.setBounds(80, 365, 1200, 280);
        
        //create a new menu that pops up when you select a gate
        //so that you set inputs and the level
        newMenu = new JFrame("Logic Gate Set Up");
		newMenu.setSize(600, 600);
		
		//JComboBox for choosing the level to draw the gate in
		JLabel pickGate = new JLabel("Pick a gate to select inputs for: ");
		newMenu.add(pickGate);
		pickGate.setBounds(60, 20, 200, 50);
		gates = new DefaultComboBoxModel<String>();
		selectGate = new JComboBox<String>(gates);	
		selectGate.addItemListener(this);
		newMenu.add(selectGate);
		selectGate.setBounds(250, 40, 100, 20);
		
		//label for the pick inputs list
		JLabel pickInputs = new JLabel("Pick Inputs: ");
		newMenu.add(pickInputs);
		pickInputs.setBounds(60, 175, 200, 50);	
	
		//add the first few input options to the JList
		inputs = new DefaultListModel<String>();
		inputs.addElement("0");
		inputs.addElement("1");
		inputs.addElement("w");
		inputs.addElement("x");
		inputs.addElement("y");
		inputs.addElement("z");
		
		//JList to select the inputs
		inputList = new JList<String>(inputs);
		inputList.addListSelectionListener(this);
		
		//allows the user to click on multiple selections instead of needing
		//to press ctrl and click on selections
		inputList.setSelectionModel(new DefaultListSelectionModel() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private int i0 = -1;
		    private int i1 = -1;

		    public void setSelectionInterval(int index0, int index1) {
		        if(i0 == index0 && i1 == index1){
		            if(getValueIsAdjusting()){
		                 setValueIsAdjusting(false);
		                 setSelection(index0, index1);
		            }
		        }else{
		            i0 = index0;
		            i1 = index1;
		            setValueIsAdjusting(false);
		            setSelection(index0, index1);
		        }
		    }
		    private void setSelection(int index0, int index1){
		        if(super.isSelectedIndex(index0)) {
		            super.removeSelectionInterval(index0, index1);
		        }else {
		            super.addSelectionInterval(index0, index1);
		        }
		    }
		});
		
		//adding a scrollbar for when needed
		JScrollPane listScroller = new JScrollPane(inputList);
		listScroller.setBounds(250, 185, 100, 150);
		newMenu.add(listScroller);
		
		//a button to display all possible inputs for the selected gate
		showInputs = new JButton("Show Inputs");		
		showInputs.addActionListener(this);
		newMenu.add(showInputs);
		showInputs.setBounds(175, 130, 125, 25);
		
		//submit button to submit the gate creation information
		submit = new JButton("Submit");		
		submit.addActionListener(this);
		newMenu.add(submit);
		submit.setBounds(200, 450, 100, 25);
        
        JLabel coloredLabel = new JLabel();
        coloredLabel.setOpaque(true);
        coloredLabel.setBackground(new Color(173, 194, 235));
        coloredLabel.setPreferredSize(new Dimension(200, 180));
        
        newMenu.getContentPane().add(coloredLabel);
		
	    frame.setJMenuBar(menuBar);
	    frame.getContentPane().add(coloredLabel);
	    frame.setVisible(true);
	}
	
	//wow, look at that main function
	public static void main( String[] args ) {
		UserInterface ui = new UserInterface();
		ui.CreateUI();
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();		
		int index = 0;
		
		//show input button is pressed
		if(source.equals(showInputs)){
			
			int maxLevel = 0;		
			
			inputs.clear();
			inputs.addElement("0");
			inputs.addElement("1");
			inputs.addElement("w");
			inputs.addElement("x");
			inputs.addElement("y");
			inputs.addElement("z");
			
			//finds out what that gate's level is to determine the
			//max level it can search for inputs from
			for(int i = 0; i<numGates; i++){
				if(userGates[i].gateName == selectGate.getSelectedItem()){
					maxLevel = userGates[i].level;
					index = i;
				}
			}
			
			//adds all of the elements that are in the same or higher levels to
			//the input list to choose from
			for(int i = 0; i<numGates; i++){
				if(userGates[i].level > maxLevel && i != index){
					inputs.addElement(userGates[i].gateName);
				}
			}
			
			inputList.setVisible(true);
		}
		
		//connections button is chosen
		if(source.equals(connections)){
			//confirms that there are gates to connect and there is one in the output level
			if(numGates > 0 && numGatesInLevel[0]==1)
				createLogicGate();
			else{
				//no gates to connect
				if(numGates == 0)
					JOptionPane.showMessageDialog(frame, "No gates to connect. Add gates.");
				//no gates in the output level (level 1)
				else
					JOptionPane.showMessageDialog(frame, "No gate in output level. Add an output gate to level 1.");
			}
		}
		
		//submit button in the create logic gate menu
		if(source.equals(submit)){
			boolean input = false;
			
			//finds the index of the selected gate
			for(int i = 0; i<numGates; i++){
				if(userGates[i].gateName == selectGate.getSelectedItem()){
					index = i;	//points to the gate that is receiving the inputs
								//This is the makeConnection's 'inputGate'
				}
			}
			
			//loops through to count the number of selections
			int minIndex = inputList.getMinSelectionIndex();
	        int maxIndex = inputList.getMaxSelectionIndex();
	        int chosenCount = 0;
	        for (int i = minIndex; i <= maxIndex; i++) {
	            if (inputList.isSelectedIndex(i)) {
	                chosenCount++;
	            }
	        }
	        
	        //number of input selections = num of gate inputs
	        if(chosenCount == userGates[index].numInputs){
	        	int j =0;
	        	//add the chosen inputs to the gate's input array
	        	for (int i = minIndex; i <= maxIndex; i++) {
	                if (inputList.isSelectedIndex(i)) {
	                    userGates[index].inputs[j] = inputs.getElementAt(i);
	                    j++;
	                }
	            }
	        	input = true;
	        }
	        
	        //too many inputs were chosen, try again
	        else if(chosenCount > userGates[index].numInputs)	        	
	        	JOptionPane.showMessageDialog(newMenu, "Too many inputs selected.");
	        
	        //too little inputs were chosen, try again
	        else
	        	JOptionPane.showMessageDialog(newMenu, "Too little inputs selected.");
	        
	        //inputs are valid, create connections
	        if(input == true){
	        	
	        	//close the create logic gate menu
	        	newMenu.setVisible(false);
	        	//clears and resets the input list
	        	if(inputs.getSize() > 5)
	        		inputs.removeRange(5, inputs.getSize()-1);
	        	
	        	//loops through all of the inputs to make the necessary connections
	        	for(int i = 0; i< userGates[index].numInputs; i++){	        		

	        		//if the input is x,y,z,0,or 1 create varGates and set flags
	        		if(userGates[index].inputs[i] == "x"){
	        			theMainLogic.makeConnection(theMainLogic.createGate("x"), theMainLogic.nodeList.get(index));
	        			drawConnections(theMainLogic.createGate("x"), theMainLogic.nodeList.get(index), i, theMainLogic.nodeList.get(index).numInputs);
	        			flagX = true;
	        		}
	        		else if(userGates[index].inputs[i] == "y"){
	        			theMainLogic.makeConnection(theMainLogic.createGate("y"), theMainLogic.nodeList.get(index));
	        			drawConnections(theMainLogic.createGate("y"), theMainLogic.nodeList.get(index), i, theMainLogic.nodeList.get(index).numInputs);
	        			flagY = true;
	        		}
	        		else if(userGates[index].inputs[i] == "z"){
	        			theMainLogic.makeConnection(theMainLogic.createGate("z"), theMainLogic.nodeList.get(index));
	        			drawConnections(theMainLogic.createGate("z"), theMainLogic.nodeList.get(index), i, theMainLogic.nodeList.get(index).numInputs);
	        			flagZ = true;
	        		}
	        		else if(userGates[index].inputs[i] == "0"){
	        			theMainLogic.makeConnection(theMainLogic.createGate("0"), theMainLogic.nodeList.get(index));
	        			drawConnections(theMainLogic.createGate("0"), theMainLogic.nodeList.get(index), i, theMainLogic.nodeList.get(index).numInputs);
	        		}
	        		else if(userGates[index].inputs[i] == "1"){
	        			theMainLogic.makeConnection(theMainLogic.createGate("1"), theMainLogic.nodeList.get(index));
	        			drawConnections(theMainLogic.createGate("1"), theMainLogic.nodeList.get(index), i, theMainLogic.nodeList.get(index).numInputs);
	        		}
	        		else if(userGates[index].inputs[i] == "w"){
	        			theMainLogic.makeConnection(theMainLogic.createGate("w"), theMainLogic.nodeList.get(index));
	        			drawConnections(theMainLogic.createGate("w"), theMainLogic.nodeList.get(index), i, theMainLogic.nodeList.get(index).numInputs);
	        			flagW = true;
	        		}
	        		
	        		for (int j = 0; j < numGates; j++) {
	        			if (userGates[j].gateName == userGates[index].inputs[i]) {
	        				theMainLogic.makeConnection(theMainLogic.nodeList.get(j), theMainLogic.nodeList.get(index));
	        				drawConnections(theMainLogic.nodeList.get(j), theMainLogic.nodeList.get(index), i, theMainLogic.nodeList.get(index).numInputs);
	        				break;
	        			}
	        		}
	        		
	        		System.out.println(userGates[index].gateName + " line to " + userGates[index].inputs[i]);
	        	}       
	        }
		}
		
		//and gate with 2 inputs
		if(source.equals(and2)){
			//set up values in the new logic gate
			userGates[numGates] = new ANDGate(2);
			userGates[numGates].gateName = "AND" + numAnds;
			userGates[numGates].inputs = new String[2];        
			gateCreatorHelper("images/2inputAND.png", "AND");
		}
		
		//and gate with 3 inputs
		if(source.equals(and3)){
			//set up values in the new logic gate
			userGates[numGates] = new ANDGate(3);
			userGates[numGates].gateName = "AND" + numAnds;
			userGates[numGates].inputs = new String[3];
			gateCreatorHelper("images/3inputAND.png", "AND");
		}
		
		//and gate with 4 inputs
		if(source.equals(and4)){
			//set up values in the new logic gate
			userGates[numGates] = new ANDGate(4);
			userGates[numGates].gateName = "AND" + numAnds;
			userGates[numGates].inputs = new String[4];
			gateCreatorHelper("images/4inputAND.png", "AND");
		}
		
		//or gate with 2 inputs
		if(source.equals(or2)){
			//set up values in the new logic gate
			userGates[numGates] = new ORGate(2);
			userGates[numGates].gateName = "OR" + numOrs;
			userGates[numGates].inputs = new String[2];		
			gateCreatorHelper("images/2inputOR.png", "OR");
		}
		
		//or gate with 3 inputs
		if(source.equals(or3)){
			//set up values in the new logic gate
			userGates[numGates] = new ORGate(3);
			userGates[numGates].gateName = "OR" + numOrs;
			userGates[numGates].inputs = new String[3];
			gateCreatorHelper("images/3inputOR.png", "OR");
		}
		
		//or gate with 4 inputs
		if(source.equals(or4)){
			//set up values in the new logic gate
			userGates[numGates] = new ORGate(4);
			userGates[numGates].gateName = "OR" + numOrs;
			userGates[numGates].inputs = new String[4];
			gateCreatorHelper("images/4inputOR.png", "OR");
		}
		
		//nand gate with 2 inputs
		if(source.equals(nand2)){
			//set up values in the new logic gate
			userGates[numGates] = new NANDGate(2);
			userGates[numGates].gateName = "NAND" + numNands;
			userGates[numGates].inputs = new String[2];
			gateCreatorHelper("images/2inputNAND.png", "NAND");
		}
		
		//nand gate with 3 inputs
		if(source.equals(nand3)){
			//set up values in the new logic gate
			userGates[numGates] = new NANDGate(3);
			userGates[numGates].gateName = "NAND" + numNands;
			userGates[numGates].inputs = new String[3];
			gateCreatorHelper("images/3inputNAND.png", "NAND");
		}
		
		//nand gate with 4 inputs
		if(source.equals(nand4)){
			//set up values in the new logic gate
			userGates[numGates] = new NANDGate(4);
			userGates[numGates].gateName = "NAND" + numNands;
			userGates[numGates].inputs = new String[4];
			gateCreatorHelper("images/4inputNAND.png", "NAND");
		}
		
		//nor gate with 2 inputs
		if(source.equals(nor2)){
			//set up values in the new logic gate
			userGates[numGates] = new NORGate(2);
			userGates[numGates].gateName = "NOR" + numNors;
			userGates[numGates].inputs = new String[2];
			gateCreatorHelper("images/2inputNOR.png", "NOR");
		}
		
		//nor gate with 3 inputs
		if(source.equals(nor3)){
			//set up values in the new logic gate
			userGates[numGates] = new NORGate(3);
			userGates[numGates].gateName = "NOR" + numNors;
			userGates[numGates].inputs = new String[3];
			gateCreatorHelper("images/3inputNOR.png", "NOR");
		}
		
		//nor gate with 4 inputs
		if(source.equals(nor4)){
			//set up values in the new logic gate
			userGates[numGates] = new NORGate(4);
			userGates[numGates].gateName = "NOR" + numNors;
			userGates[numGates].inputs = new String[4];
			gateCreatorHelper("images/4inputNOR.png", "NOR");
		}

		//not gate
		if(source.equals(not)){
			//set up values in the new logic gate
			userGates[numGates] = new NOTGate();
			userGates[numGates].gateName = "NOT" + numNots;
			userGates[numGates].inputs = new String[1];
			gateCreatorHelper("images/NOT.png", "NOT");
		}

		//xnor gate with 2 inputs
		if(source.equals(xnor2)){
			//set up values in the new logic gate
			userGates[numGates] = new XNORGate(2);
			userGates[numGates].gateName = "XNOR" + numXnors;
			userGates[numGates].inputs = new String[2];
			gateCreatorHelper("images/2inputXNOR.png", "XNOR");
		}
		
		//xnor gate with 3 inputs
		if(source.equals(xnor3)){
			//set up values in the new logic gate
			userGates[numGates] = new XNORGate(3);
			userGates[numGates].gateName = "XNOR" + numXnors;
			userGates[numGates].inputs = new String[3];
			gateCreatorHelper("images/3inputXNOR.png", "XNOR");
		}
				
		//xnor gate with 4 inputs
		if(source.equals(xnor4)){
			//set up values in the new logic gate
			userGates[numGates] = new XNORGate(4);
			userGates[numGates].gateName = "XNOR" + numXnors;
			userGates[numGates].inputs = new String[4];
			gateCreatorHelper("images/4inputXNOR.png", "XNOR");
		}
		
		//xor gate with 2 inputs
		if(source.equals(xor2)){
			//set up values in the new logic gate
			userGates[numGates] = new XORGate(2);
			userGates[numGates].gateName = "XOR" + numXors;
			userGates[numGates].inputs = new String[2];			
			gateCreatorHelper("images/2inputXOR.png", "XOR");
		}
		
		//xor gate with 3 inputs
		if(source.equals(xor3)){
			//set up values in the new logic gate
			userGates[numGates] = new XORGate(3);
			userGates[numGates].gateName = "XOR" + numXors;
			userGates[numGates].inputs = new String[3];			
			gateCreatorHelper("images/3inputXOR.png", "XOR");
		}
				
		//xor gate with 4 inputs
		if(source.equals(xor4)){
			//set up values in the new logic gate
			userGates[numGates] = new XORGate(4);
			userGates[numGates].gateName = "XOR" + numXors;
			userGates[numGates].inputs = new String[4];			
			gateCreatorHelper("images/4inputXOR.png", "XOR");
		}
		
		//User clicks "Simulate" button
		if(source.equals(simulate)) {
			if(numGatesInLevel[0] == 0)
				JOptionPane.showMessageDialog(frame, "No final output gate in final level. Please"
						+ " create an output gate in level 1 and try again.");
			
			String boolExp = theMainLogic.DFSRunThrough();
			JLabel label = new JLabel("Unsimplified boolean expression: " + boolExp);
			
			label.setVisible(true);
			output.setVisible(false);
			output.add(label);
			output.setVisible(true);
			
			//needed to redo how the variables were calculated since not keeping them in nodeList anymore
			int variables = 0;
			if(flagZ) {
				variables = 4;
			} else if (flagY) {
				variables = 3;
			} else if (flagX) {
				variables = 2;
			} else if (flagW) {
				variables = 1;
			}
			
			String[][] outputTable = OutputTable.buildTable(boolExp, variables);
			String[] columns = outputTable[0];
			Object[][] data = new Object[outputTable.length - 1][outputTable[0].length];
			
			for(int i = 1; i < outputTable.length; i++) {
				for(int j = 0; j < outputTable[0].length; j++) {
					data[i - 1][j] = outputTable[i][j];
				}
			}
			
			JTable table = new JTable(data, columns);
			JScrollPane sp = new JScrollPane(table);
			table.setShowGrid(true);
			sp.setVisible(true);
			output.setVisible(false);
			output.add(sp);
			output.setVisible(true);
		}
		
		//User clicks "Clear" button
		if(source.equals(clear)) {
			
			//Remove all gate images from all draw levels
			for(int i = 0; i < drawLevels.length; i++) {
				drawLevels[i].setVisible(false);
				drawLevels[i].removeAll();
				drawLevels[i].setVisible(true);
			}
			
			//Remove GUI's list of gates created by the user
			for(int i = 0; i < userGates.length; i++) {
				userGates[i] = null;
			}
			
			//Remove all created gates from the logic side
			theMainLogic.nodeList.clear();
			
			//Reset total number of gates to 0
			numGates = 0;
			
			//Reset total number of gates of each type to 0
			numAnds = 0; numNands = 0; numNors = 0; numOrs = 0; numXnors = 0; numXors = 0; numNots = 0;
			
			//Reset all of the variable flags
			flagW = false; flagX = false; flagY = false; flagZ = false;
			
			//Remove all choices for input gates in connection selector
			selectGate.removeAllItems();
			
			//Clear out the output box
			output.setVisible(false);
			output.removeAll();
			output.setVisible(true);
			
			//Reset number of gates in each level to 0
			for(int i = 0; i < numGatesInLevel.length; i++)
				numGatesInLevel[i] = 0;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {}
	
	public void createLogicGate(){
		//reset the combobox and list upon opening the new menu
		newMenu.setVisible(true);
		selectGate.setSelectedIndex(0);
		inputList.setVisible(false);
    	inputList.clearSelection();
    }
	
	//Adds padding based on number of gates currently in level, including gate
	//being added, to center the gates
	public void centerGates(int level) {
		if(numGatesInLevel[level - 1] == 1) {
			drawLevels[drawLevels.length - level].add(Box.createRigidArea(new Dimension(0,100)), 0);
		}
		else if(numGatesInLevel[level - 1] == 2) {
			drawLevels[drawLevels.length - level].remove(0);
			drawLevels[drawLevels.length - level].add(Box.createRigidArea(new Dimension(0,65)), 0);
		}
		else if(numGatesInLevel[level - 1] == 3) {
			drawLevels[drawLevels.length - level].remove(0);
			drawLevels[drawLevels.length - level].add(Box.createRigidArea(new Dimension(0,35)), 0);
		}
		else if(numGatesInLevel[level - 1] == 4) {
			drawLevels[drawLevels.length - level].remove(0);
		}
	}
	
	public int pickLevel(){
		
		//dialog box to input the level that you want to draw your gate in
		String temp = JOptionPane.showInputDialog("Pick a level 1 - 4:");
		
		//invalid (too big or too little) level typed in
		while(temp != "" && Integer.parseInt(temp)>4 || Integer.parseInt(temp)<1){
			JOptionPane.showMessageDialog(frame, "Not a valid level. Try again.");
			temp = JOptionPane.showInputDialog("Pick a level 1 - 4:");
		}
		
		//levels 4-2 can have max of 4 gates each
	    if(temp != "" && Integer.parseInt(temp) >= 2 && Integer.parseInt(temp) <= 4){
	       //already 4 gates in that level, pick a different level
	        while(numGatesInLevel[Integer.parseInt(temp)-1]>3){
	        	JOptionPane.showMessageDialog(frame, "Too many gates in that level. Try again.");
	        	temp = JOptionPane.showInputDialog("Pick a level 1 - 4:");
	        }
	        	
	        //there is room in that level for the gate to be added
	        if(numGatesInLevel[Integer.parseInt(temp)-1]<=3){
	        	userGates[numGates].level = Integer.parseInt(temp);;
	        	numGatesInLevel[userGates[numGates].level-1]++;
	        	return Integer.parseInt(temp);
	        }
	     }
		 //the last level(1) can only have 1 gate
	     else{
	    	 if(temp != "") {
		        //there is already a gate here, try again
		        while(numGatesInLevel[Integer.parseInt(temp)-1]>0){
		        	JOptionPane.showMessageDialog(frame, "Too many gates in that level. Try again.");
		        	temp = JOptionPane.showInputDialog("Pick a drawing level (a number between 1 - 4):");
		        }
		        	
		        //there is room for a gate here
		        if(numGatesInLevel[Integer.parseInt(temp)-1]==0){
		        	userGates[numGates].level = Integer.parseInt(temp);
		        	userGates[numGates].finalOutput = true;
		        	numGatesInLevel[userGates[numGates].level-1]++;
		        	return Integer.parseInt(temp);
		        }
	    	 }
	     }
	    
	    //if it makes it here then the level was never valid and therefore the
	    //gate can't be created
	    return -1;
	}
	
	public void drawConnections(LogicGate output, LogicGate input, int inputNumber, int totalInputs) {

		Point inputLocation = new Point(), outputLocation = new Point();
		//find input location
		for(int i = 0; i < drawLevels.length; i++) {
			for(int j = 0; j < drawLevels[i].getComponentCount(); j++) {
				if(drawLevels[i].getComponent(j).getName() != null && input.gateName.equals(drawLevels[i].getComponent(j).getName()))
					inputLocation = drawLevels[i].getComponent(j).getLocationOnScreen();
				if(drawLevels[i].getComponent(j).getName() != null && output.gateName.equals(drawLevels[i].getComponent(j).getName()))
					outputLocation = drawLevels[i].getComponent(j).getLocationOnScreen();
			}
		}
		
		Graphics g = frame.getGraphics();	
		
		if(totalInputs == 1){
			if(output instanceof varGate)
				g.drawString(output.gateName, (int)inputLocation.getX(), (int)inputLocation.getY()+35);
			else
				g.drawLine((int)inputLocation.getX() + 20, (int)inputLocation.getY() + 35, 
					(int)outputLocation.getX() + 95, (int)outputLocation.getY() + 35);
		}
		if(totalInputs == 2){
			if(output instanceof varGate)
				g.drawString(output.gateName, (int)inputLocation.getX(), (int)inputLocation.getY() + 20 + inputNumber * 25);
			else
				g.drawLine((int)inputLocation.getX() + 20, (int)inputLocation.getY() + 20 + inputNumber * 25, 
					(int)outputLocation.getX() + 95, (int)outputLocation.getY() + 35);
		}
		else if (totalInputs == 3){
			if(output instanceof varGate)
				g.drawString(output.gateName, (int)inputLocation.getX(), (int)inputLocation.getY() + 25 + inputNumber * 15);
			else
				g.drawLine((int)inputLocation.getX() + 20, (int)inputLocation.getY() + 20 + inputNumber * 15, 
					(int)outputLocation.getX() + 95, (int)outputLocation.getY() + 35);
		}
		else if (totalInputs == 4){
			if(output instanceof varGate)
				g.drawString(output.gateName, (int)inputLocation.getX(), (int)inputLocation.getY() + 20 + inputNumber * 10);
			else
				g.drawLine((int)inputLocation.getX() + 20, (int)inputLocation.getY() + 20 + inputNumber * 10, 
					(int)outputLocation.getX() + 95, (int)outputLocation.getY() + 35);
		}		
	}
	
	public void gateCreatorHelper(String imageName, String gateType) {
		int level = pickLevel();
		
		if (level > 0 && gates.getElementAt(numGates)!=userGates[numGates].gateName) {
			if (userGates[numGates].finalOutput)
				theMainLogic.createGate(userGates[numGates].operation, userGates[numGates].numInputs, true);
			else
				theMainLogic.createGate(userGates[numGates].operation, userGates[numGates].numInputs);
			
			theMainLogic.nodeList.get(theMainLogic.nodeList.size() - 1).gateName = userGates[numGates].gateName;
			
			gates.addElement(userGates[numGates].gateName);
			
			drawLevels[drawLevels.length - level].setVisible(false);
			centerGates(level);
			drawLevels[drawLevels.length - level].add(Box.createRigidArea(new Dimension(0,15)));
			image = new ImageIcon(imageName);
			JLabel label = new JLabel(image);
			label.setToolTipText(userGates[numGates].gateName);
			label.setName(userGates[numGates].gateName);
			label.setVisible(true);
			drawLevels[drawLevels.length - level].add(label);
			drawLevels[drawLevels.length - level].setVisible(true);
			
			//keep track of the number of each gate
			numGates++;
			switch (gateType) {
			case "AND": numAnds++; break;
			case "NAND": numNands++; break;
			case "OR": numOrs++; break;
			case "NOR": numNors++; break;
			case "XOR": numXors++; break;
			case "XNOR": numXnors++; break;
			case "NOT": numNots++; break;
			default: break;
			}
		}
	}
}
