/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

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
    }

    @Test
    public void testCrearSistemaDeGestion() {
        //Completar para primera entrega
    }

    @Test
    public void testRegistrarSalaOk() {
        Retorno r = miSistema.registrarSala("El che", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
         r = miSistema.registrarSala("El che2", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
         r = miSistema.registrarSala("El che3", 150);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
      @Test
     public void testRegistrarSalaError1() {
            Retorno r = miSistema.registrarSala("El che", 15);
        assertEquals(Retorno.ok().resultado, r.resultado);
          r = miSistema.registrarSala("El che", 15);
        assertEquals(Retorno.error1().resultado, r.resultado);
            r = miSistema.registrarSala("", 10);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }
       @Test
      public void testRegistrarSalaError2() {
   Retorno r = miSistema.registrarSala("El che4", -15);
        assertEquals(Retorno.error2().resultado, r.resultado);
            r = miSistema.registrarSala("El che5", -0);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testEliminarSala() {
        //Completar para primera entrega
    }

    @Test
    public void testRegistrarEvento() {
        //Completar para primera entrega
    }

    @Test
    public void testRegistrarCliente() {
        //Completar para primera entrega
    }

    @Test
    public void testListarSalas() {
        //Completar para primera entrega
    }

    @Test
    public void testListarEventos() {
        //Completar para primera entrega
    }

    @Test
    public void testListarClientes() {
        //Completar para primera entrega
    }

    @Test
    public void testEsSalaOptima() {
        //Completar para primera entrega
    }

}
