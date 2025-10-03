package src.test.java.main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.main.java.main.BalancoMassa;
import java.util.Arrays;
import java.util.List;

public class BalancoMassaTest {

	@Test
	public void testeCalculoNormalBalancoMassa() {

		BalancoMassa balanco= new BalancoMassa(1000,900,50,null);
		balanco.CalculaBalancoMassa();
		assertEquals(50, balanco.getDesvio());
		assertEquals(5.0, balanco.getDesvioPerc(), 0.001);
	}

	@Test
	public void testeCalculoDentroLimiteToleravel() {

		BalancoMassa balanco= new BalancoMassa(1000,950,30,null);
		balanco.CalculaBalancoMassa();
		assertEquals(2.0, balanco.getDesvioPerc(), 0.001);
	}

	@Test
	public void testeCalculoAcimaLimiteToleravel() {
		BalancoMassa balanco= new BalancoMassa(1000,840,100,null);
		balanco.CalculaBalancoMassa();
		assertEquals(6.0, balanco.getDesvioPerc(), 0.001);
		assertTrue(balanco.alerta());
	}

	@Test
	public void testeDadosObrigatoriosAusentesOuZerados() {
		BalancoMassa balanco = new BalancoMassa(0,0,0,null);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			balanco.CalculaBalancoMassa();
		});
		assertEquals("Todos os campos obrigatórios devem ser preenchidos corretamente", exception.getMessage());
	}

	@Test
	public void testeCalculoPorLote() {
		List<Lote> lotes = Arrays.asList(
				new Lote("XYZ", 1000, 950, 30),
				new Lote("ABC", 1200, 1100, 50));


		BalancoMassa balanco = new BalancoMassa(0,0,0,lotes);
		BalancoMassa resultado = balanco.CalculaBalancoMassaPorLote("XYZ", 5);

		assertEquals(20, resultado.getDesvio());
		assertEquals(2.0, resultado.getDesvioPerc(), 0.01);
		assertFalse(resultado.alerta());
	}
}



