package com.capeelectric.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.EarthingLocationReport;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.TestDistribution;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingRecords;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.PeriodicTestingService;
import com.capeelectric.service.PrintTestingService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

@Service
public class PrintTestingServiceImpl implements PrintTestingService {

	@Autowired
	private TestingReportRepository testingReportRepository;

	@Override
	public void printTesting(String userName, Integer siteId) throws PeriodicTestingException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document(PageSize.A4, 36, 36, 50, 36);
	//		document.setPageSize(PageSize.LETTER.rotate());
//			class RotateEvent extends PdfPageEventHelper {
//			    public void onStartPage(PdfWriter writer, Document document) {
//			    
//			    }
//			}
			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Testing.pdf"));
				// writer.addPageDictEntry(PdfName.ROTATE, PdfPage.SEASCAPE);
				List<TestingReport> supply1 = testingReportRepository.findByUserNameAndSiteId(userName, siteId);

				TestingReport supply2 = supply1.get(0);
				// List<Testing> testing=((List<TestingReport>) supply).get(0);
				List<Testing> testing = supply2.getTesting();

				Testing testing1 = testing.get(0);
				List<TestDistribution> testDistribution = testing1.getTestDistribution();
				TestDistribution distribution = testDistribution.get(0);
				List<TestingRecords> testRecords = testing1.getTestingRecords();
				// TestingRecords testingRecords=testRecords.get(0);

				String incomingVoltage = distribution.getIncomingVoltage();
				String incomingVoltage_list[] = incomingVoltage.split(",");
				String IV1 = incomingVoltage_list[0];
				String IV2 = incomingVoltage_list[1];
				String IV3 = incomingVoltage_list[2];
				String IV4 = incomingVoltage_list[3];
				String IV5 = incomingVoltage_list[4];
				String IV6 = incomingVoltage_list[5];
				String IV7 = incomingVoltage_list[6];
				String IV8 = incomingVoltage_list[7];
				String IV9 = incomingVoltage_list[8];

				String incomingIPF = distribution.getIncomingIpf();
				String incomingIPF_list[] = incomingIPF.split(",");
				String IPF1 = incomingIPF_list[0];
				String IPF2 = incomingIPF_list[1];
				String IPF3 = incomingIPF_list[2];
				String IPF4 = incomingIPF_list[3];
				String IPF5 = incomingIPF_list[4];
				String IPF6 = incomingIPF_list[5];
				String IPF7 = incomingIPF_list[6];
				String IPF8 = incomingIPF_list[7];
				String IPF9 = incomingIPF_list[8];

				String incomingZS = distribution.getIncomingZs();
				String incomingZS_list[] = incomingZS.split(",");
				String ZS1 = incomingZS_list[0];
				String ZS2 = incomingZS_list[1];
				String ZS3 = incomingZS_list[2];
				String ZS4 = incomingZS_list[3];
				String ZS5 = incomingZS_list[4];
				String ZS6 = incomingZS_list[5];
				String ZS7 = incomingZS_list[6];
				String ZS8 = incomingZS_list[7];
				String ZS9 = incomingZS_list[8];
				document.open();
				Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC | Font.BOLD | Font.UNDERLINE,
						BaseColor.BLACK);
				Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC | Font.BOLD, BaseColor.BLACK);

				Paragraph paragraphOne = new Paragraph("Part-4", font);
				paragraphOne.setAlignment(Element.ALIGN_CENTER);
				Paragraph paragraphTwo = new Paragraph("Testing", font1);
				paragraphTwo.setAlignment(Element.ALIGN_CENTER);
				Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.BOLD);
				Paragraph paragraphThree = new Paragraph(20,
						"(Initial and periodic testing of a new / existing installation up to 1000 V AC and 1500 V DC)",
						font2);
				paragraphThree.setAlignment(Element.ALIGN_CENTER);

				Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.UNDERLINE | Font.BOLD,
						BaseColor.BLACK);

				Paragraph paragraphOneFive = new Paragraph(30, "Testing Basics Information", font5);
				paragraphOneFive.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne);
				document.add(paragraphTwo);
				document.add(paragraphThree);
				document.add(paragraphOneFive);

				float[] pointColumnWidths = { 200F, 100F };
				PdfPTable table = new PdfPTable(pointColumnWidths);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setSpacingAfter(10f); // Space after table
				table.setWidthPercentage(100);
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph(testing1.getLocationNumber()));
				table.addCell(new Phrase("Location Number*:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell.setFixedHeight(30f);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell);

				PdfPCell cell2 = new PdfPCell(new Paragraph(testing1.getLocationName()));
				table.addCell(new Phrase("Location Name*:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell2.setFixedHeight(30f);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);

				PdfPCell cell3 = new PdfPCell(new Paragraph(testing1.getTestEngineerName()));
				table.addCell(new Phrase("Test Engineer Name: ", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell3.setFixedHeight(30f);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(new Paragraph(testing1.getDate()));
				table.addCell(new Phrase("Date:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell4.setFixedHeight(30f);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell4.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell4);

				PdfPCell cell5 = new PdfPCell(new Paragraph(testing1.getDesignation()));
				table.addCell(new Phrase("Designation:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell5.setFixedHeight(30f);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph(testing1.getCompanyName()));
				table.addCell(new Phrase(" Company Name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell6.setFixedHeight(30f);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(new Paragraph(testing1.getDetailsTestInstrument()));
				table.addCell(
						new Phrase("Details Of Test Instrument:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell7.setFixedHeight(30f);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell7.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Paragraph(testing1.getContinuity()));
				table.addCell(new Phrase("Continuity:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell8.setFixedHeight(30f);
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell8.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell8);

				PdfPCell cell9 = new PdfPCell(new Paragraph(testing1.getInsulationResisance()));
				table.addCell(new Phrase("Insulation Resistance:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell9.setFixedHeight(30f);
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell9.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(new Paragraph(testing1.getImpedance()));
				table.addCell(new Phrase("Impedance:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell10.setFixedHeight(30f);
				cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell10.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell10);

				PdfPCell cell11 = new PdfPCell(new Paragraph(testing1.getRcd()));
				table.addCell(new Phrase("RCD:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell11.setFixedHeight(30f);
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell11);

				PdfPCell cell12 = new PdfPCell(new Paragraph(testing1.getEarthElectrodeResistance()));
				table.addCell(
						new Phrase("Earth Electrode Resistance:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell12.setFixedHeight(30f);
				cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell12.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell12);

				document.add(table);
				document.newPage();
				Paragraph paragraphsix = new Paragraph(30, "Detailed Testing:", font5);
				paragraphsix.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphsix);
				PdfPTable table1 = new PdfPTable(pointColumnWidths);
				table1.setWidthPercentage(100); // Width 100%
				table1.setSpacingBefore(10f); // Space before table
				table1.setSpacingAfter(10f); // Space after table
				table1.getDefaultCell().setBorder(0);

				PdfPCell cell13 = new PdfPCell(new Paragraph(distribution.getDistributionBoardDetails()));
				table1.addCell(
						new Phrase("Distribution Board Details:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell13.setFixedHeight(30f);
				cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell13.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell13);

				PdfPCell cell14 = new PdfPCell(new Paragraph(distribution.getReferance()));
				table1.addCell(new Phrase("Referance (name):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell14.setFixedHeight(30f);
				cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell14.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell14);

				PdfPCell cell15 = new PdfPCell(new Paragraph(distribution.getLocation()));
				table1.addCell(new Phrase("Location:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell15.setFixedHeight(30f);
				cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell15.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(new Paragraph(distribution.getCorrectSupplyPolarity()));
				table1.addCell(
						new Phrase("Correct Supply Polarity:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell16.setFixedHeight(30f);
				cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell16.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell16);

				PdfPCell cell17 = new PdfPCell(new Paragraph(distribution.getNumOutputCircuitsSpare()));
				table1.addCell(new Phrase("Number Of Output Circuits - Spare:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell17.setFixedHeight(30f);
				cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell17.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell17);

				PdfPCell cell18 = new PdfPCell(new Paragraph(distribution.getInstalledEquipmentVulnarable()));
				table1.addCell(new Phrase("Installed Equipment Vulnarable To Testing:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell18.setFixedHeight(30f);
				cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell18.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell18);

				PdfPCell cell19 = new PdfPCell(new Paragraph(distribution.getNumOutputCircuitsUse()));
				table1.addCell(new Phrase("Number Of Output Circuits In Use:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell19.setFixedHeight(30f);
				cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell19.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell19);

				PdfPCell cell20 = new PdfPCell(new Paragraph(distribution.getRatingsAmps()));
				table1.addCell(new Phrase("Ratings In Amps:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell20.setFixedHeight(30f);
				cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell20.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell20);

				document.add(table1);

				PdfPTable table2 = new PdfPTable(10);
				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingBefore(10f); // Space before table
				table2.setSpacingAfter(10f); // Space after table
				table2.getDefaultCell().setBorder(0);
				addRow(table2, "Nature of supply parameters", "L1-L2", "L2-L3", "L1-L3", "L1-N", "L2-N", "L3-N",
						"L1-PE", "L2-PE", "L3-PE");
				addRow(table2, "Incoming Voltage", IV1, IV2, IV3, IV4, IV5, IV6, IV7, IV8, IV9);
				addRow(table2, "Incoming Zs (Ω)", IPF1, IPF2, IPF3, IPF4, IPF5, IPF6, IPF7, IPF8, IPF9);
				addRow(table2, "Incoming Ipf (Amps) ", ZS1, ZS2, ZS3, ZS4, ZS5, ZS6, ZS7, ZS8, ZS9);
				document.add(table2);

				document.newPage();

				PdfPTable table5 = new PdfPTable(1);
				table5.setWidthPercentage(100); // Width 100%
				table5.setSpacingBefore(20f); // Space before table
				table5.setWidthPercentage(100);
				// table5.getDefaultCell().setBorder(0);

				PdfPTable table55 = new PdfPTable(6);
				// table55.setWidths(new int[]{ 2, 2, 2, 2, 2,2});
				table55.setSpacingAfter(30f); // Space after table
				table55.setWidthPercentage(100);

				PdfPCell cell111;
				cell111 = new PdfPCell(new Phrase("Circuit details"));
				cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell111.setColspan(6);
				table55.addCell(cell111);

				cell111 = new PdfPCell(new Phrase("Cicuit no"));
				cell111.setRowspan(2);
				table55.addCell(cell111);
				cell111 = new PdfPCell(new Phrase("Description"));
				cell111.setRowspan(2);

				table55.addCell(cell111);
				cell111 = new PdfPCell(new Phrase("Over current device"));
				cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell111.setColspan(4);
				table55.addCell(cell111);
				table55.addCell("Standard no ");
				table55.addCell("R-N");
				table55.addCell(" Rating (A)  ");
				table55.addCell(" Breaking capacity (kA) ");

				addData(table55, testRecords);
				document.add(table55);

				PdfPTable table3 = new PdfPTable(9);
				// table55.setWidths(new int[]{ 2, 2, 2, 2, 2,2});
				table3.setSpacingAfter(30f); // Space after table
				table3.setSpacingBefore(20f); // Space before table
				table3.setWidthPercentage(100);

				PdfPCell cell1;
				cell1 = new PdfPCell(new Phrase("Conductor details"));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setColspan(3);
				table3.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("Continuity (Ω)"));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setColspan(3);
				table3.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("Insulation resistance"));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setColspan(2);
				cell1.setRowspan(2);
				table3.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("Polarity"));
				cell1.setRowspan(3);
				table3.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("Installation referance method"));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setRowspan(2);
				table3.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("cross sectional area (mm2)"));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setColspan(2);
				table3.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("Required for final circuits and protective conductor only"));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setRowspan(1);
				cell1.setColspan(3);
				table3.addCell(cell1);
				table3.addCell("Live ");
				table3.addCell("PE / CPC");
				table3.addCell("Approximate length ");
				table3.addCell("R1+R2 (Ω)");
				table3.addCell("R2 (Ω) ");
				table3.addCell("L-L ");
				table3.addCell("L-E");

				addData1(table3, testRecords);

				document.add(table3);
				PdfPTable table4 = new PdfPTable(9);
				// table55.setWidths(new int[]{ 2, 2, 2, 2, 2,2});
				table4.setSpacingAfter(30f); // Space after table
				table4.setWidthPercentage(100);

				PdfPCell cell23;
				cell23 = new PdfPCell(new Phrase("Voltage(V) note"));
				cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell23.setColspan(9);
				table4.addCell(cell23);
				table4.addCell("L1-L2");
				table4.addCell("L2-L3");
				table4.addCell("L1-L3");
				table4.addCell("L1-N");
				table4.addCell("L2-N");
				table4.addCell("L3-N");
				table4.addCell("L1-PE");
				table4.addCell("L2-PE");
				table4.addCell("L3-PE");
				addData4(table4, testRecords);

				document.add(table4);

				PdfPTable table6 = new PdfPTable(9);
				// table55.setWidths(new int[]{ 2, 2, 2, 2, 2,2});
				table6.setSpacingAfter(30f); // Space after table
				table6.setWidthPercentage(100);

				PdfPCell cell24;
				cell24 = new PdfPCell(new Phrase("Fault loop impedance (Ω)"));
				cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell24.setColspan(9);
				table6.addCell(cell24);
				table6.addCell("L1-L2");
				table6.addCell("L2-L3");
				table6.addCell("L1-L3");
				table6.addCell("L1-N");
				table6.addCell("L2-N");
				table6.addCell("L3-N");
				table6.addCell("L1-PE");
				table6.addCell("L2-PE");
				table6.addCell("L3-PE");
				addData5(table6, testRecords);

				document.add(table6);
        document.newPage();
				PdfPTable table7 = new PdfPTable(9);
				// table55.setWidths(new int[]{ 2, 2, 2, 2, 2,2});
				table7.setSpacingAfter(30f); // Space after table
				table7.setWidthPercentage(100);

				PdfPCell cell25;
				cell25 = new PdfPCell(new Phrase("Actual Short circuit / fault current (Amps)"));
				cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell25.setColspan(9);
				table7.addCell(cell25);
				table7.addCell("L1-L2");
				table7.addCell("L2-L3");
				table7.addCell("L1-L3");
				table7.addCell("L1-N");
				table7.addCell("L2-N");
				table7.addCell("L3-N");
				table7.addCell("L1-PE");
				table7.addCell("L2-PE");
				table7.addCell("L3-PE");
				addData7(table7, testRecords);

				document.add(table7);
				document.setPageSize(PageSize.LETTER.rotate());
				PdfPTable table8 = new PdfPTable(9);
				// table55.setWidths(new int[]{ 2, 2, 2, 2, 2,2});
				table8.setSpacingAfter(30f); // Space after table

				table8.setWidthPercentage(100);

				PdfPCell cell26;
				cell26 = new PdfPCell(
						new Phrase("Disconnection time (for a short circuit/fault of negligible impedance) (seconds)"));
				cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell26.setColspan(9);
				table8.addCell(cell26);
				table8.addCell("L1-L2");
				table8.addCell("L2-L3");
				table8.addCell("L1-L3");
				table8.addCell("L1-N");
				table8.addCell("L2-N");
				table8.addCell("L3-N");
				table8.addCell("L1-PE");
				table8.addCell("L2-PE");
				table8.addCell("L3-PE");
				// table8.setSplitRows(90);
				addData8(table8, testRecords);

				document.add(table8);

				PdfPTable table9 = new PdfPTable(5);
				// table55.setWidths(new int[]{ 2, 2, 2, 2, 2,2});
				table9.setSpacingAfter(30f); // Space after table
				table9.setWidthPercentage(100);

				PdfPCell cell22;
				cell22 = new PdfPCell(new Phrase("RCD"));
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell22.setColspan(4);
				table9.addCell(cell22);

				cell22 = new PdfPCell(new Phrase("Remarks"));
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell22.setRowspan(3);
				table9.addCell(cell22);

				cell22 = new PdfPCell(new Phrase("Live"));
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell22.setRowspan(2);
				table9.addCell(cell22);

				cell22 = new PdfPCell(new Phrase("Operating time"));
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell22.setRowspan(1);
				cell22.setColspan(2);

				table9.addCell(cell22);
				cell22 = new PdfPCell(new Phrase("Test button operation"));
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell22.setRowspan(2);
				table9.addCell(cell22);
				table9.addCell("I∆n (Current)");
				table9.addCell("I∆n ");
				addData2(table9, testRecords);
				table9.addCell(cell22);

				document.add(table9);
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new PeriodicTestingException("Invalid Inputs");
		}

	}

	private void addData8(PdfPTable table8, List<TestingRecords> testRecords) {
		for (TestingRecords arr : testRecords) {
			PdfPCell cell = new PdfPCell();
			String disconnectionTime = arr.getDisconnectionTime();
			String disconnectionTime_list[] = disconnectionTime.split(",");
			String DT1 = disconnectionTime_list[0];
			String DT2 = disconnectionTime_list[1];
			String DT3 = disconnectionTime_list[2];
			String DT4 = disconnectionTime_list[3];
			String DT5 = disconnectionTime_list[4];
			String DT6 = disconnectionTime_list[5];
			String DT7 = disconnectionTime_list[6];
			String DT8 = disconnectionTime_list[7];
			String DT9 = disconnectionTime_list[8];
			cell.setPhrase(new Phrase(DT1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT3));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT4));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT5));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT7));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(DT9));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
		}

	}

	private void addData7(PdfPTable table7, List<TestingRecords> testRecords) {
		for (TestingRecords arr : testRecords) {
			PdfPCell cell = new PdfPCell();
			String loopImpedence = arr.getTestFaultCurrent();
			String loopImpedence_list[] = loopImpedence.split(",");
			String LI1 = loopImpedence_list[0];
			String LI2 = loopImpedence_list[1];
			String LI3 = loopImpedence_list[2];
			String LI4 = loopImpedence_list[3];
			String LI5 = loopImpedence_list[4];
			String LI6 = loopImpedence_list[5];
			String LI7 = loopImpedence_list[6];
			String LI8 = loopImpedence_list[7];
			String LI9 = loopImpedence_list[8];
			cell.setPhrase(new Phrase(LI1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI3));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI4));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI5));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI7));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
			cell.setPhrase(new Phrase(LI9));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell);
		}

	}

	private void addData5(PdfPTable table6, List<TestingRecords> testRecords) {
		for (TestingRecords arr : testRecords) {
			PdfPCell cell = new PdfPCell();
			String faultCurrent = arr.getTestLoopImpedance();
			String faultCurrent_list[] = faultCurrent.split(",");
			String FC1 = faultCurrent_list[0];
			String FC2 = faultCurrent_list[1];
			String FC3 = faultCurrent_list[2];
			String FC4 = faultCurrent_list[3];
			String FC5 = faultCurrent_list[4];
			String FC6 = faultCurrent_list[5];
			String FC7 = faultCurrent_list[6];
			String FC8 = faultCurrent_list[7];
			String FC9 = faultCurrent_list[8];
			cell.setPhrase(new Phrase(FC1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC3));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC4));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC5));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC7));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
			cell.setPhrase(new Phrase(FC9));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell);
		}

	}

	private void addData4(PdfPTable table4, List<TestingRecords> testRecords) {
		for (TestingRecords arr : testRecords) {
			PdfPCell cell = new PdfPCell();
			String voltage = arr.getTestVoltage();
			String voltage_list[] = voltage.split(",");
			String V1 = voltage_list[0];
			String V2 = voltage_list[1];
			String V3 = voltage_list[2];
			String V4 = voltage_list[3];
			String V5 = voltage_list[4];
			String V6 = voltage_list[5];
			String V7 = voltage_list[6];
			String V8 = voltage_list[7];
			String V9 = voltage_list[8];
			cell.setPhrase(new Phrase(V1));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V2));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V3));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V4));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V5));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V7));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
			cell.setPhrase(new Phrase(V9));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell);
		}

	}

	private void addData2(PdfPTable table9, List<TestingRecords> testRecords) {
		for (TestingRecords arr : testRecords) {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getRcdCurrent()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table9.addCell(cell);
			cell.setPhrase(new Phrase(arr.getRcdOperatingCurrent()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table9.addCell(cell);
			cell.setPhrase(new Phrase(arr.getRcdOperatingFiveCurrent()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table9.addCell(cell);
			cell.setPhrase(new Phrase(arr.getRcdTestButtonOperation()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table9.addCell(cell);
			cell.setPhrase(new Phrase(arr.getRcdRemarks()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table9.addCell(cell);
		}
	}

	private void addData1(PdfPTable table3, List<TestingRecords> testRecords) {
		for (TestingRecords arr : testRecords) {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getConductorInstallation()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getConductorLive()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getConductorPecpc()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getContinutiyApproximateLength()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getContinutiyRR()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getContinutiyR()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getContinutiyLL()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getContinutiyLE()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
			cell.setPhrase(new Phrase(arr.getContinutiyPolarity()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell);
		}

	}

	private void addData(PdfPTable table55, List<TestingRecords> testRecords) {
		for (TestingRecords arr : testRecords) {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getCircuitNo()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table55.addCell(cell);
			cell.setPhrase(new Phrase(arr.getCircuitDesc()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table55.addCell(cell);
			cell.setPhrase(new Phrase(arr.getCircuitStandardNo()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table55.addCell(cell);
			cell.setPhrase(new Phrase(arr.getCircuitStandardNo()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table55.addCell(cell);
			cell.setPhrase(new Phrase(arr.getCircuitRating()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table55.addCell(cell);
			cell.setPhrase(new Phrase(arr.getCircuitBreakingCapacity()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table55.addCell(cell);
		}
	}

	private void addRow1(PdfPTable table5, String string, String string2, String string3, String string4,
			String string5, String string6) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		nameCell.setRowspan(2);
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		valueCell1.setRowspan(2);
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));

		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4));

		PdfPCell valueCell4 = new PdfPCell(new Paragraph(string5));

		PdfPCell valueCell5 = new PdfPCell(new Paragraph(string6));

		table5.addCell(nameCell);
		table5.addCell(valueCell1);
		table5.addCell(valueCell2);
		table5.addCell(valueCell3);
		table5.addCell(valueCell4);
		table5.addCell(valueCell5);

	}

	private void addRow(PdfPTable table4, String string) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);

		table4.addCell(nameCell);
	}

	private void addRow(PdfPTable table2, String string, String string2, String string3, String string4, String string5,
			String string6, String string7, String string8, String string9, String string10) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4));
		PdfPCell valueCell4 = new PdfPCell(new Paragraph(string5));
		PdfPCell valueCell5 = new PdfPCell(new Paragraph(string6));
		PdfPCell valueCell6 = new PdfPCell(new Paragraph(string7));
		PdfPCell valueCell7 = new PdfPCell(new Paragraph(string8));
		PdfPCell valueCell8 = new PdfPCell(new Paragraph(string9));
		PdfPCell valueCell9 = new PdfPCell(new Paragraph(string10));

		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(nameCell);
		table2.addCell(valueCell1);
		table2.addCell(valueCell2);
		table2.addCell(valueCell3);
		table2.addCell(valueCell4);
		table2.addCell(valueCell5);
		table2.addCell(valueCell6);
		table2.addCell(valueCell7);
		table2.addCell(valueCell8);
		table2.addCell(valueCell9);

	}

}
