package practica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;

public class U7E03 extends JPanel implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private String display = "";
	private String operaciones;
	private int contador = 0;
	private int savearray = 0;
	private int contadorpuntos = 0;
	private int contaopera = 0;
	double numeros[] = new double[300];
	double resultado = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					U7E03 window = new U7E03();
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
	public U7E03() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 390, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(new Color(143, 188, 143));
		textField.setBounds(10, 6, 354, 47);
		textField.setColumns(10);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("7");
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 64, 62, 43);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(82, 64, 62, 43);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(textField);

		JButton btnNewButton_1_1 = new JButton("9");
		btnNewButton_1_1.addActionListener(this);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(154, 64, 62, 43);
		frame.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_16 = new JButton("=");
		btnNewButton_1_16.addActionListener(this);
		btnNewButton_1_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_16.setForeground(Color.WHITE);
		btnNewButton_1_16.setBackground(Color.BLUE);
		btnNewButton_1_16.setBounds(154, 227, 62, 43);
		frame.getContentPane().add(btnNewButton_1_16);

		JButton btnNewButton_1_2 = new JButton("DEL");
		btnNewButton_1_2.addActionListener(this);
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton_1_2.setBackground(Color.ORANGE);
		btnNewButton_1_2.setBounds(232, 64, 62, 43);
		frame.getContentPane().add(btnNewButton_1_2);

		JButton btnNewButton_1_3 = new JButton("CL");
		btnNewButton_1_3.addActionListener(this);
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton_1_3.setBackground(Color.ORANGE);
		btnNewButton_1_3.setBounds(302, 64, 62, 43);
		frame.getContentPane().add(btnNewButton_1_3);

		JButton btnNewButton_1_4 = new JButton("4");
		btnNewButton_1_4.addActionListener(this);
		btnNewButton_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_4.setBounds(10, 118, 62, 43);
		frame.getContentPane().add(btnNewButton_1_4);

		JButton btnNewButton_1_5 = new JButton("5");
		btnNewButton_1_5.addActionListener(this);
		btnNewButton_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_5.setBounds(82, 118, 62, 43);
		frame.getContentPane().add(btnNewButton_1_5);

		JButton btnNewButton_1_6 = new JButton("6");
		btnNewButton_1_6.addActionListener(this);
		btnNewButton_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_6.setBounds(154, 118, 62, 43);
		frame.getContentPane().add(btnNewButton_1_6);

		JButton btnNewButton_1_7 = new JButton("+");
		btnNewButton_1_7.addActionListener(this);
		btnNewButton_1_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_7.setForeground(Color.WHITE);
		btnNewButton_1_7.setBackground(Color.BLUE);
		btnNewButton_1_7.setBounds(232, 118, 62, 43);
		frame.getContentPane().add(btnNewButton_1_7);

		JButton btnNewButton_1_8 = new JButton("-");
		btnNewButton_1_8.addActionListener(this);
		btnNewButton_1_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_8.setForeground(Color.WHITE);
		btnNewButton_1_8.setBackground(Color.BLUE);
		btnNewButton_1_8.setBounds(302, 118, 62, 43);
		frame.getContentPane().add(btnNewButton_1_8);

		JButton btnNewButton_1_9 = new JButton("1");
		btnNewButton_1_9.addActionListener(this);
		btnNewButton_1_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_9.setBounds(10, 173, 62, 43);
		frame.getContentPane().add(btnNewButton_1_9);

		JButton btnNewButton_1_10 = new JButton("2");
		btnNewButton_1_10.addActionListener(this);
		btnNewButton_1_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_10.setBounds(82, 174, 62, 43);
		frame.getContentPane().add(btnNewButton_1_10);

		JButton btnNewButton_1_11 = new JButton("3");
		btnNewButton_1_11.addActionListener(this);
		btnNewButton_1_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_11.setBounds(154, 174, 62, 43);
		frame.getContentPane().add(btnNewButton_1_11);

		JButton btnNewButton_1_12 = new JButton("*");
		btnNewButton_1_12.addActionListener(this);
		btnNewButton_1_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_12.setForeground(Color.WHITE);
		btnNewButton_1_12.setBackground(Color.BLUE);
		btnNewButton_1_12.setBounds(233, 174, 62, 43);
		frame.getContentPane().add(btnNewButton_1_12);

		JButton btnNewButton_1_13 = new JButton("/");
		btnNewButton_1_13.addActionListener(this);
		btnNewButton_1_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_13.setForeground(Color.WHITE);
		btnNewButton_1_13.setBackground(Color.BLUE);
		btnNewButton_1_13.setBounds(302, 174, 62, 43);
		frame.getContentPane().add(btnNewButton_1_13);

		JButton btnNewButton_1_14 = new JButton("0");
		btnNewButton_1_14.addActionListener(this);
		btnNewButton_1_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_14.setBounds(10, 227, 62, 43);
		frame.getContentPane().add(btnNewButton_1_14);

		JButton btnNewButton_1_15 = new JButton(".");
		btnNewButton_1_15.addActionListener(this);
		btnNewButton_1_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_15.setBounds(82, 227, 62, 43);
		frame.getContentPane().add(btnNewButton_1_15);

		JButton btnNewButton_1_17 = new JButton("^");
		btnNewButton_1_17.addActionListener(this);
		btnNewButton_1_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_17.setForeground(Color.WHITE);
		btnNewButton_1_17.setBackground(Color.BLUE);
		btnNewButton_1_17.setBounds(234, 227, 62, 43);
		frame.getContentPane().add(btnNewButton_1_17);

		JButton btnNewButton_1_18 = new JButton("R");
		btnNewButton_1_18.addActionListener(this);
		btnNewButton_1_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_18.setForeground(Color.WHITE);
		btnNewButton_1_18.setBackground(Color.BLUE);
		btnNewButton_1_18.setBounds(302, 227, 62, 43);
		frame.getContentPane().add(btnNewButton_1_18);
	}

	//Funcion de los botones
	
	public void actionPerformed(ActionEvent e) {

		String op = (String) e.getActionCommand();
		textField.setText("");

		//Numeros
		
		if (op.equals("1")) {

			contaopera = 0;
			if (contador == 0) {
				display = "1";
				contador++;
			} else {
				display = display + "1";
			}
			textField.setText(display);

		}

		else if (op.equals("2")) {
			contaopera = 0;
			if (contador == 0) {
				display = "2";
				contador++;
			} else {
				display = display + "2";
			}
			textField.setText(display);
		}

		else if (op.equals("3")) {
			contaopera = 0;
			if (contador == 0) {
				display = "3";
				contador++;
			} else {
				display = display + "3";
			}
			textField.setText(display);
		}

		else if (op.equals("4")) {
			contaopera = 0;
			if (contador == 0) {
				display = "4";

				contador++;
			} else {
				display = display + "4";
			}
			textField.setText(display);
		}

		else if (op.equals("5")) {
			contaopera = 0;
			if (contador == 0) {
				display = "5";
				contador++;
			} else {
				display = display + "5";
			}
			textField.setText(display);
		}

		else if (op.equals("6")) {
			contaopera = 0;
			if (contador == 0) {
				display = "6";
				contador++;
			} else {
				display = display + "6";
			}
			textField.setText(display);

		}

		else if (op.equals("7")) {
			contaopera = 0;
			if (contador == 0) {
				display = "7";
				contador++;
			} else {
				display = display + "7";
			}
			textField.setText(display);
		}

		else if (op.equals("8")) {
			contaopera = 0;
			if (contador == 0) {
				display = "8";
				contador++;
			} else {
				display = display + "8";
			}
			textField.setText(display);
		}

		else if (op.equals("9")) {
			contaopera = 0;
			if (contador == 0) {
				display = "9";
				contador++;
			} else {
				display = display + "9";
			}
			textField.setText(display);
		}

		else if (op.equals("0")) {

			contaopera = 0;

			if (contador == 0) {
				display = "0";
				contador++;
			} else {
				display = display + "0";
			}

			textField.setText(display);

		}

		else if (op.equals(".")) {

			if (contadorpuntos == 0) {
				if (contador == 0) {
					display = "0.";
					contadorpuntos++;
					contador++;
				} else {
					display = display + ".";
					contadorpuntos++;
				}

			}

			textField.setText(display);
		}

		// Operaciones

		else if (op.equals("-")) {

			if (!display.equals("")) {
				if (contaopera == 0) {
					numeros[savearray] = Double.parseDouble(display);
					savearray++;
					if (operaciones == null) {
						operaciones = "-";
						contaopera++;
						display = "";
					} else {
						operaciones = operaciones + "-";
						contaopera++;
						display = "";
					}
				}
				textField.setText("-");
				operaciones = textField.getText();
			}
		}

		else if (op.equals("+")) {
			if (!display.equals("")) {
				if (contaopera == 0) {
					numeros[savearray] = Double.parseDouble(display);
					savearray++;
					if (operaciones == null) {
						operaciones = "+";
						contaopera++;
						display = "";
					} else {
						operaciones = operaciones + "+";
						contaopera++;
						display = "";
					}
				}
				textField.setText("+");
				operaciones = textField.getText();
			}
		}

		else if (op.equals("*")) {
			if (!display.equals("")) {
				if (contaopera == 0) {

					numeros[savearray] = Double.parseDouble(display);
					savearray++;
					if (operaciones == null) {
						operaciones = "*";
						contaopera++;
						display = "";
					} else {
						operaciones = operaciones + "*";
						contaopera++;
						display = "";
					}

				}
				textField.setText("*");
				operaciones = textField.getText();
			}
		}

		else if (op.equals("^")) {
			if (!display.equals("")) {
				if (contaopera == 0) {

					numeros[savearray] = Double.parseDouble(display);
					savearray++;
					if (operaciones == null) {
						operaciones = "^";
						contaopera++;
						display = "";
					} else {
						operaciones = operaciones + "^";
						contaopera++;
						display = "";
					}

				}
				textField.setText("^");
				operaciones = textField.getText();
			}
		}

		else if (op.equals("/")) {
			if (!display.equals("")) {
				if (contaopera == 0) {

					numeros[savearray] = Double.parseDouble(display);
					savearray++;
					if (operaciones == null) {
						operaciones = "/";
						contaopera++;
						display = "";
					} else {
						operaciones = operaciones + "/";
						contaopera++;
						display = "";
					}
				}
				textField.setText("/");
				operaciones = textField.getText();
			}
		}

		else if (op.equals("R")) {
			double parse = Double.parseDouble(display);
			display = "" + Math.sqrt(parse);

			textField.setText(display);

		}

		else if (op.equals("DEL")) {

			if (display.length() >= 1) {
				display = display.substring(0, display.length() - 1);
				textField.setText(display);
			}
		}

		else if (op.equals("CL")) {
			display = "";
			textField.setText(display);
			for (int z = 0; z < 300; z++) {
				numeros[z] = 0.00;
			}

		}

		// Cuando apretamos = comprueba el signo +-/*^R y ejecuta la operacion del numeros[0] con el numeros[1]
		
		else if (op.equals("=")) {
			if (!display.equals("")) {
				numeros[savearray] = Double.parseDouble(display);
				resultado = numeros[0];

				for (int i = 1; i < 300; i++) {
					if (i <= operaciones.length()) {
						if (operaciones.substring((i - 1), i).equals("+")) {

							resultado += numeros[i];
							

						}

						if (operaciones.substring((i - 1), i).equals("-")) {

							resultado -= numeros[i];
							

						}

						if (operaciones.substring((i - 1), i).equals("*")) {

							resultado *= numeros[i];
							

						}

						if (operaciones.substring((i - 1), i).equals("/")) {

							resultado /= numeros[i];
							

						}

						if (operaciones.substring((i - 1), i).equals("^")) {

							resultado = Math.pow(resultado, numeros[i]);
							

						}
						
					}

				}
				textField.setText("" + resultado);
				display = "" + resultado;
				operaciones = "";
				contador = 0;
				savearray = 0;
				contadorpuntos = 0;
				contaopera = 0;
				
				
			}
		}

	}

}
