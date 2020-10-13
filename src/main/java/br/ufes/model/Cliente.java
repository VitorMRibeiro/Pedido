package br.ufes.model;

import java.time.LocalDate;

public final class Cliente {

    private final String nome;
    private final String CNPJOuCPF;
    private final LocalDate dataNascimento;
    private double pontuacao = 10;
    private String endereco;

    public Cliente(String nome, String codigo, LocalDate dataNascimento, String endereco) {
        this.nome = nome;
        this.CNPJOuCPF = codigo;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCNPJOuCPF() {
        return CNPJOuCPF;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
        
    public void incrementaPontuacao(double incremento){
        pontuacao += incremento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public String toString() {
        return "Cliente: " + nome + "\n"
               +"CNPJ/CPF: " + CNPJOuCPF + "\n"
                +"Pontuação: " + pontuacao + "\n"
                +"Endereço: " + endereco + "\n";
    }

}
