package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ObservationException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.AllComponentObservation;
import com.capeelectric.model.AlternativeInnerObservation;
import com.capeelectric.model.InspectionInnerObservations;
import com.capeelectric.model.InspectionOuterObservation;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryComment;
import com.capeelectric.model.SummaryDeclaration;
import com.capeelectric.model.SupplyOuterObservation;
import com.capeelectric.model.TestingInnerObservation;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.service.ObservationService;
import com.capeelectric.service.PrintService;
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
public class PrintServiceImpl implements PrintService {

	private static final Logger logger = LoggerFactory.getLogger(PrintServiceImpl.class);
	
	@Autowired
	private SummaryRepository summaryRepository;

	@Autowired
	private ObservationService observationService;

	@Override
	public void printSummary(String userName, Integer siteId) throws SummaryException, ObservationException {

		logger.debug("called printSummary function userName: {},siteId : {}", userName,siteId);
		
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Summary.pdf"));

				List<Summary> summary = summaryRepository.findByUserNameAndSiteId(userName, siteId);
				Summary summary11 = summary.get(0);

				List<SummaryDeclaration> declaration1 = summary11.getSummaryDeclaration();
				SummaryDeclaration declaration = declaration1.get(0);
				SummaryDeclaration declaration11 = declaration1.get(1);

				List<SummaryComment> ReportComments = summary11.getSummaryComment();
				SummaryComment comments = ReportComments.get(0);

				document.open();
				Font font = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraphOne = new Paragraph("TIC of LV electrical installation ", font);
				paragraphOne.setAlignment(Element.ALIGN_CENTER);

				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				PdfPTable table10 = new PdfPTable(1);
				table10.setWidthPercentage(100); // Width 100%
				table10.setSpacingBefore(10f); // Space before table
				table10.getDefaultCell().setBorder(0);

				PdfPCell cell9 = new PdfPCell(
						new Paragraph("Part - 5: Observations, Recommendations and Summary", font2));
				cell9.setBorder(PdfPCell.NO_BORDER);
				cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table10.addCell(cell9);

				PdfPTable table13 = new PdfPTable(1);
				table13.setWidthPercentage(100); // Width 100%
				table13.setSpacingBefore(10f); // Space before table
				table13.getDefaultCell().setBorder(0);

				PdfPCell cell8 = new PdfPCell(
						new Paragraph("Section - 1: Extent and limitations of inspection and testing :", font2));
				cell8.setBorder(PdfPCell.NO_BORDER);
				cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table13.addCell(cell8);

				Font font5 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				document.add(paragraphOne);
				document.add(table10);
				document.add(table13);

				float[] pointColumnWidths = { 90F, 90F };

				for (Summary summary1 : summary) {

					PdfPTable table4 = new PdfPTable(pointColumnWidths);
					table4.setSpacingBefore(5f); // Space before table
					table4.setWidthPercentage(100);
					table4.getDefaultCell().setBorder(0);

					Font font8 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);
					Font font9 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
					PdfPCell cell = new PdfPCell(new Paragraph(summary1.getExtentInstallation(),
							new Font(BaseFont.createFont(), 10, Font.NORMAL)));
					table4.addCell(new Phrase("Extent of installation covered by this Report:", font8));
					cell.setBorder(PdfPCell.NO_BORDER);
					table4.addCell(cell);

					PdfPCell cell1 = new PdfPCell(new Paragraph("Agreed limitations including the reasons:", font9));
					cell1.setBorder(PdfPCell.NO_BORDER);
					cell1.setGrayFill(0.92f);
					table4.addCell(cell1);
					PdfPCell cell22 = new PdfPCell(new Paragraph(summary1.getAgreedLimitations(), font9));
					cell22.setGrayFill(0.92f);
					cell22.setBorder(PdfPCell.NO_BORDER);
					table4.addCell(cell22);

					PdfPCell cell2 = new PdfPCell(new Paragraph(summary1.getAgreedWith(), font9));
					cell2.setBorder(PdfPCell.NO_BORDER);

					table4.addCell(new Phrase("Agreed with:", font8));
					table4.addCell(cell2);

					PdfPCell cell123 = new PdfPCell(
							new Paragraph("Operational limitations including the reasons:", font9));
					cell123.setBorder(PdfPCell.NO_BORDER);
					cell123.setGrayFill(0.92f);
					table4.addCell(cell123);
					PdfPCell cell24 = new PdfPCell(new Paragraph(summary1.getOperationalLimitations(), font9));
					cell24.setGrayFill(0.92f);
					cell24.setBorder(PdfPCell.NO_BORDER);
					table4.addCell(cell24);

					document.add(table4);

//					Paragraph paragraphOne5 = new Paragraph(
//							"The inspection and testing detailed in this report have been carried out in accordance with IEC60364. It should be note that cables concealed within trunk/trench and conduits, under floors which are generally within the fabric of the building or underground, have not been inspected unless it is specifically agreed between the client and inspector prior to the inspection :  "
//									+ summary1.getInspectionTestingDetailed(),
//							font8);
//					document.add(table4);
//					document.add(paragraphOne5);

					PdfPTable table15 = new PdfPTable(1);
					table15.setWidthPercentage(100); // Width 100%
					table15.setSpacingBefore(10f); // Space before table
					table15.getDefaultCell().setBorder(0);

					PdfPCell cell25 = new PdfPCell(new Paragraph(15, "Section - 2: Observations ", font5));
					cell25.setBorder(PdfPCell.NO_BORDER);
					cell25.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table15.addCell(cell25);
					document.add(table15);

					Paragraph paragraphOne6 = new Paragraph(
							"Referring to attached inspection report and test results and subject to the limitations specified at the extent and limitations of inspection and testing :  "
									+ summary1.getLimitationsInspection(),
							font8);
					document.add(paragraphOne6);

					if (summary1.getLimitationsInspection().equals("The following observations are made")) {

//						Inspection Observation Start from here

						PdfPTable obs1 = new PdfPTable(1);
						obs1.setWidthPercentage(100); // Width 100%
						obs1.setSpacingBefore(10f); // Space before table
						obs1.getDefaultCell().setBorder(0);

						PdfPCell ObservationCell1 = new PdfPCell(
								new Paragraph("Supply characteristics observations :", font5));
						ObservationCell1.setBorder(PdfPCell.NO_BORDER);
						ObservationCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
						obs1.addCell(ObservationCell1);
						document.add(obs1);

						PdfPTable Obsevation = new PdfPTable(pointColumnWidths);
						Obsevation.setWidthPercentage(100); // Width 100%
						Obsevation.setSpacingBefore(10f); // Space before table
						Obsevation.getDefaultCell().setBorder(0);

						PdfPTable InnAlterOBS = new PdfPTable(pointColumnWidths);
						InnAlterOBS.setWidthPercentage(100); // Width 100%
//						InnAlterOBS.setSpacingBefore(5f); // Space before table
						InnAlterOBS.getDefaultCell().setBorder(0);

						PdfPTable Obsevation2 = new PdfPTable(pointColumnWidths);
						Obsevation2.setWidthPercentage(100); // Width 100%
//						Obsevation2.setSpacingBefore(5f); // Space before table
						Obsevation2.getDefaultCell().setBorder(0);

						AllComponentObservation allComponentObservation = observationService
								.retrieveObservationsInSummary(userName, siteId);

						for (SupplyOuterObservation supplyObs : allComponentObservation.getSupplyOuterObservation()) {

							if (supplyObs.getObservationComponentDetails().equalsIgnoreCase("mains")) {

								PdfPCell MainsOBS = new PdfPCell(new Paragraph("Mains observation :", font9));
								MainsOBS.setBorder(PdfPCell.NO_BORDER);
								MainsOBS.setGrayFill(0.92f);
								Obsevation.addCell(MainsOBS);
								PdfPCell MainsOBS2 = new PdfPCell(
										new Paragraph(supplyObs.getObservationDescription(), font9));
								MainsOBS2.setGrayFill(0.92f);
								MainsOBS2.setBorder(PdfPCell.NO_BORDER);
								Obsevation.addCell(MainsOBS2);
							}

							if (supplyObs.getObservationComponentDetails().equalsIgnoreCase("alternate")) {

								for (AlternativeInnerObservation summaryInnerObservation : supplyObs
										.getAlternativeInnerObservation()) {

									PdfPCell alObs = new PdfPCell(new Paragraph("Alternate Observations", font9));
									alObs.setBorder(PdfPCell.NO_BORDER);
//									alObs.setGrayFill(0.92f);
									InnAlterOBS.addCell(alObs);
									PdfPCell alObs2 = new PdfPCell(
											new Paragraph(summaryInnerObservation.getObservationDescription(), font9));
//									alObs2.setGrayFill(0.92f);
									alObs2.setBorder(PdfPCell.NO_BORDER);
									InnAlterOBS.addCell(alObs2);
								}
							}

							if (supplyObs.getObservationComponentDetails().equalsIgnoreCase("earthingNoOfJointsOb")) {

								PdfPCell EarthConOBS = new PdfPCell(
										new Paragraph("Earthing Conductor Observation :", font9));
								EarthConOBS.setBorder(PdfPCell.NO_BORDER);
								EarthConOBS.setGrayFill(0.92f);
								Obsevation2.addCell(EarthConOBS);
								PdfPCell EarthConOBS2 = new PdfPCell(
										new Paragraph(supplyObs.getObservationDescription(), font9));
								EarthConOBS2.setGrayFill(0.92f);
								EarthConOBS2.setBorder(PdfPCell.NO_BORDER);
								Obsevation2.addCell(EarthConOBS2);
							}

							if (supplyObs.getObservationComponentDetails().equalsIgnoreCase("bondingNoOfJointsOb")) {

								PdfPCell BonOBS = new PdfPCell(new Paragraph("Bonding Conductor Observation :", font9));
								BonOBS.setBorder(PdfPCell.NO_BORDER);
//									BonOBS.setGrayFill(0.92f);
								Obsevation2.addCell(BonOBS);
								PdfPCell BonOBS2 = new PdfPCell(
										new Paragraph(supplyObs.getObservationDescription(), font9));
//									BonOBS2.setGrayFill(0.92f);
								BonOBS2.setBorder(PdfPCell.NO_BORDER);
								Obsevation2.addCell(BonOBS2);
							}

							if (supplyObs.getObservationComponentDetails().equals("instalLocationReportOb")) {

								PdfPCell EarthOBS = new PdfPCell(new Paragraph("Earth electrode observation :", font9));
								EarthOBS.setBorder(PdfPCell.NO_BORDER);
								EarthOBS.setGrayFill(0.92f);
								Obsevation2.addCell(EarthOBS);
								PdfPCell EarthOBS2 = new PdfPCell(
										new Paragraph(supplyObs.getObservationDescription(), font9));
								EarthOBS2.setGrayFill(0.92f);
								EarthOBS2.setBorder(PdfPCell.NO_BORDER);
								Obsevation2.addCell(EarthOBS2);
							}
						}

						document.add(Obsevation);
						document.add(InnAlterOBS);
						document.add(Obsevation2);

//						Inspection Observation Start from here

						PdfPTable obs2 = new PdfPTable(1);
						obs2.setWidthPercentage(100); // Width 100%
						obs2.setSpacingBefore(10f); // Space before table
						obs2.getDefaultCell().setBorder(0);

						PdfPCell InspectionOBS = new PdfPCell(new Paragraph("Inspection observations :", font5));
						InspectionOBS.setBorder(PdfPCell.NO_BORDER);
						InspectionOBS.setBackgroundColor(BaseColor.LIGHT_GRAY);
						obs2.addCell(InspectionOBS);
						document.add(obs2);

						PdfPTable Obsevation1 = new PdfPTable(pointColumnWidths);
						Obsevation1.setWidthPercentage(100); // Width 100%
						Obsevation1.setSpacingBefore(10f); // Space before table
						Obsevation1.getDefaultCell().setBorder(0);

						PdfPTable InnInspOBS = new PdfPTable(pointColumnWidths);
						InnInspOBS.setWidthPercentage(100); // Width 100%
//						InnInspOBS.setSpacingBefore(5f); // Space before table
						InnInspOBS.getDefaultCell().setBorder(0);

						for (InspectionOuterObservation inspectionObs : allComponentObservation
								.getInspectionOuterObservation()) {

							if (inspectionObs.getObservationComponentDetails()
									.equalsIgnoreCase("inspectionComponent")) {

								PdfPCell EarthOBS = new PdfPCell(new Paragraph("Location observation :", font9));
								EarthOBS.setBorder(PdfPCell.NO_BORDER);
								EarthOBS.setGrayFill(0.92f);
								Obsevation1.addCell(EarthOBS);
								PdfPCell EarthOBS2 = new PdfPCell(
										new Paragraph(inspectionObs.getObservationDescription(), font9));
								EarthOBS2.setGrayFill(0.92f);
								EarthOBS2.setBorder(PdfPCell.NO_BORDER);
								Obsevation1.addCell(EarthOBS2);

							}

//							if (inspectionObs.getObservationComponentDetails().equalsIgnoreCase("consumer-UnitIter")) {

							for (InspectionInnerObservations inspectionInnerObservation : inspectionObs
									.getInspectionInnerObservations()) {

								PdfPCell insInnerObs = new PdfPCell(new Paragraph("Consumer observation", font9));
								insInnerObs.setBorder(PdfPCell.NO_BORDER);
//									insInnerObs.setGrayFill(0.92f);
								InnInspOBS.addCell(insInnerObs);
								PdfPCell insInnerObs2 = new PdfPCell(
										new Paragraph(inspectionInnerObservation.getObservationDescription(), font9));
//									insInnerObs2.setGrayFill(0.92f);
								insInnerObs2.setBorder(PdfPCell.NO_BORDER);
								InnInspOBS.addCell(insInnerObs2);
//								}
							}
						}

						document.add(Obsevation1);
						document.add(InnInspOBS);

//						Testing Observation Start from here

						PdfPTable obs3 = new PdfPTable(1);
						obs3.setWidthPercentage(100); // Width 100%
						obs3.setSpacingBefore(10f); // Space before table
						obs3.getDefaultCell().setBorder(0);

						PdfPCell TestingOBS = new PdfPCell(new Paragraph("Testing observations :", font5));
						TestingOBS.setBorder(PdfPCell.NO_BORDER);
						TestingOBS.setBackgroundColor(BaseColor.LIGHT_GRAY);
						obs3.addCell(TestingOBS);
						document.add(obs3);

						PdfPTable InnTestingOBS = new PdfPTable(pointColumnWidths);
						InnTestingOBS.setWidthPercentage(100); // Width 100%
						InnTestingOBS.setSpacingBefore(10f); // Space before table
						InnTestingOBS.getDefaultCell().setBorder(0);

						for (TestingInnerObservation testingObs : allComponentObservation
								.getTestingInnerObservation()) {

							if (testingObs.getObservationComponentDetails().equalsIgnoreCase("circuit")) {

								PdfPCell EarthOBS = new PdfPCell(new Paragraph("Circuit details observation :", font9));
								EarthOBS.setBorder(PdfPCell.NO_BORDER);
								EarthOBS.setGrayFill(0.92f);
								InnTestingOBS.addCell(EarthOBS);
								PdfPCell EarthOBS2 = new PdfPCell(
										new Paragraph(testingObs.getObservationDescription(), font9));
								EarthOBS2.setGrayFill(0.92f);
								EarthOBS2.setBorder(PdfPCell.NO_BORDER);
								InnTestingOBS.addCell(EarthOBS2);

							}
						}
						document.add(InnTestingOBS);

						float[] pointColumnWidths3 = { 30F, 40F, 90F };

						PdfPTable table6 = new PdfPTable(pointColumnWidths3);
						table6.setWidthPercentage(100); // Width 100%
						table6.setSpacingBefore(10f); // Space before table
						table6.setSpacingAfter(5f); // Space after table
						table6.setWidthPercentage(100);

						tableHeader(table6);
						tableData(table6, summary1);
						document.add(table6);
					}

					PdfPTable table16 = new PdfPTable(1);
					table16.setWidthPercentage(100); // Width 100%
					table16.setSpacingBefore(5f); // Space before table
					table16.getDefaultCell().setBorder(0);
//
					PdfPCell cell26 = new PdfPCell(new Paragraph("2.2: Observations classified as:-", font5));
					cell26.setBorder(PdfPCell.NO_BORDER);
					cell26.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table16.addCell(cell26);
					document.add(table16);

					Paragraph paragraphThree22 = new Paragraph(
							"Code C1 “dangers present” or Code C2 “potentially dangerous” are acted upon the matter of urgency and remedial action to be taken immediately, \r\nCode C3 “Improvement recommended” should be given due consideration, \r\nCode R1 “required further investigation” should start investigation without any delay. \r\nSubject to necessary remedial action being taken, I/We recommended that the installation is further inspected and tested by :",
							font9);

					document.add(paragraphThree22);

					float[] pointColumnWidthsDate = { 14F, 190F };

					PdfPTable table11 = new PdfPTable(pointColumnWidthsDate);
					table11.setWidthPercentage(100); // Width 100%
					table11.setSpacingBefore(10f); // Space before table
					table11.setSpacingAfter(5f); // Space after table
					table11.getDefaultCell().setBorder(0);

					PdfPCell cell23 = new PdfPCell(new Paragraph(summary1.getRecommendationsDate(), font9));
					table11.addCell(new Phrase("Date:", font8));
					cell23.setBorder(PdfPCell.NO_BORDER);
					table11.addCell(cell23);
					document.add(table11);

					PdfPTable table22 = new PdfPTable(pointColumnWidths);
					table22.setSpacingBefore(10f); // Space before table
					table22.setSpacingAfter(10f); // Space after table
					table22.setWidthPercentage(100);
					table22.getDefaultCell().setBorder(0);

					PdfPTable table17 = new PdfPTable(1);
					table17.setWidthPercentage(100); // Width 100%
					table17.setSpacingBefore(5f); // Space before table
					table17.getDefaultCell().setBorder(0);

					PdfPCell cell27 = new PdfPCell(
							new Paragraph(15, "Section - 3:Summary And Conditions Of The Installation ", font5));
					cell27.setBorder(PdfPCell.NO_BORDER);
					cell27.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table17.addCell(cell27);
					document.add(table17);

					float[] pointColumnWidths1 = { 90F, 90F };

					PdfPTable table7 = new PdfPTable(pointColumnWidths1); // 3 columns.
					table7.setWidthPercentage(100); // Width 100%
					table7.setSpacingBefore(10f); // Space before table
//					table7.setSpacingAfter(10f); // Space after table
					table7.getDefaultCell().setBorder(0);

					PdfPCell cell132 = new PdfPCell(new Paragraph(
							"General condition of the installation in terms of electrical safety:", font9));
					cell132.setBorder(PdfPCell.NO_BORDER);
					cell132.setGrayFill(0.92f);
					table7.addCell(cell132);
					PdfPCell cell29 = new PdfPCell(new Paragraph(summary1.getGeneralConditionInstallation(), font9));
					cell29.setGrayFill(0.92f);
					cell29.setBorder(PdfPCell.NO_BORDER);
					table7.addCell(cell29);

					PdfPTable table8 = new PdfPTable(pointColumnWidths1); // 3 columns.
					table8.setWidthPercentage(100); // Width 100%
					table8.setSpacingBefore(10f); // Space before table
					table8.getDefaultCell().setBorder(0);

					PdfPCell cell30 = new PdfPCell(new Paragraph(summary1.getOverallAssessmentInstallation(), font9));
					cell30.setBorder(PdfPCell.NO_BORDER);
					cell30.setGrayFill(0.92f);
					PdfPCell cell31 = new PdfPCell(new Paragraph(
							"Overall assessment of the installation in terms of suitability on continuous use:",
							font9));
					cell31.setGrayFill(0.92f);
					cell31.setBorder(PdfPCell.NO_BORDER);
					table8.addCell(cell31);
					table8.addCell(cell30);
					document.add(table7);
					document.add(table8);

				}

//				document.newPage();

				PdfPTable table18 = new PdfPTable(1);
				table18.setWidthPercentage(100); // Width 100%
				table18.setSpacingBefore(10f); // Space before table
				table18.getDefaultCell().setBorder(0);

				PdfPCell cell28 = new PdfPCell(new Paragraph(15, "Section - 4:Declaration", font5));
				cell28.setBorder(PdfPCell.NO_BORDER);
				cell28.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table18.addCell(cell28);
				document.add(table18);

				Font font09 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				Paragraph paragraph1 = new Paragraph(
						"I/we being the person responsible for the inspection & testing  of the electrical installation (as indicated by my/our signatures below), particulars of which are described in this report, having exercised reasonable skill and care when carrying out the inspection and testing, hereby declare that information in this report including the observations provides an accurate assessment of condition of electrical installation taking into account the stated extent and limitations in part 5 of this report",
						font09);

				document.add(paragraph1);

//				for (SummaryDeclaration declaration : declaration1) {

				PdfPTable table9 = new PdfPTable(2);
				table9.setWidthPercentage(100); // Width 100%
				table9.setSpacingBefore(10f); // Space before table
				table9.getDefaultCell().setBorder(0);

				addRow(table9, "Inspected and Tested  By ", "Authorized By");

				float[] pointColumnWidthsSec5 = { 40F, 90F, 40F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidthsSec5); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				addRow(table, "Name", declaration.getName(), "Name", declaration11.getName());
				addRow(table, "Company", declaration.getCompany(), "Company", declaration11.getCompany());
				addRow(table, "Signature	", declaration.getSignature(), "Signature	",
						declaration11.getSignature());
				addRow(table, "Position", declaration.getPosition(), "Position", declaration11.getPosition());
				addRow(table, "Address", declaration.getAddress(), "Address", declaration11.getAddress());
				addRow(table, "Date", declaration.getDate(), "Date", declaration11.getDate());
				document.add(table9);
				document.add(table);

				if (comments.getViewerUserName() != null && comments.getInspectorUserName() != null) {

					document.newPage();

					PdfPTable table19911 = new PdfPTable(1);
					table19911.setSpacingBefore(10f); // Space before table
					table19911.setWidthPercentage(100);
					table19911.getDefaultCell().setBorder(0);
					PdfPCell cell6511 = new PdfPCell(
							new Paragraph(15, "Section - 6: Viewer And Inspector Comment:", font));
					cell6511.setBorder(PdfPCell.NO_BORDER);
					cell6511.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table19911.addCell(cell6511);
					document.add(table19911);

					Font font91111 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

					float[] pointColumnWidths4 = { 90F, 90F, 90F, 90F };

					PdfPTable table440 = new PdfPTable(pointColumnWidths4);
					table440.setWidthPercentage(100); // Width 100%
					table440.setSpacingBefore(10f); // Space before table

					PdfPCell cell550 = new PdfPCell(new Paragraph(comments.getViewerUserName(), font91111));
					cell550.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell3710 = new PdfPCell(new Paragraph("ViewerUserName:", font91111));
					cell3710.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell3710.setFixedHeight(25f);
					cell3710.setGrayFill(0.92f);
					table440.addCell(cell3710);
					table440.addCell(cell550);

					PdfPCell cell3801 = new PdfPCell(new Paragraph(comments.getInspectorUserName(), font91111));
					cell3801.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell37101 = new PdfPCell(new Paragraph("InspectorUserName:", font91111));
					cell37101.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell37101.setFixedHeight(25f);
					cell37101.setGrayFill(0.92f);
					table440.addCell(cell37101);
					table440.addCell(cell3801);

					PdfPCell cell5610 = new PdfPCell(new Paragraph("ViewerComment Date:", font91111));
					cell5610.setGrayFill(0.92f);
					cell5610.setHorizontalAlignment(Element.ALIGN_CENTER);

					PdfPCell cell56110 = new PdfPCell(new Paragraph("ViewerComment:", font91111));
					cell56110.setGrayFill(0.92f);
					cell56110.setHorizontalAlignment(Element.ALIGN_CENTER);
					table440.addCell(cell56110);
					table440.addCell(cell5610);

					PdfPCell cell4010 = new PdfPCell(new Paragraph("InspectorComment Date:", font91111));
					cell4010.setGrayFill(0.92f);
					cell4010.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell39101 = new PdfPCell(new Paragraph("InspectorComment:", font91111));
					cell39101.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell39101.setGrayFill(0.92f);
					table440.addCell(cell39101);
					table440.addCell(cell4010);

					tableData1(table440, ReportComments);
					document.add(table440);
				}
				document.close();
				writer.close();
			} catch (

			Exception e) {
				e.printStackTrace();
			}

		} else

		{
			throw new SummaryException("Invalid Inputs");
		}
	}

	private void tableData1(PdfPTable table440, List<SummaryComment> reportComments)
			throws DocumentException, IOException {

		Collections.sort(reportComments, new Comparator<SummaryComment>() {
			public int compare(SummaryComment periodic1, SummaryComment periodic2) {
				if (periodic1.getViewerDate() != null && periodic2.getViewerDate() != null) {
					return periodic1.getViewerDate().compareTo(periodic2.getViewerDate());
				} else {
					return 0;
				}
			}
		});

		Collections.sort(reportComments, new Comparator<SummaryComment>() {
			public int compare(SummaryComment periodic1, SummaryComment periodic2) {
				if (periodic1.getInspectorDate() != null && periodic2.getInspectorDate() != null) {
					return periodic1.getInspectorDate().compareTo(periodic2.getInspectorDate());
				} else {
					return 0;
				}
			}
		});

		for (SummaryComment arr : reportComments) {
			Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
			PdfPCell cell = new PdfPCell();

			if (arr.getViewerComment() != null) {
				cell.setPhrase(new Phrase(arr.getViewerComment(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table440.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No viewer comment available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table440.addCell(cell);
			}

			if (arr.getViewerDate() != null) {
				cell.setPhrase(new Phrase(arr.getViewerDate().toString(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table440.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No viewer date available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table440.addCell(cell);
			}

			if (arr.getInspectorComment() != null) {
				cell.setPhrase(new Phrase(arr.getInspectorComment(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table440.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No inspector comment available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table440.addCell(cell);
			}

			if (arr.getInspectorDate() != null) {
				cell.setPhrase(new Phrase(arr.getInspectorDate().toString(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table440.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No viewer date available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table440.addCell(cell);
			}

		}
	}

	private void addRow(PdfPTable table9, String string, String string2) throws DocumentException, IOException {
		Font font8 = new Font(BaseFont.createFont(), 10, Font.BOLD, BaseColor.BLACK);
		PdfPCell nameCell = new PdfPCell(new Paragraph(string, font8));
		PdfPCell nameCell1 = new PdfPCell(new Paragraph(string2, font8));
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell.setGrayFill(0.92f);
		nameCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell1.setGrayFill(0.92f);
		table9.addCell(nameCell);
		table9.addCell(nameCell1);
	}

	private void tableData(PdfPTable table6, Summary summary1) throws DocumentException, IOException {
//		for (Summary arr : summary1) {
//			if (!arr.getObervationStatus().equalsIgnoreCase("R")) {
		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell();
//				cell.setPhrase(new Phrase("", font));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				table6.addCell(cell);
//		cell.setPhrase(new Phrase(summary1.getReferanceNumberReport(), font));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table6.addCell(cell);
//		cell.setPhrase(new Phrase(summary1.getFurtherActions(), font));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table6.addCell(cell);
//		cell.setPhrase(new Phrase(summary1.getComment(), font));
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table6.addCell(cell);
//			}
	}

	// }

	private void tableHeader(PdfPTable table6) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(3);
		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
//		cell.setPhrase(new Phrase("Observations", font));
//		cell.setGrayFill(0.92f);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table6.addCell(cell);
		cell.setPhrase(new Phrase("Reference in Report", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell);
		cell.setPhrase(new Phrase("Further actions", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell);
		cell.setPhrase(new Phrase("Remedial action to be taken", font));
		cell.setGrayFill(0.92f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell);

	}

	private void addRow(PdfPTable table6, String string, String string2, String string3, String string4)
			throws DocumentException, IOException {
		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		PdfPCell nameCell = new PdfPCell(new Paragraph(string, font));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3, font));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell.setGrayFill(0.92f);
		valueCell2.setGrayFill(0.92f);
		table6.addCell(nameCell);
		table6.addCell(valueCell1);
		table6.addCell(valueCell2);
		table6.addCell(valueCell3);

	}

}
