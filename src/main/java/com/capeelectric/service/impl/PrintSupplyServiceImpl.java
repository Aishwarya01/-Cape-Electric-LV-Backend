package com.capeelectric.service.impl;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.BoundingLocationReport;
import com.capeelectric.model.CircuitBreaker;
import com.capeelectric.model.EarthingLocationReport;
import com.capeelectric.model.InstalLocationReport;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryDeclaration;
import com.capeelectric.model.SummaryObervation;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.SupplyParameters;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.service.PrintSupplyService;
import com.capeelectric.service.SupplyCharacteristicsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintSupplyServiceImpl implements PrintSupplyService {
	@Autowired
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	@Override
	public void printSupply(String userName, Integer siteId) throws SupplyCharacteristicsException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document(PageSize.A4, 36, 36, 50, 36);

			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SupplyCharacteristic.pdf"));
				List<SupplyCharacteristics> supply1 = supplyCharacteristicsRepository.findByUserNameAndSiteId(userName,
						siteId);

				SupplyCharacteristics supply = supply1.get(0);

				List<SupplyParameters> supplyParameters1 = supply.getSupplyParameters();
				SupplyParameters supplyParameters = supplyParameters1.get(0);

				List<CircuitBreaker> circuitBreaker = supply.getCircuitBreaker();
				List<InstalLocationReport> instalLocationReport = supply.getInstalLocationReport();

				String parameternominal = supplyParameters.getNominalVoltage();

				String parameternominal_list[] = parameternominal.split(",");
				String PN1 = parameternominal_list[0];
				String PN2 = parameternominal_list[1];
				String PN3 = parameternominal_list[2];
				String PN4 = parameternominal_list[3];
				String PN5 = parameternominal_list[4];
				// String PN6 = parameternominal_list[5];
//					 String PN7 = parameternominal_list[6];
//					 String PN8 = parameternominal_list[7];
//					 String PN9 =parameternominal_list[8];
//				           

				String parameterFrequency = supplyParameters.getNominalFrequency();

				String parameterFrequency_list[] = parameterFrequency.split(",");
				String PFN1 = parameterFrequency_list[0];
				String PFN2 = parameterFrequency_list[1];
				String PFN3 = parameterFrequency_list[2];
				String PFN4 = parameterFrequency_list[3];
				String PFN5 = parameterFrequency_list[4];
				// String PFN6 =parameterFrequency_list[5];
//						 String PFN7 = parameterFrequency_list[6];
//						 String PFN8 =parameterFrequency_list[7];
//						 String PFN9 =parameterFrequency_list[8];
//					           

				String parameterCurrent = supplyParameters.getFaultCurrent();

				String parameterCurrent_list[] = parameterCurrent.split(",");
				String PC1 = parameterCurrent_list[0];
				String PC2 = parameterCurrent_list[1];
				String PC3 = parameterCurrent_list[2];
				String PC4 = parameterCurrent_list[3];
				String PC5 = parameterCurrent_list[4];
				// String PC6 =parameterCurrent_list[5];
//							 String PC7 =parameterCurrent_list[6];
//							 String PC8 =parameterCurrent_list[7];
//							 String PC9 =parameterCurrent_list[8];

				String parameterImpedance = supplyParameters.getLoopImpedance();

				String parameterImpedance_list[] = parameterImpedance.split(",");
				String PI1 = parameterImpedance_list[0];
				String PI2 = parameterImpedance_list[1];
				String PI3 = parameterImpedance_list[2];
				String PI4 = parameterImpedance_list[3];
				String PI5 = parameterImpedance_list[4];
				// String PI6 =parameterImpedance_list[5];
//								 String PI7 =parameterImpedance_list[6];
//								 String PI8 =parameterImpedance_list[7];
//								 String PI9 =parameterImpedance_list[8];

				String nominal = supply.getMainNominalVoltage();
				String nominal_list[] = nominal.split(",");
				String N1 = nominal_list[0];
				String N2 = nominal_list[1];
				String N3 = nominal_list[2];
				String N4 = nominal_list[3];
				String N5 = nominal_list[4];
				String N6 = nominal_list[5];
				String N7 = nominal_list[6];
				String N8 = nominal_list[7];
				String N9 = nominal_list[8];

				String nominalFrequency = supply.getMainNominalFrequency();
				String nominalFrequency_list[] = nominalFrequency.split(",");
				String NF1 = nominalFrequency_list[0];
				String NF2 = nominalFrequency_list[1];
				String NF3 = nominalFrequency_list[2];
				String NF4 = nominalFrequency_list[3];
				String NF5 = nominalFrequency_list[4];
				String NF6 = nominalFrequency_list[5];
				String NF7 = nominalFrequency_list[6];
				String NF8 = nominalFrequency_list[7];
				String NF9 = nominalFrequency_list[8];

				String nominalFaultCurrent = supply.getMainNominalCurrent();
				String nominalFaultCurrent_list[] = nominalFaultCurrent.split(",");
				String NFC1 = nominalFaultCurrent_list[0];
				String NFC2 = nominalFaultCurrent_list[1];
				String NFC3 = nominalFaultCurrent_list[2];
				String NFC4 = nominalFaultCurrent_list[3];
				String NFC5 = nominalFaultCurrent_list[4];
				String NFC6 = nominalFaultCurrent_list[5];
				String NFC7 = nominalFaultCurrent_list[6];
				String NFC8 = nominalFaultCurrent_list[7];
				String NFC9 = nominalFaultCurrent_list[8];

				String nominalFaultLoop = supply.getMainLoopImpedance();
				String nominalFaultLoop_list[] = nominalFaultLoop.split(",");
				String NFL1 = nominalFaultLoop_list[0];
				String NFL2 = nominalFaultLoop_list[1];
				String NFL3 = nominalFaultLoop_list[2];
				String NFL4 = nominalFaultLoop_list[3];
				String NFL5 = nominalFaultLoop_list[4];
				String NFL6 = nominalFaultLoop_list[5];
				String NFL7 = nominalFaultLoop_list[6];
				String NFL8 = nominalFaultLoop_list[7];
				String NFL9 = nominalFaultLoop_list[8];

				List<BoundingLocationReport> boundingLocationReport = supply.getBoundingLocationReport();
				List<EarthingLocationReport> earthingLocationReport = supply.getEarthingLocationReport();

				document.open();
				Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC | Font.BOLD | Font.UNDERLINE,
						BaseColor.BLACK);
				Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraphOne = new Paragraph(
						"Verification (inspection and testing) of an electrical installation", font1);
				paragraphOne.setAlignment(Element.ALIGN_CENTER);
				Paragraph paragraphTwo = new Paragraph("Section-2", font);
				paragraphTwo.setAlignment(Element.ALIGN_CENTER);
				Paragraph paragraphThree = new Paragraph("Supply characteristics and earthing arrangement", font1);
				paragraphThree.setAlignment(Element.ALIGN_CENTER);
				Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.BOLD);
				Paragraph paragraphFour = new Paragraph(20,
						"(Initial and periodic inspection of an existing installation up to 1000 V AC and 1500 V DC)",
						font2);
				paragraphFour.setAlignment(Element.ALIGN_CENTER);

				Font font4 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.UNDERLINE | Font.BOLD,
						BaseColor.BLACK);
				Paragraph paragraphOneFive = new Paragraph(30, "Section-1", font5);
				Paragraph paragraphOneSix = new Paragraph(30, "Mains incoming", font4);
				paragraphOneSix.setAlignment(Element.ALIGN_CENTER);
				paragraphOneFive.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne);
				document.add(paragraphTwo);
				document.add(paragraphThree);
				document.add(paragraphFour);
				document.add(paragraphOneFive);
				document.add(paragraphOneSix);

				float[] pointColumnWidths = { 200F, 100F };
				PdfPTable table = new PdfPTable(pointColumnWidths);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setSpacingAfter(10f); // Space after table
				table.setWidthPercentage(100);
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph(supply.getMainSystemEarthing(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table.addCell(new Phrase("System earthing:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell.setFixedHeight(30f);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell);

				PdfPCell cell1 = new PdfPCell(new Paragraph(supply.getSystemEarthingBNote(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table.addCell(new Phrase("Brief note (in case of confusion):",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell1.setFixedHeight(30f);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(
						new Paragraph(supply.getLiveConductorType(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table.addCell(new Phrase("Number and type of live conductors:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell2.setFixedHeight(30f);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);

				PdfPCell cell5 = new PdfPCell(new Paragraph(supply.getLiveConductorBNote(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table.addCell(new Phrase("Brief note:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell5.setFixedHeight(30f);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell5);
				document.add(table);

				if (supply.getLiveConductorType().equals("efeef ")) {
					float[] pointColumnWidths1 = { 200F, 100F };
					PdfPTable table15 = new PdfPTable(pointColumnWidths1);
					table15.setWidthPercentage(100); // Width 100%
					table15.setSpacingBefore(10f); // Space before table
					table15.setSpacingAfter(10f); // Space after table
					table15.setWidthPercentage(100);
					table15.getDefaultCell().setBorder(0);

					PdfPCell cell3 = new PdfPCell(new Paragraph(supply.getLiveConductorAC(),
							new Font(BaseFont.createFont(), 12, Font.NORMAL)));
					table15.addCell(new Phrase("AC", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
					cell3.setFixedHeight(30f);
					cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell3.setBorder(PdfPCell.NO_BORDER);
					table15.addCell(cell3);
					document.add(table15);

					PdfPTable table1 = new PdfPTable(10);
					table1.setWidthPercentage(100); // Width 100%
					table1.setSpacingBefore(10f); // Space before table
					table1.setSpacingAfter(10f); // Space after table
					table1.setWidthPercentage(100);
					table1.getDefaultCell().setBorder(0);

					addRow(table1, "Nature of supply parameters", "R-Y", "R-B", "Y-B", "R-N", "Y-N", "B-N", "R-PE",
							"Y-PE", "B-PE");
					addRow(table1, "Nominal voltage U/U0 (V)", N1, N2, N3, N4, N5, N6, N7, N8, N9);
					addRow(table1, "Nominal Frequency f (HZ)", NF1, NF2, NF3, NF4, NF5, NF6, NF7, NF8, NF9);
					addRow(table1, "Prospective fault current Ipfc (kA) ", NFC1, NFC2, NFC3, NFC4, NFC5, NFC6, NFC7,
							NFC8, NF9);
					addRow(table1, "External Loop Impedance Ze (Ω)", NFL1, NFL2, NFL3, NFL4, NFL5, NFL6, NFL7, NFL8,
							NFL9);
					document.add(table1);
				} else {

					float[] pointColumnWidths1 = { 200F, 100F };
					PdfPTable table15 = new PdfPTable(pointColumnWidths1);
					table15.setWidthPercentage(100); // Width 100%
					table15.setSpacingBefore(10f); // Space before table
					table15.setSpacingAfter(10f); // Space after table
					table15.setWidthPercentage(100);
					table15.getDefaultCell().setBorder(0);
					PdfPCell cell4 = new PdfPCell(new Paragraph(supply.getLiveConductorDC(),
							new Font(BaseFont.createFont(), 12, Font.NORMAL)));
					table15.addCell(new Phrase("DC", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
					cell4.setFixedHeight(30f);
					cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell4.setBorder(PdfPCell.NO_BORDER);
					table15.addCell(cell4);
					document.add(table15);
				}

				PdfPTable table2 = new PdfPTable(pointColumnWidths);
				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingBefore(10f); // Space before table
				table2.setSpacingAfter(10f); // Space after table
				table2.setWidthPercentage(100);
				table2.getDefaultCell().setBorder(0);

				Paragraph paragraph1 = new Paragraph("Incoming (supply) protective device characteristics	",
						new Font(BaseFont.createFont(), 14, Font.ITALIC));
				document.add(paragraph1);
				PdfPCell cell6 = new PdfPCell(new Paragraph(supply.getMainNominalProtectiveDevice(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table2.addCell(new Phrase("Type of Over Current Protective Device:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell6.setFixedHeight(30f);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(
						new Paragraph(supply.getMainRatedCurrent(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table2.addCell(new Phrase("Rated current (A):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell7.setFixedHeight(30f);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell7.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(
						new Paragraph(supply.getMainRatedCurrent(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table2.addCell(new Phrase("Current for disconnection with in 0.2 seconds:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell8.setFixedHeight(30f);
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell8.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell8);
				document.add(table2);

				Paragraph paragraphOne1 = new Paragraph(30, "Section-2", font5);
				Paragraph paragraphOne2 = new Paragraph(30, "Alternative source of supply", font4);
				paragraphOne1.setAlignment(Element.ALIGN_CENTER);
				paragraphOne2.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne1);
				document.add(paragraphOne2);

				PdfPTable table15 = new PdfPTable(pointColumnWidths);
				table15.setWidthPercentage(100); // Width 100%
				table15.setSpacingBefore(10f); // Space before table
				table15.setSpacingAfter(10f); // Space after table
				table15.setWidthPercentage(100);
				table15.getDefaultCell().setBorder(0);

				PdfPCell cell9 = new PdfPCell(
						new Paragraph(supply.getAlternativeSupply(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table15.addCell(new Phrase(" Availability of alternate supply:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell9.setFixedHeight(30f);
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell9.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(
						new Paragraph(supply.getAlternativeSupply(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table15.addCell(new Phrase(" Number of alternate sources of supply:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell10.setFixedHeight(30f);
				cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell10.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell10);
				document.add(table15);

				for (SupplyParameters arr : supplyParameters1) {

					altenateSupply(document, arr, PN1, PN2, PN3, PN4, PN5, PFN1, PFN2, PFN3, PFN4, PFN5, PC1, PC2, PC3,
							PC4, PC5, PI1, PI2, PI3, PI4, PI5);
				}
				
				
				document.newPage();
				Paragraph paragraphOne3 = new Paragraph(30, "Section-3", font5);
				Paragraph paragraphOne4 = new Paragraph(30, "Particulars of installation referred in the report",
						font4);
				paragraphOne3.setAlignment(Element.ALIGN_CENTER);
				paragraphOne4.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne3);
				document.add(paragraphOne4);

				PdfPTable table7 = new PdfPTable(pointColumnWidths);
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
				table7.setSpacingAfter(10f); // Space after table
				table7.setWidthPercentage(100);
				table7.getDefaultCell().setBorder(0);

				PdfPCell cell22 = new PdfPCell(
						new Paragraph(supply.getMaximumDemand(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table7.addCell(new Phrase("Maximum demand kVA:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell22.setFixedHeight(30f);
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell22.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell22);

				PdfPCell cell23 = new PdfPCell(
						new Paragraph(supply.getMaximumLoad(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table7.addCell(new Phrase("Maximum load:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell23.setFixedHeight(30f);
				cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell23.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell23);

				PdfPCell cell24 = new PdfPCell(
						new Paragraph(supply.getMeansEarthing(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table7.addCell(new Phrase("Means of earthing :", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell24.setFixedHeight(30f);
				cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell24.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell24);

				PdfPCell cell25 = new PdfPCell(
						new Paragraph(supply.getElectrodeType(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table7.addCell(
						new Phrase("Type of earth electrode :", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell25.setFixedHeight(30f);
				cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell25.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell25);

				PdfPCell cell26 = new PdfPCell(
						new Paragraph(supply.getElectrodeMaterial(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table7.addCell(
						new Phrase("Material of earth electrode :", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell26.setFixedHeight(30f);
				cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell26.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell26);

				PdfPCell cell27 = new PdfPCell(new Paragraph(supply.getNoOfLocation()));
				table7.addCell(new Phrase("No of Locations :", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell27.setFixedHeight(30f);
				cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell27.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell27);
				document.add(table7);

				PdfPTable table8 = new PdfPTable(4);
				table8.setWidthPercentage(100); // Width 100%
				table8.setSpacingBefore(10f); // Space before table
				table8.setSpacingAfter(10f); // Space after table
				table8.setWidthPercentage(100);
				table8.getDefaultCell().setBorder(0);

				TableHeader(table8);
				TableData(table8, instalLocationReport);

				document.add(table8);

				document.newPage();
				Paragraph paragraphOne6 = new Paragraph(30, "Section-4", font5);
				Paragraph paragraphOne7 = new Paragraph(30, "Details of main protective conductor		", font4);
				paragraphOne6.setAlignment(Element.ALIGN_CENTER);
				paragraphOne7.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne6);
				document.add(paragraphOne7);

				PdfPTable table10 = new PdfPTable(pointColumnWidths);
				table10.setWidthPercentage(100); // Width 100%
				table10.setSpacingBefore(10f); // Space before table
				table10.setSpacingAfter(10f); // Space after table
				table10.setWidthPercentage(100);
				table10.getDefaultCell().setBorder(0);

				PdfPCell cell30 = new PdfPCell(
						new Paragraph(supply.getConductorSize(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table10.addCell(
						new Phrase("Size of earthing conductor :", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell30.setFixedHeight(30f);
				cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell30.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell30);

				PdfPCell cell31 = new PdfPCell(
						new Paragraph(supply.getConductormaterial(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table10.addCell(new Phrase("Material of earthing conductor :",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell31.setFixedHeight(30f);
				cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell31.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell31);

				PdfPCell cell32 = new PdfPCell(
						new Paragraph(supply.getConductorVerify(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table10.addCell(new Phrase("Earthing conductor continuity and connection verified :",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell32.setFixedHeight(30f);
				cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell32.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell32);

				PdfPCell cell33 = new PdfPCell(
						new Paragraph(supply.getMaximumDemand(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table10.addCell(new Phrase("Size of main protective bonding conductor :",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell33.setFixedHeight(30f);
				cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell33.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell33);

				PdfPCell cell34 = new PdfPCell(new Paragraph(supply.getBondingConductorMaterial(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table10.addCell(new Phrase("Material of main protective bonding conductor :",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell34.setFixedHeight(30f);
				cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell34.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell34);

				PdfPCell cell35 = new PdfPCell(new Paragraph(supply.getBondingConductorVerify(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table10.addCell(new Phrase("Main protective bonding conductor continuity and connection verified :",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell35.setFixedHeight(30f);
				cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell35.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(
						new Paragraph(supply.getBondingJointsType(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table10.addCell(new Phrase("Type of joints (impairing safety) :",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell36.setFixedHeight(30f);
				cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell36.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell36);

				PdfPCell cell37 = new PdfPCell(new Paragraph(supply.getBondingNoOfJoints()));
				table10.addCell(new Phrase("No of joints :", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell37.setFixedHeight(30f);
				cell37.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell37.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell37);
				document.add(table10);

				PdfPTable table11 = new PdfPTable(3);
				table11.setWidthPercentage(100); // Width 100%
				table11.setSpacingBefore(10f); // Space before table
				table11.setSpacingAfter(10f); // Space after table
				table11.setWidthPercentage(100);
				// table11.getDefaultCell().setBorder(0);

				TableHeader1(table11);
				TableData1(table11, boundingLocationReport);

				document.add(table11);

				PdfPTable table12 = new PdfPTable(pointColumnWidths);
				table12.setWidthPercentage(100); // Width 100%
				table12.setSpacingBefore(10f); // Space before table
				table12.setSpacingAfter(10f); // Space after table
				table12.setWidthPercentage(100);
				table12.getDefaultCell().setBorder(0);

				PdfPCell cell40 = new PdfPCell(new Paragraph(supply.getEarthingConductorSize(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table12.addCell(new Phrase("Size of main protective earthing conductor:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell40.setFixedHeight(30f);
				cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell40.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell40);

				PdfPCell cell41 = new PdfPCell(new Paragraph(supply.getEarthingConductorMaterial(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table12.addCell(new Phrase("Material of main protective earthing conductor:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell41.setFixedHeight(30f);
				cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell41.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell41);

				PdfPCell cell42 = new PdfPCell(new Paragraph(supply.getEarthingConductorVerify(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table12.addCell(new Phrase("Main Protective Conductor continuity and connection verified:",
						new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell42.setFixedHeight(30f);
				cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell42.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell42);

				PdfPCell cell43 = new PdfPCell(new Paragraph(supply.getEarthingJointsType(),
						new Font(BaseFont.createFont(), 12, Font.NORMAL)));
				table12.addCell(new Phrase("Type of Joints:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell43.setFixedHeight(30f);
				cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell43.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(new Paragraph(supply.getEarthingNoOfJoints()));
				table12.addCell(new Phrase("No of Joints:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
				cell44.setFixedHeight(30f);
				cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell44.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell44);
				document.add(table12);

				PdfPTable table13 = new PdfPTable(3);
				table13.setWidthPercentage(100); // Width 100%
				table13.setSpacingBefore(10f); // Space before table
				table13.setSpacingAfter(10f); // Space after table
				table13.setWidthPercentage(100);
				// table13.getDefaultCell().setBorder(0);

				TableHeader1(table13);
				TableData2(table13, earthingLocationReport);
				// /addRow(table13, "Location", "Joint No", "Joint resistance (Ω)");
				document.add(table13);
				document.newPage();
				Paragraph paragraphOne5 = new Paragraph(30, "Section-5", font5);
				Paragraph paragraphOne8 = new Paragraph(30, "Details of main switch or circuit breaker", font4);
				paragraphOne5.setAlignment(Element.ALIGN_CENTER);
				paragraphOne8.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne5);
				document.add(paragraphOne8);

				for (CircuitBreaker circuteitr : circuitBreaker) {
					circuteBraker(document, circuteitr);

				}
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new SupplyCharacteristicsException("Invalid Inputs");
		}
	}

	private void altenateSupply(Document document, SupplyParameters supplyParameters, String PN1, String PN2,
			String PN3, String PN4, String PN5, String PFN1, String PFN2, String PFN3, String PFN4, String PFN5,
			String PC1, String PC2, String PC3, String PC4, String PC5, String PI1, String PI2, String PI3, String PI4,
			String PI5) throws DocumentException, IOException {
		float[] pointColumnWidths = { 200F, 100F };
		PdfPTable table3 = new PdfPTable(pointColumnWidths);
		table3.setWidthPercentage(100); // Width 100%
		table3.setSpacingBefore(10f); // Space before table
		table3.setSpacingAfter(10f); // Space after table
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setBorder(0);

		PdfPCell cell11 = new PdfPCell(
				new Paragraph(supplyParameters.getaLSupplyNo(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase(" Alternate supply no", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell11.setFixedHeight(30f);
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell11);

		PdfPCell cell12 = new PdfPCell(new Paragraph(supplyParameters.getaLSupplyShortName(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(
				new Phrase(" Short name of alternate supply:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell12.setFixedHeight(30f);
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell12.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell12);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(pointColumnWidths);
		table4.setWidthPercentage(100); // Width 100%
		table4.setSpacingBefore(10f); // Space before table
		table4.setSpacingAfter(10f); // Space after table
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setBorder(0);

		PdfPCell cell13 = new PdfPCell(new Paragraph(supplyParameters.getaLSystemEarthing(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(new Phrase("System earthing:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell13.setFixedHeight(30f);
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell13);

		PdfPCell cell15 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorType(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(
				new Phrase("Number and type of live conductors:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell15.setFixedHeight(30f);
		cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell15.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell15);

		PdfPCell cell18 = new PdfPCell(new Paragraph(supplyParameters.getaLSystemEarthingBNote(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(new Phrase("Brief note:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell18.setFixedHeight(30f);
		cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell18.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell18);
		document.add(table4);

		if (supplyParameters.getaLLiveConductorType().equals("grtgrtgrtgt  ")) {
			float[] pointColumnWidths1 = { 200F, 100F };
			PdfPTable table16 = new PdfPTable(pointColumnWidths1);
			table16.setWidthPercentage(100); // Width 100%
			table16.setSpacingBefore(10f); // Space before table
			table16.setSpacingAfter(10f); // Space after table
			table16.setWidthPercentage(100);
			table16.getDefaultCell().setBorder(0);

			PdfPCell cell16 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorAC(),
					new Font(BaseFont.createFont(), 12, Font.NORMAL)));
			table16.addCell(new Phrase("AC", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
			cell16.setFixedHeight(30f);
			cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell16.setBorder(PdfPCell.NO_BORDER);
			table16.addCell(cell16);
			document.add(table16);

			PdfPTable table5 = new PdfPTable(10);
			table5.setWidthPercentage(100); // Width 100%
			table5.setSpacingBefore(10f); // Space before table
			table5.setSpacingAfter(10f); // Space after table
			table5.setWidthPercentage(100);
			table5.getDefaultCell().setBorder(0);

			addRow(table5, "Nature of supply parameters", "R-Y", "R-B", "Y-B", "R-N", "Y-N", "B-N", "R-PE", "Y-PE",
					"B-PE");
			addRow(table5, "Nominal voltage U/U0 (V)", PN1, PN2, PN3, PN4, PN5, "", "", "", "");
			addRow(table5, "Nominal Frequency f (HZ)", PFN1, PFN2, PFN3, PFN4, PFN5, "", "", "", "");
			addRow(table5, "Prospective fault current Ipfc (kA) ", PC1, PC2, PC3, PC4, PC5, "", "", "", "");
			addRow(table5, "External Loop Impedance Ze (Ω)", PI1, PI2, PI3, PI4, PI5, "", "", "", "");
			document.add(table5);
//				document.add(paragraphSeven);

			PdfPTable table9 = new PdfPTable(pointColumnWidths);
			table9.setWidthPercentage(100); // Width 100%
			table9.setSpacingBefore(10f); // Space before table
			table9.setSpacingAfter(10f); // Space after table
			table9.setWidthPercentage(100);
			table9.getDefaultCell().setBorder(0);

			PdfPCell cell50 = new PdfPCell(new Paragraph(supplyParameters.getInstalledCapacity(),
					new Font(BaseFont.createFont(), 12, Font.NORMAL)));
			table9.addCell(new Phrase("InstalledCapacity", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
			cell50.setFixedHeight(30f);
			cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell50.setBorder(PdfPCell.NO_BORDER);
			table9.addCell(cell50);

			PdfPCell cell51 = new PdfPCell(
					new Paragraph(supplyParameters.getActualLoad(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
			table9.addCell(
					new Phrase("Actual Load Current (R,Y,B, N)", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
			cell51.setFixedHeight(30f);
			cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell51.setBorder(PdfPCell.NO_BORDER);
			table9.addCell(cell51);
			document.add(table9);
		} else {

			PdfPTable table16 = new PdfPTable(pointColumnWidths);
			table16.setWidthPercentage(100); // Width 100%
			table16.setSpacingBefore(10f); // Space before table
			table16.setSpacingAfter(10f); // Space after table
			table16.setWidthPercentage(100);
			table16.getDefaultCell().setBorder(0);
			PdfPCell cell17 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorDC(),
					new Font(BaseFont.createFont(), 12, Font.NORMAL)));
			table16.addCell(new Phrase("DC", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
			cell17.setFixedHeight(30f);
			cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell17.setBorder(PdfPCell.NO_BORDER);
			table16.addCell(cell17);
			document.add(table16);

		}

		PdfPTable table6 = new PdfPTable(pointColumnWidths);
		table6.setWidthPercentage(100); // Width 100%
		table6.setSpacingBefore(10f); // Space before table
		table6.setSpacingAfter(10f); // Space after table
		table6.setWidthPercentage(100);
		table6.getDefaultCell().setBorder(0);

		Paragraph paragraph3 = new Paragraph("Incoming (supply) protective device characteristics	",
				new Font(BaseFont.createFont(), 14, Font.ITALIC));
		document.add(paragraph3);
		PdfPCell cell19 = new PdfPCell(new Paragraph(supplyParameters.getProtectiveDevice(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table6.addCell(new Phrase("Type of Over Current Protective Device:",
				new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell19.setFixedHeight(30f);
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell19.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell19);

		PdfPCell cell20 = new PdfPCell(
				new Paragraph(supplyParameters.getRatedCurrent(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table6.addCell(new Phrase("Rated current (A):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell20.setFixedHeight(30f);
		cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell20.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell20);

		PdfPCell cell21 = new PdfPCell(new Paragraph(supplyParameters.getCurrentDissconnection(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table6.addCell(new Phrase("Current for disconnection with in 0.2 seconds:",
				new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell21.setFixedHeight(30f);
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell21);

		PdfPCell cell55 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorBNote(),
				new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table6.addCell(
				new Phrase("Brief note (in case of confusion):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell55.setFixedHeight(30f);
		cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell55.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell55);

		document.add(table6);
	}

	private void circuteBraker(Document document, CircuitBreaker circute1) throws DocumentException, IOException {
		float[] pointColumnWidths = { 200F, 100F };
		PdfPTable table9 = new PdfPTable(pointColumnWidths);
		table9.setWidthPercentage(100); // Width 100%
		table9.setSpacingBefore(10f); // Space before table
		table9.setSpacingAfter(10f); // Space after table
		table9.setWidthPercentage(100);
		table9.getDefaultCell().setBorder(0);

		PdfPCell cell29 = new PdfPCell(
				new Paragraph(circute1.getLocation(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(new Phrase("Location:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell29.setFixedHeight(30f);
		// cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell29.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell29);

		PdfPCell cell45 = new PdfPCell(
				new Paragraph(circute1.getType(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(new Phrase("Type:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell45.setFixedHeight(30f);
		// cell45.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell45.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell45);

		PdfPCell cell47 = new PdfPCell(
				new Paragraph(circute1.getNoPoles(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(new Phrase("No of poles:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell47.setFixedHeight(30f);
		// cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell47.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell47);

		PdfPCell cell55 = new PdfPCell(
				new Paragraph(circute1.getCurrent(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(new Phrase("Current Rating:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell55.setFixedHeight(30f);
		// cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell55.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell55);

		PdfPCell cell50 = new PdfPCell(
				new Paragraph(circute1.getVoltage(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(new Phrase("Voltage Rating:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell50.setFixedHeight(30f);
		// cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell50.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(
				new Paragraph(circute1.getFuse(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(new Phrase("Fuse Rating or Settings:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell51.setFixedHeight(30f);
		// cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell51.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell51);

		PdfPCell cell38 = new PdfPCell(
				new Paragraph(circute1.getResidualCurrent(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(
				new Phrase("Rated residual operating Current I∆n:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell38.setFixedHeight(30f);
//		cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell38.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell38);

		PdfPCell cell39 = new PdfPCell(
				new Paragraph(circute1.getResidualTime(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table9.addCell(new Phrase("Rated residual operating time @I∆n     T∆n:",
				new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell39.setFixedHeight(30f);
//		cell39.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell39.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell39);
		document.add(table9);
	}

	private void TableData2(PdfPTable table13, List<EarthingLocationReport> earthingLocationReport) {
		for (EarthingLocationReport arr : earthingLocationReport) {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getLocation()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table13.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointNo()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table13.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointResistance()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table13.addCell(cell);
		}

	}

	private void TableData1(PdfPTable table11, List<BoundingLocationReport> boundingLocationReport) {
		for (BoundingLocationReport arr : boundingLocationReport) {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getLocation()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table11.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointNo()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table11.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointResistance()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table11.addCell(cell);
		}
}

	private void TableHeader1(PdfPTable table11) {
		PdfPCell cell = new PdfPCell();
//	        cell.setBorderColor(Color.BLUE);
		cell.setPadding(4);
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		// font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("Location", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table11.addCell(cell);
		cell.setPhrase(new Phrase("Joint No", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table11.addCell(cell);
		cell.setPhrase(new Phrase("Joint resistance (Ω)", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table11.addCell(cell);

	}

	private void TableData(PdfPTable table8, List<InstalLocationReport> instalLocationReport) {
		for (InstalLocationReport arr : instalLocationReport) {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getLocationNo()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(arr.getLocationName()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(arr.getElectrodeResistanceEarth()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(arr.getElectrodeResistanceGird()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
		}
	}

	private void TableHeader(PdfPTable table8) {
		PdfPCell cell = new PdfPCell();
//	        cell.setBorderColor(Color.BLUE);
		cell.setPadding(4);
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		// font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("Location No", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.addCell(cell);
		cell.setPhrase(new Phrase("Location Name", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.addCell(cell);
		cell.setPhrase(new Phrase("Electrode resistance to earth in (Ω)", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.addCell(cell);
		cell.setPhrase(new Phrase("Electrode resistance to grid (Ω) ", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.addCell(cell);
	}

	private void addHeaderRow(PdfPTable table8, String string, String string2, String string3, String string4) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4));
		table8.addCell(nameCell);
		table8.addCell(valueCell1);
		table8.addCell(valueCell2);
		table8.addCell(valueCell3);

	}

	private void addRow(PdfPTable table11, String string, String string2, String string3) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));
		table11.addCell(nameCell);
		table11.addCell(valueCell1);
		table11.addCell(valueCell2);
	}

	private void addRow(PdfPTable table8, String string, String string2, String string3, String string4) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4));
		table8.addCell(nameCell);
		table8.addCell(valueCell1);
		table8.addCell(valueCell2);
		table8.addCell(valueCell3);
	}

	private void addRow(PdfPTable table1, String string, String string2, String string3, String string4, String string5,
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
		table1.addCell(nameCell);
		table1.addCell(valueCell1);
		table1.addCell(valueCell2);
		table1.addCell(valueCell3);
		table1.addCell(valueCell4);
		table1.addCell(valueCell5);
		table1.addCell(valueCell6);
		table1.addCell(valueCell7);
		table1.addCell(valueCell8);
		table1.addCell(valueCell9);

	}
}
