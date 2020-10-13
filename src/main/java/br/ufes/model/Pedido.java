package br.ufes.model;

import br.ufes.business.ProcessadoraDePagamento;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.lang.Number;
import br.ufes.business.ProcessaNotaFiscal;



public final class Pedido {

    protected int id;
    protected Cliente cliente;
    protected double valor;
    protected double valorDesconto;
    protected double valorAPagar;
    protected final ArrayList<Item> itens;
    protected final LocalDate data;
    protected final LocalDate dataVencimento;
    protected StatusPedido status;
    protected ProcessaNotaFiscal notaFiscal;


    public Pedido(int id, Cliente cliente, ArrayList<Item> itens, LocalDate data, double valor, double valorDesconto, double valorAPagar, StatusPedido status, ProcessaNotaFiscal notaFiscal) {
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
        this.itens = new ArrayList<>(itens);
        this.notaFiscal = notaFiscal;
    }

    public void atualizarStatus(StatusPedido status) {
        this.status = status;
    }

    public StatusPedido getStatus() {
        return status;
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
    public void efetuarPagamento(String formaDePagamento, double saldo ){
        
        System.out.println("EFETUANDO PAGAMENTO...\n");
        ProcessadoraDePagamento processadora = null;
        if(formaDePagamento.equals("cartão de crédito")){
            processadora = new ProcessadoraDePagamento(new PagamentoCartaoCredito());
        }
        if(formaDePagamento.equals("cartão de débito")){
            processadora = new ProcessadoraDePagamento(new PagamentoCartaoDebito());
        }
        if(formaDePagamento.equals("dinheiro")){
            processadora = new ProcessadoraDePagamento(new PagamentoDinheiro());
        }
        
        try {
            processadora.efetuarPagamento(this, saldo);
            if( status == StatusPedido.PAGO ){
                System.out.println("CALCULANDO ICMS...\n");
                double descontoPorcentegem = valorDesconto / valor;
                Map<String, Number> aliquotas;
                double totalICMSorigem = 0;
                double totalICMSdestino = 0;
                
                for(Item item : itens){
                    aliquotas = ICMS.calculaICMS(item.getProduto().getTipo(), "SP", "RJ");

                    totalICMSorigem += item.getValorItem() * (1 - descontoPorcentegem) * aliquotas.get("Origem").doubleValue();
                    totalICMSdestino += item.getValorItem() * (1 - descontoPorcentegem) * aliquotas.get("Destino").doubleValue(); 
                }
                System.out.printf("ICMS Estado origem: R$%.2f\n", totalICMSorigem);
                System.out.printf("ICMS Estado destino: R$%.2f\n\n", totalICMSdestino);

                System.out.println("GERANDO NOTA FISCAL...\n");
                
                cliente.incrementaPontuacao(this.getValorAPagar()*0.02);
                
                //System.out.println("Pontuação final: " + cliente.getPontuacao());
                
                ProcessaNotaFiscal notaFiscal = new ProcessaNotaFiscal();
                notaFiscal.imprime(this);
                
                System.out.println("PAGAMENTO EFETUADO COM SUCESSO...\n");
            }
            else
                System.out.println("PAGAMENTO NÃO EFETUADO\n");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String retorno = "---------------------------------------- PEDIDO ---------------------------------------\n";
        retorno += "Código: " + id + "\n";
        retorno += cliente + "\n";
        retorno += "Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";
        retorno += "Data de vencimento: " + dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";
        retorno += "Valor sem desconto: R$" + df.format(getValor()) + "\n";
        retorno += "Valor do desconto: R$" + df.format(valorDesconto) + "\n";
        retorno += "Valor a pagar: R$" + df.format(valorAPagar) + "\n";
        retorno += "Status do pedido: " + status.getDescricao() + "\n";
        retorno += "Itens do pedido:\n";
        for (Item item : itens) {
            retorno += item.toString() + "\n";
        }

        return retorno;
    }

}
