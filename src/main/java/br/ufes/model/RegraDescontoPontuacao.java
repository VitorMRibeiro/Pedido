package br.ufes.model;

public class RegraDescontoPontuacao implements IPoliticaDeDesconto{

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        Cliente cliente = carrinhoCompra.getCliente();
        
        if (cliente.getPontuacao() == 100 && carrinhoCompra.valor >= 150.00){
            double valorDesconto = carrinhoCompra.valor - 100;
            carrinhoCompra.calculaDesconto(valorDesconto);
        } 
    }
}
