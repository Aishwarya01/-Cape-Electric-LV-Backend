package com.capeelectric.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capeelectric.exception.FinalReportException;
import com.capeelectric.exception.InspectionException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.Site;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintService;
import com.capeelectric.service.PrintSupplyService;
import com.capeelectric.service.PrintTestingService;
import com.capeelectric.util.HeaderFooterPageEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class FinalPDFController {
	private static final Logger logger = LoggerFactory.getLogger(FinalReportController.class);
	
	@Autowired
	private PrintService printService ;
	
	@Autowired
	private PrintTestingService printTestingService ;
	
	@Autowired
	private PrintSupplyService printSupplyService ;
	
	@Autowired
	PrintFinalPDFService printFinalPDFService;
	
	@GetMapping("/printFinalPDF/{userName}/{siteId}")
	
	public ResponseEntity printFinalPDF(@PathVariable String userName,
			@PathVariable Integer siteId, Object writer) throws Exception, SummaryException, PeriodicTestingException, SupplyCharacteristicsException {
		logger.info("called printFinalPDF function UserName : {},SiteId : {}", userName, siteId);
		
		printSupplyService.printSupply(userName,siteId);
		printTestingService.printTesting(userName, siteId);
		printService.printSummary(userName,siteId);
		printFinalPDFService.printFinalPDF(userName, siteId);
	     return new ResponseEntity(HttpStatus.OK);
		}
	
}
