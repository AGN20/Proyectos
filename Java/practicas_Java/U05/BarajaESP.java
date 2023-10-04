package U05E02;

import U05E02.CartaBrisca;

public class BarajaESP {

	CartaBrisca c = null;
	CartaBrisca[] mazo;

	//Constructor
	public BarajaESP() {
		
			//Iniciamos el mazo con 40 cartas
			mazo = new CartaBrisca[40];
			
			//Entero que indica las cartas si usar
			int quedan = 39;
			
			//Creo una array de estrings con los palos
			String[] palo = {"bastos","copas","espadas","oros"};
			
			//contador de cartas que indica la posicion de la carta en el mazo
			int contadorCartas = 0;
			
			//Utilizamos un bucle for para generar los 4 palos diferentes
			for(int i = 0; i < 4; i++) {
				//Utilizamos un bucle for para rellenar las 10 cartas de un palo especifico
				for(int j = 1; j <= 10; j++) {
					//En caso de que j sea menor o igual a 7 CartaBrisca sera i palo y j valor
					if(j <= 7) {
						c = new CartaBrisca(palo[i], j);
						mazo[contadorCartas] = c;
						contadorCartas++;
					//En caso de que j sea mallor a 7 CartaBrisca sera i palo y j+2 valor
					}else if(j > 7) {
						c = new CartaBrisca(palo[i], j+2);
						mazo[contadorCartas] = c;
						contadorCartas++;
					}
				}
				
			}
		
	}

	public void baraja() {
		
		//Recorremos toda la array
		for(int i = 0; i<mazo.length-1;i++) {
			//iniciamos un numero aleatorio
            int x = (int)(Math.random() * mazo.length-1);
            //Iniciamo c1 que tendra el valor de mazo[i]
            CartaBrisca c1 = mazo[i];
            //Iniciamo c2 que tendra el valor de mazo[x] siendo x un random
            CartaBrisca c2 = mazo[x];
            //cambiamos el valor del mazo[x] por el de c1
            mazo[x] = c1;
            //cambiamos el valor de mazo[i] por el de c2
            mazo[i] = c2;
        }
		
	}
	
	public boolean hayCarta() {
		// Creamos un booleano que controlara si hay o no cartas
		boolean quedan = true;
		
		//Recorremos toda la array en busca de cartas NO nullas
		for(int i = 0; i < 40; i++) {	
			//En caso de que una carta sea nula, quedan = false y contuinua
			if(mazo[i] == null) {quedan = false;}
			//En otro caso, si la carta contienen algo que NO es null, quedan = true y sales directamente
			//ya que si hay una que es true, quedan cartas.
			else {quedan = true; break;}
		}
		
		return quedan;
		
	}
	
	public CartaBrisca sacaCarta() throws IllegalArgumentException{
			
		//creamos un int que nos indica la posicion de las carta a sacar
        int x = 39;
       
        //si la carta que vamos a sacar es null cogeremos la siguiente en el mazo
        while(mazo[x] == null) {
            x--;
        }
        
        if(mazo[x] != null) {
        	//Una vez encontrada la posicion que tiene una carta al guardamos en i
            CartaBrisca i = mazo[x];
            
            //Ponemos la posicion en null para que sepa que esa carta ya se ha sacado
            mazo[x]=null;
            
            //debolbemos a quien nos la ha pedido la carta guardada en i
            return i;
        } else {
        	//En caso de que el mazo se quede sin cartas abisamos que se a quedado sin cartas
        	throw new IllegalArgumentException("No quedan cartas!");
        }
        
       
        
        
        
	}
	
	public CartaBrisca dimeCarta(int i){
		
		//creamos un int para saber si quedan cartas en el mazo
		int x = 39;
		
		//Recorremos todo el mazo en busca de cartas no nullas
        while(mazo[x] == null) {
            x--;
        }
        if (x == 0) { return null; }
        
        //En caso de que el i sea null debolveremos null
        if(mazo[i] == null) {
            return null;
            
        //En cualquier otro caso debolveremos una copia c de la carta en la posicion
        }else {
            CartaBrisca c = mazo[i];
            return c;
        }
		
		
	}
	
	@Override
	public String toString(){
		String cadena = "[ ";
		for(int i = 0; i < mazo.length;i++) {
			int valor = mazo[i].getValor();
			String palo = mazo[i].getPalo();
			
			cadena = cadena + " [" + valor + " de " + palo + "] "; 
		}
		cadena = cadena + " ]";
		return cadena;
	}
	
	
}
