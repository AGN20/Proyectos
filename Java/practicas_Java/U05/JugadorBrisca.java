package U05E03;

import U05E02.CartaBrisca;

public class JugadorBrisca {

	int puntos;
	CartaBrisca[] mano = new CartaBrisca[3];
	
	
	public void misCartas() {
		
		String[] manoCadena = new String[3];
	
		for(int i=0; i<3;i++) {
			if(mano[i] == null) {
				 manoCadena[i] = "sin carta";
			}else {
				 manoCadena[i] = mano[i].toString();
			}
		}
		
		
		System.out.println("Tus cartas son: 1-" + manoCadena[0] + " 2-" + manoCadena[1] + " 3-" + manoCadena[2] );
		
	}
}
