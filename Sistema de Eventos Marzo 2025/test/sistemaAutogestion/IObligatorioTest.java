/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class IObligatorioTest {

    private Sistema sistema;

    public IObligatorioTest() {
 	sistema = new Sistema();
    }

    @Before
    public void setUp() {
        sistema = new Sistema();
        sistema.crearSistemaDeGestion();
    }

    @Test
    public void testCrearSistemaDeGestion() {
        Retorno ret = sistema.crearSistemaDeGestion();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarSala() {
        Retorno ret = sistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	 ret = sistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarSala_ERROR1() {
	Retorno ret = sistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	ret = sistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = sistema.registrarSala("Sala A", 100);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testRegistrarSala_ERROR2() {

	Retorno ret = sistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

         ret = sistema.registrarSala("Sala B", 0);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);

	ret = sistema.registrarSala("Sala B", -10);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    @Test
    public void testEliminarSala() {
        Retorno ret = sistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = sistema.eliminarSala("Sala A");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarEvento() {
        Retorno ret = sistema.registrarSala("Sala A", 100);
	assertEquals(Retorno.Resultado.OK, ret.resultado);

        LocalDate fecha = LocalDate.of(2025, 5, 10);
        ret = sistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarCliente() {
        Retorno ret = sistema.registrarCliente("12345678", "Juan Pérez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);

	ret = sistema.registrarCliente("12345444", "Martina Gutierrez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarCliente_ERROR1() {
        Retorno ret = sistema.registrarCliente("1234", "Juan Pérez");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

	ret = sistema.registrarCliente("AA345444", "Martina Gutierrez");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testListarSalas() {
        sistema.registrarSala("Sala A", 50);
        sistema.registrarSala("Sala B", 70);
        sistema.registrarSala("Sala C", 100);
        Retorno ret = sistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Sala C-100#Sala B-70#Sala A-50", ret.valorString);
    }

    @Test
    public void testListarEventos() {
        //Completar para primera entrega
    }

    @Test
    public void testListarClientes() {
        sistema.registrarCliente("45678992", "Micaela Ferrez");
    	sistema.registrarCliente("23331111", "Martina Rodríguez");
    	sistema.registrarCliente("35679992", "Ramiro Perez");

    	Retorno ret = sistema.listarClientes();
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("23331111-Martina Rodríguez#35679992-Ramiro Perez#45678992-Micaela Ferrez", ret.valorString);
    }

    @Test
    public void testEsSalaOptima() {
        String[][] vistaSala = {
        {"#", "#", "#", "#", "#", "#", "#"},
        {"#", "#", "X", "X", "X", "X", "#"},
        {"#", "O", "O", "X", "X", "X", "#"},
        {"#", "O", "O", "O", "O", "X", "#"},
        {"#", "O", "O", "X", "O", "O", "#"},
        {"#", "O", "O", "O", "O", "O", "#"},
        {"#", "X", "X", "O", "O", "O", "O"},
        {"#", "X", "X", "O", "O", "O", "X"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "#", "#", "O", "#", "#", "#"},
        {"#", "#", "#", "O", "#", "#", "#"}
    	};


	Retorno ret = sistema.esSalaOptima(vistaSala);
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("Es óptimo", ret.valorString);
    }
    
    @Test
public void testListarSalasFormatoYOrden() {
    miSistema.registrarSala("Sala C", 15);
    miSistema.registrarSala("Sala D", 1);
    miSistema.registrarSala("Sala E", 150);

    Retorno r = miSistema.listarSalas();
    assertEquals(Retorno.ok().resultado, r.resultado);

    String esperado = "Sala E-150#Sala D-1#Sala C-15#Sala B-20#Sala A-10";
    assertEquals(esperado, r.valorString);
}

    @Test
public void testListarEventosFormatoYOrden() {
    // Eventos registrados en setUp()

    Retorno r = miSistema.listarEventos();
    assertEquals(Retorno.ok().resultado, r.resultado);

    String esperado = "16B97-Evento politico B-Sala A-20-0#17A98-Evento politico A-Sala B-10-0";
    assertEquals(esperado, r.valorString);
}

@Test
public void testListarClientesFormatoYOrden() {
    // Clientes registrados en setUp()

    Retorno r = miSistema.listarClientes();
    assertEquals(Retorno.ok().resultado, r.resultado);

    String esperado = "1235678-Pedro Alfonso#72829292-Ryan McLaren";
    assertEquals(esperado, r.valorString);
}

}
