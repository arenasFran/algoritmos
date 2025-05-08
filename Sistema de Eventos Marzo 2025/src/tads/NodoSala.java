package tads;

import dominio.Sala;

public class NodoSala {
    public Sala Sala;
    public NodoSala siguiente;

    public NodoSala(Sala sala) {
        this.Sala = sala;
        this.siguiente = null;
    }
}