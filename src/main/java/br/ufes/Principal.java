package br.ufes;

import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Pedido;
import br.ufes.model.Produto;
import br.ufes.model.ICMS;
import java.time.LocalDate;
import java.lang.Integer;

/**
 *
 * @author Clayton, adaptado da solução de Pedro Henrique Robadel
 */
public class Principal {

    public static void main(String[] args) {

        try {

            ICMS.calculaICMS("Arma", "MA", "ES");

        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        }

    }

}
