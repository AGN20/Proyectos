package U05E04;

import java.time.LocalDateTime;

public class Empresa {

	public static void main(String[] args) {

		Empleados[] empleados = new Empleados[4];

		//Repartidores
		Repartidor pepe = new Repartidor("Pepe", LocalDateTime.of(1981, 01, 01, 0, 0),
				LocalDateTime.of(1999, 01, 01, 0, 0), 800.00);
		Repartidor juan = new Repartidor("Juan", LocalDateTime.of(1982, 02, 02, 0, 0),
				LocalDateTime.of(2000, 02, 02, 0, 0), 800.00);

		//Comerciales
		Comercial manuel = new Comercial("Manuel", LocalDateTime.of(1983, 03, 03, 0, 0),
				LocalDateTime.of(2001, 03, 03, 0, 0), 800.00);
		Comercial laura = new Comercial("Laura", LocalDateTime.of(1984, 04, 04, 0, 0),
				LocalDateTime.of(2002, 04, 04, 0, 0), 850.00);

		// Introducimos los repartidores en la array
		empleados[0] = pepe;
		empleados[1] = juan;
		empleados[2] = manuel;
		empleados[3] = laura;

		// Creamos dos nuevos productos
		Productos p1 = new Productos("Queso", 10.00, 0.1);
		Productos p2 = new Productos("Destornillador", 5.00, 0.2);

		// Creamos las ventas
		Ventas v1 = new Ventas(p1, 2, manuel, pepe);
		boolean entregado = true;
		v1.setEntregado(entregado);

		// Imprimimos
		for (int i = 0; i < empleados.length; i++) {
			if(empleados[i].getContrato() != null) {
				System.out.println("\n"+empleados[i]);
			}
		}

	}

}
