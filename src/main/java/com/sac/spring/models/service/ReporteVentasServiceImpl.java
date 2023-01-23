package com.sac.spring.models.service;

import com.sac.spring.commons.JasperReportManager;
import com.sac.spring.enums.TipoReporteEnum;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
@Service
public class ReporteVentasServiceImpl implements ReporteVentasServiceAPI {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    /**
     * @param params
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws JRException
     * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
     * @date 24 sep. 2021
     * @see com.sac.spring.models.service.ReporteVentasServiceAPI#obtenerReporteProd(java.util.Map)
     */
    @Override
    public ReporteVentasDTO obtenerReporteProd(Map<String, Object> params)
            throws JRException, IOException, SQLException {
        String fileName = "mas-vendidos";
        ReporteVentasDTO dto = new ReporteVentasDTO();
        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }

}

