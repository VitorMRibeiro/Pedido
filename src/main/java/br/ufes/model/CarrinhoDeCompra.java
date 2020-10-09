
package br.ufes.model;

import br.ufes.business.ProcessaNotaFiscal;
import br.ufes.business.ProcessaPoliticaDesconto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CarrinhoDeCompra {
    protected int id;
    protected String cupom;
    protected Cliente cliente;
    protected double valor;
    protected double valorDesconto;
    protected double valorAPagar;
    protected final ArrayList<Item> itens = new ArrayList<>();
    protected final LocalDate data;
    protected final LocalDate dataVencimento;
    protected ProcessaNotaFiscal notaFiscal;

    public CarrinhoDeCompra(int id, Cliente cliente, LocalDate data) {
        if (cliente == null) {
            throw new RuntimeException("Informe um cliente válido");
        }
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.dataVencimento = data.plusDays(5);
    }

    public final void addItem(Produto produto, double quantidade) {
        if (quantidade <= 0) {
            throw new RuntimeException("Informe uma quantidade válida!");
        }
        if (this.getItemPorNome(produto.getNome()).isPresent()) {
            throw new RuntimeException("Produto já existe! Remova-o ou altere a quantidade");
        }
        
        Item item1 = new Item(produto, quantidade);
        this.itens.add(item1);
    }

    protected Optional<Item> getItemPorNome(String nomeProduto) {
        Optional<Item> itemEncontrado = Optional.empty();
        for (Item item : itens) {
            if (item.getProduto().getNome().toLowerCase().equals(nomeProduto.toLowerCase())) {
                itemEncontrado = Optional.of(item);
            }
        }
        return itemEncontrado;
    }

    private void calcularValor() {
        for (Item item : itens) {
            valor += item.getValorItem();
        }
    }
    
    public void calculaDesconto(double valorADescontar) {
        
        valorDesconto += valorADescontar; 
    }
    
    public void aplicaDesconto(){
        valorAPagar = valor - valorDesconto;
    }

    
    public void removerItem(String nomeProduto) {

        Optional<Item> produtoEncontrado = getItemPorNome(nomeProduto);
        if (!produtoEncontrado.isPresent()) {
            throw new RuntimeException("Item " + nomeProduto + " não encontrado");
        }

        itens.remove(produtoEncontrado.get());
        calcularValor();
    }

    public void mudarQuantidade(String nomeProduto, double novaQuantidade){
        
        Optional<Item> produtoEncontrado = getItemPorNome(nomeProduto);
        if (!produtoEncontrado.isPresent()) {
            throw new RuntimeException("Item " + nomeProduto + " não encontrado");
        }
        
        int id = itens.indexOf(produtoEncontrado.get());
        itens.get(id).setNovaQuantidade(novaQuantidade);
    }    
    
    public void setCupom(String cupom){
        this.cupom = cupom;
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

    public String getCupom() {
        return cupom;
    }
    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }
    
    public void executaPoliticasDesconto(){
        ProcessaPoliticaDesconto processaPoliticaDesconto = new ProcessaPoliticaDesconto();
        
        processaPoliticaDesconto.executa(this);
    }
    
    public Pedido concluir(){
        
        calcularValor();
        
        executaPoliticasDesconto();
        
        aplicaDesconto();
        
        Pedido pedido;
        
        pedido = new Pedido(id, cliente, itens, data, valor, valorDesconto, valorAPagar, StatusPedido.ABERTO, notaFiscal);
        
        for (Item item : itens){
            item.getProduto().setNovaQuantidade(item.getQuantidade());
        }
        
        System.out.println(pedido);
        return pedido;
    
    }
}
