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

            Cliente cliente1 = new Cliente("Fulano", "123.456.789-01");

            Produto produto1 = new Produto("Caneta", 3.50, 8);
            Produto produto2 = new Produto("Borracha", 0.50, 10);

            CarrinhoDeCompra carrinho1 = new CarrinhoDeCompra(001, cliente1, LocalDate.now());

            carrinho1.addItem(produto1, 2);
            carrinho1.addItem(produto2, 3);
            
            carrinho1.concluir();

        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        }

    }

}
