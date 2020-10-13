package br.ufes.model;

import java.util.ArrayList;

public class RegraDescontoTipoProduto implements IPoliticaDeDesconto{
            
    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        ArrayList<Item> itens = new ArrayList<>(carrinhoCompra.getItens());

        for (Item item : itens){
            Produto produto = item.getProduto();
            String tipoProduto = produto.getTipo();
            switch (tipoProduto) {
                case "Calçado":
                    {
                        carrinhoCompra.calculaDesconto(item.getValorItem() * 0.05);
                        System.out.println("Desconto produto tipo " + tipoProduto + ": " + (item.getValorItem() * 0.05));
                        break;
                    }
                case "Papelaria":
                    {
                        carrinhoCompra.calculaDesconto(item.getValorItem() * 0.10);
                        System.out.println("Desconto produto tipo " + tipoProduto + ": " + (item.getValorItem() * 0.10));
                        break;
                    }
                case "Comida":
                    {
                        carrinhoCompra.calculaDesconto(item.getValorItem() * 0.20);  
                        System.out.println("Desconto produto tipo " + tipoProduto + ": " + (item.getValorItem() * 0.20));
                        break;
                    }
                case "Roupa":
                    {
                        carrinhoCompra.calculaDesconto(item.getValorItem() * 0.25);
                        System.out.println("Desconto produto tipo " + tipoProduto + ": " + (item.getValorItem() * 0.25));
                        break;
                    }
            
                default:
                    break;
            }
            //System.out.println("desc produto");
            //System.out.println(valorDesconto);
        }
    };
}
