package br.ufes.model;

public class RegraDescontoCupom implements IPoliticaDeDesconto{

    @Override
    public void calcular(CarrinhoDeCompra carrinhoCompra) {
        if (carrinhoCompra.getCupom() != null){
        
            switch (carrinhoCompra.getCupom().toLowerCase()) {
                case "100ff":
                    {
                        carrinhoCompra.calculaDesconto(carrinhoCompra.valor * 0.10);
                        System.out.println("Desconto cupom '100ff': " + ((carrinhoCompra.valor * 0.10)));
                        break;
                    }
                case "primeiracompra":                     
                    {
                        carrinhoCompra.calculaDesconto(carrinhoCompra.valor * 0.20);
                        System.out.println("Desconto cupom 'primeiracompra': " + (carrinhoCompra.valor * 0.20));
                        break;
                    }
                case "fretegratis":
                    {
                        carrinhoCompra.calculaDesconto(carrinhoCompra.valor * 0.30);
                        System.out.println("Desconto cupom 'fretegratis': " + (carrinhoCompra.valor * 0.30));
                        break;
                    }
                default:
                    System.out.println("Cupom de desconto inválido: " + carrinhoCompra.getCupom());
                    break;
            }
            
        }
        
    }
        

}