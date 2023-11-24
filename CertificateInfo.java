package com.becoder.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CertificateInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IMEI;

	private String serial_number;

	private Date expiry_date;

	private String status;

	private String enrolled;

	private String revoked;

	public int getIMEI() {
		return IMEI;
	}

	public void setIMEI(int IMEI) {
		this.IMEI = IMEI;
	}

	public String getSerialNumber() {
		return serial_number;
	}

	public void setSerialNumber(String serial_number) {
		this.serial_number = serial_number;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date= expiry_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(String enrolled) {
		this.enrolled= enrolled;
	}

	public String getRevoked() {
		return revoked;
	}

	public void setRevoked(String revoked) {
		this.revoked = revoked;
	}

	@Override
	public String toString() {
		return "Employee [IMEI=" + IMEI + ", serial_number=" + serial_number + ", expiry_date=" + expiry_date + ", status=" + status + ", enrolled="
				+ enrolled + ", revoked=" + revoked + "]";
	}

}
