package business;

import br.ufes.model.IPoliticaDeDesconto;
import br.ufes.model.CarrinhoDeCompra;
import java.util.ArrayList;
import br.ufes.model.RegraDescontoAniversariante;
import br.ufes.model.RegraDescontoCupom;
import br.ufes.model.RegraDescontoPontuacao;
import br.ufes.model.RegraDescontoTipoProduto;

public class ProcessaPoliticaDesconto {
    private final ArrayList<IPoliticaDeDesconto> regrasDesconto;

    public ProcessaPoliticaDesconto() {
        this.regrasDesconto = new ArrayList<>();
        
        regrasDesconto.add(new RegraDescontoAniversariante());
        regrasDesconto.add(new RegraDescontoCupom());
        regrasDesconto.add(new RegraDescontoPontuacao());
        regrasDesconto.add(new RegraDescontoTipoProduto());
    }
    
    public void executa(CarrinhoDeCompra carrinhoCompra){
        for (IPoliticaDeDesconto regraDesconto : regrasDesconto){
            regraDesconto.calcular(carrinhoCompra);
        }
    }
}
