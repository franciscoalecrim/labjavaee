package com.franciscoalecrim;

import fr.opensagres.xdocreport.core.XDocReportException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    static Log log = LogFactory.getLog(OdttopdfApplication.class.getName());
    // Access http://localhost:8080/document.pdf?replace=joao
    @RequestMapping(value = "/document.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> downloadPDFFile(
            @RequestParam(value = "replace", defaultValue = "alecrim") String replaceContent
    )
            throws IOException {

        try {
            new DocumentConverter(replaceContent).doConvert();
        } catch (IOException | XDocReportException e) {
            log.info(e);
        }
        
        

        File pdfFile = new File("document.pdf");
        FileInputStream input = new FileInputStream(pdfFile);
        

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(pdfFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(input));
    }
}
