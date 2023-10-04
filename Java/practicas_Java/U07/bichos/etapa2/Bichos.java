package practica.bichos.etapa2;

import javax.imageio.ImageIO;
import javax.swing.JPanel; 
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.HashMap;

public class Bichos extends Canvas{

	public static final int ANCHO = 800;
	public static final int ALTO = 600;
	public int RETARDO = 10;
	//primer ajuste de retardo 300
	//segundo ajuste de retardo 500
	
	public int posX = ANCHO/2;
	public int posY = ALTO/2;
	
	public BufferedImage extra = null;
	public HashMap sprites;
	
	public int vX = 3;
	//valor base del vx 2
	
	public BufferedImage buffer;
	
	public BufferStrategy strategy;
	
	private int fps; // contador de fotogramas
	
	public Bichos() {
		JFrame ventana = new JFrame("Adrian Gonzalez");
		setBounds(0, 0, ANCHO, ALTO);
		JPanel panel = (JPanel)ventana.getContentPane();
		panel.setLayout(null);
		panel.add(this);
		panel.setBounds(0, 0, ANCHO, ALTO);
		panel.setPreferredSize(new Dimension(ANCHO, ALTO));
		buffer = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
		sprites = new HashMap();
		ventana.addWindowListener(
				new WindowAdapter() { 
					public void WindowClosing(WindowEvent e) {
							System.exit(0);
						} 
					}
		);
		
		ventana.setResizable(false);
		requestFocus();
		ventana.setVisible(true);
		ventana.setBounds(0, 0, ANCHO, ALTO);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		//panel.repaint();
		
	}
	
	/*
	@Override
	public void paint(Graphics g) {
		
		if(extra == null) {
			extra = cargaImagen("res/extra.gif");
		}
		g.setColor( getBackground() );
		g.fillRect(0, 0, getWidth(), getHeight());
		// g.drawImage(extra, 40, 40, this);
		 g.drawImage(getSprite("res/extra.gif"), posX, posY, this);
		
		g.drawImage(buffer, 0, 0, this);
	}
	*/
	public BufferedImage cargaImagen( String nombre ) {
		URL url = null;
		try {
			url = getClass().getResource(nombre);
			return ImageIO.read( url );
		}catch( Exception e ) {
			System.out.println("Error al cargar la imagen" + nombre + " de " + url);
			System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage() );
			System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite( String nombre ) {
		BufferedImage img = (BufferedImage)sprites.get(nombre);
		if(img == null) {
			img = cargaImagen(nombre);
			sprites.put(nombre, img);
		}
		return img;
	}
	
	public void actualizaMundo() {
		posX = (int)(Math.random() * ANCHO);
		posY = (int)(Math.random() * ALTO);
		posX += vX;
		if( posX < 0 || posX > ANCHO-10 ) vX = -vX;
		
	}
	
	public void juego() {
		while( isVisible() ) {
			long tiempo = System.currentTimeMillis();
			actualizaMundo();
			pintaMundo();
			tiempo = System.currentTimeMillis() - tiempo;
			if(tiempo != 0) fps = (int)(1000L/tiempo); else fps = -1;
			//paint( getGraphics() );
			//Retardo artificial
			try {
				Thread.sleep( RETARDO );
			}catch( InterruptedException e ) {}
		}
	}
	
	public void pintaMundo() {
		Graphics g = strategy.getDrawGraphics();
		g.setColor( Color.black );
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage( getSprite("res/extra.gif"), posX, posY, this );
		g.setColor(Color.green);
		if(fps < 0) { 
			g.drawString("---fps", 10, 10); 
		}else if(fps >= 0) { 
			g.drawString(fps + "fps", 10, 10);
		}
		strategy.show();
		
	}
	
	public static void main(String[] args) {
		Bichos b = new Bichos();
		b.juego();
	}
	
}

