package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.SignatorDetails;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.service.InstalReportPDFService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class InstalReportServiceImplPDF implements InstalReportPDFService {

	@Autowired
	private InstalReportDetailsRepository installationReportRepository;

	@Autowired
	private SiteRepository siteRepository;

	public List<ReportDetails> printBasicInfromation(String userName, Integer siteId) throws InstalReportException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {

			Document document = new Document(PageSize.A4, 36, 36, 50, 36);

			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("PrintInstalReportData.pdf"));

				document.open();

				Font font18 = new Font(Font.FontFamily.TIMES_ROMAN, 23, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Paragraph certificate1 = new Paragraph(
						" LOW VOLTAGE ELECTRICAL INSTALLATION  CERTIFICATE OF VERIFICATION", font18);
				certificate1.setAlignment(Element.ALIGN_CENTER);
				document.add(certificate1);

				Font font12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL | Font.NORMAL, BaseColor.BLACK);
				Paragraph certificate2 = new Paragraph("As per IEC 60364(1 to 6)", font12);
				certificate2.setAlignment(Element.ALIGN_CENTER);
				document.add(certificate2);

				Paragraph certificate3 = new Paragraph("IS732 code of practice of electrical wiring installations",
						font12);
				certificate3.setAlignment(Element.ALIGN_CENTER);
				document.add(certificate3);

				Paragraph gap3 = new Paragraph(90, " ", font12);
				document.add(gap3);

				Font font14 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.ITALIC | Font.NORMAL, BaseColor.BLACK);
				Paragraph certificate4 = new Paragraph(
						"I/we hereby certify that the low voltage electrical installation of M/S ...................................................... at premise ....................................... has been verified following the rules prescribed in "
								+ "IEC 60364 - 6 and IS 732:2019,"
								+ " based on the verification report no ........................................ dt ......  ......  ..........  subject to the extent and limitations, observations, recommendations, and summary included in the test report part5. It is recommended that this certificate to be read along with full test report.",
						font14);
				certificate4.setAlignment(Element.ALIGN_LEFT);
				document.add(certificate4);

				document.add(gap3);

				Font fonta = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Paragraph certificate5 = new Paragraph("NAME:", fonta);
				document.add(certificate5);

				Font gapFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.ITALIC | Font.NORMAL, BaseColor.BLACK);
				Paragraph gap5 = new Paragraph(" ", gapFont1);
				document.add(gap5);

				Paragraph certificate6 = new Paragraph("Designation:", font12);
				document.add(certificate6);

				Paragraph gap6 = new Paragraph(" ", gapFont1);
				document.add(gap6);

				Paragraph certificate7 = new Paragraph("Organisation:", font12);
				document.add(certificate7);

				Font gapFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.ITALIC | Font.NORMAL, BaseColor.BLACK);
				Paragraph gap2 = new Paragraph(" ", gapFont);
				document.add(gap2);

				Paragraph gap4 = new Paragraph(" ", gapFont);
				document.add(gap4);

				Font font20 = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.ITALIC | Font.BOLD, BaseColor.RED);
				Paragraph certificate8 = new Paragraph("                                                      RUSH...",
						font20);
				certificate8.setAlignment(Element.ALIGN_CENTER);
				document.add(certificate8);

				document.newPage();

				List<Site> siteDetails = siteRepository.findBysiteId(siteId);
				Site siteInformation = siteDetails.get(0);

				Set<SitePersons> sitePersonDetails = siteInformation.getSitePersons();
				List<SitePersons> convertion1 = new ArrayList<>(sitePersonDetails);
				SitePersons sitepersons = convertion1.get(0);

				Optional<ReportDetails> reportDetails = installationReportRepository.findBySiteId(siteId);
				ReportDetails report = reportDetails.get();

				Set<SignatorDetails> signatureDetails = report.getSignatorDetails();
				List<SignatorDetails> convertion = new ArrayList<>(signatureDetails);

				SignatorDetails observation = convertion.get(0);
//				SignatorDetails observation2 = convertion.get(1);
//				SignatorDetails observation3 = convertion.get(2);
				
				
				
				for (SignatorDetails arr : convertion) {

					System.out.println(arr.getSignatorRole().equals("designer1"));

					if (arr.getSignatorRole().equals("designer1")) {

						designer(document, arr);
						

						System.out.println("Designerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

					}

					System.out.println(arr.getSignatorRole().equals("contractor"));
					if (arr.getSignatorRole().equals("contractor")) {

						
						contractor(document, arr);
						
						

						System.out.println("contractorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

					}

					System.out.println(arr.getSignatorRole().equals("inspector"));
					if (arr.getSignatorRole().equals("inspector")) {

						inspector(document, arr);

						System.out.println("inspectorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

				}

				}
				
				
				

				document.newPage();

				Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph1 = new Paragraph(
						"Verification (inspection and testing) of an electrical installation", font);
				paragraph1.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph1);

				Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph2 = new Paragraph("Part:1", font1);
				paragraph2.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph2);

				Paragraph paragraph3 = new Paragraph("Basic Information", font);
				paragraph3.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph3);

				Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph4 = new Paragraph(
						"(Initial / periodic verification of a new / existing installation up to 1000 V AC and 1500 V DC)",
						font2);
				paragraph4.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph4);

				Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD | Font.UNDERLINE,
						BaseColor.BLACK);
				Paragraph paragraph5 = new Paragraph("Section 1", font0);
				paragraph5.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph5);

				Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph6 = new Paragraph("Description and extent of installation", font3);
				document.add(paragraph6);

				Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph7 = new Paragraph("Details of the client ", font4);
				document.add(paragraph7);

				float[] pointColumnWidths = { 200F, 100F };

				PdfPTable table0 = new PdfPTable(pointColumnWidths);

				table0.setWidthPercentage(100); // Width 100%
				table0.setSpacingBefore(10f); // Space before table
				table0.setSpacingAfter(10f); // Space after table
				table0.setWidthPercentage(100);
				table0.getDefaultCell().setBorder(0);

				PdfPCell site1 = new PdfPCell(new Paragraph(siteInformation.getClientName(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Client name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site1.setFixedHeight(30f);
				site1.setHorizontalAlignment(Element.ALIGN_LEFT);
				site1.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site1);

				PdfPCell site2 = new PdfPCell(new Paragraph(siteInformation.getDepartmentName(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Department:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site2.setFixedHeight(30f);
				site2.setHorizontalAlignment(Element.ALIGN_LEFT);
				site2.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site2);

				PdfPCell site3 = new PdfPCell(
						new Paragraph(siteInformation.getSite(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Site Name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site3.setFixedHeight(30f);
				site3.setHorizontalAlignment(Element.ALIGN_LEFT);
				site3.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site3);

				PdfPCell site4 = new PdfPCell(new Paragraph(sitepersons.getPersonIncharge(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Person In-charge:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site4.setFixedHeight(30f);
				site4.setHorizontalAlignment(Element.ALIGN_LEFT);
				site4.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site4);

				PdfPCell site5 = new PdfPCell(
						new Paragraph(sitepersons.getContactNo(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Contact No:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site5.setFixedHeight(30f);
				site5.setHorizontalAlignment(Element.ALIGN_LEFT);
				site5.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site5);

				PdfPCell site6 = new PdfPCell(new Paragraph(sitepersons.getPersonInchargeEmail(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Mail id:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site6.setFixedHeight(30f);
				site6.setHorizontalAlignment(Element.ALIGN_LEFT);
				site6.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site6);

				PdfPCell site7 = new PdfPCell(new Paragraph(siteInformation.getAddressLine_1(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Site Address line1:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site7.setFixedHeight(30f);
				site7.setHorizontalAlignment(Element.ALIGN_LEFT);
				site7.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site7);

				PdfPCell site8 = new PdfPCell(new Paragraph(siteInformation.getAddressLine_2(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Site Address line2:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site8.setFixedHeight(30f);
				site8.setHorizontalAlignment(Element.ALIGN_LEFT);
				site8.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site8);

				PdfPCell site9 = new PdfPCell(
						new Paragraph(siteInformation.getLandMark(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Site Landmark:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site9.setFixedHeight(30f);
				site9.setHorizontalAlignment(Element.ALIGN_LEFT);
				site9.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site9);

				PdfPCell site10 = new PdfPCell(
						new Paragraph(siteInformation.getState(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("State:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site10.setFixedHeight(30f);
				site10.setHorizontalAlignment(Element.ALIGN_LEFT);
				site10.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site10);

				PdfPCell site11 = new PdfPCell(
						new Paragraph(siteInformation.getZipCode(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Pin code:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site11.setFixedHeight(30f);
				site11.setHorizontalAlignment(Element.ALIGN_LEFT);
				site11.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site11);

				PdfPCell site12 = new PdfPCell(
						new Paragraph(siteInformation.getCountry(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table0.addCell(new Phrase("Country:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				site12.setFixedHeight(30f);
				site12.setHorizontalAlignment(Element.ALIGN_LEFT);
				site12.setBorder(PdfPCell.NO_BORDER);
				table0.addCell(site12);
				document.add(table0);

				PdfPTable table1 = new PdfPTable(pointColumnWidths);

				table1.setWidthPercentage(100); // Width 100%
				table1.setSpacingBefore(10f); // Space before table
				table1.setSpacingAfter(10f); // Space after table
				table1.setWidthPercentage(100);
				table1.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(
						new Paragraph(report.getDescriptionReport(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Description of Report:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell.setFixedHeight(30f);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell);

				PdfPCell cell1 = new PdfPCell(
						new Paragraph(report.getReasonOfReport(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Reason for this report:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell1.setFixedHeight(30f);
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(
						new Paragraph(report.getInstallationType(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Type of installation:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell2.setFixedHeight(30f);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell2);

				PdfPCell cell3 = new PdfPCell(new Paragraph(report.getDescriptionPremise(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(
						new Phrase("Description of the premise:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell3.setFixedHeight(30f);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(
						new Paragraph(report.getEstimatedWireAge(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Estimated age of the wiring system:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell4.setFixedHeight(30f);
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell4);

				PdfPCell cell5 = new PdfPCell(
						new Paragraph(report.getEvidanceAddition(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Evidance of addition / altrations:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell5.setFixedHeight(30f);
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(
						new Paragraph(report.getEvidanceWireAge(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(
						new Phrase("If yes estimated age year:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell6.setFixedHeight(30f);
				cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell6.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(
						new Paragraph(report.getPreviousRecords(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(
						new Phrase("Previous records available:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell7.setFixedHeight(30f);
				cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell7.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(
						new Paragraph(report.getLastInspection(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(
						new Phrase("Last date of inspection:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell8.setFixedHeight(30f);
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell8);

				PdfPCell cell9 = new PdfPCell(new Paragraph(report.getExtentInstallation(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Extent of installation covered by this report:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell9.setFixedHeight(30f);
				cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell9.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(
						new Paragraph(report.getClientDetails(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Details of client / person ordering this report:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell10.setFixedHeight(30f);
				cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell10.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell10);

				PdfPCell cell11 = new PdfPCell(new Paragraph(report.getInstallationDetails(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Details of installation referred in this report:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell11.setFixedHeight(30f);
				cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell11.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell11);

				PdfPCell cell12 = new PdfPCell(
						new Paragraph(report.getVerificationDate(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Date of starting the verfication:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell12.setFixedHeight(30f);
				cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell12.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell12);

				PdfPCell cell13 = new PdfPCell(
						new Paragraph(report.getVerifiedEngineer(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Name of engineer who carries out verification:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell13.setFixedHeight(30f);
				cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell13.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell13);

				PdfPCell cell14 = new PdfPCell(
						new Paragraph(report.getDesignation(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Designation:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell14.setFixedHeight(30f);
				cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell14.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell14);

				PdfPCell cell15 = new PdfPCell(
						new Paragraph(report.getCompany(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Company:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell15.setFixedHeight(30f);
				cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell15.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(
						new Paragraph(report.getLimitations(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table1.addCell(new Phrase("Read and confirmed the extent and limitations (part 5, section 1):",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell16.setFixedHeight(30f);
				cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell16.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell16);
				document.add(table1);

				Paragraph paragraph8 = new Paragraph("Section 2", font0);
				paragraph8.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph8);

				Paragraph paragraph9 = new Paragraph("Liability and declaration", font3);
				document.add(paragraph9);

				Paragraph paragraph10 = new Paragraph("Declaration of designer of the electrical installation", font4);
				document.add(paragraph10);

				Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.NORMAL, BaseColor.BLACK);
				Paragraph paragraph11 = new Paragraph(
						"I/we being the person responsible for the design of the electrical installation (as indicated by my/our signatures below), particulars of which are described above, having exercised reasonable skill and care when carrying out the design hereby declare that design work for which I/We have been responsible is to the best of my/our knowledge and belief in accordance with IEC 60364. The extent of liability of the signatory or signatories is limited to the work described above as the subject of this report.",
						font5);
				document.add(paragraph11);

				Paragraph paragraph12 = new Paragraph("Designer 1", font4);
				document.add(paragraph12);

//				PdfPTable table = new PdfPTable(6); // 6 columns.
//				table.setWidthPercentage(100); // Width 100%
//				table.setSpacingBefore(10f); // Space before table
//				table.setSpacingAfter(10f); // Space after table
//				table.setWidthPercentage(100);
//				table.getDefaultCell().setBorder(0);
//
//				addRow(table, "Signature:", observation.getDeclarationSignature(), "Date:",
//						observation.getDeclarationDate(), "Name:", observation.getDeclarationName());
//				document.add(table);

				Paragraph paragraph13 = new Paragraph("Designer 2", font4);
				document.add(paragraph13);

//				PdfPTable table2 = new PdfPTable(6); // 6 columns.
//				table2.setWidthPercentage(100); // Width 100%
//				table2.setSpacingBefore(10f); // Space before table
//				table2.setSpacingAfter(10f); // Space after table
//				table2.setWidthPercentage(100);
//				table2.getDefaultCell().setBorder(0);
//
//				addRow(table2, "Signature:", observation.getDeclarationSignature(), "Date:",
//						observation.getDeclarationDate(), "Name:", observation.getDeclarationName());
//				document.add(table2);

				Paragraph paragraph14 = new Paragraph("Declaration of contactor of installation", font4);
				document.add(paragraph14);

				Paragraph paragraph15 = new Paragraph(
						"I/we being the person responsible for the construction of the electrical installation (as indicated by my/our signatures below), particulars of which are described above, having exercised reasonable skill and care when carrying out the design hereby declare that the design work for which I/We have been responsible is to the best of my/our knowledge and belief in accordance with IEC 60364. The extent of liability of the signatory is limited to the work described above as the subject of this report.",
						font5);
				document.add(paragraph15);

				document.newPage();

				Paragraph paragraph16 = new Paragraph("Contractor ", font4);
				document.add(paragraph16);

//				PdfPTable table3 = new PdfPTable(6); // 6 columns.
//				table3.setWidthPercentage(100); // Width 100%
//				table3.setSpacingBefore(10f); // Space before table
//				table3.setSpacingAfter(10f); // Space after table
//				table3.setWidthPercentage(100);
//				table3.getDefaultCell().setBorder(0);
//
//				addRow(table3, "Signature:", observation2.getDeclarationSignature(), "Date:",
//						observation2.getDeclarationDate(), "Name:", observation2.getDeclarationName());
//				document.add(table3);

				Paragraph paragraph17 = new Paragraph("Declaration of Safety Engineer (Inspector)", font4);
				document.add(paragraph17);

				Paragraph paragraph18 = new Paragraph(
						"I/we being the person responsible for the inspection & testing  of the electrical installation (as indicated by my/our signatures below), particulars of which are described above, having exercised reasonable skill and care when carrying out the design hereby declare that the design work for which I/We have been responsible is to the best of my/our knowledge and belief in accordance with IEC 60364. The extent of liability of the signatory is limited to the work described above as the subject of this report.",
						font5);
				document.add(paragraph18);

//				Paragraph paragraph19 = new Paragraph("Inspector", font4);
//				document.add(paragraph19);
//
//				PdfPTable table4 = new PdfPTable(6); // 6 columns.
//				table4.setWidthPercentage(100); // Width 100%
//				table4.setSpacingBefore(10f); // Space before table
//				table4.setSpacingAfter(10f); // Space after table
//				table4.setWidthPercentage(100);
//				table4.getDefaultCell().setBorder(0);
//
//				addRow(table4, "Signature:", observation3.getDeclarationSignature(), "Date:",
//						observation3.getDeclarationDate(), "Name:", observation3.getDeclarationName());
//				document.add(table4);

				Paragraph paragraph20 = new Paragraph("Section 3", font0);
				paragraph20.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph20);

				Paragraph paragraph21 = new Paragraph("Next inspection", font3);
				document.add(paragraph21);

				Paragraph paragraph22 = new Paragraph(
						"I/we recommend that this installation is further inspected and tested after an interval of not more than__"
								+ report.getNextInspection() + "__years",
						font5);
				document.add(paragraph22);

				Paragraph paragraph23 = new Paragraph("Details of the signatories to the verification report", font4);
				document.add(paragraph23);

				Paragraph paragraph24 = new Paragraph("Section 4", font0);
				paragraph24.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph24);

				Font font30 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph25 = new Paragraph("Designer-1", font30);
				document.add(paragraph25);
				
				
				designer(document, observation);

//				Paragraph paragraph26 = new Paragraph("Designer-2", font3);
//				document.add(paragraph26);
//
//				PdfPTable table6 = new PdfPTable(pointColumnWidths);
//
//				table6.setWidthPercentage(100); // Width 100%
//				table6.setSpacingBefore(10f); // Space before table
//				table6.setSpacingAfter(10f); // Space after table
//				table6.setWidthPercentage(100);
//				table6.getDefaultCell().setBorder(0);
//
//				PdfPCell cell31 = new PdfPCell(
//						new Paragraph(observation.getPersonName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
//				table6.addCell(new Phrase("Person name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
//				cell31.setFixedHeight(30f);
//				cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell31.setBorder(PdfPCell.NO_BORDER);
//				table6.addCell(cell31);
//
//				PdfPCell cell32 = new PdfPCell(new Paragraph(observation.getPersonContactNo(),
//						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
//				table6.addCell(new Phrase("Contact no:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
//				cell32.setFixedHeight(30f);
//				cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell32.setBorder(PdfPCell.NO_BORDER);
//				table6.addCell(cell32);
//
//				PdfPCell cell33 = new PdfPCell(new Paragraph(observation.getPersonMailID(),
//						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
//				table6.addCell(new Phrase("Mail ID:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
//				cell33.setFixedHeight(30f);
//				cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell33.setBorder(PdfPCell.NO_BORDER);
//				table6.addCell(cell33);
//				document.add(table6);

				Paragraph paragraph27 = new Paragraph("Section 5", font0);
				paragraph27.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph27);

				Paragraph paragraph28 = new Paragraph("Contractor", font3);
				document.add(paragraph28);

				contractor(document, observation);

				Paragraph paragraph29 = new Paragraph("Section 6", font0);
				paragraph29.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph29);

				Paragraph paragraph30 = new Paragraph("Inspector", font3);
				document.add(paragraph30);

				inspector(document, observation);

				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new InstalReportException("Invalid Inputs");
		}
		return null;
	}

	/**
	 * @param document
	 * @param observation
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void designer(Document document, SignatorDetails observation) throws DocumentException, IOException {
		float[] pointColumnWidths1 = { 200F, 100F };
		PdfPTable table5 = new PdfPTable(pointColumnWidths1);

		table5.setWidthPercentage(100); // Width 100%
		table5.setSpacingBefore(10f); // Space before table
		table5.setSpacingAfter(10f); // Space after table
		table5.setWidthPercentage(100);
		table5.getDefaultCell().setBorder(0);

		PdfPCell cell17 = new PdfPCell(
				new Paragraph(observation.getPersonName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Person name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell17.setFixedHeight(30f);
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell17.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph(observation.getPersonContactNo(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Contact no:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell18.setFixedHeight(30f);
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell18.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(
				new Paragraph(observation.getPersonMailID(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Mail ID:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell19.setFixedHeight(30f);
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell19.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell19);

		PdfPCell cell21 = new PdfPCell(
				new Paragraph(observation.getManagerName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Manager name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell21.setFixedHeight(30f);
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell21.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph(observation.getManagerContactNo(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Contact no:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell22.setFixedHeight(30f);
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell22.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell22);

		PdfPCell cell23 = new PdfPCell(new Paragraph(observation.getManagerMailID(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Mail ID:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell23.setFixedHeight(30f);
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell23.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell23);

		PdfPCell cell24 = new PdfPCell(
				new Paragraph(observation.getCompanyName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Company name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell24.setFixedHeight(30f);
		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell24.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell24);

		PdfPCell cell25 = new PdfPCell(
				new Paragraph(observation.getAddressLine1(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Address line1:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell25.setFixedHeight(30f);
		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell25.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(
				new Paragraph(observation.getAddressLine2(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Address line2:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell26.setFixedHeight(30f);
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(
				new Paragraph(observation.getLandMark(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Landmark:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell27.setFixedHeight(30f);
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell27.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(
				new Paragraph(observation.getState(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("State:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell28.setFixedHeight(30f);
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell28.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(
				new Paragraph(observation.getPinCode(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Pin code:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell29.setFixedHeight(30f);
		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell29.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell29);

		PdfPCell cell30 = new PdfPCell(
				new Paragraph(observation.getCountry(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Country:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell30.setFixedHeight(30f);
		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell30.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell30);
		document.add(table5);
	}

	/**
	 * @param document
	 * @param observation
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void contractor(Document document, SignatorDetails observation) throws DocumentException, IOException {
		float[] pointColumnWidths2 = { 200F, 100F };
		PdfPTable table7 = new PdfPTable(pointColumnWidths2);

		table7.setWidthPercentage(100); // Width 100%
		table7.setSpacingBefore(10f); // Space before table
		table7.setSpacingAfter(10f); // Space after table
		table7.setWidthPercentage(100);
		table7.getDefaultCell().setBorder(0);

		PdfPCell cell34 = new PdfPCell(
				new Paragraph(observation.getPersonName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Person name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell34.setFixedHeight(30f);
		cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell34.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell34);

		PdfPCell cell35 = new PdfPCell(new Paragraph(observation.getPersonContactNo(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Contact no:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell35.setFixedHeight(30f);
		cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell35.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell35);

		PdfPCell cell36 = new PdfPCell(
				new Paragraph(observation.getPersonMailID(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Mail ID:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell36.setFixedHeight(30f);
		cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell36.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell36);

		PdfPCell cell37 = new PdfPCell(
				new Paragraph(observation.getManagerName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Manager name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell37.setFixedHeight(30f);
		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell37.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell37);

		PdfPCell cell38 = new PdfPCell(new Paragraph(observation.getManagerContactNo(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Contact no:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell38.setFixedHeight(30f);
		cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell38.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell38);

		PdfPCell cell39 = new PdfPCell(new Paragraph(observation.getManagerMailID(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Mail ID:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell39.setFixedHeight(30f);
		cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell39.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell39);

		PdfPCell cell40 = new PdfPCell(
				new Paragraph(observation.getCompanyName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Company name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell40.setFixedHeight(30f);
		cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell40.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell40);

		PdfPCell cell41 = new PdfPCell(
				new Paragraph(observation.getAddressLine1(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Address line1:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell41.setFixedHeight(30f);
		cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell41.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell41);

		PdfPCell cell42 = new PdfPCell(
				new Paragraph(observation.getAddressLine2(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Address line2:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell42.setFixedHeight(30f);
		cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell42.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell42);

		PdfPCell cell43 = new PdfPCell(
				new Paragraph(observation.getLandMark(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Landmark:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell43.setFixedHeight(30f);
		cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell43.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell43);

		PdfPCell cell44 = new PdfPCell(
				new Paragraph(observation.getState(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("State:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell44.setFixedHeight(30f);
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell44.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(
				new Paragraph(observation.getPinCode(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Pin code:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell45.setFixedHeight(30f);
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell45.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell45);

		PdfPCell cell46 = new PdfPCell(
				new Paragraph(observation.getCountry(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Country:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell46.setFixedHeight(30f);
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell46);
		document.add(table7);
	}

	/**
	 * @param document
	 * @param observation
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void inspector(Document document, SignatorDetails observation) throws DocumentException, IOException {
		float[] pointColumnWidths3 = { 200F, 100F };
		PdfPTable table8 = new PdfPTable(pointColumnWidths3);

		table8.setWidthPercentage(100); // Width 100%
		table8.setSpacingBefore(10f); // Space before table
		table8.setSpacingAfter(10f); // Space after table
		table8.setWidthPercentage(100);
		table8.getDefaultCell().setBorder(0);

		PdfPCell cell47 = new PdfPCell(
				new Paragraph(observation.getPersonName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Person name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell47.setFixedHeight(30f);
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell47.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell47);

		PdfPCell cell48 = new PdfPCell(new Paragraph(observation.getPersonContactNo(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Contact no:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell48.setFixedHeight(30f);
		cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell48.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(
				new Paragraph(observation.getPersonMailID(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Mail ID:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell49.setFixedHeight(30f);
		cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell49.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(
				new Paragraph(observation.getManagerName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Manager name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell50.setFixedHeight(30f);
		cell50.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell50.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph(observation.getManagerContactNo(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Contact no:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell51.setFixedHeight(30f);
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell51.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(observation.getManagerMailID(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Mail ID:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell52.setFixedHeight(30f);
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell52.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(
				new Paragraph(observation.getCompanyName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Company name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell53.setFixedHeight(30f);
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell53.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(
				new Paragraph(observation.getAddressLine1(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Address line1:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell54.setFixedHeight(30f);
		cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell54.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(
				new Paragraph(observation.getAddressLine2(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Address line2:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell55.setFixedHeight(30f);
		cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell55.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(
				new Paragraph(observation.getLandMark(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Landmark:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell56.setFixedHeight(30f);
		cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell56.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(
				new Paragraph(observation.getState(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("State:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell57.setFixedHeight(30f);
		cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell57.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(
				new Paragraph(observation.getPinCode(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Pin code:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell58.setFixedHeight(30f);
		cell58.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell58.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(
				new Paragraph(observation.getCountry(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table8.addCell(new Phrase("Country:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell59.setFixedHeight(30f);
		cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell59.setBorder(PdfPCell.NO_BORDER);
		table8.addCell(cell59);
		document.add(table8);
	}

	private void addRow(PdfPTable table, String value, String value1, String value2, String value3, String value4,
			String value5) {

		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.addCell(value);
		table.addCell(value1);
		table.addCell(value2);
		table.addCell(value3);
		table.addCell(value4);
		table.addCell(value5);
	}

}