/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.franciscoalecrim;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentConverter {
    
    private final String replaceContent;
    
    public DocumentConverter(String replaceContent){
        this.replaceContent = replaceContent;
    }
        
    public void doConvert() throws FileNotFoundException, IOException, XDocReportException{
                // 1) Load ODT file and set Velocity template engine and cache it to the registry           
        InputStream in = new FileInputStream(new File("document.odt"));
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

// 2) Create Java model context 
        IContext context = report.createContext();
        context.put("name", replaceContent);

// 3) Set PDF as format converter
        Options options = Options.getTo(ConverterTypeTo.PDF);

// 3) Generate report by merging Java model with the ODT and convert it to PDF
        OutputStream out = new FileOutputStream(new File("document.pdf"));
        report.convert(context, options, out);
    }

}
