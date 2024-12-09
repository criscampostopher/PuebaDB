
package cl.ipvg.puebadb;


public class Botilleria {
    private String nombre;
    private String direccion;
    private String imagenURL;
    private Coordenadas coordenadas;

    public Botilleria() {
        // Constructor vacío necesario para Firebase
    }

    public Botilleria(String nombre, String direccion, String imagenURL, double latitud, double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagenURL = imagenURL;
        this.coordenadas = new Coordenadas(latitud, longitud);
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getImagenURL() { return imagenURL; }
    public void setImagenURL(String imagenURL) { this.imagenURL = imagenURL; }

    public Coordenadas getCoordenadas() { return coordenadas; }
    public void setCoordenadas(Coordenadas coordenadas) { this.coordenadas = coordenadas; }




    // Clase interna para las coordenadas
    public static class Coordenadas {
        private double latitud;
        private double longitud;

        public Coordenadas() {}

        public Coordenadas(double latitud, double longitud) {
            this.latitud = latitud;
            this.longitud = longitud;
        }

        // Getters y Setters
        public double getLatitud() { return latitud; }
        public void setLatitud(double latitud) { this.latitud = latitud; }

        public double getLongitud() { return longitud; }
        public void setLongitud(double longitud) { this.longitud = longitud; }
    }
}

