package practica.bichos.etapa4;

import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.File;


public class SpriteCache extends RecursosCache implements ImageObserver{
	
	protected Object cargaRecurso( File f ) {
		try {
			return ImageIO.read(f);
		}catch (Exception e) {
			System.out.println("Error al cargar imagen " + f);
			System.out.println("ERROR: " +  e.getClass().getName() + " " + e.getMessage());
			System.exit(0);
			return null;
		}
	}
		
	public BufferedImage creaCompatible(int ancho, int alto, int transparencia) {
		GraphicsConfiguration gc = GraphicsEnvironment
										.getLocalGraphicsEnvironment()
										.getDefaultScreenDevice()
										.getDefaultConfiguration();
		BufferedImage compatible = gc.createCompatibleImage(ancho, alto, transparencia);
		return compatible;
	}
	
	public BufferedImage getSprite( String nombre ) {
		BufferedImage carga = (BufferedImage)getRecurso(nombre);
		BufferedImage compatible = creaCompatible(carga.getWidth(),carga.getHeight(), Transparency.BITMASK);
		Graphics g = compatible.getGraphics();
		g.drawImage(carga, 0, 0, this);
		return compatible;
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		return (infoflags & (ALLBITS | ABORT)) == 0;
	}
		
}
