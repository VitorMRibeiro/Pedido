package br.ufes.business;

import java.util.Collections;
import java.util.Set;
import static java.util.Map.entry;
import java.util.Map;
import java.lang.Double;
import java.lang.Integer;


/**
    Essa classe fornece metodos para o calculo de ICMS interno e interestadual. Esta classe não deve ser
    instanciada.
 */
public final class ICMS {
    
        private static final Map <String, Integer> indices = Map.ofEntries(
        entry("AC", 0),
        entry("AL", 1),
        entry("AM", 2),
        entry("AP", 3),
        entry("BA", 4),
        entry("CE", 5),
        entry("DF", 6),
        entry("ES", 7),
        entry("GO", 8),
        entry("MA", 9),
        entry("MT", 10),
        entry("MS", 11),
        entry("MG", 12),
        entry("PA", 13),
        entry("PB", 14),
        entry("PR", 15),
        entry("PE", 16),
        entry("PI", 17),
        entry("RN", 18),
        entry("RS", 19),
        entry("RJ", 20),
        entry("RO", 21),
        entry("RR", 22),
        entry("SC", 23),
        entry("SP", 24),
        entry("SE", 25),
        entry("TO", 26)        
    );


    /* Tabela contendo as aliquotas internas e interestaduais de ICMS. Os valores na diaguanal
       principal representam as aliquotas internas e os demais valores representam as aliquotas
       interestaduais partindo do estado da linha do elemento para o da coluna. */
    private static final double[][]tabelaAliquotas = 
      /* AC  AL  AM  AP  BA  CE  DF  ES  GO  MA  MT  MS  MG  PA  PB  PR  PE  PI  RN  RS  RJ  RO  RR  SC  SP  SE  TO*/
/*AC*/{{ 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*AL*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*AM*/ { 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*AP*/ { 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*BA*/ { 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*CE*/ { 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*DF*/ { 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*ES*/ { 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*GO*/ { 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*MA*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*MT*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*MS*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*MG*/ {  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 7,  18,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7},
/*PA*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*PB*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*PR*/ {  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 7,  18,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7},
/*PE*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*PI*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12},
/*RN*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12},
/*RS*/ {  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 18,  7,  7,  7,  7,  7,  7,  7},
/*RJ*/ {  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 20,  7,  7,  7,  7,  7,  7},
/*RO*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,17.5,12, 12, 12, 12, 12},
/*RR*/ {  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 17,  7,  7,  7,  7},
/*SC*/ {  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 17,  7,  7,  7},
/*SP*/ {  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 18,  7,  7},
/*SE*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12},
/*TO*/ { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18}};


    /* Tipos de produto isentos de ICMSs */
    private static final Set<String> isentos = Set.of("Revista", "Jornal", "Livro", "Lubrificante");


    /* Aliqutas internas por tipo de produto segundo legislação estadual
       
       Obs: As aliquotas definidas aqui falham em capturar toda a nuance presente na legislação, 
       que por vezes faz distinção entre comprador e finalidade da compra. Alem disso, so uma
       parte das regras esta presente aqui; vasculhar todas as legislações estaduais e acordos
       seria trabalhoso demais.
     */
    private static final Map<Set, Number> aliquotaRegrasEstaduais = Map.ofEntries(
        // Acre
        entry(Set.of("AC", "Bedida Alcoolica"), 33),
        entry(Set.of("AC", "Fumo"), 30),
        entry(Set.of("AC", "Cerveja"), 27),
        entry(Set.of("AC", "Armas e Municoes"), 27),
        entry(Set.of("AC", "Joais"), 27),
        // Alagoas
        entry(Set.of("AL", "Armas e Municoes"), 29),
        entry(Set.of("AL", "Cigarro"), 29),
        entry(Set.of("AL", "Fumo"), 29),
        entry(Set.of("AL", "Cachimbo"), 29),
        entry(Set.of("AL", "Joias"), 29),
        entry(Set.of("AL", "Bebida Alcoolica"), 27),
        // Amapa
        entry(Set.of("AP", "Armas e Municoes"), 29),
        entry(Set.of("AP", "Bebidas Alcoolicas"), 29),
        entry(Set.of("AP", "Joias"), 29),
        entry(Set.of("AP", "Arroz"), 13),
        entry(Set.of("AP", "Cafe"), 13),
        entry(Set.of("AP", "Acucar"), 13),
        entry(Set.of("AP", "Farinha"), 13),
        // Amazonas
        entry(Set.of("AM", "Armas e Municoes"), 30),
        // Bahia
        entry(Set.of("BA", "Armas e Municoes"), 38),
        entry(Set.of("BA", "Arroz"), 7),
        entry(Set.of("BA", "Feijao"), 7),
        // Ceara
        entry(Set.of("CE", "Armas e Municoes"), 28),
        // Distrito Federal
        entry(Set.of("DF", "Fumo"), 35),
        entry(Set.of("DF", "Bebidas Alcoolicas"), 29),
        // Espirito Santo
        entry(Set.of("ES", "Alcool"), 27),
        entry(Set.of("ES", "Armas e Municoes"), 27),
        // Goias
        entry(Set.of("GO", "Fumo"), 25),
        entry(Set.of("GO", "Armas e Municoes"), 25),
        entry(Set.of("GO", "Peixe"), 12),
        entry(Set.of("GO", "Carne"), 12),
        // Maranhao
        entry(Set.of("MA", "Bebidas Alcoolicas"), 26),
        entry(Set.of("MA", "Armas e Municoes"), 26),
        // Mato Grosso
        entry(Set.of("MT", "Armas e Municoes"), 35),
        entry(Set.of("MT", "Arroz"), 12),
        // Mato Grosso do Sul
        entry(Set.of("MS", "Armas e Municoes"), 27),
        // Minas Gerais
        entry(Set.of("MG", "Refrigerante"), 27),
        entry(Set.of("MG", "Armas e Municoes"), 27),
        entry(Set.of("MG", "Frutas"), 15),
        entry(Set.of("MG", "Arroz"), 15),
        // Para
        entry(Set.of("PA", "Bebidas Alcoolicas"), 30),
        entry(Set.of("PA", "Armas e Municoes"), 30),
        // Rio de Janeiro
        entry(Set.of("RJ", "Armas e Municoes"), 200), // carai
        // Sao Paulo
        entry(Set.of("SP", "Jogos Eletronicos"), 30),
        entry(Set.of("SP", "Motocicletas"), 30),
        entry(Set.of("SP", "Raquete de Tenis"), 25)
    );

    public static Map<String, Number> calculaICMS(String tipoProduto, String estadoOrigem, String estadoDestino){
        
        if(!( indices.containsKey(estadoOrigem) && indices.containsKey(estadoDestino) )){
            throw new RuntimeException("Estado inválido");
        }
        

        if(isentos.contains(tipoProduto)){
            return Map.of("Origem", 0, "Destino", 0);
        }
        else{
            double aliquotaInterna;

            // procura aliquota nas regras estaduais
            Number aliquota = aliquotaRegrasEstaduais.get(Set.of(estadoDestino, tipoProduto));
            // se nao encontrar
            if( aliquota == null){
                // usar aliquota interna geral
                aliquotaInterna = tabelaAliquotas[indices.get(estadoDestino)][indices.get(estadoDestino)];
            }
            else{
                aliquotaInterna = aliquota.doubleValue();
            }

            // ICMS interno
            if( estadoOrigem == estadoDestino ){
                // vale a aliquota interna
                return Map.of("Origem", aliquotaInterna / 100, "Destino", 0);
                //System.out.printf("ICMS para o estado de origem: %.1f porcento \n", aliquotaInterna);
            }
            // ICMS interestadual
            else{
                // vale aliquota interestadual. Mandar o diferencial para o estado de destino.
                double aliquotaInterestadual = tabelaAliquotas[indices.get(estadoOrigem)][indices.get(estadoDestino)];
                double diferencial = aliquotaInterna - aliquotaInterestadual;

                return Map.of("Origem", aliquotaInterestadual / 100, "Destino", diferencial / 100);
                //System.out.printf("ICMS para o estado de origem: %.1f porcento \n", aliquotaInterestadual);
                //System.out.printf("ICMS para o estado de destino: %.1f porcento \n", diferencial);
            }
        }
    }

    private ICMS(){}

}