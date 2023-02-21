package br.com.locadora.steps;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;

import br.com.locadoda.entidades.Filme;
import br.com.locadoda.entidades.NotaAluguel;
import br.com.locadoda.entidades.TipoAluguel;
import br.com.locadoda.servicos.AluguelService;
import br.com.locadoda.utilitarios.DataUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;


public class ProjetoLocadora_Steps {
	private Filme filme;
	private AluguelService aluguel= new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private TipoAluguel tipoAluguel;
	
	
	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void um_filme_com_estoque_de_unidades(int arg1) throws Throwable {
	   filme = new Filme();
	   filme.setEstoque(arg1);
	}
	
	@Dado("^um filme$")
	public void umFilme(DataTable tabela) throws Throwable {
	    Map<String, String> map = tabela.asMap(String.class, String.class);
	    filme = new Filme();
		 filme.setEstoque(Integer.parseInt(map.get("estoque")));
		 filme.setAluguel(Integer.parseInt(map.get("preco")));
		 String tipo = map.get("tipo");
		 tipoAluguel = tipo.equals("semanal")?TipoAluguel.SEMANAL: tipo.equals("extendido")?
				    TipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
		 
		 
	}

	@Dado("^que o preço de aluguel seja R\\$ (\\d+)$")
	public void que_o_preço_de_aluguel_seja_R$(int arg1) throws Throwable {
	    filme.setAluguel(arg1);
	}

	@Quando("^alugar$")
	public void alugar() throws Throwable {
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		}catch(RuntimeException e){
			erro = e.getMessage();
		}
			
	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void o_preço_do_aluguel_será_R$1(int arg1) throws Throwable {
	   Assert.assertEquals(arg1, nota.getPreco());
	}

	@Então("^o estoque do filme será (\\d+) unidade$")
	public void o_estoque_do_filme_será_unidade(int arg1) throws Throwable {
	    Assert.assertEquals(arg1, filme.getEstoque());
	}

	@Então("^não será possivel por falta de estoque$")
	public void não_será_possivel_por_falta_de_estoque() throws Throwable {
		Assert.assertEquals("Filme sem estoque", erro);
	}

	@Dado("^o preço do aluguél seja (\\d+)$")
	public void o_preço_do_aluguél_seja(int arg1) throws Throwable {
	    
	}

	@Dado("^que o tipo do aluguel seja (.*)$")
	public void que_o_tipo_do_aluguel_seja_extendido(String tipo) throws Throwable {
	   tipoAluguel = tipo.equals("semanal")?TipoAluguel.SEMANAL: tipo.equals("extendido")?
			    TipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
	}

	@Então("^o preço do aluguel será R\\$(\\d+)$")
	public void o_preço_do_aluguel_será_R$(int arg1) throws Throwable {
	   
	}

	@Então("^a data de entrega sera em (\\d+) dias?$")
	public void a_data_de_entrega_sera_em_dias(int arg1) throws Throwable {
		Date dataEsperada = DataUtils.obterDataDiferencaDias(arg1);
		Date dataReal = nota.getDataEntrega();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
	}

	@Então("^a pontuação recebida será de (\\d+) pontos$")
	public void a_pontuação_recebida_será_de_pontos(int arg1) throws Throwable {
	    Assert.assertEquals(arg1, nota.getPontuacao());
	}
}
