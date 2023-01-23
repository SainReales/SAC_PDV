package com.sac.spring.helper;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

@Component
public class InvoiceOperations {

	public Long getTotalItbis(Double itbis, int quatity, Double price) {
		DecimalFormat df = new DecimalFormat("#.00");
		String totalItbis = df.format(quatity * (price * (itbis / 100)));
		return Long.parseLong(totalItbis);
	}

}
