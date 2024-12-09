package cl.ipvg.puebadb;

import java.util.ArrayList;
import java.util.List;

public class CarritoManager {
    private static CarritoManager instance;
    private List<ProductoCarrito> carrito;

    private CarritoManager() {
        carrito = new ArrayList<>();
    }

    public static synchronized CarritoManager getInstance() {
        if (instance == null) {
            instance = new CarritoManager();
        }
        return instance;
    }

    public List<ProductoCarrito> getCarrito() {
        return carrito;
    }

    public void agregarProducto(ProductoCarrito producto) {
        for (ProductoCarrito p : carrito) {
            if (p.getIdProducto().equals(producto.getIdProducto())) {
                p.setCantidad(p.getCantidad() + producto.getCantidad());
                return;
            }
        }
        carrito.add(producto);
    }

    public void vaciarCarrito() {
        carrito.clear();
    }
}
