package com.example.jasperreport2;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/document")
public class JasperDemoController {

    @GetMapping()
    public void getDocument(HttpServletResponse response) throws IOException, JRException {

        File file = ResourceUtils.getFile("classpath:CustomReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBaseTextField textField = (JRBaseTextField) jasperReport.getTitle().getElementByKey("name");
//        textField.setForecolor(Color.RED);
        List<SampleBean> dataList = new ArrayList<SampleBean>();
        SampleBean sampleBean1 = new SampleBean("a","red");
        SampleBean sampleBean2 = new SampleBean("a","red");
        SampleBean sampleBean3 = new SampleBean("b","red");
        SampleBean sampleBean4 = new SampleBean("a","red");
        SampleBean sampleBean5 = new SampleBean("a","red");
        SampleBean sampleBean6= new SampleBean("a","red");
        SampleBean sampleBean7= new SampleBean("a","red");
        SampleBean sampleBean8= new SampleBean("a","red");
        SampleBean sampleBean9= new SampleBean("c","red");
        SampleBean sampleBean10=new SampleBean("a","red");



        dataList.add(sampleBean1);
        dataList.add(sampleBean2);
        dataList.add(sampleBean3);
        dataList.add(sampleBean4);
        dataList.add(sampleBean5);
        dataList.add(sampleBean6);
        dataList.add(sampleBean7);
        dataList.add(sampleBean8);
        dataList.add(sampleBean9);
        dataList.add(sampleBean10);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap();
        parameters.put("studentName", "trang");
        parameters.put("tableData", beanColDataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[] { "sheet1" });
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader("Content-Disposition", "attachment;filename=jasperReport.xlsx");
        response.setContentType("application/octet-stream");
        exporter.exportReport();
        System.out.println("ok");
    }
}