package practica.bichos.etapa3;

import java.awt.image.ImageObserver;

public interface Escenario extends ImageObserver {

	public static final int ANCHO = 640;
	public static final int ALTO = 480;
	public static final int RETARDO = 10;
	
	public SpriteCache getSpriteCache();
	public void insertarPersonaje( Personaje p );
	
	public static final int LIMITE = 400;

}
