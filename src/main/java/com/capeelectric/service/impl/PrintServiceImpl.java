package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryDeclaration;
import com.capeelectric.model.SummaryObervation;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.service.PrintService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintServiceImpl implements PrintService {

	@Autowired
	private SummaryRepository summaryRepository;

	@Override
    public void printSummary(String userName, Integer siteId) throws SummaryException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document();
			try {
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("PrintData.pdf"));
				document.open();
				//document.setMarginMirroringTopBottom(true);
				//List<Summary> s =summaryRepository.findById( 1995);
				Optional<Summary> s=summaryRepository.findById( siteId);
               Summary summary = s.get( );
				System.out.println(summary);
				List<SummaryObervation> observation1 = summary.getSummaryObervation();
				List<SummaryDeclaration> declaration1 = summary.getSummaryDeclaration();

				SummaryDeclaration declaration = declaration1.get(0);
				SummaryDeclaration declaration11 = declaration1.get(1);
			
				SummaryObervation observation = observation1.get(0);

				// document.add(new Paragraph("Extent And Limitations Of Inspection And
				// Testing"));
                 document.add(new Paragraph(
						"Extent Of Installation Covered By This Report:" + summary.getExtentInstallation()));
				document.add(
						new Paragraph("Agreed Limitations Including The Reasons:" + summary.getAgreedLimitations()));
				document.add(new Paragraph("Agreed With:" + summary.getAgreedWith()));
				document.add(new Paragraph(
						"Operational Limitations Including The Reasons:" + summary.getOperationalLimitations()));
				document.add(new Paragraph(
						"The inspection and testing detailed in this report have been carried out in accordance with IEC60364. It should be note that cables concealed within trunk/trench and conduits, under floors and generally within the fabric of the building or underground, have not been inspected unless specifically agreed between the client and inspector prior to inspection:"
								+ summary.getInspectionTestingDetailed()));
				// document.add(new Paragraph("Referring to attached inspection report and test
				// results and subject to the limitations specified at the extent and
				// limitations of inspection and testing :"+summary.get()));
				document.add(new Paragraph("Observations:" + observation.getObservations()));
				document.add(new Paragraph("Further actions:" + observation.getFurtherActions()));
				document.add(new Paragraph("Reference number in report:" + observation.getReferanceNumberReport()));
				document.add(new Paragraph("Comment:" + observation.getComment()));
				document.add(new Paragraph(
						"Where the overall assessment of the suitability of the installation for continuous use above is stated as unsatisfactory, I/We recommend that any observations classified as “danger present” (Code C1) or “potentially dangerous” (Code C2) are acted upon the matter of urgency. Investigation without delay is recommended for observations identified as “Required further investigation”. Observations classified as “Improvement recommended” (Code C3) should be given due consideration. Subject to necessary remedial action being taken, I/We recommended that the installation is further inspected and tested by …………Date.:"
								+ summary.getRecommendationsDate()));
				document.add(new Paragraph("General condition of the installation in terms of electrical safety:"
						+ summary.getGeneralConditionInstallation()));
				document.add(new Paragraph(
						"Overall assessment of the installation in terms of suitability of continuous use:"
								+ summary.getOverallAssessmentInstallation()));
				document.add(new Paragraph("Inspected and tested by:" ));
				document.add(new Paragraph("Name:" + declaration.getName()));
			document.add(new Paragraph("Signature:" + (declaration.getSignature())));
				document.add(new Paragraph("Company:" + declaration.getCompany()));
				document.add(new Paragraph("Position:" + declaration.getPosition()));
				document.add(new Paragraph("Address:" + declaration.getAddress()));
				document.add(new Paragraph("Date:" + declaration.getDate()));
				
				document.add(new Paragraph("Authorised by:" ));
				document.add(new Paragraph("Name:" + declaration11.getName()));
				document.add(new Paragraph("Signature:" + (declaration11.getSignature())));
				document.add(new Paragraph("Company:" + declaration11.getCompany()));
				document.add(new Paragraph("Position:" + declaration11.getPosition()));
				document.add(new Paragraph("Address:" + declaration11.getAddress()));
				document.add(new Paragraph("Date:" + declaration11.getDate()));

				document.close();
				writer.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new SummaryException("Invalid Inputs");
		}
	}

	
}
