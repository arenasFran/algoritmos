package dominio;

public class Cliente implements Comparable<Cliente> {

    private String name;
    private String cedula;

    public Cliente(String name, String cedula) {
        this.name = name;
        this.cedula = cedula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Cliente other = (Cliente) obj;
        return this.cedula.equals(other.getCedula());
    }

    @Override
    public String toString() {
        return this.cedula + "-" + this.name;
    }

    @Override
    public int compareTo(Cliente otro) {
        return this.cedula.compareTo(otro.getCedula());
    }
}
