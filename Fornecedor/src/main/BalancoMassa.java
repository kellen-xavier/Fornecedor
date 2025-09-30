package main;
import java.util.List;

public class BalancoMassa {

	private int perdas;
	private int materiaPrimaConsumida;
	private int produtoGerado;
	private int desvio;
	private double desvioPerc;
	private double limiteToleravel;
	private List<Lote> lotes;
	
	public BalancoMassa(int materiaPrimaConsumida, int produtoGerado, int perdas, List<Lote> lotes) {
		this.materiaPrimaConsumida= materiaPrimaConsumida;
		this.produtoGerado=  produtoGerado;
		this.perdas= perdas;
		this.lotes=lotes;
	}
	
	public void CalculaBalancoMassa() {
		if(this.produtoGerado <=0 || this.materiaPrimaConsumida <=0 || this.perdas<=0) {
	        throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos corretamente");
		}
		this.desvio= this.materiaPrimaConsumida - (this.produtoGerado + this.perdas);
		this.desvioPerc= ((double)this.desvio/this.materiaPrimaConsumida)* 100;
	}

	public int getDesvio() {
		return this.desvio;
	}

	public double getDesvioPerc() {
		return this.desvioPerc;
	}
	
	public void setLimiteToleravel(double limite) {
		this.limiteToleravel= limite;
	}
	
	public boolean  alerta() {
		if(this.limiteToleravel<this.desvioPerc)
			return true;
		return false;
	}
	
	public BalancoMassa CalculaBalancoMassaPorLote(String idLote, double limiteToleravel) {
	    for (Lote lote : lotes) {
	        if (lote.getId().equals(idLote)) {
	          
	            this.materiaPrimaConsumida = lote.getMateriaPrimaConsumida();
	            this.produtoGerado = lote.getProdutoGerado();
	            this.perdas = lote.getPerdas();
	            this.limiteToleravel = limiteToleravel;

	            this.CalculaBalancoMassa(); 
	            return this; 
	        }
	    }
	    throw new IllegalArgumentException("Lote não encontrado");
	}
}
