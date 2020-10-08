package br.ufes.model;

import java.text.DecimalFormat;

public final class Item {

    protected double valorUnitario;
    protected double quantidade;
    protected double valorItem;
    protected Produto produto;

    public Item(Produto produto, double quantidadeAdquirida) {
        if (!produto.estoqueDisponivel(quantidadeAdquirida)) {
            throw new RuntimeException("Estoque indisponível para atender a quantidade solicitada (" + quantidadeAdquirida
                    + ") para o produto " + produto.getNome()
                    + ", restam " + produto.getQuantidade() + " em estoque.");
        }
        this.produto = produto;
        this.quantidade = quantidadeAdquirida;
        this.valorUnitario = produto.getValorUnitario();
        calculaValorItem();
    }

    private void calculaValorItem() {
        this.valorItem = valorUnitario * quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getValorItem() {
        calculaValorItem();
        return valorItem;
    }

    public Produto getProduto() {
        return produto;
    }
    
    public void setNovaQuantidade(double novaQuantidade){
        quantidade = novaQuantidade;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return produto.getNome()
                + ", tipo: " + produto.getTipo()
                + ", valor Unitario: R$ " + valorUnitario
                + ", quantidade no pedido:" + quantidade
                + ", valor Total: R$ " + df.format(getValorItem());
    }

}
