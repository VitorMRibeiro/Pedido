package br.ufes.model;

public class RegraDescontoPontuacao implements IPoliticaDeDesconto{

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        Cliente cliente = carrinhoCompra.getCliente();
        
        if (cliente.getPontuacao() == 100 && carrinhoCompra.valorAPagar >= 150.00){
            double valorDesconto = carrinhoCompra.valorAPagar - 100;
            carrinhoCompra.aplicaDesconto(valorDesconto);
        } 
    }
}
