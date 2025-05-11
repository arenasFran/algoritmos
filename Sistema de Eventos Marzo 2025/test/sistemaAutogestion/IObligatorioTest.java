/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import dominio.Sala;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
    }

    @Before
    public void setUp() {
        //Inicializo el sistema
        miSistema = new Sistema();
        miSistema.registrarSala("Sala A", 10);
        miSistema.registrarSala("Sala B", 20);
        miSistema.registrarEvento("17A98", "Evento politico A", 19, LocalDate.of(2025, 10, 7));
        miSistema.registrarEvento("16B97", "Evento politico B", 8, LocalDate.of(2025, 10, 7));
        miSistema.registrarCliente("1235678", "Pedro Alfonso");
        miSistema.registrarCliente("72829292", "Ryan McLaren");

    }

    @Test
    public void testCrearSistemaDeGestionOk() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaOk() {
        Retorno r = miSistema.registrarSala("Sala C", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sala D", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sala E", 150);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaError1() {

        Retorno r = miSistema.registrarSala("Sala A", 15);
        assertEquals(Retorno.error1().resultado, r.resultado);
        r = miSistema.registrarSala("", 10);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaError2() {
        Retorno r = miSistema.registrarSala("Sala Z", -15);
        assertEquals(Retorno.error2().resultado, r.resultado);
        r = miSistema.registrarSala("Sala W", -0);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testEliminarSalaOk() {
        Retorno r = miSistema.registrarSala("Sala Z", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.eliminarSala("Sala Z");
        assertEquals(Retorno.ok().resultado, r.resultado);

    }

    @Test
    public void testEliminarSalaError1() {
        Retorno r = miSistema.registrarSala("Sala Z", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.eliminarSala("Sala ZZ");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEventoOk() {
        Retorno r = miSistema.registrarSala("Sala Z", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarSala("Sala test", 40);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("8a96", "Evento politico 1", 15, LocalDate.of(2025, 10, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("8a77", "Evento politico 2", 39, LocalDate.of(2025, 10, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);

    }

    @Test
    public void testRegistrarEventoError1() {
        Retorno r = miSistema.registrarSala("Sala Z", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("8a96", "Evento politico 1", 14, LocalDate.of(2025, 10, 7));
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("8a96", "Evento politico 1", 14, LocalDate.of(2025, 10, 7));
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEventoError2() {
        Retorno r = miSistema.registrarSala("Sala Z", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarEvento("8a96", "Evento politico 1", -30, LocalDate.of(2025, 10, 7));
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEventoError3() {

        Retorno r = miSistema.registrarEvento("8a96", "Evento politico 1", 30, LocalDate.of(2025, 10, 7));
        assertEquals(Retorno.error3().resultado, r.resultado);
        r = miSistema.registrarEvento("8a95", "Evento politico 3", 40, LocalDate.of(2025, 10, 6));
        assertEquals(Retorno.error3().resultado, r.resultado);

    }

    @Test
    public void testRegistrarCliente() {
        Retorno r = miSistema.registrarCliente("12345678", "Gerald Rivia");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.registrarCliente("21321", "Alastor Moody");
        assertEquals(Retorno.error1().resultado, r.resultado);

        // registrar con la misma cedula
    }

    @Test
    public void testListarSalas() {
        Retorno r = miSistema.listarSalas();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testListarEventosOk() {
        Retorno r = miSistema.listarEventos();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testListarClientes() {
        Retorno r = miSistema.listarClientes();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testEsSalaOptima() {
        // Preparamos una matriz como en el ejemplo del enunciado
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
            {"#", "#", "#", "O", "#", "#", "#"},};
            

        Retorno r = miSistema.esSalaOptima(vistaSala);

        assertEquals(Retorno.ok().resultado, r.resultado);

        assertTrue(r.valorString.contains("Es óptimo"));
    }

}
