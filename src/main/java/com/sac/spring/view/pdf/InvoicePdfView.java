package com.sac.spring.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sac.spring.models.entity.GlobalConfig;
import com.sac.spring.models.entity.InvoiceItem;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.sac.spring.models.entity.Invoice;
import com.lowagie.text.Document;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("invoice/details")
public class InvoicePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Invoice invoice = (Invoice) model.get("invoice");
		GlobalConfig globalConfig= (GlobalConfig)  model.get("global");

		PdfPCell cell = null;


		cell = new PdfPCell(new Phrase("Datos del cliente"));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		PdfPTable table1 = new PdfPTable(1);
		table1.setSpacingAfter(20);
		table1.addCell(cell);
		table1.addCell(invoice.getClient().getName() + " " + invoice.getClient().getName());
		table1.addCell(invoice.getClient().getEmail());

		cell = new PdfPCell(new Phrase("Datos de la factura"));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);
		table2.addCell(cell);
		table2.addCell("Folio: " + invoice.getId());
		table2.addCell("Fecha: " + invoice.getCreatedAt());
		table2.addCell("Descripcion: " + invoice.getDescription());

		PdfPTable table3 = new PdfPTable(4);
		table3.setWidths(new float[] { 3.5f, 1, 1, 1 });
		table3.addCell("Prducto");
		table3.addCell("Precio");
		table3.addCell("Cantidad");
		table3.addCell("Total");

		for (InvoiceItem items : invoice.getItems()) {

			double price = items.getProduct().getPrice();
			int quantity = items.getQuatity();
			double itbis = (price * ((items.getProduct().getIva() / 100))) * quantity;

			table3.addCell(items.getProduct().getName());
			table3.addCell(String.valueOf((items.getProduct().getPrice() + itbis)));
			table3.addCell(items.getQuatity().toString());
			table3.addCell(items.calcularImporte().toString());

		}

		cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

		table3.addCell(cell);
		table3.addCell(invoice.getTotal().toString());

		HeaderFooter footer = new HeaderFooter(new Phrase("This is my footer"), true);
		document.setFooter(footer);

		document.add(table1);
		document.add(table2);
		document.add(table3);
	}

}
