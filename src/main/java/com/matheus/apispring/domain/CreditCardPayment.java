package com.matheus.apispring.domain;

import com.matheus.apispring.domain.enums.PaymentStatus;

import javax.persistence.Entity;

@Entity
public class CreditCardPayment extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer installments;

    public CreditCardPayment() {
    }

    public CreditCardPayment(Integer id, PaymentStatus status, Order order, Integer installments) {
        super(id, status, order);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}