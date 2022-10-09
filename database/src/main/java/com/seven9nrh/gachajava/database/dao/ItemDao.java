package com.seven9nrh.gachajava.database.dao;

import com.seven9nrh.gachajava.database.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, String> {}
