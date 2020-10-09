/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import com.itextpdf.text.DocumentException;
import java.io.IOException;

/**
 *
 * @author FERNANDO
 */
public interface ITipoDeArquivo {
    
    public void gerar(Pedido pedido) throws IOException, DocumentException;
    
}
