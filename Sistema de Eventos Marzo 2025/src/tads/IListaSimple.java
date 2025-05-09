package tads;

public interface IListaSimple<T> {
    public void agregar(T dato);
    public void agregarInicio(T dato);
    public void agregarFin(T dato);
    public boolean eliminar(T dato);
    public  boolean contiene(T dato);
    public void mostrar();
    public int tamaño();
    public void esVacia();
    public void esLLena();
    public void vaciar();
    public Nodo<T> getInicio();
    public T obtenerPorIndice(int indice);
   
}
