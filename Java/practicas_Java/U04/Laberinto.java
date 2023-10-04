package Ejercicio5;

import Ejercicio3.GNA;
import Ejercicio2.MiCo;

public class Laberinto{
	
	String Pared = "*";
	String Camino = ".";
	String Entrada = "E";
	String Meta = "M";
	
	String[][] laberinto;
	
	String ruta = "";
	
	GNA g;
	
	int posX;
	int posY;
	
	
	public Laberinto(int fL, int cL, int fS, int fM, long semilla, double p) {
		
		laberinto = new String[fL][cL];
		
		//Paredes
		for(int i = 0; i < laberinto.length; i++) {
			for(int j = 0 ; j < laberinto.length; j++ ) {
				if(i == 0 || i == fL-1 || j == 0 || j == cL-1) {
					laberinto[i][j] = Pared;
				}
			}
		}
		
		laberinto[fS][0] = Entrada;
		laberinto[fM][cL-1] = Meta;
		
		g = new GNA(semilla);
		
		
		for(int i = laberinto.length / 2; i < laberinto.length; i++) {
			for(int j = laberinto.length / 2 ; j < laberinto.length; j++ ) {
				double x =g.r();
				if(x < p) {
					laberinto[i][j] = Pared;
				}
			}
		}
		
	}
	
	public void dibuja(int fila, int columna) {
		
		posX = fila;
		posY = columna;
		
		MiCo mico = new MiCo();
		
		mico.setCursor(posX,posY);
		
		for (int i = 0; i < laberinto.length; i ++) {
			for (int j = 0; j < laberinto.length; j++) {
				if(laberinto[i][j] != null) {
					if(laberinto[i][j].equals(Entrada) || laberinto[i][j].equals(Meta)) {
						mico.setColor("rojo","gris");
					}else {
						mico.setColor("azul","gris");
					}
				}
			}
		}
		
	}
	
	
	boolean moverA(int x, int y) {
		
		if(laberinto[x][y].equals(Meta)) {
			return true;
		}else if(laberinto[x][y].equals(Pared) || laberinto[x][y].equals(Camino) || laberinto[x][y].equals(Entrada)) {
			return false;
		}
		
		System.out.print(Camino);
		boolean intento;
		
		//Norte
		intento = moverA(x-1,y);
		if (intento) {
			ruta = 'N' + ruta;
			return true;
		}
		//Este
		intento = moverA(x,y-1);
		if (intento) {
			ruta = 'E' + ruta;
			return true;
		}
		//Sur
		intento = moverA(x+1,y);
		if (intento) {
			ruta = 'S' + ruta;
			return true;
		}
		//Oeste
		intento = moverA(x,y+1);
		if (intento) {
			ruta = 'O' + ruta;
			return true;
		}
		
		laberinto [x][y] = " ";
		System.out.print(laberinto[x][y]);
		return false;
		
	}
	
	
	
}
