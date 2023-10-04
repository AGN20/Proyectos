package U05E02;

import U05E01.Carta;

public class CartaBrisca extends Carta{

	private int puntos;
	
	// constructo cartaBrisca
	public CartaBrisca(String palo, int valor) {
		super(palo,valor);
		
		Carta c = new Carta(palo, valor);
		
		int[] pbrisca = {11,0,10,0,0,0,0,0,0,2,3,4};
		
		puntos = pbrisca[valor-1];

	}
	
	public int getPuntos() {
		int p = puntos;
		return p;
	}
	
}
