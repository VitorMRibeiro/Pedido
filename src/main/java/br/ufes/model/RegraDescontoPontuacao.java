package br.ufes.model;

public class RegraDescontoPontuacao implements IPoliticaDeDesconto{

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        Cliente cliente = carrinhoCompra.getCliente();
        
       if ((carrinhoCompra.valor >= 500)){
           carrinhoCompra.calculaDesconto(cliente.getPontuacao());
           System.out.println(" desc pontuação");
           System.out.println(cliente.getPontuacao());
        } 
    }
}
