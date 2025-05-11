
package sistemaAutogestion;

import java.time.LocalDate;

/**
 *
 * @author frana
 */
public class EventosTesting {
    
      public static void main(String[] args) {
        juegoprueba();
    }
      
      public static void juegoprueba(){
         Sistema s = new Sistema();
         Retorno r;
       
         
         r= s.registrarCliente("12345678", "Fran");
         r= s.registrarCliente("13345678", "Pedro");
         r= s.registrarCliente("12345679", "Goku");
         r= s.registrarCliente("87654321", "Rafa");
         r= s.registrarCliente("98765432", "Cohen");
         r= s.registrarCliente("19999999", "COtelo");
         r= s.registrarCliente("28888888", "Carmen");
         r = s.listarClientes();
         
        
        
         
         
         
      }
}
