package br.ufes.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class Pedido {

    protected int id;
    protected Cliente cliente;
    protected double valor;
    protected double valorDesconto;
    protected double valorAPagar;
    protected final ArrayList<Item> itens = new ArrayList<>();
    protected final LocalDate data;
    protected final LocalDate dataVencimento;
    protected StatusPedido status;

    public Pedido(int id, Cliente cliente, ArrayList<Item> itens, LocalDate data, double valor, double valorDesconto, double valorAPagar, StatusPedido status) {
        if (cliente == null) {
            throw new RuntimeException("Informe um cliente válido");
        }
        this.id = id;
        this.cliente = cliente;
        this.valor = valor;
        this.valorDesconto = valorDesconto;
        this.valorAPagar = valorAPagar;
        this.data = data;
        this.dataVencimento = data.plusDays(5);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public double getValor() {
        return valor;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String retorno = "--------------- Pedido --------------\n";
        retorno += "Código: " + id + "\n";
        retorno += cliente + "\n";
        retorno += "Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", ";
        retorno += "Data de vencimento: " + dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";
        retorno += "Valor sem desconto: R$ " + df.format(getValor()) + "\n";
        retorno += "Valor do desconto: R$ " + df.format(valorDesconto) + "\n";
        retorno += "Valor a pagar: R$ " + df.format(valorAPagar) + "\n";
        retorno += "Status do pedido " + status.getDescricao() + "\n";
        retorno += "Itens do pedido:\n";
        for (Item item : itens) {
            retorno += item.toString() + "\n";
        }

        return retorno;
    }

}
