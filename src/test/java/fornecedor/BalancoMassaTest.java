package fornecedor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class BalancoMassaTest {

    @Test
    public void testeCalculoNormal() {
        BalancoMassa balanco = new BalancoMassa(1000, 900, 50, null);
        balanco.CalculaBalancoMassa();
        assertEquals(5.0, balanco.getDesvioPerc(), 0.001);
    }

}