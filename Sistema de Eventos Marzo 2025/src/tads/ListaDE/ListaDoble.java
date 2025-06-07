
package tads.ListaDE;

import tads.ListaDE.IListaDoble;



public class ListaDoble<T extends Comparable<T>> implements IListaDoble<T> {

  private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamanio;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }
      @Override
    public void agregarInicio(T x) {
        NodoDoble<T> nuevo = new NodoDoble<>(x);
        if (esVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }
  @Override
    public void mostrar() {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }
  @Override
    public int cantidadElementos() {
        return tamanio;
    }
  @Override
    public boolean esVacia() {
        return tamanio == 0;
    }
  @Override
    public void vaciar() {
        cabeza = cola = null;
        tamanio = 0;
    }
  @Override
    public boolean existeElemento(T x) {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(x)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
  @Override
    public T obtenerElemento(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }
  @Override
    public void agregarFinal(T x) {
        NodoDoble<T> nuevo = new NodoDoble<>(x);
        if (esVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamanio++;
    }

  @Override
    public void eliminarInicio() {
        if (esVacia()) return;
        if (cabeza == cola) {
            vaciar();
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
            tamanio--;
        }
    }

  @Override
    public void eliminarFinal() {
        if (esVacia()) return;
        if (cabeza == cola) {
            vaciar();
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
            tamanio--;
        }
    }
      @Override
    public void agregarOrdenado(T x) {
        if (esVacia() || cabeza.getDato().compareTo(x) >= 0) {
            agregarInicio(x);
            return;
        }
        if (cola.getDato().compareTo(x) <= 0) {
            agregarFinal(x);
            return;
        }
        NodoDoble<T> actual = cabeza;
        while (actual != null && actual.getDato().compareTo(x) < 0) {
            actual = actual.getSiguiente();
        }
        NodoDoble<T> nuevo = new NodoDoble<>(x);
        NodoDoble<T> anterior = actual.getAnterior();
        anterior.setSiguiente(nuevo);
        nuevo.setAnterior(anterior);
        nuevo.setSiguiente(actual);
        actual.setAnterior(nuevo);
        tamanio++;
    }

  


    
}
