package U05E04;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Empleados {

	private String nombre;
	private LocalDateTime nace;
	private LocalDateTime contrato;
	private double salarioBase;
	private double nominaActual;

	public Empleados(String nombre, LocalDateTime nace, LocalDateTime contrato, double salarioBase) {
		long edad = ChronoUnit.YEARS.between(nace, contrato);
		if (edad < 18) {
			System.out.print("No se puede contratar a esta persona, es menor de edad \n");
		} else {
			this.nombre = nombre;
			this.nace = nace;
			this.contrato = contrato;
			this.salarioBase = salarioBase;
		}
	}

	// getter abstracto
	abstract public double getSalario();

	// Setter
	public void setNominaActual(double nominActual) {
		this.nominaActual = nominaActual;
	}

	// getters
	public double getSalarioBase() {
		return salarioBase;
	}

	public double getNominaActual() {
		return nominaActual;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDateTime getNace() {
		return nace;
	}

	public LocalDateTime getContrato() {
		return contrato;
	}

	// to String
	@Override
	public String toString() {
		String empString = "Empleado: " + nombre + ", nacido el " + nace + ", contratado el " + contrato
				+ ", con un salario de " + salarioBase + "€, y nomina actual de " + nominaActual + "€";

		return empString;
	}
}
