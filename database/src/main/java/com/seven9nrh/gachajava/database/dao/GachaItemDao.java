package com.seven9nrh.gachajava.database.dao;

import com.seven9nrh.gachajava.database.entity.GachaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GachaItemDao extends JpaRepository<GachaItemEntity, String> {}
