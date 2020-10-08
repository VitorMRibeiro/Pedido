package br.ufes;

import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Pedido;
import br.ufes.model.Produto;
import business.ICMS;
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

            Cliente cliente1 = new Cliente("Fulano", "123.456.789-01", LocalDate.of(1990,10,10));

            Produto produto1 = new Produto("Caneta", 3.50, 8, "Papelaria");
            Produto produto2 = new Produto("Borracha", 0.50, 10, "Papelaria");

            CarrinhoDeCompra carrinho1 = new CarrinhoDeCompra(001, cliente1, LocalDate.now());

            carrinho1.addItem(produto1, 2);
            carrinho1.addItem(produto2, 3);
            
            Pedido pedido;
            pedido=carrinho1.concluir();
            pedido.efetuarPagamento("cartão de crédito", 6);
            System.out.println(pedido.getStatus().getDescricao());

            ICMS.calculaICMS("Armas e Municoes", "SP", "RJ");
            
        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        }

    }

}
