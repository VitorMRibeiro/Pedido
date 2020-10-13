/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import br.ufes.model.ITipoDeArquivo;
import br.ufes.model.Pedido;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 *
 * @author FERNANDO
 */
public class ArquivoPDF implements ITipoDeArquivo {
    
    @Override
    public void gerar(Pedido pedido) /*throws IOException, DocumentException*/ {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Pedido.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(4);

            PdfPCell cellHeader1 = new PdfPCell(new Paragraph("PEDIDO " + pedido.id));
            PdfPCell cellHeader2 = new PdfPCell(new Paragraph("" + pedido.cliente));

            cellHeader1.setColspan(4);
            cellHeader2.setColspan(4);

            table.addCell(cellHeader1);
            table.addCell(cellHeader2);

            table.addCell("");
            table.addCell("Data: " + pedido.data);
            table.addCell("Data vencimento: " + pedido.dataVencimento);
            table.addCell("Status: " + pedido.status);

            PdfPCell cellHeader3 = new PdfPCell(new Paragraph("ITENS"));
            cellHeader3.setColspan(4);
            cellHeader3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cellHeader3);

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
            
            PdfPCell cellHeader4 = new PdfPCell(new Paragraph("Valor total: " + df.format(pedido.valor)));
            cellHeader4.setColspan(4);
            cellHeader4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(cellHeader4);
            
            PdfPCell cellHeader5 = new PdfPCell(new Paragraph("Desconto: " + df.format(pedido.valorDesconto)));
            cellHeader5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cellHeader5.setColspan(4);
            table.addCell(cellHeader5);

            PdfPCell cellHeader6 = new PdfPCell(new Paragraph("VALOR A PAGAR: " + df.format(pedido.valorAPagar)));
            cellHeader6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cellHeader6.setColspan(4);
            table.addCell(cellHeader6);

            document.add(table);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
    
}
