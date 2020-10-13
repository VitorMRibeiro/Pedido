package br.ufes.model;

import java.time.LocalDate;

public class RegraDescontoAniversariante implements IPoliticaDeDesconto{

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        Cliente cliente = carrinhoCompra.getCliente();
        LocalDate hoje = LocalDate.now();
                
        if (cliente.getDataNascimento().getMonth()== hoje.getMonth() && cliente.getDataNascimento().getDayOfMonth() == hoje.getDayOfMonth()){
            double valorDesconto = carrinhoCompra.getValor() * 0.2;
            carrinhoCompra.calculaDesconto(valorDesconto);
            
            System.out.println("Desconto de aniversariante: " + valorDesconto);
            //System.out.println(valorDesconto);
        } 
    }
}
