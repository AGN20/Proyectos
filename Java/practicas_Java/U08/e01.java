package E01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class e01 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Character> lista = toString(sc.nextLine());
		
		double resultado = evalua(lista);
		
		System.out.println(resultado);
		
		
	}
	
	public static ArrayList toString(String e) throws NumberFormatException{
		
		Stack<Character> pOperaciones = new Stack<>();
		ArrayList<Character> lista = new ArrayList<>();
		
		for( int i = 0; i<e.length(); i++ ) {
			
			char c = e.charAt(i);
		
			if( c == '(' || c == '[' || c == '{') { // metemos los parentesis en pila
				pOperaciones.push(c);
			}else if(c == ')' || c == ']' || c == '}') { //sacamos parentesis de pila y metemis en lista
				if(c == ')') {
					if(pOperaciones.contains('^') || pOperaciones.contains('*') || pOperaciones.contains('/') || pOperaciones.contains('+') || pOperaciones.contains('-') ) {
						lista.add(pOperaciones.pop());
						} 
					lista.add(pOperaciones.pop()); 
					lista.add(c);
					}
				if(c == ']') {
					if(pOperaciones.contains('^') || pOperaciones.contains('*') || pOperaciones.contains('/') || pOperaciones.contains('+') || pOperaciones.contains('-') ) {
						lista.add(pOperaciones.pop());
						} 
					lista.add(pOperaciones.pop()); 
					lista.add(c);
				}
				if(c == '}') {
					if(pOperaciones.contains('^') || pOperaciones.contains('*') || pOperaciones.contains('/') || pOperaciones.contains('+') || pOperaciones.contains('-') ) {
						lista.add(pOperaciones.pop());
					} 
					lista.add(pOperaciones.pop()); 
					lista.add(c);
				}
			}else if(c == '^' || c == '*' || c == '/' || c == '+' || c == '-' ) {
				if ( c == '^' || c == '*' || c == '/') { //metemos caracteres fuertes en pila y en la lista, si ya se encuentra alguno en lista lo saca antes
					if(pOperaciones.contains('^') || pOperaciones.contains('*') || pOperaciones.contains('/')) {
						char c1 = pOperaciones.pop();
						lista.add(c1);
					}
					pOperaciones.push(c);
				}else if( c == '+' || c == '-'  ) {
					if(pOperaciones.contains('^') || pOperaciones.contains('*') || pOperaciones.contains('/') || pOperaciones.contains('+') || pOperaciones.contains('-') ) {
						char c1 = pOperaciones.pop();
						lista.add(c1);
					}
					pOperaciones.push(c); 
					
					
				}
			}else {
				lista.add(c);
			}
		}
		if(pOperaciones.isEmpty() == false){
			lista.addAll(pOperaciones);
		}
		return lista;
		
	}
	
	public static double evalua( ArrayList a) {
		
		Stack<Double> pValores = new Stack<>();
		
		for(int i=0; i<a.size(); i++) {
			char c = (char)a.get(i);
			if(c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {
				double n2 = pValores.pop();
				double n1 = pValores.pop();
				if(c == '^') pValores.add(Math.pow(n1, n2));
				if(c == '*') pValores.add((n1 * n2));
				if(c == '/') pValores.add((n1 / n2));
				if(c == '+') pValores.add((n1 + n2));
				if(c == '-') pValores.add((n1 - n2));
			}else if( c == '(' || c == '[' || c == '{' ||  c == ')' || c == ']' || c == '}'){
			}else {
				double n = c;
				pValores.add(n);
			}
		}
		
		double resultado = pValores.get(pValores.size());
		return resultado;
	}
	
}
