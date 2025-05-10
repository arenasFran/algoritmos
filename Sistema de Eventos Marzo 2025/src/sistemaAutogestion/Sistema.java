package sistemaAutogestion;

import dominio.Cliente;
import dominio.Evento;
import dominio.Sala;
import java.time.LocalDate;
import tads.IListaSimple;
import tads.ListaSimple;
import tads.Nodo;

public class Sistema implements IObligatorio {
     private IListaSimple<Cliente> listaClientes;
     private IListaSimple<Sala> listaSalas;
     private IListaSimple<Evento> listaEventos;
    
     public Sistema(){
        listaClientes = new ListaSimple<Cliente>();
        listaSalas = new ListaSimple<Sala>();
        listaEventos = new ListaSimple<Evento>();
    }
    
  
    
    @Override
    public Retorno crearSistemaDeGestion() {
        return Retorno.noImplementada();
    }

@Override
public Retorno registrarSala(String nombre, int capacidad) {
    // Validar capacidad (ERROR_2)
    if (capacidad <= 0) {
        return new Retorno(Retorno.Resultado.ERROR_2);
    }
    
    // Validar nombre no nulo o vacío (ERROR_1)
    if (nombre == null || nombre.trim().isEmpty()) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }
    
    // Crear objeto temporal para comparación
    Sala salaComparacion = new Sala(nombre.trim(), 0); // La capacidad no importa para la comparación
    
    // Verificar si ya existe una sala con ese nombre usando contiene()
    if (listaSalas.contiene(salaComparacion)) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }
    
    // Crear y agregar la nueva sala
    Sala nuevaSala = new Sala(nombre.trim(), capacidad);
    listaSalas.agregarFin(nuevaSala);
    
    return new Retorno(Retorno.Resultado.OK);
}

@Override
    public Retorno eliminarSala(String nombre) {
    // Validar nombre no nulo o vacío (ERROR_1)
    if (nombre == null || nombre.trim().isEmpty()) {
        return new Retorno(Retorno.Resultado.ERROR_1);
    }

    // Crear un objeto temporal para la comparación
    Sala salaComparacion = new Sala(nombre.trim(), 0);
    
    // Verificar si la sala existe en la lista
    if (!listaSalas.contiene(salaComparacion)) {
        return new Retorno(Retorno.Resultado.ERROR_1); // No se encontró la sala
    }
    
    // Eliminar la sala de la lista
    listaSalas.eliminar(salaComparacion);
    
    return new Retorno(Retorno.Resultado.OK); // Sala eliminada correctamente
}

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
    // Validar aforo necesario
    if (aforoNecesario <= 0) {
        return new Retorno(Retorno.Resultado.ERROR_2);  // Error 2: Aforo no válido
    }

    // Verificar si ya existe un evento con el mismo código
    for (int i = 0; i < listaEventos.tamaño(); i++) {
        Evento evento = listaEventos.obtenerPorIndice(i);
        if (evento.getCodigo().equals(codigo)) {
            return new Retorno(Retorno.Resultado.ERROR_1);  // Error 1: Evento ya existe
        }
    }

    // Buscar una sala disponible
    Sala salaAsignada = null;
    for (int i = 0; i < listaSalas.tamaño(); i++) {  // Recorrer la lista de salas
        Sala sala = listaSalas.obtenerPorIndice(i);
        if (sala.getCapacidad() >= aforoNecesario && !sala.getFechasOcupadas().contiene(fecha)) {
            salaAsignada = sala;
            break;  // Encontramos una sala adecuada
        }
    }

    // Si no hay una sala disponible
    if (salaAsignada == null) {
        return new Retorno(Retorno.Resultado.ERROR_3);  // Error 3: No hay salas disponibles
    }

    // Crear y registrar el evento
    Evento nuevoEvento = new Evento(codigo, descripcion, aforoNecesario, fecha, salaAsignada);
    listaEventos.agregarFin(nuevoEvento);  // Agregar el evento a la lista de eventos

    return new Retorno(Retorno.Resultado.OK);  // Registro exitoso
}


    
    
    
    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        Cliente c = new Cliente(cedula,nombre);
       
        if(c.getCedula().length() != 8)
        {
            return Retorno.error1();
        }
        Nodo<Cliente> actual = listaClientes.getInicio();
        while(actual != null){
            if(actual.getDato().equals(c)){
                return Retorno.error2();
            }
            actual=actual.getSiguiente();
        }
        listaClientes.agregar(c);
        return Retorno.ok();
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarSalas() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEventos() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientes() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEsperaEvento() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }

}

