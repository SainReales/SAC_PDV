package com.sac.spring.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sac.spring.models.entity.GlobalConfig;

public interface IGlobalConfigDao extends JpaRepository<GlobalConfig, Long> {

}
