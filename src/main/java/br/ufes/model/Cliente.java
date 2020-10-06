package br.ufes.model;

import java.time.LocalDate;

public final class Cliente {

    private final String nome;
    private final String CNPJOuCPF;
    private final LocalDate dataNascimento;
    private int pontuacao = 0;

    public Cliente(String nome, String codigo, LocalDate dataNascimento) {
        this.nome = nome;
        this.CNPJOuCPF = codigo;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCNPJOuCPF() {
        return CNPJOuCPF;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
        
    public void incrementaPontuacao(int incremento){
        pontuacao += incremento;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + "\n"
                +"CNPJ/CPF = " + CNPJOuCPF + "\n"
                +"Pontuação = " + pontuacao + "\n";
    }

}
