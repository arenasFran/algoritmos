
package dominio;

/**
 *
 * @author frana
 */
public class Sala {
    private String Nombre;
    private int Capacidad;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }
    
    public Sala(String nombre, int capacidad){
        this.setNombre(nombre);
        this.setCapacidad(capacidad);
    }
}
