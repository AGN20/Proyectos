package Ejercicio4;

import java.util.Scanner;

public class Zp63 {

	static long p;
	boolean pPrimo;
	
	public Zp63(long p) {
		
	}
	
	//POTENCIA MOD
	public long potenciaMod(long b, long e, long p) {
		long x = 1;
		b = b % p;
		e = e % p;
		while(e>0 && b>1) {
			if(e%2 == 1) {
				x = (x*b)%p;
				e = e-1;
			}
			b = (b*b)%p;
			e = e/2;
		}
		return x;
	}
	
	//INVERSO FERMAT
	private void inversoFermat(long a) {
		double x = Math.pow(a, p-1);
	}
	
	
	//PRIMO
	public long[] primosBajos = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
			71, 73, 79, 83, 89, 97, 101 ,103, 107, 109, 113, 127, 131, 137, 139, 149,
			151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
			233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
			317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
			419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499,
			503, 509};
	
	public boolean millerTest(int d, int n) {
		int a = 2 + (int) (Math.random() * 10) + 1 %(n-4);
		long x = potenciaMod(a, d, n);
		if (x == 1 || x == n-1) return true;
		while(d != n-1) {
			x = (x*x)%n;
			d *= 2;
			if(x==1)return false;
			if(x==n-1)return true;
		}
		return false;
	}
	
	public boolean esPrimoPorbable(int n, int p) {
		if(n == 1 || n == 4) {
			return false;
		}
		if(n == 3) {
			return true;
		}
		boolean esta = false;
		if(n == primosBajos[primosBajos.length-1]) {
			for(int i = 0; i<primosBajos.length; i++) {
				if(n == primosBajos[i]) {
					esta = true;
					break;
				}
			}
			if(esta == true) return true;
			else return false;
		}
		int K =(int)Math.floor(Math.log(1-p)/Math.log(0.5));
		int d = n-1;
		while(d%2==0) {
			d/=2;
		}
		for(int i = 0; i==K-1;i++) {
			if(!millerTest(d,n)) {
				return false;
			}
		}
		return true;
		
	}
	
	
	//INVERSO MCD Y EUCLIDES
	public long mcd(long a, long b) {
		if(a<b) {
			long c = a;
			a = b;
			b = c;
		}
		long r0 = a;
		long r1 = b;
		while(r1 > 0) {
			long t = r1;
			r1 = r0 % r1;
			r0 = t;
		}
		return r0;
	}
	
	public long[] mcdExtendido(long a, long b) {
		long[] resul = new long[3];
		long u = 1;
		long g = a;
		long x = 0;
		long y = b;
		while(true) {
			if(y == 0) {
				long v = (g-a*u)/b;
				resul[0] = g;
				resul[1] = u;
				resul[2] = v;
				return resul;
			}
			long q = g/y;
			long t = g % y;
			long s = u-(q*x);
			u = x;
			g = y;
			x = s;
			y = t;
		}
	}
	
	
	
	//SUMA
	public long suma(long a, long b) {
		long s = 0;
		if(a+b<p) {
			s = a+b;
		}else if(a+b >=p) {
			s = (a+b)-p;
		}
		return s;
	}
	
	//RESTA
	public long resta(long a, long b) {
		long s = 0;
		if(0<=(a-b) && (a-b)<p) {
			s = a-b;
		}else if(a-b < p) {
			s = p+(a-b);
		}
		return s;
	}
	
	//MULTI
	public long multi(long a, long b) {
		long s = 0;
		if(a*b<p) {
			s = a*b;
		}else if((a*b>=p)) {
			s = (a*b)%p;
		}
		return s;
	}
	
	//DIVISIÓN
	public long divi(long a, long b) {
		long s = 0;
		if(a*b<p) {
			s = a*b;
		}else if((a*b>=p)) {
			s = (a*b)%p;
		}
		return s;
	}
	
	//Inverso Modular
	public void inversoModular(long a) {
		
	}
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		boolean verdadero = false;
		do {
			System.out.print("Teclee p: ");
			p = teclado.nextLong();
			if(p > 0b111111L && p < 1L) {
				verdadero = false;
			} else {
				verdadero = true;
			}
		} while(verdadero = false);
		System.out.println(verdadero);
	}
	
}
