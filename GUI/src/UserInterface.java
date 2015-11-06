import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class UserInterface implements ActionListener, ItemListener {
	
	JMenuItem newFile;
	JMenuItem open;
	JMenuItem save;
	
	JMenuItem input1;
	JMenuItem input2;
	JMenuItem input3;
	JMenuItem zeroInput;
	JMenuItem oneInput;
	
	JMenuItem and2;
	JMenuItem and3;
	JMenuItem and4;
	
	JMenuItem or2;
	JMenuItem or3;
	JMenuItem or4;
	
	JMenuItem nand2;
	JMenuItem nand3;
	JMenuItem nand4;
	
	JMenuItem nor2;
	JMenuItem nor3;
	JMenuItem nor4;
	
	JMenuItem xor2;
	JMenuItem xnor2;
	JMenuItem not;
	
	JMenuItem manual;
	
	
	void CreateUI() {
		
		JFrame frame = new JFrame("Digital Circuit Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080, 720);
		
		JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(154, 165, 127));
        menuBar.setPreferredSize(new Dimension(1080, 40));
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        fileMenu.add(newFile);
        open = new JMenuItem("Open...");
        fileMenu.add(open);
        open.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        fileMenu.add(save);
        
        JMenu inputOutputMenu = new JMenu("Inputs");
        menuBar.add(inputOutputMenu);
        oneInput = new JMenuItem("1");
        oneInput.addActionListener(this);
        inputOutputMenu.add(oneInput);
        zeroInput = new JMenuItem("0");
        zeroInput.addActionListener(this);
        inputOutputMenu.add(zeroInput);
        JMenu addInputMenu = new JMenu("Add Variables");
        inputOutputMenu.add(addInputMenu);
        input1 = new JMenuItem("x");
        input1.addActionListener(this);
        addInputMenu.add(input1);
        input2 = new JMenuItem("y");
        addInputMenu.add(input2);
        input2.addActionListener(this);
        input3 = new JMenuItem("z");
        input3.addActionListener(this);
        addInputMenu.add(input3);
        
        JMenu andMenu = new JMenu("AND Gates");
        menuBar.add(andMenu);
        and2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputAND.png"));
        and2.addActionListener(this);
        andMenu.add(and2);
        and3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputAND.png"));
        andMenu.add(and3);
        and3.addActionListener(this);
        and4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputAND.png"));
        and4.addActionListener(this);
        andMenu.add(and4);
        
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
        
        JMenu xorXnorNotMenu = new JMenu("XOR/XNOR/NOT Gates");
        menuBar.add(xorXnorNotMenu);
        xor2 = new JMenuItem("XOR", new ImageIcon("images/2inputXOR.png"));
        xor2.addActionListener(this);
        xorXnorNotMenu.add(xor2);
        xnor2 = new JMenuItem("XNOR", new ImageIcon("images/2inputXNOR.png"));
        xnor2.addActionListener(this);
        xorXnorNotMenu.add(xnor2);
        not = new JMenuItem("NOT", new ImageIcon("images/NOT.png"));
        not.addActionListener(this);
        xorXnorNotMenu.add(not);
        
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        manual = new JMenuItem("User Manual");
        manual.addActionListener(this);
        helpMenu.add(manual);
        
        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(200, 180));
		
	    frame.setJMenuBar(menuBar);
	    frame.getContentPane().add(yellowLabel);
	    frame.setVisible(true);
	}
	
	public static void main( String[] args ) {
		UserInterface ui = new UserInterface();
		ui.CreateUI();
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Clicked");
		Object source = arg0.getSource();
		if(source.equals(and2))
			System.out.println("Clicked and2");
		else if(source.equals(and3))
			System.out.println("Clicked and3");
		
	}

    
}
