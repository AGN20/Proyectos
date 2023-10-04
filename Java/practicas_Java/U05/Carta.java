package U05E01;

public class Carta {

	private String palo; // contendra el palo de la carta
	private int valor;	// contendra el numero de la carta
	
	public Carta( String p, int v ) throws IllegalArgumentException {
		
		
		//Excepción tiene que ser bastos, copas, espadas o oros
		if ( p.equals("bastos") || p.equals("copas") || p.equals("espadas") || p.equals("oros") ) {
			palo = p;
		}else {
			throw new IllegalArgumentException("palo debe contener bastos, copas, espadas o oros en minusculas");
		}
		
		//Excepción valores del 1 al 7 y del 10 al 12
		if (v > 1 || v < 8 && v > 9 || v < 13) {
			valor = v;
		}else {
			throw new IllegalArgumentException("valor solo puede contener valores des de el 1 al 7 y del 10 al 12");
		}
		
	}
	
	//getters
	//coge el palo
	public String getPalo() {
		String p = palo;
		return p;
	}
	
	//coge el valor
	public int getValor() {
		int v = valor;
		return v;
	}
	
	// Sobre escribimos to String para que nos devuelva un String 
	@Override
	public String toString() {
		
		int val = this.getValor();
		String pal = this.getPalo();
		
		String[] numero = {"AS","2","3","4","5","6","7","0","0","SOTA","CABALLO","REY"};
		
		return numero[val-1] + " de " + pal;
		
	}
	
}
