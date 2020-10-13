/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author FERNANDO
 */
public class ArquivoPDF implements ITipoDeArquivo {
    
    @Override
    public void gerar(Pedido pedido) /*throws IOException, DocumentException*/ {
        
        try {
            Document document = new Document();
            String nomeArquivo = "Pedido " + pedido.getId() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            document.open();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cellHeader1 = new PdfPCell(new Paragraph("NOTA FISCAL NF-e " + pedido.id));
            PdfPCell cellHeader2 = new PdfPCell(new Paragraph("Data e hora de emissão: " + LocalDateTime.now().format(formatter)));
            
            PdfPCell cellHeader3 = new PdfPCell(new Paragraph("PEDIDO " + pedido.id));
            PdfPCell cellHeader4 = new PdfPCell(new Paragraph("" + pedido.cliente));

            cellHeader1.setColspan(4);
            cellHeader2.setColspan(4);
            cellHeader3.setColspan(4);
            cellHeader4.setColspan(4);

            table.addCell(cellHeader1);
            table.addCell(cellHeader2);
            table.addCell(cellHeader3);
            table.addCell(cellHeader4);

            table.addCell("");
            table.addCell("Data: " + pedido.data);
            table.addCell("Data vencimento: " + pedido.dataVencimento);
            table.addCell("Status: " + pedido.status);

            PdfPCell cellHeader5 = new PdfPCell(new Paragraph("ITENS"));
            cellHeader5.setColspan(4);
            cellHeader5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cellHeader5);

            table.addCell("NOME");
            table.addCell("QUANTIDADE");
            table.addCell("VALOR");
            table.addCell("VALOR ITEM");
            
            DecimalFormat df = new DecimalFormat("0.00");

            for (Item item : pedido.itens) {

                table.addCell(item.produto.getNome());
                table.addCell("" + df.format(item.getQuantidade()));
                table.addCell("" + df.format(item.getValorUnitario()));
                table.addCell("" + df.format(item.getValorItem()));

            }
            
            PdfPCell cellHeader6 = new PdfPCell(new Paragraph("Valor total: " + df.format(pedido.valor)));
            cellHeader6.setColspan(4);
            cellHeader6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(cellHeader6);
            
            PdfPCell cellHeader7 = new PdfPCell(new Paragraph("Desconto: " + df.format(pedido.valorDesconto)));
            cellHeader7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cellHeader7.setColspan(4);
            table.addCell(cellHeader7);

            PdfPCell cellHeader8 = new PdfPCell(new Paragraph("VALOR A PAGAR: " + df.format(pedido.valorAPagar)));
            cellHeader8.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cellHeader8.setColspan(4);
            table.addCell(cellHeader8);

            document.add(table);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
    
}
