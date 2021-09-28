package com.capeelectric.service.impl;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.swing.text.StyleConstants.FontConstants;

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
import com.capeelectric.util.HeaderFooterPageEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintSupplyServiceImpl implements PrintSupplyService {
	@Autowired
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	@Override
	public void printSupply(String userName, Integer siteId) throws SupplyCharacteristicsException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SupplyCharacteristic.pdf"));
				List<SupplyCharacteristics> supply1 = supplyCharacteristicsRepository.findByUserNameAndSiteId(userName,
						siteId);
				SupplyCharacteristics supply = supply1.get(0);

				List<SupplyParameters> supplyParameters1 = supply.getSupplyParameters();
				SupplyParameters supplyParameters = supplyParameters1.get(0);

				List<CircuitBreaker> circuitBreaker = supply.getCircuitBreaker();
				List<InstalLocationReport> instalLocationReport = supply.getInstalLocationReport();

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

				Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
				Font font1 = new Font(BaseFont.createFont(), 11, Font.NORMAL, BaseColor.BLACK);
                Font font4 = new Font(BaseFont.createFont(), 11, Font.NORMAL, BaseColor.BLACK);
                Font font5 = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font6 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
				Font font9 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
				Font font10 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraphOne = new Paragraph("TIC of LV electrical installation ", font);
				paragraphOne.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraphOne);
				PdfPTable table14 = new PdfPTable(1);
				table14.setWidthPercentage(100); // Width 100%
				table14.setSpacingBefore(10f); // Space before table
				table14.setWidthPercentage(100);
				table14.getDefaultCell().setBorder(0);
				PdfPCell cell45 = new PdfPCell(
						new Paragraph(30, "Part - 2: Supply characteristics and earthing arrangement ", font5));
				cell45.setBorder(PdfPCell.NO_BORDER);
				cell45.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table14.addCell(cell45);
				document.add(table14);

				PdfPTable table16 = new PdfPTable(1);
				table16.setWidthPercentage(100); // Width 100%
				table16.setSpacingBefore(10f); // Space before table
				table16.setWidthPercentage(100);
				table16.getDefaultCell().setBorder(0);
				PdfPCell cell8 = new PdfPCell(new Paragraph(30, "Section - 1:Mains incoming", font5));
				cell8.setBorder(PdfPCell.NO_BORDER);
				cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table16.addCell(cell8);
				document.add(table16);

				float[] pointColumnWidths = { 90F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidths);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setWidthPercentage(100);
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph(supply.getMainSystemEarthing(), font6));
				table.addCell(new Phrase("System earthing:", font9));
				cell.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell);

				PdfPCell cell29 = new PdfPCell(new Paragraph("Brief note (in case of confusion):", font9));
				cell29.setBorder(PdfPCell.NO_BORDER);
				cell29.setGrayFill(0.92f);
				table.addCell(cell29);
				PdfPCell cell38 = new PdfPCell(new Paragraph(supply.getSystemEarthingBNote(), font6));
				cell38.setGrayFill(0.92f);
				cell38.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell38);

				PdfPCell cell2 = new PdfPCell(new Paragraph(supply.getLiveConductorType(), font6));
				table.addCell(new Phrase(8, "Number and type of live conductors:", font9));
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);

				PdfPCell cell5 = new PdfPCell(new Paragraph("Brief note:", font9));
				cell5.setBorder(PdfPCell.NO_BORDER);
				cell5.setGrayFill(0.92f);
				table.addCell(cell5);
				PdfPCell cell39 = new PdfPCell(new Paragraph(supply.getLiveConductorBNote(), font6));
				cell39.setGrayFill(0.92f);
				cell39.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell39);
				document.add(table);

				if (supply.getLiveConductorType().equals("AC")) {
					float[] pointColumnWidths1 = { 90F, 90F };
					PdfPTable table15 = new PdfPTable(pointColumnWidths1);
					table15.setWidthPercentage(100); // Width 100%
					table15.setWidthPercentage(100);
					table15.getDefaultCell().setBorder(0);

					PdfPCell cell3 = new PdfPCell(new Paragraph(supply.getLiveConductorAC(), font6));
					table15.addCell(new Phrase("AC :", font6));
					cell3.setBorder(PdfPCell.NO_BORDER);
					table15.addCell(cell3);
					document.add(table15);

					PdfPTable table1 = new PdfPTable(10);
					table1.setWidthPercentage(100); // Width 100%
					table1.setSpacingAfter(10f); // Space after table
					table1.setWidthPercentage(100);
					table1.getDefaultCell().setBorder(0);

					PdfPTable table34 = new PdfPTable(10);
					table34.setSpacingBefore(10f); // Space before table
					table34.setWidthPercentage(100); // Width 100%
					table34.getDefaultCell().setBorder(0);
					tableHead(table34);
					addRow(table1, "Nominal voltage U/U0 (V)", N1, N2, N3, N4, N5, N6, N7, N8, N9);
					addRow(table1, "Nominal Frequency f (HZ)", NF1, NF2, NF3, NF4, NF5, NF6, NF7, NF8, NF9);
					addRow(table1, "Prospective fault current Ipfc (kA) ", NFC1, NFC2, NFC3, NFC4, NFC5, NFC6, NFC7,
							NFC8, NF9);
					addRow(table1, "External Loop Impedance Ze (Ω)", NFL1, NFL2, NFL3, NFL4, NFL5, NFL6, NFL7, NFL8,
							NFL9);
					document.add(table34);
					document.add(table1);
				} else {

					float[] pointColumnWidths1 = { 90F, 90F };
					PdfPTable table15 = new PdfPTable(pointColumnWidths1);
					table15.setWidthPercentage(100); // Width 100%
					table15.setSpacingBefore(5f); // Space before table
					table15.setSpacingAfter(5f); // Space after table
					table15.setWidthPercentage(100);
					table15.getDefaultCell().setBorder(0);
					PdfPCell cell4 = new PdfPCell(new Paragraph(supply.getLiveConductorDC(), font6));
					table15.addCell(new Phrase("DC :", font9));
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

				PdfPTable table17 = new PdfPTable(1);
				table17.setWidthPercentage(100); // Width 100%
				table17.setSpacingBefore(10f); // Space before table
				table17.setWidthPercentage(100);
				table17.getDefaultCell().setBorder(0);
				PdfPCell cell46 = new PdfPCell(
						new Paragraph("Incoming (supply) protective device characteristics	", font10));
				cell46.setBorder(PdfPCell.NO_BORDER);
				cell46.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table17.addCell(cell46);
				document.add(table17);

				PdfPCell cell6 = new PdfPCell(new Paragraph(supply.getMainNominalProtectiveDevice(), font6));
				table2.addCell(new Phrase("Type of Over Current Protective Device:", font9));
				cell6.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(new Paragraph("Rated current (A):", font9));
				cell7.setBorder(PdfPCell.NO_BORDER);
				cell7.setGrayFill(0.92f);
				table2.addCell(cell7);
				PdfPCell cell47 = new PdfPCell(new Paragraph(supply.getMainRatedCurrent(), font6));
				cell47.setGrayFill(0.92f);
				cell47.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell47);

				PdfPCell cell88 = new PdfPCell(new Paragraph(supply.getMainRatedCurrent(), font6));
				table2.addCell(new Phrase("Current for disconnection with in 0.2 seconds:", font9));
				cell88.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell88);
				document.add(table2);
				document.newPage();

				PdfPTable table21 = new PdfPTable(1);
				table21.setWidthPercentage(100); // Width 100%
				table21.setSpacingBefore(10f); // Space before table
				table21.setWidthPercentage(100);
				table21.getDefaultCell().setBorder(0);
				PdfPCell cell53 = new PdfPCell(new Paragraph("Section - 2:Alternative source of supply", font5));
				cell53.setBorder(PdfPCell.NO_BORDER);
				cell53.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table21.addCell(cell53);
				document.add(table21);

				PdfPTable table15 = new PdfPTable(pointColumnWidths);
				table15.setWidthPercentage(100); // Width 100%
				table15.setWidthPercentage(100);
				table15.getDefaultCell().setBorder(0);

				PdfPCell cell9 = new PdfPCell(new Paragraph(supply.getAlternativeSupply(), font6));
				table15.addCell(new Phrase(" Availability of alternate supply:", font9));
				cell9.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(new Paragraph(8, " Number of alternate sources of supply:", font9));
				cell10.setBorder(PdfPCell.NO_BORDER);
				cell10.setGrayFill(0.92f);
				table15.addCell(cell10);
				PdfPCell cell68 = new PdfPCell(new Paragraph(supply.getAlternativeSupply(), font6));
				cell68.setGrayFill(0.92f);
				cell68.setBorder(PdfPCell.NO_BORDER);
				table15.addCell(cell68);
				document.add(table);

				document.add(table15);

				for (SupplyParameters arr : supplyParameters1) {
					altenateSupply(document, arr);
				}

				document.newPage();

				PdfPTable table18 = new PdfPTable(1);
				table18.setWidthPercentage(100); // Width 100%
				table18.setSpacingBefore(10f); // Space before table
				table18.setWidthPercentage(100);
				table18.getDefaultCell().setBorder(0);
				PdfPCell cell50 = new PdfPCell(
						new Paragraph("Section - 3:Particulars of installation referred in the report", font10));
				cell50.setBorder(PdfPCell.NO_BORDER);
				cell50.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table18.addCell(cell50);
				document.add(table18);

				PdfPTable table7 = new PdfPTable(pointColumnWidths);
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
				table7.setSpacingAfter(10f); // Space after table
				table7.setWidthPercentage(100);
				table7.getDefaultCell().setBorder(0);

				PdfPCell cell22 = new PdfPCell(new Paragraph(supply.getMaximumDemand(), font6));
				table7.addCell(new Phrase("Maximum demand kVA:", font9));
				cell22.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell22);

				PdfPCell cell23 = new PdfPCell(new Paragraph("Maximum load:", font9));
				cell23.setBorder(PdfPCell.NO_BORDER);
				cell23.setGrayFill(0.92f);
				table7.addCell(cell23);
				PdfPCell cell56 = new PdfPCell(new Paragraph(supply.getMaximumLoad(), font6));
				cell56.setGrayFill(0.92f);
				cell56.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell56);

				PdfPCell cell24 = new PdfPCell(new Paragraph(supply.getMeansEarthing(), font6));
				table7.addCell(new Phrase("Means of earthing :", font9));
				cell24.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell24);

				PdfPCell cell25 = new PdfPCell(new Paragraph("Type of earth electrode :", font9));
				cell25.setBorder(PdfPCell.NO_BORDER);
				cell25.setGrayFill(0.92f);
				table7.addCell(cell25);
				PdfPCell cell57 = new PdfPCell(new Paragraph(supply.getElectrodeType(), font6));
				cell57.setGrayFill(0.92f);
				cell57.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell57);

				PdfPCell cell26 = new PdfPCell(new Paragraph(supply.getElectrodeMaterial(), font6));
				table7.addCell(new Phrase("Material of earth electrode :", font9));
				cell26.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell26);

				PdfPCell cell27 = new PdfPCell(new Paragraph("No of Locations :", font9));
				cell27.setBorder(PdfPCell.NO_BORDER);
				cell27.setGrayFill(0.92f);
				table7.addCell(cell27);
				PdfPCell cell58 = new PdfPCell(new Paragraph(supply.getNoOfLocation().toString(), font6));
				cell58.setGrayFill(0.92f);
				cell58.setBorder(PdfPCell.NO_BORDER);
				table7.addCell(cell58);

				document.add(table7);

				PdfPTable table8 = new PdfPTable(4);
				table8.setWidthPercentage(100); // Width 100%
				table8.setSpacingBefore(10f); // Space before table
				table8.setSpacingAfter(10f); // Space after table
				table8.setWidthPercentage(100);
				table8.getDefaultCell().setBorder(0);

				tableHeader(table8);
				tableData(table8, instalLocationReport);

				document.add(table8);

				document.newPage();

				PdfPTable table19 = new PdfPTable(1);
				table19.setWidthPercentage(100); // Width 100%
				table19.setSpacingBefore(10f); // Space before table
				table19.setWidthPercentage(100);
				table19.getDefaultCell().setBorder(0);
				PdfPCell cell51 = new PdfPCell(
						new Paragraph("Section - 4:Details of main protective conductor ", font10));
				cell51.setBorder(PdfPCell.NO_BORDER);
				cell51.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table19.addCell(cell51);
				document.add(table19);

				PdfPTable table10 = new PdfPTable(pointColumnWidths);
				table10.setWidthPercentage(100); // Width 100%
				table10.setSpacingBefore(10f); // Space before table
				table10.setSpacingAfter(10f); // Space after table
				table10.setWidthPercentage(100);
				table10.getDefaultCell().setBorder(0);

				PdfPCell cell30 = new PdfPCell(new Paragraph(supply.getConductorSize(), font6));
				table10.addCell(new Phrase("Size of earthing conductor :", font9));
				cell30.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell30);

				PdfPCell cell31 = new PdfPCell(new Paragraph("Material of earthing conductor :", font9));
				cell31.setBorder(PdfPCell.NO_BORDER);
				cell31.setGrayFill(0.92f);
				table10.addCell(cell31);
				PdfPCell cell59 = new PdfPCell(new Paragraph(supply.getConductormaterial(), font6));
				cell59.setGrayFill(0.92f);
				cell59.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell59);

				PdfPCell cell32 = new PdfPCell(new Paragraph(supply.getConductorVerify(), font6));
				table10.addCell(new Phrase("Earthing conductor continuity and connection verified :", font9));
				cell32.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell32);

				PdfPCell cell33 = new PdfPCell(new Paragraph("Size of main protective bonding conductor :", font9));
				cell33.setBorder(PdfPCell.NO_BORDER);
				cell33.setGrayFill(0.92f);
				table10.addCell(cell33);
				PdfPCell cell60 = new PdfPCell(new Paragraph(supply.getMaximumDemand(), font6));
				cell60.setGrayFill(0.92f);
				cell60.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell60);

				PdfPCell cell34 = new PdfPCell(new Paragraph(supply.getBondingConductorMaterial(), font6));
				table10.addCell(new Phrase("Material of main protective bonding conductor :", font9));
				cell34.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell34);

				PdfPCell cell35 = new PdfPCell(
						new Paragraph("Main protective bonding conductor continuity and connection verified :", font9));
				cell35.setBorder(PdfPCell.NO_BORDER);
				cell35.setGrayFill(0.92f);
				table10.addCell(cell35);
				PdfPCell cell61 = new PdfPCell(new Paragraph(supply.getBondingConductorVerify(), font6));
				cell61.setGrayFill(0.92f);
				cell61.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell61);

				PdfPCell cell36 = new PdfPCell(new Paragraph(supply.getBondingJointsType(), font6));
				table10.addCell(new Phrase("Type of joints (impairing safety) :", font9));
				cell36.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell36);

				PdfPCell cell37 = new PdfPCell(new Paragraph("No of joints :", font9));
				cell37.setBorder(PdfPCell.NO_BORDER);
				cell37.setGrayFill(0.92f);
				table10.addCell(cell37);
				PdfPCell cell62 = new PdfPCell(new Paragraph(supply.getBondingNoOfJoints().toString(), font6));
				cell62.setGrayFill(0.92f);
				cell62.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell62);
				document.add(table10);

				PdfPTable table11 = new PdfPTable(3);
				table11.setWidthPercentage(100); // Width 100%
				table11.setSpacingBefore(10f); // Space before table
				table11.setSpacingAfter(10f); // Space after table

				tableHeader1(table11);
				tableData1(table11, boundingLocationReport);

				document.add(table11);

				PdfPTable table12 = new PdfPTable(pointColumnWidths);
				table12.setWidthPercentage(100); // Width 100%
				table12.setSpacingBefore(10f); // Space before table
				table12.setSpacingAfter(10f); // Space after table
				table12.setWidthPercentage(100);
				table12.getDefaultCell().setBorder(0);

				PdfPCell cell40 = new PdfPCell(new Paragraph(supply.getEarthingConductorSize(), font6));
				table12.addCell(new Phrase("Size of main protective earthing conductor:", font9));
				cell40.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell40);

				PdfPCell cell41 = new PdfPCell(new Paragraph("Material of main protective earthing conductor:", font9));
				cell41.setBorder(PdfPCell.NO_BORDER);
				cell41.setGrayFill(0.92f);
				table12.addCell(cell41);
				PdfPCell cell63 = new PdfPCell(new Paragraph(supply.getEarthingConductorMaterial(), font6));
				cell63.setGrayFill(0.92f);
				cell63.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell63);

				PdfPCell cell42 = new PdfPCell(new Paragraph(supply.getEarthingConductorVerify(), font6));
				table12.addCell(new Phrase("Main Protective Conductor continuity and connection verified:", font9));
				cell42.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell42);

				PdfPCell cell43 = new PdfPCell(new Paragraph("Type of Joints:", font9));
				cell43.setBorder(PdfPCell.NO_BORDER);
				cell43.setGrayFill(0.92f);
				table12.addCell(cell43);
				PdfPCell cell64 = new PdfPCell(new Paragraph(supply.getEarthingJointsType(), font9));
				cell64.setGrayFill(0.92f);
				cell64.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell64);

				PdfPCell cell44 = new PdfPCell(new Paragraph(supply.getEarthingNoOfJoints().toString(), font6));
				table12.addCell(new Phrase("No of Joints:", font6));
				cell44.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell44);
				document.add(table12);

				PdfPTable table13 = new PdfPTable(3);
				table13.setWidthPercentage(100); // Width 100%
				table13.setSpacingBefore(10f); // Space before table
				table13.setSpacingAfter(10f); // Space after table

				tableHeader1(table13);
				TableData2(table13, earthingLocationReport);
				document.add(table13);
				document.newPage();

				PdfPTable table20 = new PdfPTable(1);
				table20.setWidthPercentage(100); // Width 100%
				table20.setSpacingBefore(10f); // Space before table
				table20.setWidthPercentage(100);
				table20.getDefaultCell().setBorder(0);

				PdfPCell cell49 = new PdfPCell(
						new Paragraph("Section - 5: Details of main switch or circuit breaker", font5));
				cell49.setBorder(PdfPCell.NO_BORDER);
				cell49.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table20.addCell(cell49);
				document.add(table20);
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

	private void altenateSupply(Document document, SupplyParameters supplyParameters)
			throws DocumentException, IOException {

		String parameternominal = supplyParameters.getNominalVoltage();
		String parameternominal_list[] = parameternominal.split(",");
		String PN1 = parameternominal_list[0];
		String PN2 = parameternominal_list[1];
		String PN3 = parameternominal_list[2];
		String PN4 = parameternominal_list[3];
		String PN5 = parameternominal_list[4];
		String PN6 = parameternominal_list[5];
		String PN7 = parameternominal_list[6];
		String PN8 = parameternominal_list[7];
		String PN9 = parameternominal_list[8];

		String parameterFrequency = supplyParameters.getNominalFrequency();

		String parameterFrequency_list[] = parameterFrequency.split(",");
		String PFN1 = parameterFrequency_list[0];
		String PFN2 = parameterFrequency_list[1];
		String PFN3 = parameterFrequency_list[2];
		String PFN4 = parameterFrequency_list[3];
		String PFN5 = parameterFrequency_list[4];
		String PFN6 = parameterFrequency_list[5];
		String PFN7 = parameterFrequency_list[6];
		String PFN8 = parameterFrequency_list[7];
		String PFN9 = parameterFrequency_list[8];

		String parameterCurrent = supplyParameters.getFaultCurrent();
		String parameterCurrent_list[] = parameterCurrent.split(",");
		String PC1 = parameterCurrent_list[0];
		String PC2 = parameterCurrent_list[1];
		String PC3 = parameterCurrent_list[2];
		String PC4 = parameterCurrent_list[3];
		String PC5 = parameterCurrent_list[4];
		String PC6 = parameterCurrent_list[5];
		String PC7 = parameterCurrent_list[6];
		String PC8 = parameterCurrent_list[7];
		String PC9 = parameterCurrent_list[8];

		String parameterImpedance = supplyParameters.getLoopImpedance();
		String parameterImpedance_list[] = parameterImpedance.split(",");
		String PI1 = parameterImpedance_list[0];
		String PI2 = parameterImpedance_list[1];
		String PI3 = parameterImpedance_list[2];
		String PI4 = parameterImpedance_list[3];
		String PI5 = parameterImpedance_list[4];
		String PI6 = parameterImpedance_list[5];
		String PI7 = parameterImpedance_list[6];
		String PI8 = parameterImpedance_list[7];
		String PI9 = parameterImpedance_list[8];

		float[] pointColumnWidths = { 90F, 90F };
		PdfPTable table3 = new PdfPTable(pointColumnWidths);
		table3.setWidthPercentage(100); // Width 100%
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setBorder(0);
		Font font6 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell11 = new PdfPCell(new Paragraph(supplyParameters.getaLSupplyNo(), font6));
		table3.addCell(new Phrase(" Alternate supply no :", font6));
		cell11.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell11);

		PdfPCell cell12 = new PdfPCell(new Paragraph(8, " Short name of alternate supply:", font6));
		cell12.setBorder(PdfPCell.NO_BORDER);
		cell12.setGrayFill(0.92f);
		table3.addCell(cell12);
		PdfPCell cell69 = new PdfPCell(new Paragraph(supplyParameters.getaLSupplyShortName(), font6));
		cell69.setGrayFill(0.92f);
		cell69.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell69);

		document.add(table3);
		PdfPTable table4 = new PdfPTable(pointColumnWidths);
		table4.setWidthPercentage(100); // Width 100%
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setBorder(0);
		PdfPCell cell13 = new PdfPCell(new Paragraph(supplyParameters.getaLSystemEarthing(), font6));
		table4.addCell(new Phrase("System earthing:", font6));
		cell13.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell13);

		PdfPCell cell15 = new PdfPCell(new Paragraph(8, "Number and type of live conductors:", font6));
		cell15.setBorder(PdfPCell.NO_BORDER);
		cell15.setGrayFill(0.92f);
		table4.addCell(cell15);
		PdfPCell cell70 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorType(), font6));
		cell70.setGrayFill(0.92f);
		cell70.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell70);
//     	  String file= "D:\\Rushlogo.PNG";
//          Image image = Image.getInstance(file);
//          image.scaleToFit(20, 20);
//        document.add(image);

		PdfPCell cell18 = new PdfPCell(new Paragraph(supplyParameters.getaLSystemEarthingBNote(), font6));
		table4.addCell(new Phrase("Brief note:", font6));
		cell18.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell18);
		document.add(table4);

		if (supplyParameters.getaLLiveConductorType().equals("AC")) {
			float[] pointColumnWidths1 = { 90F, 90F };
			PdfPTable table16 = new PdfPTable(pointColumnWidths1);
			table16.setWidthPercentage(100); // Width 100%
			table16.setWidthPercentage(100);
			table16.getDefaultCell().setBorder(0);

			PdfPCell cell16 = new PdfPCell(new Paragraph("AC :", font6));
			cell16.setBorder(PdfPCell.NO_BORDER);
			cell16.setGrayFill(0.92f);
			table16.addCell(cell16);
			PdfPCell cell71 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorAC(), font6));
			cell71.setGrayFill(0.92f);
			cell71.setBorder(PdfPCell.NO_BORDER);
			table16.addCell(cell71);

			document.add(table16);
			PdfPTable table5 = new PdfPTable(10);
			table5.setWidthPercentage(100); // Width 100%
			table5.setWidthPercentage(100);
			table5.getDefaultCell().setBorder(0);

			PdfPTable table34 = new PdfPTable(10);
			table34.setSpacingBefore(10f); // Space before table
			table34.setWidthPercentage(100); // Width 100%
			table34.getDefaultCell().setBorder(0);
			tableHead(table34);

			addRow(table5, "Nominal voltage U/U0 (V)", PN1, PN2, PN3, PN4, PN5, PN6, PN7, PN8, PN9);
			addRow(table5, "Nominal Frequency f (HZ)", PFN1, PFN2, PFN3, PFN4, PFN5, PFN6, PFN7, PFN8, PFN9);
			addRow(table5, "Prospective fault current Ipfc (kA) ", PC1, PC2, PC3, PC4, PC5, PC6, PC7, PC8, PC9);
			addRow(table5, "External Loop Impedance Ze (Ω)", PI1, PI2, PI3, PI4, PI5, PI6, PI7, PI8, PI9);
			document.add(table34);
			document.add(table5);
			PdfPTable table9 = new PdfPTable(pointColumnWidths);
			table9.setWidthPercentage(100); // Width 100%
			table9.setSpacingBefore(10f); // Space before table
			table9.setSpacingAfter(10f); // Space after table
			table9.setWidthPercentage(100);
			table9.getDefaultCell().setBorder(0);

			PdfPCell cell50 = new PdfPCell(new Paragraph("InstalledCapacity :", font6));
			cell50.setBorder(PdfPCell.NO_BORDER);
			cell50.setGrayFill(0.92f);
			table9.addCell(cell50);
			PdfPCell cell72 = new PdfPCell(new Paragraph(supplyParameters.getInstalledCapacity(), font6));
			cell72.setGrayFill(0.92f);
			cell72.setBorder(PdfPCell.NO_BORDER);
			table9.addCell(cell72);

			PdfPCell cell51 = new PdfPCell(new Paragraph(supplyParameters.getActualLoad(), font6));
			table9.addCell(new Phrase("Actual Load Current (R,Y,B, N) :", font6));
			cell51.setBorder(PdfPCell.NO_BORDER);
			table9.addCell(cell51);
			document.add(table9);
		} else {
			PdfPTable table16 = new PdfPTable(pointColumnWidths);
			table16.setWidthPercentage(100); // Width 100%
			table16.setWidthPercentage(100);
			table16.getDefaultCell().setBorder(0);
			PdfPCell cell17 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorDC(), font6));
			table16.addCell(new Phrase("DC :", font6));
			cell17.setBorder(PdfPCell.NO_BORDER);
			table16.addCell(cell17);
			document.add(table16);

		}

		PdfPTable table6 = new PdfPTable(pointColumnWidths);
		table6.setWidthPercentage(100); // Width 100%
		table6.setSpacingBefore(10f); // Space before table
		table6.setWidthPercentage(100);
		table6.getDefaultCell().setBorder(0);

		PdfPTable table20 = new PdfPTable(1);
		table20.setWidthPercentage(100); // Width 100%
		table20.setSpacingBefore(10f); // Space before table
		table20.setWidthPercentage(100);
		table20.getDefaultCell().setBorder(0);
		PdfPCell cell51 = new PdfPCell(new Paragraph("Incoming (supply) protective device characteristics",
				new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		cell51.setBorder(PdfPCell.NO_BORDER);
		cell51.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table20.addCell(cell51);
		document.add(table20);

		PdfPCell cell19 = new PdfPCell(new Paragraph(supplyParameters.getProtectiveDevice(), font6));
		table6.addCell(new Phrase("Type of Over Current Protective Device:", font6));
		cell19.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell19);

		PdfPCell cell50 = new PdfPCell(new Paragraph("Rated current (A):", font6));
		cell50.setBorder(PdfPCell.NO_BORDER);
		cell50.setGrayFill(0.92f);
		table6.addCell(cell50);
		PdfPCell cell72 = new PdfPCell(new Paragraph(supplyParameters.getRatedCurrent(), font6));
		cell72.setGrayFill(0.92f);
		cell72.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell72);

		PdfPCell cell21 = new PdfPCell(new Paragraph(supplyParameters.getCurrentDissconnection(), font6));
		table6.addCell(new Phrase("Current for disconnection with in 0.2 seconds:", font6));
		cell21.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell21);

		PdfPCell cell55 = new PdfPCell(new Paragraph("Brief note (in case of confusion):", font6));
		cell55.setBorder(PdfPCell.NO_BORDER);
		cell55.setGrayFill(0.92f);
		table6.addCell(cell55);
		PdfPCell cell73 = new PdfPCell(new Paragraph(supplyParameters.getaLLiveConductorBNote(), font6));
		cell73.setGrayFill(0.92f);
		cell73.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell73);
		document.add(table6);
		document.newPage();
	}

	private void tableHead(PdfPTable table34) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(10);
		Font font1 = new Font(BaseFont.createFont(), 8, Font.NORMAL, BaseColor.BLACK);
		Font font = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);
		cell.setPhrase(new Phrase("Nature of supply parameters", font1));
		cell.setGrayFill(0.92f);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("R-Y", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("R-B", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("Y-B", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("R-N", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("Y-N", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("B-N", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("R-PE", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("Y-PE", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);
		cell.setPhrase(new Phrase("B-PE", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table34.addCell(cell);

	}

	private void circuteBraker(Document document, CircuitBreaker circute1) throws DocumentException, IOException {
		float[] pointColumnWidths = { 90F, 90F };
		PdfPTable table9 = new PdfPTable(pointColumnWidths);
		table9.setWidthPercentage(100); // Width 100%
		table9.setSpacingBefore(10f); // Space before table
		table9.setSpacingAfter(10f); // Space after table
		table9.setWidthPercentage(100);
		table9.getDefaultCell().setBorder(0);
		Font font6 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell29 = new PdfPCell(new Paragraph(circute1.getLocation(), font6));
		table9.addCell(new Phrase("Location:", font6));
		cell29.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell29);
		PdfPCell cell45 = new PdfPCell(new Paragraph(circute1.getType(), font6));
		table9.addCell(new Phrase("Type:", font6));
		cell45.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell45);
		PdfPCell cell47 = new PdfPCell(new Paragraph(circute1.getNoPoles(), font6));
		table9.addCell(new Phrase("No of poles:", font6));
		cell47.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell47);
		PdfPCell cell55 = new PdfPCell(new Paragraph(circute1.getCurrent(), font6));
		table9.addCell(new Phrase("Current Rating:", font6));
		cell55.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell55);
		PdfPCell cell50 = new PdfPCell(new Paragraph(circute1.getVoltage(), font6));
		table9.addCell(new Phrase("Voltage Rating:", font6));
		cell50.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell50);
		PdfPCell cell51 = new PdfPCell(new Paragraph(circute1.getFuse(), font6));
		table9.addCell(new Phrase("Fuse Rating or Settings:", font6));
		cell51.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell51);
		PdfPCell cell38 = new PdfPCell(new Paragraph(circute1.getResidualCurrent(), font6));
		table9.addCell(new Phrase("Rated residual operating Current I∆n:", font6));
		cell38.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell38);
		PdfPCell cell39 = new PdfPCell(new Paragraph(circute1.getResidualTime(), font6));
		table9.addCell(new Phrase("Rated residual operating time @I∆n     T∆n:", font6));

		cell39.setBorder(PdfPCell.NO_BORDER);
		table9.addCell(cell39);
		document.add(table9);
	}

	private void TableData2(PdfPTable table13, List<EarthingLocationReport> earthingLocationReport) {
		for (EarthingLocationReport arr : earthingLocationReport) {
			PdfPCell cell = new PdfPCell();
			Font font6 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC, BaseColor.BLACK);
			cell.setPhrase(new Phrase(arr.getLocation(), font6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table13.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointNo(), font6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table13.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointResistance(), font6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table13.addCell(cell);
		}

	}

	private void tableData1(PdfPTable table11, List<BoundingLocationReport> boundingLocationReport) {
		for (BoundingLocationReport arr : boundingLocationReport) {
			PdfPCell cell = new PdfPCell();
			Font font6 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC, BaseColor.BLACK);
			cell.setPhrase(new Phrase(arr.getLocation(), font6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table11.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointNo(), font6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table11.addCell(cell);
			cell.setPhrase(new Phrase(arr.getJointResistance(), font6));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table11.addCell(cell);
		}
	}

	private void tableHeader1(PdfPTable table11) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(4);

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		cell.setPhrase(new Phrase("Location", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setGrayFill(0.92f);
		table11.addCell(cell);
		cell.setPhrase(new Phrase("Joint No", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setGrayFill(0.92f);
		table11.addCell(cell);
		cell.setPhrase(new Phrase("Joint resistance (Ω)", font1));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table11.addCell(cell);

	}

	private void tableData(PdfPTable table8, List<InstalLocationReport> instalLocationReport) {
		for (InstalLocationReport arr : instalLocationReport) {
			PdfPCell cell = new PdfPCell();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC, BaseColor.BLACK);
			cell.setPhrase(new Phrase(arr.getLocationNo(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(arr.getLocationName(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(arr.getElectrodeResistanceEarth(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
			cell.setPhrase(new Phrase(arr.getElectrodeResistanceGird(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table8.addCell(cell);
		}
	}

	private void tableHeader(PdfPTable table8) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(4);
		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		cell.setPhrase(new Phrase("Location No", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.addCell(cell);
		cell.setPhrase(new Phrase("Location Name", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.addCell(cell);
		cell.setPhrase(new Phrase("Electrode resistance to earth in (Ω)", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.addCell(cell);
		cell.setPhrase(new Phrase("Electrode resistance to grid (Ω) ", font));
		cell.setGrayFill(0.92f);
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
			String string6, String string7, String string8, String string9, String string10)
			throws DocumentException, IOException {

		PdfPCell nameCell = new PdfPCell(
				new Paragraph(string, new Font(BaseFont.createFont(), 8, Font.NORMAL, BaseColor.BLACK)));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4));
		PdfPCell valueCell4 = new PdfPCell(new Paragraph(string5));
		PdfPCell valueCell5 = new PdfPCell(new Paragraph(string6));
		PdfPCell valueCell6 = new PdfPCell(new Paragraph(string7));
		PdfPCell valueCell7 = new PdfPCell(new Paragraph(string8));
		PdfPCell valueCell8 = new PdfPCell(new Paragraph(string9));
		PdfPCell valueCell9 = new PdfPCell(new Paragraph(string10));
		nameCell.setGrayFill(0.92f);
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell9.setHorizontalAlignment(Element.ALIGN_CENTER);
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