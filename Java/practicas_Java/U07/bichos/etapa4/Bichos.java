package practica.bichos.etapa4;

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
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.Point;
import java.awt.Cursor;

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
	
	private boolean acaba = false;
	
	private BufferedImage fondo;
	private int t;
	
	private BufferedImage loseta;
	private int fondoY;
	
	private SonidoCache sonic;
	
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
		sonic.bucleSonido("musica.wav");
		loseta = sc.getSprite("fondo.gif");
		fondo = sc.creaCompatible(Escenario.ANCHO,
				Escenario.ALTO + loseta.getHeight(),
				Transparency.OPAQUE
		);
		Graphics2D g = (Graphics2D) fondo.getGraphics();
		g.setPaint(new TexturePaint(loseta, new Rectangle(0,0,loseta.getWidth(),loseta.getHeight())));
		g.fillRect(0, 0, fondo.getWidth(), fondo.getHeight());
		fondoY = loseta.getHeight();
	}
	
	public void actualizaMundo() {
		int i = 0;
		while(i<personajes.size()) { 
			Personaje p = (Personaje)personajes.get(i);
			if(p.estaMarcadoParaBorrar()) {
				personajes.remove(i);
			} else {
				p.actua();
				i++;
			}
		}
		jugador.actua();
	}
	
	public void pintaMundo() {
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		g.drawImage(fondo, 0, 0, Escenario.ANCHO, Escenario.ALTO,0,fondoY,Escenario.ANCHO, fondoY + Escenario.ALTO, this);
		for(int i = 0; i < personajes.size();i++) {
			Personaje p = (Personaje)personajes.get(i);
			p.paint(g);
		}
		jugador.paint(g);
		pintaEstado(g);
		strategy.show();
	}
	
	public SpriteCache getSpriteCache() {return sc;}
	
	public void juego() {
		tiempo = 1000;
		fps = 1000;
		iniciaMundo();
		while(isVisible()&&!acaba){
			long tiempo1 = System.currentTimeMillis();
			fondoY--;
			if(fondoY<0)fondoY=loseta.getHeight();
			actualizaMundo();
			compruebaColisiones();
			pintaMundo();
			tiempo = System.currentTimeMillis()-tiempo1;
			if(tiempo > 0) fps = (int)(1000/tiempo); else fps = -1;
			do {Thread.yield();} while(System.currentTimeMillis()-tiempo1<17);
		}
		pintaGameOver();
	}
	
	public Bichos() {
		sc = new SpriteCache();
		JFrame ventana = new JFrame("Bichos");
		JPanel panel = (JPanel)ventana.getContentPane();
		setBounds(0, 0, Escenario.ANCHO, Escenario.ALTO);
		panel.setPreferredSize( new Dimension(Escenario.ANCHO, Escenario.ALTO) );
		panel.setLayout(null);
		panel.add(this);
		ventana.setTitle("Adrian Gonzalez Nuñez");
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
		setIgnoreRepaint(true);
		BufferedImage cursor = sc.creaCompatible(10,10,Transparency.BITMASK);
		Toolkit t = Toolkit.getDefaultToolkit();
		Cursor c = t.createCustomCursor(cursor, new Point(5,5), "null");
		setCursor(c);
		sonic = new SonidoCache();
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
	
	public Jugador getJugador() { return jugador; }
	
	public void setGameOver() { acaba = true; }
	
	public void pintaGameOver() {
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.setPaint(Color.orange);
		g.drawString("GAME OVER", Escenario.ANCHO/2-150, Escenario.ALTO/2-50);
		pintaEstado(g);
		strategy.show();
	}
	
	public SonidoCache getSonidoCache() { return sonic; }
	
} //fin clase Bichos
