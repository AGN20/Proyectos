package examen;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ExamenU07 {

	private JFrame frmAdrianGonzalezNuez;
	JPanel panel;
	JPanel panel_1;
	private JTextField textFieldA;
	private JTextField textFieldF;
	private JTextField textFieldP;
	private int radio;
	private int ex;
	private int ey;
	private int aciertos = 0;
	private int fallos = 0;
	private int puntos = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamenU07 window = new ExamenU07();
					window.frmAdrianGonzalezNuez.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExamenU07() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdrianGonzalezNuez = new JFrame();
		frmAdrianGonzalezNuez.setAlwaysOnTop(true);
		frmAdrianGonzalezNuez.setTitle("Adrian Gonzalez Nu\u00F1ez");
		frmAdrianGonzalezNuez.setBounds(0, 0, 400, 450); // tamaño estandar de 400*450
		frmAdrianGonzalezNuez.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdrianGonzalezNuez.setResizable(false);
		frmAdrianGonzalezNuez.setVisible(true);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(400, 50);
		frmAdrianGonzalezNuez.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Acierto");
		lblNewLabel.setBounds(10, 11, 48, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fallos");
		lblNewLabel_1.setBounds(98, 11, 48, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Puntuaci\u00F3n");
		lblNewLabel_2.setBounds(181, 11, 54, 14);
		panel.add(lblNewLabel_2);
		
		textFieldA = new JTextField();
		textFieldA.setEditable(false);
		textFieldA.setBounds(52, 11, 36, 18);
		panel.add(textFieldA);
		textFieldA.setColumns(10);
		
		textFieldF = new JTextField();
		textFieldF.setEditable(false);
		textFieldF.setBounds(245, 10, 36, 16);
		panel.add(textFieldF);
		textFieldF.setColumns(10);
		
		textFieldP = new JTextField();
		textFieldP.setEditable(false);
		textFieldP.setBounds(135, 11, 36, 17);
		panel.add(textFieldP);
		textFieldP.setColumns(10);
		
		JButton btnNuevoIntento = new JButton("Nuevo Intento");
		btnNuevoIntento.setBounds(289, 7, 101, 23);
		btnNuevoIntento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ponemos a o los aciertos, fallos y puntuacion.
				textFieldA.setText("0");
				textFieldF.setText("0");
				textFieldP.setText("0");
				
				//valores iniciales del circulo
				radio = 200;
				ex = (int)(Math.random() * panel_1.getWidth());
				ey = (int)(Math.random() * panel_1.getHeight());
				
				if(ex + 200 > 400) {ex -= 200;}
				if(ey + 200 > 400) {ey -= 200;}
				
				panel_1.repaint();
				
			}
			
		}
		);
		panel.add(btnNuevoIntento);
		
		panel_1 = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				//this.setBackground(Color.white);
				g.setColor(Color.blue);
				g.drawOval(ex, ey, radio*2, radio*2);
				g.fillOval(ex, ey, radio*2, radio*2);
				g.setColor(Color.black);
				g.drawOval(ex, ey, radio*2, radio*2);
			}
		};
		panel_1.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int cx = ex - 200;
				int cy = ey - 200;
				int distancia = (int)(Math.sqrt(Math.pow((x-cx), 2)+Math.pow((y-cy), 2)));
				
				System.out.println(e.getX() + " x " + e.getY() + " y " + distancia + "" + radio*2);
				
				if(distancia <= radio*2) { 
					aciertos += 1;
					radio -= 10;
					ex = (int)(Math.random() * panel_1.getWidth());
					ey = (int)(Math.random() * panel_1.getHeight());
					
					if(ex + 200 > 400) {ex -= 200;}
					if(ey + 200 > 400) {ey -= 200;}
					panel_1.repaint();
				}else{
					fallos += 1;
				}
				
				puntos = (100*aciertos)/(aciertos+fallos);
				
				textFieldA.setText("" + aciertos);
				textFieldF.setText("" + fallos);
				textFieldP.setText("" + puntos);

			}
		});
		panel_1.setMinimumSize(new Dimension(400, 400));
		frmAdrianGonzalezNuez.getContentPane().add(panel_1);
	}
	
}