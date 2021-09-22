package com.capeelectric.util;

import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;

import org.apache.logging.log4j.core.pattern.MaxLengthConverter;
import org.hibernate.mapping.Collection;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Collections;

public class AddPageNumbers<ImageData> extends PdfPageEventHelper {

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		final int currentPageNumber = writer.getCurrentPageNumber();

		if (currentPageNumber == 0) {
			return;
		}

		try {
			final Rectangle pageSize = document.getPageSize();
			final PdfContentByte directContent = writer.getDirectContent();

			directContent.setColorFill(new GrayColor(0.50f));
			directContent.setFontAndSize(BaseFont.createFont(), 8);
			directContent.setTextMatrix(pageSize.getRight(520), pageSize.getBottom(30));
			String file = "file:///D:/project%20cape/siva/Cape-Back-end/src/main/resources/image/rush-logo.png";
			Image image = Image.getInstance(file);
			image.scaleToFit(185, 185);
			image.setAbsolutePosition(30, -9);
			document.add(image);

			directContent.setTextMatrix(pageSize.getRight(400), pageSize.getBottom(45));
			directContent.showText(
					String.valueOf("Testing Inspection and Verification (TIC) of LV electrical installation"));
			directContent.setTextMatrix(pageSize.getRight(380), pageSize.getBottom(36));
			directContent.showText(String.valueOf("Electrical safety in Industrial and Commercial premises"));
			directContent.setTextMatrix(pageSize.getRight(330), pageSize.getBottom(26));
			directContent.showText(String.valueOf("as per IEC 60364 – 6 (IS 732)"));
		} catch (DocumentException | IOException | java.io.IOException e) {
		}
	}
}
