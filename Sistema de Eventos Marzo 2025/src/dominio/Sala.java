
package dominio;
import java.time.LocalDate;
import tads.ListaSimple;

/**
 *
 * @author frana
 */
public class Sala {
    private String Nombre;
    private int Capacidad;
    private ListaSimple<LocalDate> fechasOcupadas;

    public ListaSimple<LocalDate> getFechasOcupadas() {
        return fechasOcupadas;
    }
    
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
        this.fechasOcupadas = new ListaSimple<>();

    }
}
