package com.capeelectric.service.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.StyleConstants.FontConstants;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Blue;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.BoundingLocationReport;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryDeclaration;
import com.capeelectric.model.SummaryObervation;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.service.PrintService;
import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintServiceImpl implements PrintService {

	@Autowired
	private SummaryRepository summaryRepository;

	@Override
	public void printSummary(String userName, Integer siteId) throws SummaryException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document(PageSize.A4, 36, 36, 50, 36);

			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Summary.pdf"));
				document.open();

				List<Summary> s = summaryRepository.findByUserNameAndSiteId(userName, siteId);
	          	Summary summary = s.get(0);
             	List<SummaryObervation> observation1 = summary.getSummaryObervation();
				List<SummaryDeclaration> declaration1 = summary.getSummaryDeclaration();
				SummaryDeclaration declaration = declaration1.get(0);
				SummaryDeclaration declaration11 = declaration1.get(1);
//				SummaryObervation observation = observation1.get(0);
//
//			 SummaryObervation observation11 = observation1.get(1);

				Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC | Font.BOLD | Font.UNDERLINE,
						BaseColor.BLACK);
				Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraphOne = new Paragraph("Section-5", font);
				paragraphOne.setAlignment(Element.ALIGN_CENTER);
				Paragraph paragraphOne1 = new Paragraph("Observations, Recommendations and Summery", font1);
				paragraphOne1.setAlignment(Element.ALIGN_CENTER);
				Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.BOLD);
				Paragraph paragraphOne2 = new Paragraph(20,
						"(Initial and periodic inspection of an existing installation up to 1000 V AC and 1500 V DC)",
						font2);
				paragraphOne2.setAlignment(Element.ALIGN_CENTER);

				Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.UNDERLINE | Font.BOLD,
						BaseColor.BLACK);
				Paragraph paragraphOne7 = new Paragraph(30, "Section-1", font5);
				Paragraph paragraphOne3 = new Paragraph(30, "Extent and limitations of inspection and testing", font4);
				paragraphOne7.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne);
				document.add(paragraphOne1);
				document.add(paragraphOne2);
				document.add(paragraphOne7);
				document.add(paragraphOne3);
		 
				float[] pointColumnWidths = { 200F, 100F };
				PdfPTable table4 = new PdfPTable(pointColumnWidths);
				table4.setWidthPercentage(100); // Width 100%
				table4.setSpacingBefore(10f); // Space before table
				table4.setSpacingAfter(10f); // Space after table
				table4.setWidthPercentage(100);
				table4.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph(summary.getExtentInstallation(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table4.addCell(new Phrase("Extent of installation covered by this Report:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell.setFixedHeight(30f);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell);

				PdfPCell cell1 = new PdfPCell(new Paragraph(summary.getAgreedLimitations(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table4.addCell(new Phrase("Agreed limitations including the reasons:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setFixedHeight(30f);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(
						new Paragraph(summary.getAgreedWith(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table4.addCell(new Phrase("Agreed with:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell2.setFixedHeight(30f);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell2);

				PdfPCell cell3 = new PdfPCell(new Paragraph(summary.getOperationalLimitations(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table4.addCell(new Phrase("Operational limitations including the reasons:",
					
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell3.setFixedHeight(30f);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(new Paragraph(summary.getInspectionTestingDetailed(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table4.addCell(new Phrase(
						"The inspection and testing detailed in this report have been carried out in accordance with IEC60364. It should be note that cables concealed within trunk/trench and conduits, under floors which are generally within the fabric of the building or underground, have not been inspected unless it is specifically agreed between the client and inspector prior to the inspection :",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell4.setFixedHeight(80f);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell4.setBorder(PdfPCell.NO_BORDER);
				table4.addCell(cell4);
				document.add(table4);

				Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraphOne9 = new Paragraph(30, "Sectction-2", font5);
				paragraphOne9.setAlignment(Element.ALIGN_CENTER);
				Paragraph paragraphOne4 = new Paragraph("Observations", font3);
				document.add(paragraphOne9);
				document.add(paragraphOne4);

				PdfPTable table8 = new PdfPTable(2);
				table8.setWidthPercentage(100); // Width 100%
				table8.setSpacingBefore(10f); // Space before table
				table8.setSpacingAfter(10f); // Space after table
				table8.setWidthPercentage(100);
				table8.getDefaultCell().setBorder(0);

				PdfPCell cell22 = new PdfPCell(new Paragraph(summary.getInspectionReportTestResult(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table8.addCell(new Phrase("Referring to attached inspection report and test results and subject to the limitations specified at the extent and limitations of inspection and testing:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell22.setFixedHeight(30f);
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell22.setBorder(PdfPCell.NO_BORDER);
				table8.addCell(cell22);
				document.add(table8);
				PdfPCell cell13 = new PdfPCell(new Paragraph(summary.getRecommendationsDate(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));

				PdfPTable table6 = new PdfPTable(4);
				table6.setWidthPercentage(100); // Width 100%
				table6.setSpacingBefore(10f); // Space before table
				table6.setSpacingAfter(5f); // Space after table
				table6.setWidthPercentage(100);
				// table6.getDefaultCell().setBorder(0);
				TableHeader(table6);
				TableData(table6, observation1);
                 document.add(table6);
                document.newPage();
				Font font6 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraphOne10 = new Paragraph(30, "Section-3", font5);
				paragraphOne10.setAlignment(Element.ALIGN_CENTER);
				Paragraph paragraphOne8 = new Paragraph("Recommendations", font6);
				document.add(paragraphOne10);
				document.add(paragraphOne8);
				Paragraph paragraphThree22 = new Paragraph(
						"The overall assessment of the suitability of installation for continuous use is stated as unsatisfactory, I/We recommend that any observations that are classified as “danger present” (Code C1) or “potentially dangerous” (Code C2) should be acted upon as a matter of urgency.\r\n"
						+ "Investigation without delay is recommended for observations which are identified as “Required further investigation”. Observations classified as “Improvement recommended” (Code C3) should be given due consideration. Subject to necessary remedial action being taken, I/We recommended that the installations should be further inspected and tested.	\r\n"
						,
						new Font(BaseFont.createFont(), 12, Font.ITALIC));
				document.add(paragraphThree22);
				PdfPTable table11= new PdfPTable(pointColumnWidths);
				table11.setWidthPercentage(100); // Width 100%
				table11.setSpacingBefore(10f); // Space before table
				table11.setSpacingAfter(5f); // Space after table
				table11.setWidthPercentage(100);
				table11.getDefaultCell().setBorder(0);
			 
				PdfPCell cell23 = new PdfPCell(new Paragraph(summary.getRecommendationsDate(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table11.addCell(new Phrase(" Date:",
					 new Font(BaseFont.createFont(), 12, Font.ITALIC)));
			 
				cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell23.setBorder(PdfPCell.NO_BORDER);
				table11.addCell(cell23);
				document.add(table11);
				// document.add(paragraphThree1);
				PdfPTable table22 = new PdfPTable(pointColumnWidths);
				table22.setWidthPercentage(100); // Width 100%
				table22.setSpacingBefore(10f); // Space before table
				table22.setSpacingAfter(10f); // Space after table
				table22.setWidthPercentage(100);
				table22.getDefaultCell().setBorder(0);

//				PdfPCell cell21 = new PdfPCell(new Paragraph(summary.getRecommendationsDate(),
//						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
//				table22.addCell(new Phrase(
//						"Where the overall assessment of the suitability of the installation for continuous use above is stated as unsatisfactory, I/We recommend that any observations classified as “danger present” (Code C1) or “potentially dangerous” (Code C2) are acted upon the matter of urgency. Investigation without delay is recommended for observations identified as “Required further investigation”. Observations classified as “Improvement recommended” (Code C3) should be given due consideration. Subject to necessary remedial action being taken, I/We recommended that the installation is further inspected and tested by …………Date.:",
//						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
//				cell21.setFixedHeight(145f);
//				cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
//				cell21.setBorder(PdfPCell.NO_BORDER);
//				table22.addCell(cell21);
//				document.add(table22);

				PdfPTable table7 = new PdfPTable(pointColumnWidths); // 3 columns.
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
				table7.setSpacingAfter(10f); // Space after table
				table7.setWidthPercentage(100);
				table7.getDefaultCell().setBorder(0);
//				document.newPage();
				Paragraph paragraphThree11 = new Paragraph(40, "Section-4 ", font5);
				paragraphThree11.setAlignment(Element.ALIGN_CENTER);
				Paragraph paragraphThree1 = new Paragraph("Summary And Conditions Of The Installation ", font3);
				document.add(paragraphThree11);
				document.add(paragraphThree1);

				PdfPCell cell19 = new PdfPCell(new Paragraph(summary.getGeneralConditionInstallation(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table7.addCell(new Phrase("General condition of the installation in terms of electrical safety:",
					 new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell19.setFixedHeight(30f);
				cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell19.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell19);
				PdfPCell cell20 = new PdfPCell(new Paragraph(summary.getOverallAssessmentInstallation(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table7.addCell(
						new Phrase("Overall assessment of the installation in terms of suitability on continuous use:",
						 new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell20.setFixedHeight(30f);
				cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell20.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell20);
				document.add(table7);

				Paragraph paragraphThree = new Paragraph(40, "Section 5", font5);
				paragraphThree.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphThree);
				Paragraph paragraphTwo = new Paragraph("Declaration", font3);
				document.add(paragraphTwo);

				Paragraph paragraph1 = new Paragraph(
						"I/we being the person responsible for the inspection & testing  of the electrical installation (as indicated by my/our signatures below), particulars of which are described in this report, having exercised reasonable skill and care when carrying out the inspection and testing, hereby declare that information in this report including the observations provides an accurate assessment of condition of electrical installation taking into account the stated extent and limitations in part 5 of this report",
						new Font(BaseFont.createFont(), 12, Font.ITALIC));

				document.add(paragraph1);

				PdfPTable table9 = new PdfPTable(2);
				table9.setWidthPercentage(100); // Width 100%
				table9.setSpacingBefore(20f); // Space before table
				table9.setWidthPercentage(100);
				table9.getDefaultCell().setBorder(0);

				addRow(table9, "Inspected and Tested  By ", "Authorised By");

				PdfPTable table = new PdfPTable(4); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setWidthPercentage(100);

				addRow(table, "Name", declaration.getName(), "Name", declaration11.getName());
				addRow(table, "Company", declaration.getCompany(), "Company", declaration11.getCompany());
				addRow(table, "Signature	", declaration.getSignature(), "Signature	",
						declaration11.getSignature());
				addRow(table, "Company", declaration.getCompany(), "Company", declaration11.getCompany());
				addRow(table, "Position", declaration.getPosition(), "Position", declaration11.getPosition());
				addRow(table, "Address", declaration.getAddress(), "Address", declaration11.getAddress());
				addRow(table, "Date", declaration.getDate(), "Date", declaration11.getDate());
				document.add(table9);
				document.add(table);

				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new SummaryException("Invalid Inputs");
		}
	}

	private void addRow(PdfPTable table9, String string, String string2) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell nameCell1 = new PdfPCell(new Paragraph(string2));

		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell1.setHorizontalAlignment(Element.ALIGN_CENTER);

		table9.addCell(nameCell);
		table9.addCell(nameCell1);
	}

	private void TableData(PdfPTable table6, List<SummaryObervation> observation1) {
		for (SummaryObervation arr : observation1) {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getObservations()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(arr.getFurtherActions()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(arr.getReferanceNumberReport()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(arr.getComment()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
		}

	}

	private void TableHeader(PdfPTable table6) {
		PdfPCell cell = new PdfPCell();
//	        cell.setBorderColor(Color.BLUE);
		cell.setPadding(4);

		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.BOLD, BaseColor.BLACK);

		// font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("Observations", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell);
		cell.setPhrase(new Phrase("Further actions", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell);
		cell.setPhrase(new Phrase("Reference number in report", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell);
		cell.setPhrase(new Phrase("Comment", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell);

	}

	private void addRow(PdfPTable table6, String string, String string2, String string3, String string4) {

		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4));
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(nameCell);
		table6.addCell(valueCell1);
		table6.addCell(valueCell2);
		table6.addCell(valueCell3);

	}

	private void addRow(PdfPTable table, String string, String value, String value1) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(value1));
		PdfPCell valueCell = new PdfPCell(new Paragraph(value));
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(nameCell);
		valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.addCell(valueCell);
		table.addCell(valueCell1);
	}
}
