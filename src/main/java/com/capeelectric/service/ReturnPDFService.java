package com.capeelectric.service;

import java.net.MalformedURLException;
 
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class ReturnPDFService {

	 @Value("${files.path}")
	    private String filesPath;

	 public Resource printFinalPDF(String userName, Integer siteId) {
 
        try {
         
        	Resource resource = new UrlResource(filesPath);

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}