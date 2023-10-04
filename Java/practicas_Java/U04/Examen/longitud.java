package E04E2;

public class longitud {

	public int longi(int[][] n) {
		int r = 0;
		if (n.length == 1) {
			r = n[0][0];
			return r;
		}else if(n.length == 2){
			r = n[0][0]*n[1][1]-n[0][1]*n[1][0];
			return r;
		}else {
			int[][] x = new int[2][n[0].length];
			for(int i=0; i<2;i++) {
				for(int z=1; z<n.length;z++) {
					for(int j=0; j<n[0].length;j++) {
						x[i][j] = n[z][j];
					}
				}
			}
			for(int i=0; i<n[0].length; i++) {
				r = r + n[0][i]*(-1)*(1+i)*longi(x);
			}
			return r;
		}
	}
	
	public longitud(int n) {
		//Creamos la matriz
		int[][] matriz = new int[n][n];
		//rellenamos la matriz
		for(int i=0; i<matriz.length;i++) {
			for(int j=0; j<matriz.length; j++) {
				matriz[i][j] = (int)Math.random()*10-1;
			}
		}
		
		//Imprimimos la matriz entera
		for(int i=0; i<matriz.length;i++) {
			for(int j=0; j<matriz.length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println("");
		}
		
		//llama a la funcion recursiva
		int longitud = longi(matriz);
		
		System.out.print("La longitud es " + longitud);
	}
	
}
