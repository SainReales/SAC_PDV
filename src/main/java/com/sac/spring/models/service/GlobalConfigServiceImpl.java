package com.sac.spring.models.service;

import com.sac.spring.models.dao.IGlobalConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sac.spring.models.entity.GlobalConfig;

@Service
public class GlobalConfigServiceImpl implements IGlobalConfigService {

	@Autowired
	private IGlobalConfigDao globalConfigDao;

	@Override
	public GlobalConfig properties() {
		return globalConfigDao.findAll().get(0);
	}

	@Override
	public void saveConfig(GlobalConfig config) {
		globalConfigDao.save(config);
	}

}
