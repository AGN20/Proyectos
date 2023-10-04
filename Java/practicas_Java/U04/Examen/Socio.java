package E04;

public class Socio {

	private int maxSocios = 5;
	private int actSocios = 0;
	private int carnet;
	private String nombre;
	private double inversion;
	private static double capitalSocial;
	
	public Socio(String nombre, double inversion) {
		//Se a alcanzado el maximo de socios
		if(actSocios <= actSocios) {
			//si no actualiza suma un socio y crea su carnet
			actSocios++;
			carnet = actSocios;
			//Ha invertido mas de 0
			if(inversion > 0) {
				//Si, actualiza su nombre y su inversion
				nombre = nombre;
				inversion = inversion;
				//Por ultimo actualizamos el capital total de la empresa.
				capitalSocial = capitalSocial + inversion;
			}else {
				//NO, imprime el error
				System.out.println("El importe del nuevo socio no es adecuado, tiene que ser mas de 0");
			}
			
		}else {
			//NO, imprime el error
			System.out.print("Limite de Socios alcanzado, el nuevo socio no se ha podido crear");
		}
	}
	
	public Socio(double inversion) {
		Socio anonimo = new Socio("ANONIMO",inversion);
	}
	
	//Getters
	public int getCarnet() {return carnet;}
	public String getNombre() {return nombre;}
	public double getInversion() {return inversion;}
	
	//Setters
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	//Que pasa si un socio vende capital
	public boolean vendeCapital(double importe, Socio comprador) {
		//Comprobamos que el socio al que se le va a vender existe y el socio vendedor no se queda con 0 o menos
		if(inversion-importe > 0 && comprador.getCarnet() <= carnet && comprador.getCarnet()>0) {
			//Actualizamos los datos de los dos inversores y devolbemos true
			comprador.inversion = comprador.inversion + importe;
			inversion = inversion - importe;
			return true;
		}else {
			//En caso de que lo anterior no se cumpla avisamos y devolbemos false
			System.out.println("Si quiere comprar parte del capital de otro socio, este no se puede quedar con 0 o menos de la inversion y el socio comprador debe existir");
			return false;
		}
	}
	
	//Si se va i vende todo a otro socio
	boolean vendeTodo(Socio comprador) {
		//Comprobamos que el socio al que se le va a vender existe y el socio vendedor no se queda con 0 o menos
		if(comprador.getCarnet() <= carnet && comprador.getCarnet()>0) {
			//Actualizamos los datos de los dos inversores y devolbemos true
			comprador.inversion = comprador.inversion + inversion;
			inversion = 0;
			return true;
		}else {
			//En caso de que lo anterior no se cumpla avisamos y devolbemos false
			System.out.println("Si quiere comprar parte del capital de otro socio, el socio comprador debe existir");
			return false;
		}
	}
	
	//Que pasa si un socio avandona la empresa
	public void abandonaEmpresa() {
		actSocios = actSocios-1;
		carnet = 0;
		nombre = "Dejo de ser socio";
		capitalSocial = capitalSocial - inversion;
	
	}
	
	//Sobreescribe toString
	@Override
	public String toString() {
		return "Socio " + carnet + " " + nombre + " capital: " + inversion;
	}
	
	//Sobreescribe equals
	@Override
	public boolean equals( Object n) {
		if(inversion == this.inversion || carnet == this.getCarnet()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
