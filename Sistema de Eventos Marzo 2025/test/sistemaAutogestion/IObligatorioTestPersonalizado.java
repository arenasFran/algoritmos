/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author PCMYA
 */
public class IObligatorioTestPersonalizado {
    
    
    



    private Obligatorio sistema;
   public IObligatorioTestPersonalizado() {
 	sistema = new Obligatorio();
    }

    @Before
    public void setUp() {
        sistema = new Obligatorio();
        sistema.crearSistemaDeGestion();
    }

    // REGISTRO DE SALAS
    @Test
    public void testRegistrarSala_NombreVacio() {
        Retorno ret = sistema.registrarSala("", 50);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado); // Según implementación esperada
    }

    @Test
    public void testRegistrarSala_NombreNulo() {
        Retorno ret = sistema.registrarSala(null, 50);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testRegistrarSala_CapacidadMaxima() {
        Retorno ret = sistema.registrarSala("Sala Grande", Integer.MAX_VALUE);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    // REGISTRO DE EVENTOS


    @Test
    public void testRegistrarEvento_FechaHoy() {
        sistema.registrarSala("Sala A", 100);
        Retorno ret = sistema.registrarEvento("E2", "Evento Hoy", 50, LocalDate.now());
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarEvento_CapacidadIgualSala() {
        sistema.registrarSala("Sala B", 80);
        Retorno ret = sistema.registrarEvento("E3", "Justo", 80, LocalDate.now().plusDays(1));
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    // REGISTRO DE CLIENTES
    @Test
    public void testRegistrarCliente_CedulaVacia() {
        Retorno ret = sistema.registrarCliente("", "Sin Cédula");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testRegistrarCliente_CedulaConEspacios() {
        Retorno ret = sistema.registrarCliente(" 87654321 ", "Con espacios");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    // SALA ÓPTIMA
    @Test
    public void testEsSalaOptima_TodasOcupadas() {
        String[][] vista = new String[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                vista[i][j] = "X";
        Retorno ret = sistema.esSalaOptima(vista);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("No es óptimo", ret.valorString); // Suposición: 100% ocupación no es óptima
    }

    @Test
    public void testEsSalaOptima_VistaVacia() {
        String[][] vista = {};
        Retorno ret = sistema.esSalaOptima(vista);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado); // Esperando manejo adecuado
    }

    @Test
  public void testSalaNoOptima() {
    String[][] vistaSala = {
        {"#", "#", "#", "#", "#"},
        {"#", "O", "X", "X", "#"},
        {"#", "O", "O", "X", "#"},
        {"#", "O", "O", "X", "#"},
        {"#", "O", "X", "X", "#"},
        {"#", "X", "X", "X", "#"},
        {"#", "#", "#", "#", "#"}
    };

    Retorno ret = sistema.esSalaOptima(vistaSala);
    assertEquals(Retorno.Resultado.OK, ret.resultado);
    assertEquals("No es óptimo", ret.valorString);
}
}

    
    
    
    
    
    
    
    
    
    
    
    

