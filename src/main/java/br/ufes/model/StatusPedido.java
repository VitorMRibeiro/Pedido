/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

/**
 *
 * @author tabat
 */
public enum StatusPedido {
    ABERTO("Aberto"), VENCIDO("Vencido"), PAGO("Pago"), CONCLUIDO("Concluído"), NAOAPROVADO("Não aprovado");   
    
    private String descricao;
    
    StatusPedido(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
