package com.sac.spring.models.dao;

import com.sac.spring.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICsvDao extends JpaRepository<Client, Long>{


}