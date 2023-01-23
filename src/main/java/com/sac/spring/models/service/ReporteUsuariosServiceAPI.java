package com.sac.spring.models.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.sac.spring.models.service.ReporteUsuariosDTO;

import net.sf.jasperreports.engine.JRException;

public interface ReporteUsuariosServiceAPI {

    /**
     * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
     * @date 24 sep. 2021
     * @description
     * @HU_CU_REQ
     * @param params
     * @return
     */
    ReporteUsuariosDTO obtenerReporteVentas(Map<String, Object> params) throws JRException, IOException, SQLException;

}
