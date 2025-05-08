package tads;

public interface IListaSimple<T> {
    void agregarInicio(T dato);
    void agregarFin(T dato);
    boolean eliminar(T dato);
    boolean contiene(T dato);
    void mostrar();
    int tamaño();
}
