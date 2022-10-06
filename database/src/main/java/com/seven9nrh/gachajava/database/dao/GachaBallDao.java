package com.seven9nrh.gachajava.database.dao;

import com.seven9nrh.gachajava.database.entity.GachaBallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GachaBallDao extends JpaRepository<GachaBallEntity, String> {}
