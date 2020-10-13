package br.ufes.business;

import br.ufes.model.CarrinhoDeCompra;
import java.util.ArrayList;
import br.ufes.model.IPoliticaDeDesconto;
import br.ufes.model.RegraDescontoAniversariante;
import br.ufes.model.RegraDescontoCupom;
import br.ufes.model.RegraDescontoPontuacao;
import br.ufes.model.RegraDescontoTipoProduto;

public class ProcessaPoliticaDesconto {
    private ArrayList<IPoliticaDeDesconto> regrasDesconto = new ArrayList<>();

    public ProcessaPoliticaDesconto() {
        regrasDesconto.add(new RegraDescontoTipoProduto());
        regrasDesconto.add(new RegraDescontoAniversariante());
        regrasDesconto.add(new RegraDescontoCupom());
        regrasDesconto.add(new RegraDescontoPontuacao());        
    }
    
    public void executa(CarrinhoDeCompra carrinhoCompra){
        for (IPoliticaDeDesconto regraDesconto : regrasDesconto){
            regraDesconto.calcular(carrinhoCompra);
        }
    }
}
