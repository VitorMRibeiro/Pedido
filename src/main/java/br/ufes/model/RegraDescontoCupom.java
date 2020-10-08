package br.ufes.model;

public class RegraDescontoCupom implements IPoliticaDeDesconto{

    //("10OFF", 0.10);
    //("PRIMEIRACOMPRA", 0.20);
    //("FRETEGRATIS", 0.25);

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        if (carrinhoCompra.getCupom() == null){
        
        }else if (carrinhoCompra.getCupom().toLowerCase().equals("100ff")){
            double valorDesconto = carrinhoCompra.valor * 0.10;
            carrinhoCompra.calculaDesconto(valorDesconto);
        } 
        else if (carrinhoCompra.getCupom().toLowerCase().equals("primeiracompra")){
            double valorDesconto = carrinhoCompra.valor * 0.20;
            carrinhoCompra.calculaDesconto(valorDesconto);
        }
        else if (carrinhoCompra.getCupom().toLowerCase().equals("fretegratis")){
            double valorDesconto = carrinhoCompra.valor * 0.30;
            carrinhoCompra.calculaDesconto(valorDesconto);
        }
    }
}
