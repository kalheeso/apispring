package com.matheus.apispring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.apispring.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BankSlipPayment extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date deadLine;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public BankSlipPayment() {
    }

    public BankSlipPayment(Integer id, PaymentStatus status, Order order, Date deadLine, Date paymentDate) {
        super(id, status, order);
        this.deadLine = deadLine;
        this.paymentDate = paymentDate;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}