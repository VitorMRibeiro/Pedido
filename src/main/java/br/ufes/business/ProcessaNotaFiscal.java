/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.business;

import br.ufes.model.ArquivoJSON;
import br.ufes.model.ArquivoPDF;
import br.ufes.model.ITipoDeArquivo;
import br.ufes.model.Pedido;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author FERNANDO
 */
public class ProcessaNotaFiscal {
    
    public ArrayList<ITipoDeArquivo> tiposDeArquivo = new ArrayList<>();

    public ProcessaNotaFiscal() {
        tiposDeArquivo.add(new ArquivoPDF());
        tiposDeArquivo.add(new ArquivoJSON());
        
    }
    
    public void imprime(Pedido pedido) throws IOException, DocumentException{
        for (ITipoDeArquivo tipoDeArquivo : tiposDeArquivo){
            tipoDeArquivo.gerar(pedido);
        }
    }
    
}