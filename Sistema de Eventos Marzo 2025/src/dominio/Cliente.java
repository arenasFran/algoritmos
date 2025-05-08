
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
    
           
}
