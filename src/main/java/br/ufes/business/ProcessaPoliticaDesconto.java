package br.ufes.business;

import java.util.ArrayList;
import br.ufes.model.IPoliticaDeDesconto;
import br.ufes.model.RegraDescontoAniversariante;
import br.ufes.model.RegraDescontoCupom;
import br.ufes.model.RegraDescontoPontuacao;
import br.ufes.model.RegraDescontoTipoProduto;

public class ProcessaPoliticaDesconto {
    private ArrayList<IPoliticaDesconto> regrasDesconto = new ArrayList<>();

    public ProcessaPoliticaDesconto() {
        regrasDesconto.add(new RegraDescontoAniversariante());
        regrasDesconto.add(new RegraDescontoCupom());
        regrasDesconto.add(new RegraDescontoPontuacao());
        regrasDesconto.add(new RegraDescontoTipoProduto());
    }
    
    public void executa(CarrinhoDeCompra carrinhoCompra){
        for (IPoliticaDesconto regraDesconto : regrasDesconto){
            regraDesconto.calcular(carrinhoCompra);
        }
    }
}