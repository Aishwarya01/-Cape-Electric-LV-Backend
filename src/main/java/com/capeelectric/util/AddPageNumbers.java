package com.capeelectric.util;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.imageio.ImageIO;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.jni.File;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.el.parser.AstInteger;

import antlr.collections.List;
import io.jsonwebtoken.io.IOException;
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
                 directContent.setColorFill(BaseColor.BLACK);
                 directContent.setFontAndSize(BaseFont.createFont(), 8);
				 directContent.setTextMatrix(pageSize.getRight(100), pageSize.getBottom(30));
				 directContent.setColorFill(BaseColor.BLACK);

                directContent.showText(String.valueOf("Page"+currentPageNumber)); 
                String file= "file:///D:/project%20cape/siva/Cape-Back-end/src/main/resources/image/Rusglogo.PNG";
                Image image = Image.getInstance(file);
               image.scaleToFit(30, 30);
               image.setAbsolutePosition(100, 20);
               document.add(image);
               directContent.setColorFill(new GrayColor(0.50f));
               directContent.setTextMatrix(pageSize.getRight(400), pageSize.getBottom(45));
                directContent.showText(String.valueOf("Testing Inspection and Verification (TIC) of LV electrical installation"));
                directContent.setTextMatrix(pageSize.getRight(380), pageSize.getBottom(36));
                directContent.showText(String.valueOf("Electrical safety in Industrial and Commercial premises"));
                directContent.setTextMatrix(pageSize.getRight(330), pageSize.getBottom(26));
                directContent.showText(String.valueOf("as per IEC 60364 – 6 (IS 732)"));
                } catch (DocumentException | IOException | java.io.IOException  e) {
        
			}
      }
	}

