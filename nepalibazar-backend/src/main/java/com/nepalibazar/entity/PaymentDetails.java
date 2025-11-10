package com.nepalibazar.entity;

import com.nepalibazar.domain.PAYMENT_STATUS;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
@Serdeable
public class PaymentDetails {

    private String paymentId;

    private String khaltiPaymentLinkId;
    private String khaltiPaymentLinkReferenceId;
    private String khaltiPaymentLinkStatus;
    private String khaltiPaymentIdZWSP;

    private String stripePaymentLinkId;
    private String stripePaymentLinkReferenceId;
    private String stripePaymentLinkStatus;
    private String stripePaymentIdZWSP;

    @Enumerated(EnumType.STRING)
    private PAYMENT_STATUS paymentStatus = PAYMENT_STATUS.PENDING;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getKhaltiPaymentLinkId() {
        return khaltiPaymentLinkId;
    }

    public void setKhaltiPaymentLinkId(String khaltiPaymentLinkId) {
        this.khaltiPaymentLinkId = khaltiPaymentLinkId;
    }

    public String getKhaltiPaymentLinkReferenceId() {
        return khaltiPaymentLinkReferenceId;
    }

    public void setKhaltiPaymentLinkReferenceId(String khaltiPaymentLinkReferenceId) {
        this.khaltiPaymentLinkReferenceId = khaltiPaymentLinkReferenceId;
    }

    public String getKhaltiPaymentLinkStatus() {
        return khaltiPaymentLinkStatus;
    }

    public void setKhaltiPaymentLinkStatus(String khaltiPaymentLinkStatus) {
        this.khaltiPaymentLinkStatus = khaltiPaymentLinkStatus;
    }

    public String getKhaltiPaymentIdZWSP() {
        return khaltiPaymentIdZWSP;
    }

    public void setKhaltiPaymentIdZWSP(String khaltiPaymentIdZWSP) {
        this.khaltiPaymentIdZWSP = khaltiPaymentIdZWSP;
    }

    public String getStripePaymentLinkId() {
        return stripePaymentLinkId;
    }

    public void setStripePaymentLinkId(String stripePaymentLinkId) {
        this.stripePaymentLinkId = stripePaymentLinkId;
    }

    public String getStripePaymentLinkReferenceId() {
        return stripePaymentLinkReferenceId;
    }

    public void setStripePaymentLinkReferenceId(String stripePaymentLinkReferenceId) {
        this.stripePaymentLinkReferenceId = stripePaymentLinkReferenceId;
    }

    public String getStripePaymentLinkStatus() {
        return stripePaymentLinkStatus;
    }

    public void setStripePaymentLinkStatus(String stripePaymentLinkStatus) {
        this.stripePaymentLinkStatus = stripePaymentLinkStatus;
    }

    public String getStripePaymentIdZWSP() {
        return stripePaymentIdZWSP;
    }

    public void setStripePaymentIdZWSP(String stripePaymentIdZWSP) {
        this.stripePaymentIdZWSP = stripePaymentIdZWSP;
    }

    public PAYMENT_STATUS getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PAYMENT_STATUS paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
