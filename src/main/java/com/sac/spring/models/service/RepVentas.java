package com.sac.spring.models.service;

import com.sac.spring.models.dao.IClientDao;
import com.sac.spring.models.dao.IProductoDao;
import com.sac.spring.models.entity.Client;
import com.sac.spring.models.entity.Product;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RepVentas {

    @Autowired
    private DataSource dataSource;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException, SQLException {
        String path = "C:\\Users\\SONY\\Desktop";


        //load file and compile it
        File file = ResourceUtils.getFile("classpath:mas-vendidos.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("SAC", "Sistema Administrativo Condor");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile( path + "\\mas-vendidos.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile( jasperPrint,path + "\\mas-vendidos.pdf");
        }

        return "redirect:/products";
    }
}
