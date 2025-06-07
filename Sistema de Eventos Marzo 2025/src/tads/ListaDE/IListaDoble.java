
package tads.ListaDE;

public interface IListaDoble<T> {
   void agregarInicio(T x);

    void mostrar();

    int cantidadElementos();

    boolean esVacia();

    void vaciar();

    boolean existeElemento(T x);

    T obtenerElemento(int indice);

    void agregarFinal(T x);

    void eliminarInicio();

    void eliminarFinal();
    
    void agregarOrdenado(T x);
}