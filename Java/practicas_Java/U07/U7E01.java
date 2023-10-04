package practica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class U7E01 extends JPanel implements ActionListener{

	static JFrame f;
	
	public static void main(String[] args) {
		
		/* Interface grafica */
		f = new JFrame("Conversor Adrian Gonzalez");
		U7E01 panel = new U7E01();
		f.setContentPane(panel);
		f.setSize(400,200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	JTextField tfImporte;
	JLabel lb3;
	
	public U7E01() {
		
		//Creamos los textos
		JLabel lb1 = new JLabel("Importe en Euros:");
		lb1.setHorizontalAlignment(lb1.CENTER);;
		JLabel lb2 = new JLabel("Pulse para obtener el importe en pesetas");
		lb2.setHorizontalAlignment(lb2.CENTER);;
		
		//Resultado de la operacion
		lb3 = new JLabel("0 pst");
		lb3.setHorizontalAlignment(lb3.CENTER);;
		
		//Creamos el cuadro de texto que el usuario podra usar
		tfImporte = new JTextField();
		
		//Creamos el boton
		JButton b1 = new JButton("Convertir");
		b1.addActionListener(this);
		
		//Añadimos todo al JField
		setLayout( new GridLayout(0, 1, 1, 10) );
		add(lb1);
		add(tfImporte);
		add(b1);
		add(lb2);
		add(lb3);
		
		
		
		
	}
	
	
	//Accion del botton, calcula las pesetas pasandole los euros
	public void actionPerformed(ActionEvent evt) {
		
		double euros;
		
		try {
			String eurStr = tfImporte.getText();
			euros = Double.parseDouble(eurStr);
		}catch( NumberFormatException e ){
			return;
		}
		double resultado = euros * 166.386;
		lb3.setText( resultado + " pst");
		
	}
	
}