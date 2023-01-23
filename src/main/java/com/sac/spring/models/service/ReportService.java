package com.sac.spring.models.service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sac.spring.models.dao.IProductoDao;
import com.sac.spring.models.entity.Product;
@Service
public class ReportService {

    @Autowired
    private IProductoDao repository;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Desktop";
        List<Product> product = repository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ReporteInventario.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(product);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("SAC", "Sistema Administrativo Condor");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\Inventario.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Inventario.pdf");
        }

        return "redirect:/products";
    }
}
