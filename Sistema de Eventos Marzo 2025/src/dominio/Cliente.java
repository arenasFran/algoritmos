
package dominio;


public class Cliente {
    private String Name;
    String Cedula; 

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }
    

    public Cliente(String name,String cedula)
    {
        this.setName(name);
        this.setCedula(cedula);
        
    }
    
    @Override
    public boolean equals(Object obj) {
     if (this == obj) return true;  
     if (obj == null || this.getClass() != obj.getClass()) return false; 

        Cliente other = (Cliente) obj;
        return this.getCedula().equals(other.getCedula()); 
    }
    
    @Override
    public String toString() {
     
        return this.getCedula() + "-" + this.getName();
    }
}
    

