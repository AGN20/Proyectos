package Ejercicio3;

import java.time.LocalDateTime;

public class GNA {

	long u,v,w;
	long semilla;
	//Constructores
	
	public Object Object() {
		
		LocalDateTime date = LocalDateTime.now();
		
		int año = date.getYear();
		int mes = date.getMonthValue();
		int dia = date.getDayOfMonth();
		int h = date.getHour();
		int m = date.getMinute();
		int s = date.getSecond();
		
		this.semilla = dia * s + año * m + mes * h ;
		
		return semilla;
	}
	
	public GNA( long semilla ) {
		
		w = 1;
		v = 4101842887655102017L;
		u = semilla ^ v;
		int64();
		v = u;
		int64();
		w = v;
		int64();
		
	}
	
	private long int64(){
		
		long n;
		long x;
		u = u * 2862933555777941757L + 7046029254386353087L;
		v = v ^ (v >> 17);
		v = v ^ v << 31;
		v = v ^ v >> 8;
		w = 4294957665L * (w & 0x7FFFFFFFFFFFFFFFL) + ( w >> 32 );
		x = u ^ ( u << 21 );
		x = x ^ (x >> 35 );
		x = x ^ ( x << 4 );
		
		n =  (x + v) ^ w;	
		
		if(n < 0) {
			n = n*(-1);
		}
		
		return n;
		
	}
	
	//Metodos Publicos
	
	// Genera aleatorio double 0.0 <= x <= 1.0
	public double r() {
		return (5.42101086242752217*Math.pow(10, -20))* int64();
	}
	
	// Genera aleatorio long a <= x <= b
	public long r(long a, long b) {
		long n = (long)(this.r()*(double)b*2) + 1L;
		
		if(n < a) {
			n += a;
		}
		
		return n;
		
	}
	
	// Genera aleatorio int a <= x <= b
	public int r( int a, int b) {
		int n = (int)(this.r()*(double)b*2) + 1;
		
		if(n < a) {
			n+=a;
		}
		
		return n;
	}
	
	// Generar cadena aleatoria de n caracteres cogidos de la cadena alfabeto
	public String r(int n, String alfabeto) {	
		
		char[] letra = new char[alfabeto.length()];
		String palabra = "";
		
		if(alfabeto.equals("")){
			alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}
		
		for(int i = 0; i > alfabeto.length(); i++) {
			letra[i] = alfabeto.charAt(i);
		}
		
		for(int j = 0; j < n; j++) {
			char cadena = alfabeto.charAt((int)(this.r() * (double) alfabeto.length()));
			palabra = palabra + cadena;
		}
		
		
		return palabra;
		
	}
	
	public static void main(String[] args) {
		
		GNA g1 = new GNA(1);
		double[] n = new double[10];
		
		GNA gna2 = new GNA(0L);
		long[] z = new long[20];
		
		
		for(int i = 0; i < 10; i++) {
			n[i] = g1.r(0,20);
			System.out.println(n[i]);
		}
		
		for(int i = 0; i < 20; i++){
			z[i] = gna2.r(0,20);
			System.out.println(z[i]);
		}
		
		System.out.println(g1.r(0,20));
		
		System.out.println(g1.r(10,""));
		 
		
		
	}
	
}
