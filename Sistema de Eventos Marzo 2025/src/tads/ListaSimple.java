package tads;

public class ListaSimple<T> implements IListaSimple<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public ListaSimple() {
        this.inicio = null;
        this.fin = null;
        this.tamaño = 0;
    }
        @Override
        public T obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        
        Nodo<T> actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }
    
    
    @Override
    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            Nodo<T> actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    @Override
    public void agregarInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(inicio);
        inicio = nuevoNodo;
        tamaño++;
    }

    @Override
    public void agregarFin(T dato) {
        agregar(dato);  // Internamente usará el método agregar
    }

    @Override
    public boolean eliminar(T dato) {
        if (inicio == null) {
            return false;
        }
        if (inicio.getDato().equals(dato)) {
            inicio = inicio.getSiguiente();
            tamaño--;
            return true;
        }
        Nodo<T> actual = inicio;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public boolean contiene(T dato) {
        Nodo<T> actual = inicio;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public void mostrar() {
        Nodo<T> actual = inicio;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    @Override
    public int tamaño() {
        return tamaño;
    }

    @Override
    public void esVacia() {
        if (inicio == null) {
            System.out.println("La lista está vacía.");
        } else {
            System.out.println("La lista no está vacía.");
        }
    }

    @Override
    public void esLLena() {
        // Este método depende de si la lista tiene un límite, 
        // pero en una lista enlazada simple generalmente no está "llena".
        System.out.println("La lista nunca está llena en una implementación de lista enlazada simple.");
    }

    public Nodo<T> getInicio() {
        return inicio;
    }
      @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        tamaño = 0;
    }
}
