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

    public Sistema() {
        listaClientes = new ListaSimple<Cliente>();
        listaSalas = new ListaSimple<Sala>();
        listaEventos = new ListaSimple<Evento>();
    }

    @Override
    public Retorno crearSistemaDeGestion() {
        listaClientes = new ListaSimple<Cliente>();
        listaSalas = new ListaSimple<Sala>();
        listaEventos = new ListaSimple<Evento>();
        return Retorno.ok();
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
        listaSalas.agregarInicio(nuevaSala);

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
        if (aforoNecesario <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_2);  // Aforo no válido
        }

        // Verificar duplicado
        for (int i = 0; i < listaEventos.tamaño(); i++) {
            Evento evento = listaEventos.obtenerPorIndice(i);
            if (evento.getCodigo().equals(codigo)) {
                return new Retorno(Retorno.Resultado.ERROR_1);  // Ya existe
            }
        }

        // Buscar sala disponible
        Sala salaAsignada = null;
        for (int i = 0; i < listaSalas.tamaño(); i++) {
            Sala sala = listaSalas.obtenerPorIndice(i);
            if (sala.getCapacidad() >= aforoNecesario && !sala.getFechasOcupadas().contiene(fecha)) {
                salaAsignada = sala;
                break;
            }
        }

        if (salaAsignada == null) {
            return new Retorno(Retorno.Resultado.ERROR_3);  // Sin salas
        }

        // Marcar fecha como ocupada en la sala asignada
        salaAsignada.getFechasOcupadas().agregar(fecha);

        // Crear y registrar evento
        Evento nuevoEvento = new Evento(codigo, descripcion, aforoNecesario, fecha, salaAsignada);
        listaEventos.agregarFin(nuevoEvento);

        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        Cliente c = new Cliente(cedula, nombre);

        if (cedula.length() != 8) {
            return Retorno.error1();
        }

        // Verificar si ya existe
        Nodo<Cliente> actual = listaClientes.getInicio();
        while (actual != null) {
            if (actual.getDato().equals(c)) {
                return Retorno.error2();
            }
            actual = actual.getSiguiente();
        }

        // Insertar ordenadamente por cédula (ascendente)
        Nodo<Cliente> nuevoNodo = new Nodo<>(c);
        if (listaClientes.getInicio() == null
                || c.compareTo(listaClientes.getInicio().getDato()) < 0) {
            // Insertar al principio
            nuevoNodo.setSiguiente(listaClientes.getInicio());
            listaClientes.setInicio(nuevoNodo);
        } else {
            // Buscar posición
            Nodo<Cliente> anterior = listaClientes.getInicio();
            while (anterior.getSiguiente() != null
                    && c.compareTo(anterior.getSiguiente().getDato()) > 0) {
                anterior = anterior.getSiguiente();
            }
            nuevoNodo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevoNodo);
        }

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
        listarSalasInversoRecursivo(listaSalas.getInicio(), false);
        return Retorno.ok();
    }

    private void listarSalasInversoRecursivo(Nodo<Sala> nodo, boolean esUltima) {
        if (nodo == null) {
            return;
        }

        // cuando llegamos al último nodo esultima pasa a true
        listarSalasInversoRecursivo(nodo.getSiguiente(), nodo.getSiguiente() == null);

        System.out.print(nodo.getDato());

        if (!esUltima) {
            System.out.print('#');
        }

    }

    @Override
    public Retorno listarEventos() {
        if (listaEventos.tamaño() == 0) {
            return new Retorno(Retorno.Resultado.OK, "No hay eventos registrados.");
        }

        // Paso 1: Copiar los eventos a una nueva ListaSimple para ordenarlos
        ListaSimple<Evento> eventosOrdenados = new ListaSimple<>();
        for (int i = 0; i < listaEventos.tamaño(); i++) {
            eventosOrdenados.agregar(listaEventos.obtenerPorIndice(i));
        }

        // Paso 2: Ordenar la lista usando Bubble Sort (comparando códigos)
        for (int i = 0; i < eventosOrdenados.tamaño() - 1; i++) {
            for (int j = 0; j < eventosOrdenados.tamaño() - i - 1; j++) {
                Evento e1 = eventosOrdenados.obtenerPorIndice(j);
                Evento e2 = eventosOrdenados.obtenerPorIndice(j + 1);

                if (e1.getCodigo().compareToIgnoreCase(e2.getCodigo()) > 0) {
                    // Intercambio manual
                    Evento temp = e1;
                    eventosOrdenados.eliminar(e1);
                    eventosOrdenados.eliminar(e2);
                    eventosOrdenados.agregarInicio(e2);
                    eventosOrdenados.agregarInicio(temp);

                    // Restaurar el orden del resto
                    ListaSimple<Evento> temporal = new ListaSimple<>();
                    for (int k = 2; k < eventosOrdenados.tamaño(); k++) {
                        temporal.agregar(eventosOrdenados.obtenerPorIndice(k));
                    }
                    eventosOrdenados.vaciar();
                    eventosOrdenados.agregar(temp);
                    eventosOrdenados.agregar(e2);
                    for (int k = 0; k < temporal.tamaño(); k++) {
                        eventosOrdenados.agregar(temporal.obtenerPorIndice(k));
                    }
                }
            }
        }

        // Paso 3: Construir el string de retorno
        String salida = "";
        for (int i = 0; i < eventosOrdenados.tamaño(); i++) {
            Evento ev = eventosOrdenados.obtenerPorIndice(i);
            int capacidadTotal = ev.getSalaAsignada().getCapacidad();
            int vendidas = (ev.getEntradasVendidas() != null) ? ev.getEntradasVendidas().tamaño() : 0;
            int disponibles = capacidadTotal - vendidas;

            salida += "Código: " + ev.getCodigo() + "\n";
            salida += "Descripción: " + ev.getDescripcion() + "\n";
            salida += "Sala asignada: " + ev.getSalaAsignada().getNombre() + "\n";
            salida += "Entradas disponibles: " + disponibles + "\n";
            salida += "Entradas vendidas: " + vendidas + "\n";
            salida += "-------------------------\n";
        }
        System.out.println(salida); // <---Debug
        return new Retorno(Retorno.Resultado.OK, salida.toString().trim());
    }

    @Override
    public Retorno listarClientes() {
        Nodo<Cliente> actual = listaClientes.getInicio();

        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getSiguiente() != null) {
                System.out.print('#');
            }
            actual = actual.getSiguiente();
        }

        return Retorno.ok();
    }

    @Override

    public Retorno esSalaOptima(String vistaSala[][]) {
        if (vistaSala == null || vistaSala.length == 0 || vistaSala[0].length == 0) {
            return Retorno.error1();
        }

        int filas = vistaSala.length;
        int columnas = vistaSala[0].length;

        int columnasOptimas = 0;

        for (int c = 0; c < columnas; c++) {
            int maxOcupadosConsecutivos = 0;
            int ocupadosConsecutivosActual = 0;
            int libres = 0;

            for (int f = 0; f < filas; f++) {
                String celda = vistaSala[f][c];

                if (celda.equals("#")) {
                    ocupadosConsecutivosActual = 0; // límite, reinicia
                } else if (celda.equals("O")) {
                    ocupadosConsecutivosActual++;
                    if (ocupadosConsecutivosActual > maxOcupadosConsecutivos) {
                        maxOcupadosConsecutivos = ocupadosConsecutivosActual;
                    }
                } else if (celda.equals("X")) {
                    libres++;
                    ocupadosConsecutivosActual = 0; // rompe la racha
                }
            }

            if (maxOcupadosConsecutivos > libres) {
                columnasOptimas++;
            }
        }

        if (columnasOptimas >= 2) {
            return Retorno.ok("Es óptimo");
        } else {
            return Retorno.ok("No es óptimo");
        }
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
