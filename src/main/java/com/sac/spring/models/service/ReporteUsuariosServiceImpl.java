package com.sac.spring.models.service;

import com.sac.spring.enums.TipoReporteEnum;
import com.sac.spring.models.service.ReporteUsuariosDTO;
import com.sac.spring.models.service.ReporteUsuariosServiceAPI;
import com.sac.spring.commons.JasperReportManager;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
 * @project demo-spring-boot-jasper
 * @class ReporteVentasServiceImpl
 * @description
 * @HU_CU_REQ
 * @date 24 sep. 2021
 */
@Service
public class ReporteUsuariosServiceImpl implements ReporteUsuariosServiceAPI {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    /**
     * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
     * @date 24 sep. 2021
     * @param params
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws JRException
     * @see com.sac.spring.models.service.ReporteUsuariosServiceAPI#obtenerReporteVentas(java.util.Map)
     */
    @Override
    public ReporteUsuariosDTO obtenerReporteVentas(Map<String, Object> params)
            throws JRException, IOException, SQLException {
        String fileName = "vendedores";
        ReporteUsuariosDTO dto = new ReporteUsuariosDTO();
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
