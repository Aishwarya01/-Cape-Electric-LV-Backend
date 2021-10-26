package com.capeelectric.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.util.HeaderFooterPageEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintFinalPDFServiceImpl implements PrintFinalPDFService {

	@Autowired
	private AWSS3ServiceImpl awsS3ServiceImpl;

	@Value("${s3.bucket.name}")
	private String s3BucketName;
	
	@Value("${access.key.id}")
	private String accessKeyId;

	@Value("${access.key.secret}")
	private String accessKeySecret;

	@Override
	public void printFinalPDF(String userName, Integer siteId) throws Exception {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			try {
				List<InputStream> inputPdfList = new ArrayList<InputStream>();
				
				inputPdfList.add(new FileInputStream("PrintInstalReportData.pdf"));
				inputPdfList.add(new FileInputStream("SupplyCharacteristic.pdf"));
				inputPdfList.add(new FileInputStream("PrintInspectionDetailsData.pdf"));
				inputPdfList.add(new FileInputStream("Testing.pdf"));
				inputPdfList.add(new FileInputStream("Summary.pdf"));

				OutputStream outputStream = new FileOutputStream("finalreport.pdf");
				mergePdfFiles(inputPdfList, outputStream, awsS3ServiceImpl);

				try {
//					SECTION 1 : Create a S3 client with in-program credential
					BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, accessKeySecret);
					AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
							.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

//					SECTION 2: Put file in S3 bucket
					String folderName = "pdffiles";
					String fileNameInS3 = "finalreport.pdf";
					String fileNameInLocalPC = "finalreport.pdf";
//
					PutObjectRequest request = new PutObjectRequest(s3BucketName, folderName + "/" + fileNameInS3,
							new File(fileNameInLocalPC));
					s3Client.putObject(request);
					System.out.println("Uploading file done in AWS s3 ");
					
//				    5 seconds of time for executing File Upload And Download in AWS s3 bucket
					Thread.sleep(5000);
					
//					SECTION 3: Get file from S3 bucket
					S3Object fullObject;
					fullObject = s3Client
							.getObject(new GetObjectRequest(s3BucketName, folderName + "/" + fileNameInS3));
					System.out.println("Downloading file done from AWS s3");
					// Print file content line by line
					InputStream is = fullObject.getObjectContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					String line;
					System.out.println("--File content:");
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}

				} catch (AmazonS3Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("Invalid Inputs");
		}
	}

	private static void mergePdfFiles(List<InputStream> inputPdfList, OutputStream outputStream,
			AWSS3ServiceImpl awss3ServiceImpl) throws Exception {
		Document document = new Document();
		List<PdfReader> readers = new ArrayList<PdfReader>();
		int totalPages = 0;
		Iterator<InputStream> pdfIterator = inputPdfList.iterator();
		while (pdfIterator.hasNext()) {
			InputStream pdf = pdfIterator.next();
			PdfReader pdfReader = new PdfReader(pdf);
			readers.add(pdfReader);
			totalPages = totalPages + pdfReader.getNumberOfPages();
		}
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		Image image = Image.getInstance(awss3ServiceImpl.findByName("rush-logo.png"));
		image.scaleToFit(185, 185);
		image.setAbsolutePosition(-3, -9);
		
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent((PdfPageEvent) event);
		document.open();
		PdfContentByte pageContentByte = writer.getDirectContent();
		PdfImportedPage pdfImportedPage;
		int currentPdfReaderPage = 1;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
				document.newPage();
				document.add(image);
				pdfImportedPage = writer.getImportedPage(pdfReader, currentPdfReaderPage);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);
				currentPdfReaderPage++;
			}
			currentPdfReaderPage = 1;
		}
		outputStream.flush();
		document.close();
		outputStream.close();
	}
}