package br.ufes;

import br.ufes.business.ICMS;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Pedido;
import br.ufes.model.Produto;
import br.ufes.business.ICMS;
import br.ufes.business.ProcessaPoliticaDesconto;
import java.time.LocalDate;
import java.lang.Integer;
import java.time.Month;

/**
 *
 * @author Clayton, adaptado da solução de Pedro Henrique Robadel
 */
public class Principal {

    public static void main(String[] args) {

        try {
            
            //Cadastrando cliente
            Cliente cliente1 = new Cliente("Fulano", "123.456.789-01", LocalDate.of(1990,10,10));
            Cliente cliente2 = new Cliente("Ciclano", "123.978.412-01", LocalDate.of(1990,10,10));

            //Cadastrando produtos
            Produto produto1 = new Produto("Caneta", 3.50, 8, "Papelaria");
            Produto produto2 = new Produto("Borracha", 0.50, 10, "Papelaria");

            //Abrindo um carrinho
            CarrinhoDeCompra carrinho1 = new CarrinhoDeCompra(001, cliente1, LocalDate.now());

            //Efetuando compras
            carrinho1.addItem(produto1, 2);
            carrinho1.addItem(produto2, 3);
            
            //Trocando quantidade do produto
            carrinho1.mudarQuantidade("Caneta", 5);
            
            //Cadastrando cupom
            carrinho1.setCupom("primeiracompra");
            
            //Encerrando compra
            Pedido pedido;
            pedido = carrinho1.concluir();
            
            //Efetuando pagamento, concluindo a compra e impressão nota fiscal (Incluir PDF e JSON)
            pedido.efetuarPagamento("cartão de crédito", 50);
            
            //Novo status do pedido
            System.out.println(pedido.getStatus().getDescricao());

        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        }

    }

}
