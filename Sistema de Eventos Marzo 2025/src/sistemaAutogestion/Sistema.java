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
        Sala salaComparacion = new Sala(nombre.trim(), 0); 

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

    
        // Validar fecha (día entre 1-30, mes entre 1-12)

    if (fecha.getDayOfMonth() < 1 || fecha.getDayOfMonth() > 30 || 
        fecha.getMonthValue() < 1 || fecha.getMonthValue() > 12) {
        return new Retorno(Retorno.Resultado.ERROR_3);  // Fecha inválida
    }

    
    
    // Verificar duplicado
    for (int i = 0; i < listaEventos.tamaño(); i++) {
        Evento evento = listaEventos.obtenerPorIndice(i);
        if (evento.getCodigo().equals(codigo)) {
            return new Retorno(Retorno.Resultado.ERROR_1);  // Ya existe
        }
    }

    // Buscar sala disponible más pequeña que cumpla con el aforo
int menorCapacidad = 0;
for (int i = 0; i < listaSalas.tamaño(); i++) {
    menorCapacidad += listaSalas.obtenerPorIndice(i).getCapacidad();
}
menorCapacidad += 1; // Asegurar que sea mayor que cualquier capacidad individual

Sala salaAsignada = null;
for (int i = 0; i < listaSalas.tamaño(); i++) {
    Sala sala = listaSalas.obtenerPorIndice(i);
    if (sala.getCapacidad() >= aforoNecesario && !sala.getFechasOcupadas().contiene(fecha)) {
        if (sala.getCapacidad() < menorCapacidad) {
            salaAsignada = sala;
            menorCapacidad = sala.getCapacidad();
        }
    }
}
    
    for (int i = 0; i < listaSalas.tamaño(); i++) {
        Sala sala = listaSalas.obtenerPorIndice(i);
        if (sala.getCapacidad() >= aforoNecesario && 
            !sala.getFechasOcupadas().contiene(fecha)) {
            
            // Si encontramos una sala con menor capacidad que la actual asignada
            if (sala.getCapacidad() < menorCapacidad) {
                salaAsignada = sala;
                menorCapacidad = sala.getCapacidad();
            }
        }
    }

    if (salaAsignada == null) {
        return new Retorno(Retorno.Resultado.ERROR_3);  // Sin salas disponibles
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
    // Paso 1: Validar la cédula
    if (!cedulaEsValida(cedula)) {
        return Retorno.error1();  // Cédula inválida
    }

    // Paso 2: Verificar si ya existe un cliente con la misma cédula
    Cliente c = new Cliente(cedula, nombre);
    Nodo<Cliente> actual = listaClientes.getInicio();
    while (actual != null) {
        if (actual.getDato().equals(c)) {
            return Retorno.error2();  // Cliente ya registrado
        }
        actual = actual.getSiguiente();
    }

    // Paso 3: Insertar el cliente de manera ordenada por cédula (ascendente)
    Nodo<Cliente> nuevoNodo = new Nodo<>(c);

    // Si la lista está vacía o el nuevo cliente tiene una cédula menor que el primer cliente
    if (listaClientes.getInicio() == null || c.compareTo(listaClientes.getInicio().getDato()) < 0) {
        nuevoNodo.setSiguiente(listaClientes.getInicio());
        listaClientes.setInicio(nuevoNodo);  // Insertar al principio
    } else {
        // Buscar la posición correcta para insertar
        Nodo<Cliente> anterior = listaClientes.getInicio();
        while (anterior.getSiguiente() != null && c.compareTo(anterior.getSiguiente().getDato()) > 0) {
            anterior = anterior.getSiguiente();
        }
        nuevoNodo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevoNodo);  // Insertar en el lugar correcto
    }

    return Retorno.ok();  // Registro exitoso
}

public boolean cedulaEsValida(String cedula) {
    // Verifica si la cédula tiene exactamente 8 caracteres numéricos
    if (cedula == null || cedula.length() != 8) {
        return false;  // Longitud incorrecta
    }

    // Verificar que todos los caracteres sean dígitos numéricos
    for (int i = 0; i < cedula.length(); i++) {
        char c = cedula.charAt(i);
        if (c < '0' || c > '9') {
            return false;  // No es un número
        }
    }

    return true;  // La cédula es válida
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
    if (listaSalas.tamaño() == 0) {
        return new Retorno(Retorno.Resultado.OK, "No hay salas registradas.");
    }


    ListaSimple<Sala> salasOrdenadas = new ListaSimple<>();
    for (int i = 0; i < listaSalas.tamaño(); i++) {
        salasOrdenadas.agregar(listaSalas.obtenerPorIndice(i));
    }

    // Paso 2: Recorrer la lista de salas y construir la salida
    String salida = "";
    for (int i = 0; i < salasOrdenadas.tamaño(); i++) {
        Sala sala = salasOrdenadas.obtenerPorIndice(i);

        // Si no es el primer elemento, se agrega el separador '#'
        if (i > 0) {
            salida += "#";
        }

        // Agregar el nombre y la capacidad de la sala
        salida += sala.getNombre() + "-" + sala.getCapacidad();
    }

    // Devolver la salida formateada
    return new Retorno(Retorno.Resultado.OK, salida);
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
    // Verificar si hay clientes registrados
    if (listaClientes.getInicio() == null) {
        return new Retorno(Retorno.Resultado.OK, "No hay clientes registrados.");
    }

    String salida = "";
    Nodo<Cliente> actual = listaClientes.getInicio();
    
    // Recorrer la lista de clientes y construir el string de salida
    while (actual != null) {
        Cliente c = actual.getDato();
        salida += c.getCedula() + "-" + c.getName();
        
        if (actual.getSiguiente() != null) {
            salida += "#";  // Agregar "#" entre los clientes, excepto al final
        }

        actual = actual.getSiguiente();
    }

    return new Retorno(Retorno.Resultado.OK, salida);
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
                    ocupadosConsecutivosActual = 0;
                } else if (celda.equals("O")) {
                    ocupadosConsecutivosActual++;
                    if (ocupadosConsecutivosActual > maxOcupadosConsecutivos) {
                        maxOcupadosConsecutivos = ocupadosConsecutivosActual;
                    }
                } else if (celda.equals("X")) {
                    libres++;
                    ocupadosConsecutivosActual = 0; 
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
