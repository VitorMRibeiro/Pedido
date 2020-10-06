package br.ufes.model;

public class RegraDescontoCupom implements IPoliticaDeDesconto{

    //("10OFF", 0.10);
    //("PRIMEIRACOMPRA", 0.20);
    //("FRETEGRATIS", 0.25);

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        
        if (carrinhoCompra.getCupom().toLowerCase().equals("10OFF")){
            double valorDesconto = carrinhoCompra.valorAPagar * 0.10;
            carrinhoCompra.aplicaDesconto(valorDesconto);
        } 
        else if (carrinhoCompra.getCupom().toLowerCase().equals("PRIMEIRACOMPRA")){
            double valorDesconto = carrinhoCompra.valorAPagar * 0.20;
            carrinhoCompra.aplicaDesconto(valorDesconto);
        }
        else if (carrinhoCompra.getCupom().toLowerCase().equals("FRETEGRATIS")){
            double valorDesconto = carrinhoCompra.valorAPagar * 0.30;
            carrinhoCompra.aplicaDesconto(valorDesconto);
        }
    }
}
