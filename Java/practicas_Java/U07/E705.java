package practica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JLabel;

import practica.bichos.etapa3.Personaje;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Transparency;
import java.awt.CardLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class E705 extends JPanel {

	private JFrame frame;
	protected JViewport vPort;
	private JScrollPane jsp;
	private File f;
	JPanel drawP = new JPanel();
	private JLabel lbImg;
	private BufferStrategy strategy;
	public BufferedImage bi = null;
	DrawPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E705 window = new E705();
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
	public E705() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Adrian Gonzalez");
		frame.setBounds(100, 100, 726, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		DrawPanel panel = new DrawPanel();
		panel.setBounds(24, 326, 315, 166);
		frame.getContentPane().add(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 710, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 516, Short.MAX_VALUE));
		panel.setLayout(gl_panel);

		panel_1 = new DrawPanel();
		panel_1.b = bi;
		panel_1.setBounds(369, 326, 316, 164);
		frame.getContentPane().add(panel_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 332, Short.MAX_VALUE)
				.addGap(0, 710, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGap(0, 190, Short.MAX_VALUE)
				.addGap(0, 516, Short.MAX_VALUE));
		panel_1.setLayout(gl_panel_1);

		jsp = new JScrollPane();
		jsp.setBounds(24, 62, 315, 238);
		frame.getContentPane().add(jsp);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(367, 63, 315, 238);
		frame.getContentPane().add(scrollPane_1);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setBounds(222, 11, 91, 36);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Ecualizar");
		btnNewButton_1.setBounds(122, 11, 90, 35);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Abrir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarImagen();
			}
		});
		btnNewButton_2.setBounds(24, 11, 89, 35);
		frame.getContentPane().add(btnNewButton_2);
	}

	// Buscamos imagen y la pintamos
	public void cargarImagen() {

		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JGP, PNG & GIF", "jpg", "png", "gif");
		jfc.setFileFilter(filtrado);

		int respuesta = jfc.showOpenDialog(jfc);

		String ruta = null;
		if (respuesta == JFileChooser.APPROVE_OPTION) {
			ruta = jfc.getSelectedFile().getPath();

			File f = jfc.getSelectedFile();

			try {
				bi = ImageIO.read(f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bi = filtrar(bi);

			lbImg = new JLabel();
			lbImg.setBounds(10, 63, 289, 163);

			ImageIcon ii = new ImageIcon(bi.getScaledInstance(lbImg.getWidth(), lbImg.getHeight(), Image.SCALE_SMOOTH));
			jsp.setViewportView(new JLabel(ii));

		}
	}

	// Comprobar imagen escala de grises, si no esta en escala de grises lo pasa
	public BufferedImage filtrar(BufferedImage bi) {
		BufferedImage biDestino = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration().createCompatibleImage(bi.getWidth(), bi.getHeight(), Transparency.OPAQUE);

		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				Color c1 = new Color(bi.getRGB(x, y));
				int med = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;
				if (med != c1.getRed() || med != c1.getGreen() || med != c1.getBlue()) {
					biDestino.setRGB(x, y, new Color(med, med, med).getRGB());
				}
			}
		}

		return biDestino;

	}

	public class DrawPanel extends JPanel {
		private BufferedImage b;
		private int[] h = new int[256];
		private int[] ha = new int[256];

		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.white);
			g.drawRect(0, 0, getWidth(), getHeight());
			if (b == null || h == null || ha == null)
				return;

			for (int i = 0; i < 256; i++) {
				g.setColor(Color.red);
				g.drawLine(0, 0, getWidth(), h[i]);

				g.setColor(Color.black);
				g.drawRect(0, 0, getWidth(), ha[i]);
			}

		}

		private int calcularMedia(Color color) {
			int mediaColor;
			mediaColor = (int) ((color.getRed() + color.getGreen() + color.getBlue()) / 3);
			return mediaColor;
		}

		// Calcular histograma
		private void histograma() {
			Color colorAux;

			for (int i = 0; i < b.getWidth(); i++) {
				for (int j = 0; j < b.getHeight(); j++) {
					colorAux = new Color(b.getRGB(i, j));
					h[calcularMedia(colorAux)] += 1;
				}
			}

		}

		// Calcular histograma acumulativo
		private void histogramaAcu() {

			int[] count1 = new int[256];
			for (int i = 0; i < 256; i++) {
				count1[i] = h[i] / (b.getWidth() * b.getHeight());
			}

			for (int i = 0; i < b.getWidth(); i++) {
				for (int j = 0; j < b.getHeight(); j++) {
					ha[i] += count1[j];
				}
			}

		}

	}

	private BufferedImage ecualizar(BufferedImage img) {
		BufferedImage imgNew;
		int[] histoAcu = panel_1.h;
		
		for (int i = 0; i < img.getWidth(); i++) {
			histoAcu[i] = 255 * histoAcu[i] / histoAcu[255];
		}

	}

}
