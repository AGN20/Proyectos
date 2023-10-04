package practica.bichos.etapa3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList; //Para la lista de personajes

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Bichos extends Canvas implements Escenario, KeyListener{

	private BufferStrategy strategy;
	private long tiempo;
	private int fps;
	
	private SpriteCache sc;
	private ArrayList personajes;
	private Jugador jugador;
	
	public void iniciaMundo() {
		personajes = new ArrayList();
		for(int i = 0; i < 10; i++) {
			Alien a = new Alien(this);
			a.setX( (int)(Math.random() * Escenario.ANCHO) );
			a.setY( i * 20 );
			int v = (int)(Math.random() * 20 - 10);
			a.setVx( (v == 0)? 1:v );
			personajes.add(a);
		}
		jugador = new Jugador(this);
		jugador.setX( Escenario.ANCHO / 2 );
		jugador.setY( Escenario.ALTO - 2 * jugador.getAlto() );
		jugador.setVx(5);
	}
	
	public void actualizaMundo() {
		for(int i = 0; i < personajes.size(); i++) {
			Personaje p = (Personaje)personajes.get(i);
			if( p.estaMarcadoParaBorrar() ) {
				personajes.remove(i);
			}
			p.actua();
			i++;
		}
		jugador.actua();
	}
	
	public void pintaMundo() {
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < personajes.size(); i++) {
			Personaje p = (Personaje)personajes.get(i);
			p.paint(g);
		}
		jugador.paint(g);
		g.setColor(Color.green);
		pintaEstado(g);
		strategy.show();
	}
	
	public SpriteCache getSpriteCache() {return sc;}
	
	public void juego() {
		fps = 1000;
		iniciaMundo();
		while( isVisible() ) {
			long tiempo = System.currentTimeMillis();
			pintaMundo();
			actualizaMundo();
			compruebaColisiones();
			tiempo = System.currentTimeMillis() - tiempo;
			if( tiempo > 0 ) fps = (int)(1000/tiempo); else fps = -1;
			try {
				Thread.sleep(RETARDO);
			}catch(InterruptedException e) {}
		}
	}
	
	public Bichos() {
		sc = new SpriteCache();
		JFrame ventana = new JFrame("Bichos");
		JPanel panel = (JPanel)ventana.getContentPane();
		setBounds(0, 0, Escenario.ANCHO, Escenario.ALTO);
		panel.setPreferredSize( new Dimension(Escenario.ANCHO, Escenario.ALTO) );
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0, 0, Escenario.ANCHO, Escenario.ALTO);
		ventana.setVisible(true);
		ventana.addWindowListener( new WindowAdapter() {
										public void windowClosing(WindowEvent e) {
											System.exit(0);
										}
									} 
								);
		ventana.setResizable(false);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();
		addKeyListener(this);
	}
	
	public void keyPressed( KeyEvent e ) { jugador.keyPressed(e); }
	public void keyReleased(KeyEvent e) { jugador.keyReleased(e); }
	public void keyTyped(KeyEvent e) {}
	
	public static void main(String[] args) {
		Bichos b = new Bichos();
		b.juego();
	}
	
	public void insertarPersonaje( Personaje p ) { personajes.add(p); }
	
	public void compruebaColisiones() {
		Rectangle jugadorFrontras = jugador.getFronteras();
		for(int i = 0; i < personajes.size(); i++) {
			Personaje p1 = (Personaje)personajes.get(i);
			Rectangle r1 = p1.getFronteras();
			if( r1.intersects(jugadorFrontras) ) {
				jugador.colision(p1);
				p1.colision(jugador);
			}
			for(int j = 0; j < personajes.size(); j++) {
				Personaje p2 = (Personaje)personajes.get(j);
				Rectangle r2 = p2.getFronteras();
				if( r1.intersects(r2) ) {
					p1.colision(p2);
					p2.colision(p1);
				}
			}
		}
	}
	
	public void pintaVida(Graphics2D g) {
		g.setPaint(Color.red);
		g.fillRect(280, Escenario.LIMITE, Jugador.MAX_VIDA, 30);
		g.setPaint(Color.blue);
		g.fillRect(280 + Jugador.MAX_VIDA - jugador.getVida(), Escenario.LIMITE, jugador.getVida(), 30);
		g.setFont( new Font("Arial", Font.BOLD, 20) );
		g.setPaint(Color.green);
		g.drawString("Vida", 170, Escenario.LIMITE + 20);
	}
	
	public void pintaFps( Graphics2D g ) {
		g.setFont( new Font("Arial", Font.BOLD, 12) );
		g.setColor(Color.white);
		if( fps > 0 )
			g.drawString( String.valueOf(fps) + " fps", Escenario.ANCHO - 50, Escenario.LIMITE);
		else
			g.drawString("--- fps", Escenario.ANCHO - 50, Escenario.LIMITE);
	}
	
	public void pintaPuntos(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setPaint(Color.green);
		g.drawString("Puntos", 20, Escenario.LIMITE + 20);
		g.setColor(Color.red);
		g.drawString(jugador.getPuntos() + "", 100, Escenario.LIMITE + 20);
	}
	
	public void pintaArmas (Graphics2D g) {
		int xBase = 280 + Jugador.MAX_VIDA + 10;
		for(int i = 0; i < jugador.getReservaBombas(); i++) {
			BufferedImage bomba = sc.getSprite("bombaSI.gif");
			g.drawImage(bomba, xBase + i * bomba.getWidth(), Escenario.LIMITE, this);
		}
	}
	
	public void pintaEstado(Graphics2D g) {
		pintaPuntos(g);
		pintaVida(g);
		pintaArmas(g);
		pintaFps(g);
	}
	
} //fin clase Bichos
