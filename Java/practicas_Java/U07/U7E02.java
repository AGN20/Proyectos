package practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class U7E02 extends JPanel implements ActionListener{

	private JFrame frmC;
	private JTextField textField;
	JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					U7E02 window = new U7E02();
					window.frmC.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public U7E02() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmC = new JFrame();
		frmC.setTitle("Conversor Adrian Gonzalez");
		frmC.setBounds(100, 100, 450, 300);
		frmC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmC.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Importe en pesetas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmC.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		frmC.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pulse para convertir a euros");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frmC.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Convertir");
		btnNewButton.addActionListener(this);
		frmC.getContentPane().add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("\u20AC");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		frmC.getContentPane().add(lblNewLabel_2);
	}	
		
	public void actionPerformed(ActionEvent e) {
			
		String cantidad = textField.getText();
			
		if (cantidad.equals("")) {
			lblNewLabel_2.setText("El importe esta vacio");
			textField.setBackground(Color.RED);
			lblNewLabel_2.setForeground(Color.RED);
		}else {
			
			boolean esNum = cantidad.matches("-?\\d+");
			if (esNum == true) {
				double num = Double.parseDouble(cantidad);
				double resultado = (num / 166.386);
				lblNewLabel_2.setText( String.format("%1.2f €", resultado));
				textField.setBackground(Color.WHITE);
				lblNewLabel_2.setForeground(Color.BLACK);
			}else {
				lblNewLabel_2.setText("El importe no es un numero");
				textField.setBackground(Color.RED);
				lblNewLabel_2.setForeground(Color.RED);
			}
			
		}
			
	}

	
}
