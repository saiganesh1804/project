package com.becoder.service;

import java.util.Date;
import java.util.List;

import com.becoder.entity.CertificateInfo;

public interface CertificateInfoService {

	public CertificateInfo saveCer(CertificateInfo cer);

	public List<CertificateInfo> getAllCer();

	public CertificateInfo getCerByIMEI(int IMEI);

	public boolean deleteCer(int IMEI);

	public CertificateInfo saveOrUpdateCer(CertificateInfo cer);

	CertificateInfo extractAndUpdateCertificateDetails(String certificateFilePath);

	void updateSerialNumberAndExpiryDate(int imei, String serialNumber, Date expiryDate);

}
