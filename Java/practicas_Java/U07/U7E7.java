package practica;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferStrategy;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class U7E7 {

	private JFrame frame;
	private JTextField textField;
	private JPanel panel;
	private BufferStrategy bs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					U7E7 window = new U7E7();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public U7E7() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 536, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JRadioButton rdbtnEcuacinRecursivaXnr = new JRadioButton("Ecuaci\u00F3n Recursiva: xn=r Xn(1-Xn)");
		
		JRadioButton rdbtnIfs = new JRadioButton("IFS");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblXo = new JLabel("Xo");
		
		JButton btnDibuja = new JButton("Dibuja");
		
		panel = new JPanel();
		panel.paint(grafico());
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnEcuacinRecursivaXnr)
								.addComponent(rdbtnIfs))
							.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblXo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnDibuja, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnEcuacinRecursivaXnr)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnIfs)
						.addComponent(btnDibuja))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public Graphics2D grafico () {
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		double num = 0.1;
		int cont = 1;
		
		double r;
		
		panel.setComponentOrientation(null);
		
		g.setColor(Color.black);
		
		g.drawLine(panel.getWidth()/10 *1, panel.getHeight()/12 * 1, panel.getWidth()/10 *1, panel.getHeight()/12 * 11);
		g.drawLine(panel.getWidth()/10 *1, panel.getHeight()/12 * 1, panel.getWidth()/10 * 9, panel.getHeight()/12 * 1);
		
		for( int i=0; i< panel.getHeight(); i++) {
			for( int j=0; j< panel.getWidth(); i++) {
				
				if(panel.getHeight() /12 * i > panel.getHeight()) g.drawString("" + num, panel.getWidth() /10 * 1, panel.getHeight() /12 * i);
				if(panel.getWidth() /10 * j > panel.getHeight()) g.drawString("" + cont, panel.getWidth() /10 * j, panel.getHeight() /12 * 1);
				
				num += 0.1;
				cont++;
			}
			
		}
		
		double x0 = Double.parseDouble(textField.getText());
		
		for(int i=0; i<400; i++) {
			r = i/100;
			if( x0 < 0 && x0 > 1) {
				g.setBackground(Color.red);
				break;
			}
			double x = x0;
			for(int j=0; j<1000; j++) { x = r * x * (1 - x); }
			for(int j=0; j<100; j++) { 
				x = r * x * (1 - x);
				g.drawLine((int)x, i, (int)x, i);
			}
			
		}
		
		return g;
		
	}
	
}
