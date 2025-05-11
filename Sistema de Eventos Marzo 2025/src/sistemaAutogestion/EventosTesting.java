
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
        
       
         
        Sistema sistema = new Sistema();

        
        sistema.registrarCliente("12345678", "Franchesco");
        sistema.registrarCliente("12345679", "Falucho");
        sistema.registrarCliente("23456789", "Pedro");
        sistema.registrarCliente("92345678", "Giuliani");
        sistema.registrarCliente("98345678", "Radamel");
        sistema.registrarCliente("99123413", "Sicora");
        sistema.listarClientes();

         
        
        
         
         
         
      }
}
