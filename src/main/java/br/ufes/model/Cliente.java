package br.ufes.model;

public final class Cliente {

    private final String nome;
    private final String CNPJOuCPF;
    private int pontuacao = 0;

    public Cliente(String nome, String codigo) {
        this.nome = nome;
        this.CNPJOuCPF = codigo;
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
