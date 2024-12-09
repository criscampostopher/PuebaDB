package cl.ipvg.puebadb;

import java.util.List;

public class Venta {
    private String idVenta;
    private String fecha;
    private List<ProductoCarrito> productos;
    private double total;

    public Venta() {
    }

    public Venta(String idVenta, String fecha, List<ProductoCarrito> productos, double total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.productos = productos;
        this.total = total;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<ProductoCarrito> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCarrito> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
