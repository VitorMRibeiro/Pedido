/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;


import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author FERNANDO
 */
public class ArquivoJSON implements ITipoDeArquivo {
    
    @Override
    public void gerar(Pedido pedido){
    
        Gson gson = new Gson();

        String json = gson.toJson(pedido);

        FileWriter writeFile;

            try {
                String nomeArquivo = "Pedido " + pedido.getId() + ".json";
                writeFile = new FileWriter(nomeArquivo);
                writeFile.write(json);
                writeFile.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }
    
}
