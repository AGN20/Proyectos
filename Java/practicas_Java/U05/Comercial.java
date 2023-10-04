package U05E04;

import java.time.LocalDateTime;

class Comercial extends Empleados {

	double comision;

	public Comercial(String nombre, LocalDateTime nace, LocalDateTime contrato, double salarioBase) {
		super(nombre, nace, contrato, salarioBase);
		}
		
	@Override
	public double getSalario() {
		double x = getSalarioBase() + comision;
		this.comision = 0;
		setNominaActual(x);
		return x;
	}

	public String toString() {
		String esteC = "Empleado Comercial: " + getNombre() + " ,nacido el " + getNace() + ", contratado el "
				+ getContrato() + ", con un salario base de: " + getSalarioBase() + "€, y nomina actual de "
				+ getNominaActual() + ". Tiene una comisión de: " + comision + " y un salario de " + getSalario();
		return esteC;
	}

}
