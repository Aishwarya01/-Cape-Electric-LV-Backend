package com.capeelectric.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.util.HeaderFooterPageEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintFinalPDFServiceImpl implements PrintFinalPDFService {

	@Autowired
	private AWSS3ServiceImpl awsS3ServiceImpl;
	@Override
	public void printFinalPDF(String userName, Integer siteId) throws Exception {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			try {
				List<InputStream> inputPdfList = new ArrayList<InputStream>();
				
				inputPdfList.add(new FileInputStream("PrintInstalReportData.pdf"));
				inputPdfList.add(new FileInputStream("SupplyCharacteristic.pdf"));
				inputPdfList.add(new FileInputStream("PrintInspectionDetailsData.pdf"));
				inputPdfList.add(new FileInputStream("Testing.pdf"));
				inputPdfList.add(new FileInputStream("Summary.pdf"));

				OutputStream outputStream = new FileOutputStream("TotalMergedData.pdf");
				mergePdfFiles(inputPdfList, outputStream, awsS3ServiceImpl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("Invalid Inputs");
		}
	}

	private static void mergePdfFiles(List<InputStream> inputPdfList, OutputStream outputStream, AWSS3ServiceImpl awss3ServiceImpl) throws Exception {
		Document document = new Document();
		List<PdfReader> readers = new ArrayList<PdfReader>();
		int totalPages = 0;
		Iterator<InputStream> pdfIterator = inputPdfList.iterator();
		while (pdfIterator.hasNext()) {
			InputStream pdf = pdfIterator.next();
			PdfReader pdfReader = new PdfReader(pdf);
			readers.add(pdfReader);
			totalPages = totalPages + pdfReader.getNumberOfPages();
		}
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		Image image = Image.getInstance(awss3ServiceImpl.findByName("rush-logo.png"));
		image.scaleToFit(185, 185);
		image.setAbsolutePosition(-3, -9);
		
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent((PdfPageEvent) event);
		document.open();
		PdfContentByte pageContentByte = writer.getDirectContent();
		PdfImportedPage pdfImportedPage;
		int currentPdfReaderPage = 1;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
				document.newPage();
				document.add(image);
				pdfImportedPage = writer.getImportedPage(pdfReader, currentPdfReaderPage);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);
				currentPdfReaderPage++;
			}
			currentPdfReaderPage = 1;
		}
		outputStream.flush();
		document.close();
		outputStream.close();
	}
}