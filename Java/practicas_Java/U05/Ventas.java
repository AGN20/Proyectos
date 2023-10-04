package U05E04;

public class Ventas {

	private Comercial com;
	private Repartidor rep;
	private Productos pro;
	private int cantidad;
	private boolean entrega = false;

	public Ventas(Productos pro, int cantidad, Comercial com, Repartidor rep) {
		this.pro = pro;
		this.setCantidad(cantidad);
		this.com = com;
		this.rep = rep;
		
		rep.setNumRepartos(rep.getNumRepartos() + 1);
		com.comision = pro.getPrecioUnidad() * pro.getPorComision() * cantidad;
		
	}


	// setter
	public void setEntregado(boolean entregado) {
		this.entrega = entregado;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	// getters
	public int getCantidad() {
		return cantidad;
	}

	// toString
	@Override
	public String toString() {
		String esta;
		if (entrega == false) {
			esta = "No entregado";
		} else {
			esta = "Entregado";
		}

		String estaEntregado = "Producto: " + pro + ", con estado: " + esta + ". Cantidad de producto: " + getCantidad()
				+ ". Vendido por comercial: " + com + " y repartido por " + rep;
		
		return estaEntregado;
	}

}
