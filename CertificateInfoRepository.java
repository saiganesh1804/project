package com.becoder.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.becoder.entity.CertificateInfo;

import jakarta.transaction.Transactional;

public interface CertificateInfoRepository extends JpaRepository<CertificateInfo, Integer> {
	
	 	@Transactional
	    @Modifying
	    @Query("UPDATE CertificateInfo c SET c.serial_number = :serialNumber, c.expiry_date = :expiryDate WHERE c.IMEI = :imei")
	    void updateSerialNumberAndExpiryDate(int imei, String serialNumber, Date expiryDate);

}
