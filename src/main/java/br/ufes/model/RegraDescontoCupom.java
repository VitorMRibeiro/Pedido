package br.ufes.model;

public class RegraDescontoCupom implements IPoliticaDeDesconto{

    //("10OFF", 0.10);
    //("PRIMEIRACOMPRA", 0.20);
    //("FRETEGRATIS", 0.25);

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        if (carrinhoCompra.getCupom() != null){
        
            if (carrinhoCompra.getCupom().toLowerCase().equals("100ff")){
                double valorDesconto = carrinhoCompra.valor * 0.10;
                carrinhoCompra.calculaDesconto(valorDesconto);
                System.out.println("cupom 100ff");
                System.out.println(valorDesconto);
            } 
            else if (carrinhoCompra.getCupom().toLowerCase().equals("primeiracompra")){
                double valorDesconto = carrinhoCompra.valor * 0.20;
                carrinhoCompra.calculaDesconto(valorDesconto);
                System.out.println("cupom primeira compra");
                System.out.println(valorDesconto);            
            }
            else if (carrinhoCompra.getCupom().toLowerCase().equals("fretegratis")){
            double valorDesconto = carrinhoCompra.valor * 0.30;
            carrinhoCompra.calculaDesconto(valorDesconto);
            System.out.println("cupom frete grátis");
            }
            else
                System.out.println("cupom de desconto inválido: " + carrinhoCompra.getCupom());
            
        }
        
    }
        

}