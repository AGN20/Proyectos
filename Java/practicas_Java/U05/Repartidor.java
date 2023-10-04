package U05E04;

import java.time.LocalDateTime;

public class Repartidor extends Empleados {

	private int numRepartos;

	// Creamos el constructo Repartidor
	public Repartidor(String nombre, LocalDateTime nace, LocalDateTime contrato, double salarioBase) {
		super(nombre, nace, contrato, salarioBase);
	}

	// Creamos el Getter de Salario
	@Override
	public double getSalario() {
		double x = getNominaActual() + getSalarioBase() + getNumRepartos();
		this.setNumRepartos(0);
		setNominaActual(x);
		return x;
	}

	// Creamos el toString
	@Override
	public String toString() {
		String repString = "Empleado Repartidor: " + getNombre() + ", nacido el " + getNace() + ", contratado el "
				+ getContrato() + ", con un salario de " + getSalarioBase() + "€, y nomina actual de "
				+ getNominaActual() + "€. Su numero de repartos es " + getNumRepartos() + " con salario de "
				+ getSalario();
		
		return repString;
	}

	//Creamos el Getter y el Setter de NumRepartos
	public int getNumRepartos() {return numRepartos;}
	public void setNumRepartos(int numRepartos) {
		this.numRepartos = numRepartos;
	}
	
}
