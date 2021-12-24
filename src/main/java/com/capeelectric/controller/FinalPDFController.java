package com.capeelectric.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.exception.InstalReportException;
import com.capeelectric.exception.ObservationException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.service.InspectionServicePDF;
import com.capeelectric.service.InstalReportPDFService;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintService;
import com.capeelectric.service.PrintSupplyService;
import com.capeelectric.service.PrintTestingService;
import com.capeelectric.service.ReturnPDFService;

@RestController
@RequestMapping("api/v2")
public class FinalPDFController {

	private final ReturnPDFService returnPDFService;

	@Autowired
	public FinalPDFController(ReturnPDFService returnPDFService) {
		this.returnPDFService = returnPDFService;
	}

	@Autowired
	private PrintService printService;

	@Autowired
	private PrintTestingService printTestingService;

	@Autowired
	private PrintSupplyService printSupplyService;

	@Autowired
	private PrintFinalPDFService printFinalPDFService;

	@Autowired
	private InstalReportPDFService instalReportPDFService;

	@Autowired
	private InspectionServicePDF inspectionServicePDF;

	@GetMapping("/printFinalPDF/{userName}/{siteId}")
	@ResponseBody
	public ResponseEntity<byte[]> printFinalPDF(@PathVariable String userName, @PathVariable Integer siteId)
			throws InstalReportException, SupplyCharacteristicsException, InspectionException, PeriodicTestingException,
			SummaryException, Exception, ObservationException {
		instalReportPDFService.printBasicInfromation(userName, siteId);
		printSupplyService.printSupply(userName, siteId);
		inspectionServicePDF.printInspectionDetails(userName, siteId);
		printTestingService.printTesting(userName, siteId);
		printService.printSummary(userName, siteId);
		printFinalPDFService.printFinalPDF(userName, siteId);

		String keyname = "finalreport.pdf";
		ByteArrayOutputStream downloadInputStream = returnPDFService.printFinalPDF(userName, siteId);

		return ResponseEntity.ok().contentType(contentType(keyname))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + keyname + "\"")
				.body(downloadInputStream.toByteArray());
	}

	private MediaType contentType(String keyname) {
		String[] fileArrSplit = keyname.split("\\.");
		String fileExtension = fileArrSplit[fileArrSplit.length - 1];
		switch (fileExtension) {
		case "txt":
			return MediaType.TEXT_PLAIN;
		case "png":
			return MediaType.IMAGE_PNG;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		case "pdf":
			return MediaType.APPLICATION_PDF;
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
