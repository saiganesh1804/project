package com.becoder.service;

import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import com.becoder.entity.CertificateInfo;
import com.becoder.repository.CertificateInfoRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public abstract class CertificateInfoServiceImpl implements CertificateInfoService {

	@Autowired
	private CertificateInfoRepository cerRepo;
	
	@Override
    public CertificateInfo extractAndUpdateCertificateDetails(String certificateFilePath) {
        try {
        	certificateFilePath = "C://Project//sample.cer";

            // Load the certificate from the file
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            FileInputStream fis = new FileInputStream(certificateFilePath);
            X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(fis);

            // Extract Serial Number and Expiry Date from the certificate
            String serial_number = certificate.getSerialNumber().toString();
            Date expiry_date = certificate.getNotAfter();

            CertificateInfo cer = new CertificateInfo();
            cer.setSerialNumber(serial_number);
            cer.setExpiry_date(expiry_date);

            // Update the database with these details
            return cerRepo.save(cer);
        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
    }
	
	@Override
    public void updateSerialNumberAndExpiryDate(int imei, String serialNumber, Date expiryDate) {
        cerRepo.updateSerialNumberAndExpiryDate(imei, serialNumber, expiryDate);
    }

	@Override
	public CertificateInfo saveCer(CertificateInfo cer) {
		CertificateInfo newCer = cerRepo.save(cer);
		return newCer;
	}

	@Override
	public List<CertificateInfo> getAllCer() {

		return cerRepo.findAll();
	}

	@Override
	public CertificateInfo getCerByIMEI(int IMEI) {
		return cerRepo.findById(IMEI).get();
	}

	@Override
	public boolean deleteCer(int IMEI) {
		CertificateInfo cer = cerRepo.findById(IMEI).get();
		if (cer != null) {
			cerRepo.delete(cer);
			return true;
		}
		return false;
	}

	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

	@Override
	public CertificateInfo saveOrUpdateCer(CertificateInfo cer) {
		// TODO Auto-generated method stub
		return null;
	}

}
