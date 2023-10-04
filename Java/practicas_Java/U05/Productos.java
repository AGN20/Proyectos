package U05E04;

public class Productos {

	private String nombre;
	private double precioUnidad;
	private double porComision;

	// Constructo Producto
	public Productos(String nombre, double precioUnidad, double porComision) {
		this.nombre = nombre;
		this.setPrecioUnidad(precioUnidad);
		this.setPorComision(porComision);
	}

	// Creamos el toString
	public String toString() {
		String pro = "El producto: " + nombre + ", a precio de " + getPrecioUnidad() + " €, con comisión de "
				+ getPorComision() + "%.";
		return pro;
	}
	
	//Getters
	public double getPrecioUnidad() {return precioUnidad;}
	public double getPorComision() {return porComision;}
	
	//Setters
	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
	public void setPorComision(double porComision) {
		this.porComision = porComision;
	}

}
