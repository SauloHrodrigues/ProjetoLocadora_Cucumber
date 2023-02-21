package br.com.locadoda.servicos;

import java.util.Calendar;

import br.com.locadoda.entidades.Filme;
import br.com.locadoda.entidades.NotaAluguel;
import br.com.locadoda.utilitarios.DataUtils;


public class AluguelService {

	public NotaAluguel alugar(Filme filme, String tipoAluguel) {
		if(filme.getEstoque()==0) 
			throw new RuntimeException("Filme sem estoque");
		NotaAluguel nota = new NotaAluguel();
		if("extendido".equalsIgnoreCase(tipoAluguel)) {
			nota.setPreco(filme.getAluguel()*2);	
			nota.setDadaEntrega(DataUtils.obterDataDiferencaDias(3));
			nota.setPontuacao(2);
		}else {
			nota.setPreco(filme.getAluguel());
			nota.setDadaEntrega(DataUtils.obterDataDiferencaDias(1));
			nota.setPontuacao(1);
		}
		filme.setEstoque(filme.getEstoque()-1);
		return nota;	
	}

}
