package br.ufes.model;

import java.time.LocalDate;

public class RegraDescontoAniversariante implements IPoliticaDeDesconto{

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        Cliente cliente = carrinhoCompra.getCliente();
        final LocalDate hoje = LocalDate.now();
        
        if (cliente.getDataNascimento().equals(hoje)){
            double valorDesconto = carrinhoCompra.getValor() * 0.2;
            carrinhoCompra.aplicaDesconto(valorDesconto);
        } 
    }
}
