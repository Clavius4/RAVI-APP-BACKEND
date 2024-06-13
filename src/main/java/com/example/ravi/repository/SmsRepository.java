package com.example.ravi.repository;

import com.example.ravi.entity.SmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * SmsRepository .
 * 
 * @author admin
 *
 */
@Repository
public interface SmsRepository extends JpaRepository<SmsEntity, Integer> {

}
