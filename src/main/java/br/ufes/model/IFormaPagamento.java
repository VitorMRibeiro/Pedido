/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import br.ufes.model.Pedido;

/**
 *
 * @author hiago
 */
public interface IFormaPagamento {
    public void validarPagamento(Pedido pedido, double saldo);
}
