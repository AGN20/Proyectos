package practica.bichos.etapa3;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteCache {
	private HashMap sprites;
	
	public SpriteCache() { sprites = new HashMap(); }
	
	private BufferedImage cargaImagen( String nombre ) {
		
		URL url = null; //Primero intentamos con url (falla en eclipse)
		File f = null;  //Intentamos con ficheros (falla fuera de eclipse)
		try {
			url = getClass().getClassLoader().getResource("practica/bichos/etapa3/res/" + nombre);
			return ImageIO.read(url);
		}catch( Exception e ){
			System.out.println("Error al cargar imagen " + nombre + " de " + url);
			System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage());
			try {
				url = getClass().getClassLoader().getResource("practica/bichos/etapa3/res/");
				return ImageIO.read( new File(url.getPath() + nombre ) );
			}catch( Exception e1 ){
				System.out.println("Error al cargar imagen de fichero " + url);
				System.out.println("ERROR: " + e1.getClass().getName() + " " + e.getMessage());
				System.exit(0);
				return null;
			}
		}
		
	}
		
	public BufferedImage getSprite( String nombre ) {
		BufferedImage img = (BufferedImage)sprites.get(nombre);
		if(img == null) {
			img = cargaImagen(nombre);
			sprites.put(nombre, img); //Añadir el sprite al hashMap
		}
		return img;
	}
	
}
