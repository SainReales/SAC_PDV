package com.sac.spring.models.service;

import com.sac.spring.models.entity.GlobalConfig;

public interface IGlobalConfigService {

	GlobalConfig properties();

	void saveConfig(GlobalConfig config);
}
