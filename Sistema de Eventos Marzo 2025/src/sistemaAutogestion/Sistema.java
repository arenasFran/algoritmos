package sistemaAutogestion;

import dominio.Cliente;
import java.time.LocalDate;

public class Sistema implements IObligatorio {

    public Sistema(){
        
    }
    
    
    
    
    @Override
    public Retorno crearSistemaDeGestion() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarSala(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        
        Cliente c = new Cliente(cedula,nombre);
        
        String cedulaStr = String.valueOf(cedula);
        if(cedulaStr.length() != 8)
        {
            return Retorno.error1();
        }
        return Retorno.noImplementada();
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
