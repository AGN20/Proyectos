package practica.bichos.etapa4;

import java.net.URL;
import java.util.HashMap;
import java.io.File;

public abstract class RecursosCache {

	protected HashMap recursos;
	
	public RecursosCache() { recursos = new HashMap(); }
	
	protected Object cargaRecurso( String nombre) {
		URL url= null;
		url = getClass().getClassLoader().getResource("practica/bichos/etapa4/res/");
		return cargaRecurso( new File(url.getPath() + nombre ) );
	}
	
	protected Object getRecurso(String nombre) {
		Object res = recursos.get(nombre);
		if(res == null) {
			res = cargaRecurso(nombre);
			recursos.put(nombre, res);
		}
		return res;
	}
	
	protected abstract Object cargaRecurso(File f);
	
	protected File getFicheroRecurso(String nombre) {
		URL url = null;
		url = getClass().getClassLoader().getResource("practica/bichos/etapa4/res/");
		return new File(url.getPath() + nombre );
	}

}
