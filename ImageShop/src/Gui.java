/**
 * 
 */

/**
 * @author duke

 *
 */
import acm.program.*;
import javafx.scene.paint.Color;

import java.awt.Font;
import java.awt.event.*; 
import javax.swing.*;

public class Gui extends Program {
	private JTextField textBox;
	private JLabel tipLabel;
	private JRadioButton eighteenBtn;
	private JRadioButton fifteenBtn;
	
	
	public void init() {	
		//yeAndne();
		//textAndButton();
		setSize(500 , 250);
		tipCalculator();
		addActionListeners();
	}
	
	public void tipCalculator() {
		JLabel subtotalLabel = new JLabel("Subtotal: $");
		add(subtotalLabel , NORTH);
		textBox = new JTextField(6);
		add(textBox , NORTH);
		// addding event listener to textbox
		textBox.addActionListener(this);
		textBox.setActionCommand("Calculate Tip!");
		
		JButton btn10 = new JButton("10%");
		// setting icon 
		btn10.setIcon(new ImageIcon("res/game.png"));
		// btn10.setBorder(BorderFactory.createLineBorder(Color.BLUE , 1));
		add(btn10, NORTH);
		
		
		JButton btn15 = new JButton("20%");
		add(btn15, NORTH);
		
		JButton calc = new JButton("Calculate Tip!");
		add(calc , NORTH);
		
		tipLabel = new JLabel("$0.00");
		tipLabel.setHorizontalAlignment(JLabel.CENTER);
		tipLabel.setFont(new Font("Consolas" , Font.BOLD , 30));
		add(tipLabel);
		
		fifteenBtn = new JRadioButton("15%");
		fifteenBtn.setSelected(true);
		add(fifteenBtn , SOUTH);
		eighteenBtn = new JRadioButton("18%");
		add(eighteenBtn , SOUTH);
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(fifteenBtn);
		btnGrp.add(eighteenBtn);
	}
	
	public void textAndButton() {
		JTextField textbox = new JTextField(10);
		JButton btn = new JButton("Enter");
		add(textbox , SOUTH);
		add(btn , SOUTH);
	}
	public void yeAndne() {
		JButton ye = new JButton("Yay");
		JButton ne = new JButton("Nay");
		add(ye , SOUTH);
		add(ne , SOUTH);
	}
	
	public void actionPerformed(ActionEvent event) {
		double ammount = Double.parseDouble(textBox.getText());
		String command = event.getActionCommand();
		
		if(command.equals("10%")) {
			
			ammount = ammount * 0.10;
			
		} else if(command.equals("20%")) {
			
			ammount = ammount * 0.20;
			
		} else if(command.equals("Calculate Tip!")) {
			
			if(fifteenBtn.isSelected()) {
				ammount = ammount * .15;
			} else {
				ammount = ammount * .18;
			}
			
		}
		tipLabel.setText("$" + ammount);
	}
}
