/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.business;

import br.ufes.model.Pedido;

/**
 *
 * @author hiago
 */
public class ProcessadoraDePagamento {
    private IFormaPagamento formaDePagamento;
    //private double saldo;//no caso de cartão é o limite disponivel e em caso de dinheiro é o valor
    public ProcessadoraDePagamento(IFormaPagamento formaPagamentoP){
        this.formaDePagamento = formaPagamentoP;
        
        }
    public void efetuarPagamento(Pedido pedido, double saldop){
        this.formaDePagamento.validarPagamento(pedido,saldop);    
    }
}
