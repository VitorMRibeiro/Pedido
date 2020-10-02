package br.ufes.model;

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
        // Maranhão
        entry(Set.of("MA", "Arma"), 28.5)
    );

    public static double calculaICMS(String tipoProduto, String estadoOrigem, String estadoDestino){
        if(!( indices.containsKey(estadoOrigem) && indices.containsKey(estadoDestino) )){
            throw new RuntimeException("Estado inválido");
        }
        

        if(isentos.contains(tipoProduto)){
            return 0;
        }
        else{
            if( estadoOrigem == estadoDestino ){
                double valorAliquota; 

                // procura aliquota nas regras estaduais
                Number aliquota = aliquotaRegrasEstaduais.get(Set.of(estadoOrigem, tipoProduto));
                // se nao encontrar
                if( aliquota == null){
                    // usar aliquota interna geral
                    valorAliquota = tabelaAliquotas[indices.get(estadoOrigem)][indices.get(estadoOrigem)];
                }
                else{
                    valorAliquota = aliquota.doubleValue();
                }
                System.out.printf("ICMS para o estado de origem: %.1f porcento \n", valorAliquota);
            }
            else{
                // usar aliquota interestadual, e mandar o diferencial para o estado de destino.
            
            }
        }

        return 2;
    }

    private ICMS(){}

}