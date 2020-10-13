package br.ufes.model;

import java.util.ArrayList;

public class RegraDescontoTipoProduto implements IPoliticaDeDesconto{
            
    //("Papelaria", 0.10);
    //("Comida", 0.20);
    //("Roupa", 0.25);
    //("Calçado", 0.05);
    
    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        ArrayList<Item> itens = new ArrayList<>(carrinhoCompra.getItens());

        for (Item item : itens){
            Produto produto = item.getProduto();
            String tipoProduto = produto.getTipo();
            if (tipoProduto.equals("Calçado")){
                double valorDesconto = item.getValorItem() * 0.05;
                carrinhoCompra.calculaDesconto(valorDesconto);
            } else if (tipoProduto.equals("Papelaria")){
                double valorDesconto = item.getValorItem() * 0.10;
                carrinhoCompra.calculaDesconto(valorDesconto);
                System.out.println(valorDesconto);
            } else if (tipoProduto.equals("Comida")){
                double valorDesconto = item.getValorItem() * 0.20;
                carrinhoCompra.calculaDesconto(valorDesconto);
                System.out.println(valorDesconto);
            } else if (tipoProduto.equals("Roupa")){
                double valorDesconto = item.getValorItem() * 0.25;
                carrinhoCompra.calculaDesconto(valorDesconto);
            } else {
                //double valorDesconto = carrinhoCompra.valor;
                //carrinhoCompra.calculaDesconto(valorDesconto);
            }  
            System.out.println("desc produto");
            //System.out.println(valorDesconto);
        }
    };
}
