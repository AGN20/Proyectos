package Ejercicio7;

import Ejercicio3.GNA;

public class TestAleatorios {
	
	
	
	double[][] array;
		
	public double uniformidad(int k) {
		
		array = new double[3][k];
		GNA g = new GNA(k);
		int[] tiros = new int[100];
		
		for(int i = 0; i < tiros.length; i++ ) {
			tiros[i] = g.r(1, 6);
		}
		
		int[] cantidad = new int[k];
		
		
		for(int i=0; i < tiros.length; i++) {
			for(int j = 0; j < cantidad.length; j++) {
				if(tiros[i] == j) cantidad[j] += 1;
			}
		}

		double probabilidad = (double)(k - 1)/k;
		
		double teoria = 100*(1/k);
		
		double gLibertad = k-1;
		
		
		
	}
	
	
	
}

