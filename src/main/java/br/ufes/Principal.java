package br.ufes;

import br.ufes.model.ICMS;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Pedido;
import br.ufes.model.Produto;
import br.ufes.model.ICMS;
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
            Cliente cliente1 = new Cliente("Fulano", "123.456.789-01", LocalDate.of(1990,10,13));
            Cliente cliente2 = new Cliente("Ciclano", "123.978.412-01", LocalDate.of(1990,10,10));

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
