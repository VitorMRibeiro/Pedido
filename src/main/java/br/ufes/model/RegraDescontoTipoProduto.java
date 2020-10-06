package br.ufes.model;

import java.util.ArrayList;

public class RegraDescontoTipoProduto implements IPoliticaDeDesconto{
            
    //("Papelaria", 0.10);
    //("Comida", 0.20);
    //("Roupa", 0.25);
    //("Calçado", 0.05);
    
    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        ArrayList<Item> itens = carrinhoCompra.getItens();

        for (Item item : itens){
            Produto produto = item.getProduto();
            String nomeProduto = produto.getNome();
            if (nomeProduto.equals("Papelaria")){
                double valorDesconto = carrinhoCompra.valorAPagar * 0.10;
                carrinhoCompra.aplicaDesconto(valorDesconto);
            } else if (nomeProduto.equals("Comida")){
                double valorDesconto = carrinhoCompra.valorAPagar * 0.20;
                carrinhoCompra.aplicaDesconto(valorDesconto);
            } else if (nomeProduto.equals("Roupa")){
                double valorDesconto = carrinhoCompra.valorAPagar * 0.25;
                carrinhoCompra.aplicaDesconto(valorDesconto);
            } else if (nomeProduto.equals("Comida")){
                double valorDesconto = carrinhoCompra.valorAPagar * 0.05;
                carrinhoCompra.aplicaDesconto(valorDesconto);
            } else {
                double valorDesconto = carrinhoCompra.valorAPagar;
                carrinhoCompra.aplicaDesconto(valorDesconto);
            }  
        }
    };
}
