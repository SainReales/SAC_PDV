package com.sac.spring.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quatity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	private Double itbis;

	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuatity() {
		return quatity;
	}

	public void setQuatity(Integer quatity) {
		this.quatity = quatity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product Product) {
		this.product = Product;
	}

	public Double calcularImporte() {
		if (getItbis() != null) {
			return quatity.doubleValue() * ((price * (getItbis() / 100.0)) + price);
		}
		return 0.0;
	}

	public Double getItbis() {
		return itbis;
	}

	public void setItbis(Double itbis) {
		this.itbis = itbis;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	private static final long serialVersionUID = 1L;
}
