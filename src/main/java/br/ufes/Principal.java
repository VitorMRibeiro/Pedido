package br.ufes;

import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Pedido;
import br.ufes.model.Produto;
import java.time.LocalDate;

/**
 *
 * @author Clayton, adaptado da solução de Pedro Henrique Robadel
 */
public class Principal {

    public static void main(String[] args) {

        try {
            
            //Cadastrando cliente
            Cliente cliente1 = new Cliente("Tábata", "123.456.789-01", LocalDate.of(1990,10,13), "Rua Colapso Cardíaco, n° 2020");
            Cliente cliente2 = new Cliente("Fernando", "123.978.412-01", LocalDate.of(1990,10,10), "Avenida Infarto do Miocárdio, n° 2021");

            //Cadastrando produtos
            Produto produto1 = new Produto("Caneta", 100, 50, "Papelaria");
            Produto produto2 = new Produto("Pastel", 50, 30, "Comida");

            //Abrindo um carrinho
            CarrinhoDeCompra carrinho1 = new CarrinhoDeCompra(001, cliente1, LocalDate.now());

            //Efetuando compras
            carrinho1.addItem(produto1, 8);
            carrinho1.addItem(produto2, 6);
            
            //Trocando quantidade do produto
            carrinho1.mudarQuantidade("Pastel", 4);
            
            //Cadastrando cupom
            carrinho1.setCupom("primeiracompra");
            
            //Encerrando compra
            Pedido pedido;
            pedido = carrinho1.concluir();
            
            //Efetuando pagamento, concluindo a compra e impressão nota fiscal (Incluir PDF e JSON)
            pedido.efetuarPagamento("cartão de crédito", 700.00);
            
            //Novo status do pedido
            System.out.println("STATUS DO PEDIDO: " + pedido.getStatus().getDescricao());
            
            

        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        }

    }

}
