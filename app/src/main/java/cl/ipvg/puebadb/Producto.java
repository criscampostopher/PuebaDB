package cl.ipvg.puebadb;

public class Producto {

    private String nombre;
    private String imagenUrl;
    private int cantidad;
    private String precio;

    public Producto() {
        // Constructor vacío requerido para Firebase
    }

    public Producto(String nombre, String imagenUrl, int cantidad, String precio) {
        this.nombre = nombre;
        this.imagenUrl = imagenUrl;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
