package practica.bichos.etapa4;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;

public class SonidoCache extends RecursosCache {

	protected Object cargaRecurso(File f) {
		try { return Applet.newAudioClip( f.toURL() ); } catch(Exception e) {};
		return null;
	}
	
	public AudioClip getAudioClip( String nombre ) { return (AudioClip)getRecurso(nombre);}
	
	public void tocaSonido(final String nombre) { 
		new Thread(
					new Runnable() {
						public void run() { getAudioClip(nombre).play(); }
					}
				).start();
	}
	
	public void bucleSonido( final String nombre ) { 
		new Thread(
				new Runnable() {
					public void run() {
						try {
							Clip clip = AudioSystem.getClip();
							clip.open( AudioSystem.getAudioInputStream(getFicheroRecurso(nombre)));
							clip.start();
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						} catch(Exception e) {}
					}
				}
			).start();
		
	}
	
}
