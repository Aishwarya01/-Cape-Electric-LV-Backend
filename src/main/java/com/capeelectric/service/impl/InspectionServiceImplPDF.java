package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.Circuit;
import com.capeelectric.model.ConsumerUnit;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.IsolationCurrent;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.service.InspectionServicePDF;
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
public class InspectionServiceImplPDF implements InspectionServicePDF {

	@Autowired
	private InspectionRepository inspectionRepository;

	@Override
	public List<PeriodicInspection> retrieveInspectionDetails(String userName, Integer siteId)
			throws InspectionException {

		if (userName != null && !userName.isEmpty() && siteId != null) {

			Document document = new Document(PageSize.A4, 36, 36, 50, 36);

			try {
				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream("PrintInspectionDetailsData.pdf"));

			
				Optional<PeriodicInspection> inspectionDetails = inspectionRepository.findBySiteId(siteId);
				PeriodicInspection inspection = inspectionDetails.get();

				List<IpaoInspection> ipo = inspection.getIpaoInspection();
				IpaoInspection ipoInspection = ipo.get(0);
				
				List<ConsumerUnit> consumer = ipoInspection.getConsumerUnit();
				ConsumerUnit consumerUnit = consumer.get(0);
				
				List<Circuit> circuitDetails = ipoInspection.getCircuit();
				Circuit circuit = circuitDetails.get(0);
				
				List<IsolationCurrent> isolationCurrentDetails = ipoInspection.getIsolationCurrent();
				IsolationCurrent isolationCurrent = isolationCurrentDetails.get(0);
				
				document.open();
                
				Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph1 = new Paragraph(
						"Verification (inspection and testing) of an electrical installation", font);
				paragraph1.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph1);

				Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph2 = new Paragraph("Part:3", font1);
				paragraph2.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph2);

				Paragraph paragraph3 = new Paragraph("Inspection", font);
				paragraph3.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph3);

				Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph4 = new Paragraph(
						"(Initial and periodic inspection of an existing installation up to 1000 V AC and 1500 V DC)",
						font2);
				paragraph4.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph4);

				Font font7 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.NORMAL, BaseColor.BLACK);
				Paragraph paragraph5 = new Paragraph(
						"Note: For periodic inspection, a visual inspection should be made to find out the external condition of all electrical equipment which is not concealed. Further detailed inspection, including partial dismantling of equipment (as required), should be carried out as agreed with the customer.",
						font7);
				document.add(paragraph5);

				
				for ( IpaoInspection arr : ipo ) {
					
					inspectionIteration(document, arr, consumerUnit, circuit, isolationCurrent);
					
				}
				
				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new InspectionException("Invalid Inputs");
		}
		return null;
	}


	/**
	 * @param document
	 * @param ipoInspection
	 * @param consumerUnit
	 * @param circuit
	 * @param isolationCurrent
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void inspectionIteration(Document document, IpaoInspection ipoInspection, ConsumerUnit consumerUnit,
			Circuit circuit, IsolationCurrent isolationCurrent) throws DocumentException, IOException {
		Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD | Font.UNDERLINE,
				BaseColor.BLACK);
		Paragraph paragraph6 = new Paragraph("Section 1", font0);
		paragraph6.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph6);

		Font font6 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC | Font.BOLD, BaseColor.BLACK);
		Paragraph paragraph7 = new Paragraph("Incoming equipment", font6);
		document.add(paragraph7);

		float[] pointColumnWidths = { 200F, 100F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths);

		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(10f); // Space after table
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(0);
		
		PdfPCell cell80 = new PdfPCell(
				new Paragraph(ipoInspection.getLocationNumber()));
		table1.addCell(new Phrase("Location number:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell80.setFixedHeight(30f);
		cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell80.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell80);
		
		PdfPCell cell81 = new PdfPCell(
				new Paragraph(ipoInspection.getLocationName(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table1.addCell(new Phrase("Location name:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell81.setFixedHeight(30f);
		cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell81.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell81);

		PdfPCell cell = new PdfPCell(
				new Paragraph(ipoInspection.getServiceCable(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table1.addCell(new Phrase("Service cable:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell.setFixedHeight(30f);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell);
		
		PdfPCell cell1 = new PdfPCell(
				new Paragraph(ipoInspection.getServiceFuse(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table1.addCell(new Phrase("Service cut out / fuse:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell1.setFixedHeight(30f);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell1);
		
		PdfPCell cell2 = new PdfPCell(
				new Paragraph(ipoInspection.getMeterDistributor(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table1.addCell(new Phrase("Meter tails – Distributor:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell2.setFixedHeight(30f);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell(
				new Paragraph(ipoInspection.getMeterConsumer(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table1.addCell(new Phrase("Meter tails – Consumer:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell3.setFixedHeight(30f);
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell3);
		
		PdfPCell cell4 = new PdfPCell(
				new Paragraph(ipoInspection.getMeterEqu(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table1.addCell(new Phrase("Metering Equipment:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell4.setFixedHeight(30f);
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell4.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell4);
		
		PdfPCell cell5 = new PdfPCell(
				new Paragraph(ipoInspection.getIsolator(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table1.addCell(new Phrase("Isolator (Means to isolate the public supply system):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell5.setFixedHeight(15f);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell5);
		document.add(table1);
		
		Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC | Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph8 = new Paragraph(
				"Note: Where inadequacies in distributor’s equipment are encountered, it is recommended that the user informs this to the appropriate authority.", font5);
		document.add(paragraph8);
		
		Paragraph paragraph9 = new Paragraph("Section 2", font0);
		paragraph9.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph9);
		
		Paragraph paragraph10 = new Paragraph("Presence of adequate arrangements for parallel or switched alternative sources of supply", font6);
		document.add(paragraph10);
		
		
		PdfPTable table2 = new PdfPTable(pointColumnWidths);

		table2.setWidthPercentage(100); // Width 100%
		table2.setSpacingBefore(10f); // Space before table
		table2.setSpacingAfter(10f); // Space after table
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(0);

		PdfPCell cell6 = new PdfPCell(
				new Paragraph(ipoInspection.getEarthingArrangement(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table2.addCell(new Phrase("Dedicated earthing arrangement independent to that of public supply:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell6.setFixedHeight(35f);
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell6.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell6);
		
		PdfPCell cell7 = new PdfPCell(
				new Paragraph(ipoInspection.getAdequateArrangement(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table2.addCell(new Phrase("Presence of adequate arrangements where generator to operate in parallel with the public supply system:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell7.setFixedHeight(35f);
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell7);
		
		PdfPCell cell8 = new PdfPCell(
				new Paragraph(ipoInspection.getConnectionGenerator(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table2.addCell(new Phrase("Correct connections of generator in parallel. (note: Special attention to circulating currents):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell8.setFixedHeight(35f);
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell8.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell8);
		
		PdfPCell cell9 = new PdfPCell(
				new Paragraph(ipoInspection.getCompatibilityCharacteristics(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table2.addCell(new Phrase("Compatibility of characteristics of means of generation:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell9.setFixedHeight(25f);
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell9);
		
		PdfPCell cell10 = new PdfPCell(
				new Paragraph(ipoInspection.getAutomaticDisconnectGenerator(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table2.addCell(new Phrase("Means to provide automatic disconnection of generator in the event of loss of public supply system or voltage or frequency deviation beyond declared values:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell10.setFixedHeight(50f);
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell10);
		
		PdfPCell cell11 = new PdfPCell(
				new Paragraph(ipoInspection.getPreventConnectGenerator(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table2.addCell(new Phrase("Means to prevent connection of generator in the event of loss of public supply system or voltage or frequency deviation beyond declared values:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell11.setFixedHeight(50f);
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell11);
		
		PdfPCell cell12 = new PdfPCell(
				new Paragraph(ipoInspection.getIsolateGenerator(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table2.addCell(new Phrase("Means to isolate generator from the public supply system :", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell12.setFixedHeight(30f);
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell12.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell12);
		document.add(table2);
		
		Paragraph paragrap11 = new Paragraph("Section 3", font0);
		paragrap11.setAlignment(Element.ALIGN_CENTER);
		document.add(paragrap11);
		
		Paragraph paragraph12 = new Paragraph("Automatic disconnection of supply", font6);
		document.add(paragraph12);
		
		PdfPTable table3 = new PdfPTable(pointColumnWidths);

		table3.setWidthPercentage(100); // Width 100%
		table3.setSpacingBefore(10f); // Space before table
		table3.setSpacingAfter(10f); // Space after table
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setBorder(0);

		PdfPCell cell13 = new PdfPCell(
				new Paragraph(ipoInspection.getMainEarting(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Main earthing / bonding arrangements:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell13.setFixedHeight(30f);
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell13);
		
		PdfPCell cell14 = new PdfPCell(
				new Paragraph(ipoInspection.getMainEarting(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Main earthing / bonding arrangements:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell14.setFixedHeight(25f);
		cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell14.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell14);
		
		PdfPCell cell15 = new PdfPCell(
				new Paragraph(ipoInspection.getEarthElectordeArrangement(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Presence and adequacy of energy suppliers earthing arrangement or installation earth electrode arrangement:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell15.setFixedHeight(40f);
		cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell15.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell15);
		
		PdfPCell cell16 = new PdfPCell(
				new Paragraph(ipoInspection.getEarthConductorConnection(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Presence and adequacy of earthing conductor and connections:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell16.setFixedHeight(30f);
		cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell16.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell16);
		
		PdfPCell cell17 = new PdfPCell(
				new Paragraph(ipoInspection.getAccessibility(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Accessibility of earthing conductor connections:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell17.setFixedHeight(30f);
		cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell17.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell17);
		
		PdfPCell cell18 = new PdfPCell(
				new Paragraph(ipoInspection.getAainProtectBonding(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Presence and adequacy of main protective bonding conductors and connections (colour, sizes, termination, and provision of independent earthing):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell18.setFixedHeight(45f);
		cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell18.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell18);
		
		PdfPCell cell19 = new PdfPCell(
				new Paragraph(ipoInspection.getAllProtectBonding(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Accessibility of all protective bonding connections:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell19.setFixedHeight(30f);
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell19.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell19);
		
		PdfPCell cell20 = new PdfPCell(
				new Paragraph(ipoInspection.getAllAppropriateLocation(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Presence and adequacy of electrical earthing/bonding labels at all appropriate locations:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell20.setFixedHeight(35f);
		cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell20.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell20);
		
		PdfPCell cell21 = new PdfPCell(
				new Paragraph(ipoInspection.getFelvRequirement(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table3.addCell(new Phrase("Accessibility of FELV requirements satisfied:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell21.setFixedHeight(25f);
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell21);
		document.add(table3);
		
		
		Paragraph paragrap12 = new Paragraph("Section 4", font0);
		paragrap12.setAlignment(Element.ALIGN_CENTER);
		document.add(paragrap12);
		
		Paragraph paragraph13 = new Paragraph("Other methods of protection applicable to locations where automatic disconnection of supply is not employed. If any of the methods listed below are employed details should be provided on separate page.", font6);
		document.add(paragraph13);
		
		Paragraph paragraph14 = new Paragraph("Basic and fault protection.", font5);
		document.add(paragraph14);
		
		
		PdfPTable table4 = new PdfPTable(pointColumnWidths);

		table4.setWidthPercentage(100); // Width 100%
		table4.setSpacingBefore(10f); // Space before table
		table4.setSpacingAfter(10f); // Space after table
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setBorder(0);

		PdfPCell cell22 = new PdfPCell(
				new Paragraph(ipoInspection.getSelvSystem(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(new Phrase("SELV system, including the source and associated circuits:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell22.setFixedHeight(30f);
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell22);
		
		PdfPCell cell23 = new PdfPCell(
				new Paragraph(ipoInspection.getPelvSystem(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(new Phrase("PELV system, including the source and associated circuits:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell23.setFixedHeight(30f);
		cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell23.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell23);
		
		PdfPCell cell24 = new PdfPCell(
				new Paragraph(ipoInspection.getDoubleInsulation(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(new Phrase("Double insulation (Class II or equivalent equipment and associated circuits):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell24.setFixedHeight(35f);
		cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell24.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell24);
		
		PdfPCell cell25 = new PdfPCell(
				new Paragraph(ipoInspection.getReinforcedInsulation(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(new Phrase("Reinforced insulation:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell25.setFixedHeight(25f);
		cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell25.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell25);
		
		PdfPCell cell26 = new PdfPCell(
				new Paragraph(ipoInspection.getBasicElectricalSepartion(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table4.addCell(new Phrase("Electrical separation for one item of equipment (shaver supply unit):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell26.setFixedHeight(10f);
		cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell26.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell26);
		document.add(table4);
		
		Paragraph paragraph15 = new Paragraph("Basic protection (prevention of contact with live parts).", font5);
		document.add(paragraph15);
		
		PdfPTable table5 = new PdfPTable(pointColumnWidths);

		table5.setWidthPercentage(100); // Width 100%
		table5.setSpacingBefore(10f); // Space before table
		table5.setSpacingAfter(10f); // Space after table
		table5.setWidthPercentage(100);
		table5.getDefaultCell().setBorder(0);

		PdfPCell cell27 = new PdfPCell(
				new Paragraph(ipoInspection.getInsulationLiveParts(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Insulation of live parts (conductors completely covered with durable insulating material):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell27.setFixedHeight(35f);
		cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell27.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell27);
		
		PdfPCell cell28 = new PdfPCell(
				new Paragraph(ipoInspection.getInsulationLiveParts(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Insulation of live parts (conductors completely covered with durable insulating material):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell28.setFixedHeight(35f);
		cell28.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell28.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell28);
		
		PdfPCell cell29 = new PdfPCell(
				new Paragraph(ipoInspection.getBarriersEnclosers(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Barriers or enclosures (correct IP rating):", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell29.setFixedHeight(20f);
		cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell29.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell29);
		
		PdfPCell cell30 = new PdfPCell(
				new Paragraph(ipoInspection.getObstacles(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Obstacles:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell30.setFixedHeight(25f);
		cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell30.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell30);
		
		PdfPCell cell31 = new PdfPCell(
				new Paragraph(ipoInspection.getPlacingOutReach(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table5.addCell(new Phrase("Placing out of reach:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell31.setFixedHeight(10f);
		cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell31.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell31);
		document.add(table5);
		
		Paragraph paragraph16 = new Paragraph("Fault protection.", font5);
		document.add(paragraph16);
		
		PdfPTable table6 = new PdfPTable(pointColumnWidths);

		table6.setWidthPercentage(100); // Width 100%
		table6.setSpacingBefore(10f); // Space before table
		table6.setSpacingAfter(10f); // Space after table
		table6.setWidthPercentage(100);
		table6.getDefaultCell().setBorder(0);

		PdfPCell cell32 = new PdfPCell(
				new Paragraph(ipoInspection.getFaultNonConductLocation(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table6.addCell(new Phrase("Non-Conducting location – earth free local equipotential bonding:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell32.setFixedHeight(35f);
		cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell32.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell32);
		
		PdfPCell cell33 = new PdfPCell(
				new Paragraph(ipoInspection.getFaultElectricalSepartion(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table6.addCell(new Phrase("Electrical separation:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell33.setFixedHeight(10f);
		cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell33.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell33);
		document.add(table6);

		Paragraph paragraph17 = new Paragraph("Additional protection.", font5);
		document.add(paragraph17);
		
		PdfPTable table7 = new PdfPTable(pointColumnWidths);

		table7.setWidthPercentage(100); // Width 100%
		table7.setSpacingBefore(10f); // Space before table
		table7.setSpacingAfter(10f); // Space after table
		table7.setWidthPercentage(100);
		table7.getDefaultCell().setBorder(0);
		
		PdfPCell cell34 = new PdfPCell(
				new Paragraph(ipoInspection.getOperatingCurrent(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("RCD(s) not exceeding 30 mA operating current:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell34.setFixedHeight(30f);
		cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell34.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell34);
		
		PdfPCell cell35= new PdfPCell(
				new Paragraph(ipoInspection.getSupplementaryBonding(), new Font(BaseFont.createFont(), 12, Font.NORMAL)));
		table7.addCell(new Phrase("Supplementary bonding:", new Font(BaseFont.createFont(), 12, Font.ITALIC)));
		cell35.setFixedHeight(20f);
		cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell35.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell35);
		document.add(table7);
		
		Paragraph paragraph18 = new Paragraph("SPECIFIC INSPECTION EXAMPLES to be included in the report as appropriate to the installation.", font5);
		document.add(paragraph18);
		
		
		Paragraph paragrap19 = new Paragraph("Section 5", font0);
		paragrap19.setAlignment(Element.ALIGN_CENTER);
		document.add(paragrap19);
		
		Paragraph paragraph20 = new Paragraph("Consumer unit(s) / distribution board(s) / distribution equipment", font6);
		document.add(paragraph20);
		

		float[] pointColumnWidths1 = { 30F ,255F, 65F };
		
		PdfPTable table8 = new PdfPTable(pointColumnWidths1); // 3 columns.
		table8.setWidthPercentage(100); // Width 100%
		table8.setSpacingBefore(20f); // Space before table
		table8.setSpacingAfter(15f); // Space after table
		table8.setWidthPercentage(100);
		
		addRow(table8, "1", "Adequacy of access and working space for items of electrical equipment including switchgear", consumerUnit.getAccessWorking());
		addRow(table8, "2", "Security of fixing ", consumerUnit.getSecurityFixing());
		addRow(table8, "3", "Insulation of live parts not damaged during erection", consumerUnit.getLivePartsDamage());
		addRow(table8, "4", "Adequacy / security of barriers", consumerUnit.getSecurityBarriers());
		addRow(table8, "5", "Suitability of enclosure(s) for IP and fire ratings", consumerUnit.getSuitabilityEnclosure());
		addRow(table8, "6", "Enclosure not damaged during installation", consumerUnit.getEnclosureDamaged());
		addRow(table8, "7", "presence and effectiveness of obstacles", consumerUnit.getPresenceObstacles());
		addRow(table8, "8", "Placing out of reach", consumerUnit.getPlacingOutOfConsumer());
		addRow(table8, "9", "Presence of main switches linked where required", consumerUnit.getPresenceMainSwitches());
		addRow(table8, "10", "Operation of main switches (functional checks)", consumerUnit.getOperationMainSwitches());
		addRow(table8, "11", "Manual operation of circuit breakers and RCD’s to prove functionally", consumerUnit.getManualCircuitBreakers());
		addRow(table8, "12", "Confirmation that integral test button / switch causes RCD’s to trip when operated (functional check)", consumerUnit.getSwitchCausesRcd());
		addRow(table8, "13", "RCD’s provided for fault protection, where specified", consumerUnit.getRcdFaultProtection());
		addRow(table8, "14", "RCD’s provided for additional protection, where specified", consumerUnit.getRcdAdditionalProtection());
		addRow(table8, "15", "Confirmation of over voltage protection (SPD’s) provided where specified", consumerUnit.getOverVoltageProtection());
		addRow(table8, "16", "Confirmation of indication that SPD is functional", consumerUnit.getIndicationOfSpd());
		addRow(table8, "17", "Presence of RCD quarterly test notice at or near origin", consumerUnit.getRcdQuarterlyTest());
		addRow(table8, "18", "Presence of diagrams charts or schedules at or near each distribution board where required", consumerUnit.getDiagramsCharts());
		addRow(table8, "19", "presence of nonstandard (mixed) cable colour warning notice near appropriate distribution board, as required", consumerUnit.getNonstandardCableColour());
		addRow(table8, "20", "Presence of alternative supply - warning notice at or near the origin", consumerUnit.getNonstandardCableColour());
		addRow(table8, "21", "Presence of alternative supply - warning notice at or near the meter position, if remote from origin", consumerUnit.getAlSupplyOfMeter());
		addRow(table8, "22", "Presence of alternative supply - warning notice at or near the distribution board to which alternative sources are connected", consumerUnit.getAlSupplyDistribution());
		addRow(table8, "23", "Presence of alternative supply - warning notice at or near all points of isolation of ALL sources of supply", consumerUnit.getAllPointsIsolation());
		addRow(table8, "24", "Presence of next inspection recommendation label", consumerUnit.getNextInspection());
		addRow(table8, "25", "Presence of other required labelling", consumerUnit.getOtherRequiredLabelling());
		addRow(table8, "26", "Selection of protective devices and bases correct type and rating (no signs of unacceptable thermal damage, arcing or overheating)", consumerUnit.getBasesCorrectType());
		addRow(table8, "27", "Single pole protective devices in line conductor only", consumerUnit.getSinglePole());
		addRow(table8, "28", "Protection against mechanical damage where cables enter equipment", consumerUnit.getMechanicalDamage());
		addRow(table8, "29", "Protection against electromagnetic/heating effects where cables enter ferromagnetic enclosures", consumerUnit.getElectromagnetic());
		addRow(table8, "30", "Confirmation that all conductor connections including connections to busbars are correctly located in terminals and are tight and secure", consumerUnit.getAllConductorCon());
		document.add(table8);

		
		Paragraph paragraph21 = new Paragraph("Section 6", font0);
		paragraph21.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph21);

		Paragraph paragraph22 = new Paragraph("Circuits", font6);
		document.add(paragraph22);

		
		PdfPTable table9 = new PdfPTable(pointColumnWidths1);

		table9.setWidthPercentage(100); // Width 100%
		table9.setSpacingBefore(10f); // Space before table
		table9.setSpacingAfter(10f); // Space after table
		table9.setWidthPercentage(100);
		table9.getDefaultCell().setBorder(0);
		
		addRow1(table9, "1", "Identification of conductors including main earthing / bonding arrangements", circuit.getCableInstallation());
		addRow1(table9, "2", "Cable installation methods (including support) suitable for the location(s) and external influences (Cables correctly supported throughout)", circuit.getIdentificationConductors());
		addRow1(table9, "3", "Examination of cables for signs of mechanical damage during installation", circuit.getExaminationCables());
		addRow1(table9, "4", "Examination of insulation of live parts not damaged during erection", circuit.getExaminationInsulation());
		addRow1(table9, "5", "Non-Sheathed cables protected by enclosure in conduit, ducting or trucking", circuit.getNonSheathedCables());
		addRow1(table9, "6", "Suitability of containment systems including flexible conduit", circuit.getContainmentSystems());
		addRow1(table9, "7", "Correct temperature rating of cable insulation", circuit.getTemperatureRating());
		addRow1(table9, "8", "Cables correctly terminated in enclosures", circuit.getCablesTerminated());
		addRow1(table9, "9", "Adequacy of conductors for current-carrying capacity with respect to type and nature of the installation", circuit.getCurrentCarryCapacity());
		addRow1(table9, "10", "Adequacy of protective devices, type and fault current rating for fault protection", circuit.getAdequacyProtectDevices());
		addRow1(table9, "11", "Presence, adequacy, and correct termination of protective conductors", circuit.getPresenceProtectConductors());
		addRow1(table9, "12", "Co-ordination between conductors and overload protective devices", circuit.getCoOrdination());
		addRow1(table9, "13", "Wiring systems and cable installation methods/ practices with regard to the type and nature of installation and external influences", circuit.getWiringSystems());
		addRow1(table9, "14", "Cables concealed under floors above ceilings, in wall adequately protected against damage by contact with fixings", circuit.getCablesConcealUnderFloors());
		addRow1(table9, "15", "Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for circuits used to supply mobile equipment not exceeding 32A rating for use outdoors in all cases", circuit.getOperatingCurrentCircuits());
		addRow1(table9, "16", "Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for all socket outlets of rating 20A or less provided for use by ordinary persons unless exempt", circuit.getOperatingCurrentSocket());
		addRow1(table9, "17", "Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for cables concealed in walls at a depth of less than 50mm", circuit.getCablesConcDepth());
		addRow1(table9, "18", "Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for Cables concealed in walls / sections containing metal sections regardless of depth", circuit.getSectionsRegardlessDepth());
		addRow1(table9, "19", "Provision of fire barriers, sealing arrangements so as to minimize the spread of fire", circuit.getProvisionFireBarriers());
		addRow1(table9, "20", "Segregation/separation of Band I (ELV) and Band II (LV) circuits", circuit.getSeparationBand());
		addRow1(table9, "21", "Segregation/separation of electrical and non-electrical services", circuit.getSeparationElectrical());
		addRow1(table9, "22", "Condition of circuit accessories", circuit.getConditionCircuitAccessories());
		addRow1(table9, "23", "Cables and conductors correctly terminated, enclosed and with no undue mechanical strain", circuit.getConductorCorrectTerminated());
		addRow1(table9, "24", "No basic insulation of a conductor visible outside enclosure", circuit.getConductorVisibleOutside());
		addRow1(table9, "25", "Connections of live conductors adequately enclosed", circuit.getConnLiveConductors());
		addRow1(table9, "26", "Adequately connected at the point of entry to enclosure (glands, bushes etc.)", circuit.getAdequatelyConnectedEnclosure());
		addRow1(table9, "27", "Suitability of circuit accessories for external influences", circuit.getSuitabilityCircuitAccessories());
		addRow1(table9, "28", "Condition of accessories including socket-outlets, switches and joint boxes (Circuit accessories not damaged, securely fixed, correctly connected, suitable for external influences)", circuit.getConditionAccessories());
		addRow1(table9, "29", "Single-pole devices for switching or protection in line conductors only", circuit.getSinglePoleDevices());
		addRow1(table9, "30", "Adequacy of connections, including protective conductors, within accessories and fixed and stationary equipment", circuit.getAdequacyConnections());
		addRow1(table9, "31", "Presence, operation, and correct location of appropriate devices for isolation and switching", circuit.getIsolationSwitching());
		document.add(table9);

		
		Paragraph paragraph23 = new Paragraph("Section 7", font0);
		paragraph23.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph23);

		Paragraph paragraph24 = new Paragraph("Isolation and switching", font6);
		document.add(paragraph24);
		
		PdfPTable table10 = new PdfPTable(pointColumnWidths1);

		table10.setWidthPercentage(100); // Width 100%
		table10.setSpacingBefore(10f); // Space before table
		table10.setSpacingAfter(10f); // Space after table
		table10.setWidthPercentage(100);
		table10.getDefaultCell().setBorder(0);
		
		addRow2(table10, "1", "Isolators - Presence of appropriate devices ", isolationCurrent.getPresenceDevices());
		addRow2(table10, "2", "Isolators – Condition of appropriate devices ", isolationCurrent.getConditionDevices());
		addRow2(table10, "3", "Isolators - Location of appropriate devices (state if local or remote from equipment in question)", isolationCurrent.getLocationDevices());
		addRow2(table10, "4", "Isolators - Capable of being secured in OFF position", isolationCurrent.getCapableSecured());
		addRow2(table10, "5", "Isolators - Correct operation verified (functional checks)", isolationCurrent.getOperationVerify());
		addRow2(table10, "6", "Isolators - The installation, circuit or Section thereof that will be isolated is clearly identified by location and/or durable marking", isolationCurrent.getInstallCircuit());
		addRow2(table10, "7", "Isolators - Warning label posted in the situation where live Sections cannot be isolated by the operation of single device", isolationCurrent.getWarningLabel());
		addRow2(table10, "8", "Switching off for mechanical maintenance - Presence of appropriate devices", isolationCurrent.getSwPresenceDevices());
		addRow2(table10, "9", "Switching off for mechanical maintenance - Condition of appropriate devices", isolationCurrent.getSwConditionDevices());
		addRow2(table10, "10", "Switching off for mechanical maintenance - Acceptable location (state if local or remote from equipment in question)", isolationCurrent.getSwAcceptableLocation());
		addRow2(table10, "11", "Switching off for mechanical maintenance - Capable of being secured in OFF position", isolationCurrent.getSwCapableOffPosition());
		addRow2(table10, "12", "Switching off for mechanical maintenance - Correct operation verified (functional check)", isolationCurrent.getSwCorrectOperation());
		addRow2(table10, "13", "Switching off for mechanical maintenance - The circuit or section there of that will be disconnected clearly identified by location and / or durable marking", isolationCurrent.getSwCircuit());
		addRow2(table10, "14", "Switching off for mechanical maintenance - Warning label posted in situations where live parts cannot be isolated by the operation of a single device", isolationCurrent.getSwWarningLabel());
		addRow2(table10, "15", "Emergency switching / stopping - Presence of appropriate devices", isolationCurrent.getEmSwitPresenceDevices());
		addRow2(table10, "16", "Emergency switching / stopping – Condition of appropriate devices", isolationCurrent.getEmSwitConditionDevices());
		addRow2(table10, "17", "Emergency switching / stopping - Location of appropriate devices (readily accessible for operation where danger might occur)", isolationCurrent.getEmSwitLocationDevices());
		addRow2(table10, "18", "Emergency switching / stopping - Correct operation verified (functional check)", isolationCurrent.getEmSwitOperationVerify());
		addRow2(table10, "19", "Emergency switching / stopping - The installation circuit or Section there of that will be disconnected clearly identified by location and / or durable marking", isolationCurrent.getEmSwitInstallCircuit());
		addRow2(table10, "20", "Functional switching - Presence of appropriate devices", isolationCurrent.getFuSwitPresenceDevices());
		addRow2(table10, "21", "Functional switching - Location of appropriate devices", isolationCurrent.getFuSwitLocationDevices());
		addRow2(table10, "22", "Functional switching - Correct operation verified (functional check)", isolationCurrent.getFuSwitOperationVerify());
		document.add(table10);
		
		Paragraph paragraph25 = new Paragraph("Section 8", font0);
		paragraph25.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph25);

		Paragraph paragraph26 = new Paragraph("Current – using equipment (permanently connected)", font6);
		document.add(paragraph26);
		
		
		PdfPTable table11 = new PdfPTable(pointColumnWidths1);

		table11.setWidthPercentage(100); // Width 100%
		table11.setSpacingBefore(10f); // Space before table
		table11.setSpacingAfter(10f); // Space after table
		table11.setWidthPercentage(100);
		table11.getDefaultCell().setBorder(0);
		
		addRow3(table11, "1", "Suitability of the equipment in terms of IP and fire ratings", isolationCurrent.getSuitabilityEquipment());
		addRow3(table11, "2", "Enclosure not damaged / deteriorated during installation so as to impair safety", isolationCurrent.getEnclosureNotDamaged());
		addRow3(table11, "3", "Suitability for the environment and external influences", isolationCurrent.getSuitabilityEnvironment());
		addRow3(table11, "4", "Security of fixing", isolationCurrent.getSecurityFixing());
		addRow3(table11, "5", "Cable entry holes in ceilings above luminaries, seized or sealed so as to restrict the spread of fire.", isolationCurrent.getCableEntryHoles());
		addRow3(table11, "6", "Provision (condition) of under voltage protection, where specified.", isolationCurrent.getProvisionVoltage());
		addRow3(table11, "7", "Provision (condition) of overload protection, where specified.", isolationCurrent.getProvisionOverload());
		addRow3(table11, "8", "Recessed luminaires (downlighters) - Correct type of lamps fitted ", isolationCurrent.getCorrectTypeLamps());
		addRow3(table11, "9", "Recessed luminaires (downlighters) - Installed to minimise build-up of heat by use of “fire rated” fittings, insulation displacement box or similar ", isolationCurrent.getInsulaDisplacementBox());
		addRow3(table11, "10", "No signs of overheating to surrounding building fabric (applicable for periodic inspection)", isolationCurrent.getOverheatSurrounding());
		addRow3(table11, "11", "No signs of overheating to conductors / terminations (applicable for periodic inspection)", isolationCurrent.getOverheatConductors());
		document.add(table11);
	}


	private void addRow(PdfPTable table8, String string, String string2, String string3) {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3));
		
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		
		table8.addCell(nameCell);
		table8.addCell(valueCell1);
		table8.addCell(valueCell2);
	}
	
	private void addRow1(PdfPTable table9, String string, String string2, String string3) {
		PdfPCell nameCell1 = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell4 = new PdfPCell(new Paragraph(string3));
		
		nameCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		
		table9.addCell(nameCell1);
		table9.addCell(valueCell3);
		table9.addCell(valueCell4);
	}
	
	private void addRow2(PdfPTable table10, String string, String string2, String string3) {
		PdfPCell nameCell2 = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell5 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell6 = new PdfPCell(new Paragraph(string3));
		
		nameCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		
		table10.addCell(nameCell2);
		table10.addCell(valueCell5);
		table10.addCell(valueCell6);
	}
	
	private void addRow3(PdfPTable table11, String string, String string2, String string3) {
		PdfPCell nameCell3 = new PdfPCell(new Paragraph(string));
		PdfPCell valueCell7 = new PdfPCell(new Paragraph(string2));
		PdfPCell valueCell8 = new PdfPCell(new Paragraph(string3));
		
		nameCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell7.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		
		table11.addCell(nameCell3);
		table11.addCell(valueCell7);
		table11.addCell(valueCell8);
	}

}