/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import br.ufes.model.IFormaPagamento;
import br.ufes.model.Pedido;
import br.ufes.model.StatusPedido;
import java.time.LocalDate;

/**
 *
 * @author hiago
 */
public class PagamentoCartaoCredito implements IFormaPagamento{
    private LocalDate dataAtual=LocalDate.now();
    @Override
    public void validarPagamento(Pedido pedido, double saldo){
        if(dataAtual.isAfter(pedido.getDataVencimento())){
            pedido.atualizarStatus(StatusPedido.VENCIDO);
        }
        else if(saldo>pedido.getValorAPagar()){
            pedido.atualizarStatus(StatusPedido.PAGO);
        }else{
            pedido.atualizarStatus(StatusPedido.NAOAPROVADO);
        }
    }
}
