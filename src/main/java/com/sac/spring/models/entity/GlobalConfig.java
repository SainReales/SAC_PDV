package com.sac.spring.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "global_config")
public class GlobalConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_phone")
	private String companyPhone;

	@Column(name = "company_email")
	private String companyEmail;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "company_nit")
	private String companyNit;

	@Column(name = "invoice_footer")
	private String invoiceFooter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyNit() {
		return companyNit;
	}

	public void setCompanyNit(String companyNit) {
		this.companyNit = companyNit;
	}

	public String getInvoiceFooter() {
		return invoiceFooter;
	}

	public void setInvoiceFooter(String invoiceFooter) {
		this.invoiceFooter = invoiceFooter;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

}
