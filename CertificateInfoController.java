package com.becoder.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.becoder.entity.CertificateInfo;
import com.becoder.service.CertificateInfoService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpSession;

@Controller
public class CertificateInfoController {

	@Autowired
	private CertificateInfoService cerService;

	@GetMapping("/")
	public String index(Model m) {
		List<CertificateInfo> list = cerService.getAllCer();
		m.addAttribute("cerList", list);
		return "index";
	}

	@GetMapping("/loadCerSave")
	public String loadCerSave() {
		return "cer_save";
	}

	@GetMapping("/EditCer/{IMEI}")
	public String EditCer(@PathVariable int IMEI, Model m) {
		// System.out.println(id);
		CertificateInfo cer = cerService.getCerByIMEI(IMEI);
		m.addAttribute("cer", cer);
		return "edit_cer";
	}

	@PostMapping("/saveCer")
	public String saveCer(@ModelAttribute CertificateInfo cer, HttpSession session) {
		// System.out.println(cer);

		CertificateInfo newCer = cerService.saveCer(cer);

		if (newCer != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Register sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/loadCerSave";
	}

	@PostMapping("/updateCerDtls")
	public String updateCer(@ModelAttribute CertificateInfo cer, HttpSession session) {
		// System.out.println(cer);

		CertificateInfo updateCer = cerService.saveCer(cer);

		if (updateCer != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Update sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/";
	}

	@GetMapping("/deleteCer/{IMEI}")
	public String loadCerSave(@PathVariable int IMEI, HttpSession session) {
		boolean f = cerService.deleteCer(IMEI);
		if (f) {
			session.setAttribute("msg", "Delete sucessfully");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}
	
	@PostMapping("/extractAndUpdate")
    public String extractAndUpdateCertificate(@RequestParam("certificateFilePath") String certificateFilePath, HttpSession session) {
        CertificateInfo updatedCer = cerService.extractAndUpdateCertificateDetails(certificateFilePath);

        if (updatedCer != null) {
            session.setAttribute("msg", "Certificate details updated successfully");
        } else {
            session.setAttribute("msg", "Something went wrong while updating certificate details");
        }

        return "redirect:/"; // Redirect to an appropriate page after updating
    }


}
