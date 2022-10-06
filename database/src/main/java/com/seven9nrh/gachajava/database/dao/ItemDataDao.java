package com.seven9nrh.gachajava.database.dao;

import com.seven9nrh.gachajava.database.entity.ItemDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDataDao extends JpaRepository<ItemDataEntity, String> {}
