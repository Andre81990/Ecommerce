package com.alibou.ecommerce.payment;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue
  private Integer id;

  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  private Integer orderId;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;


  // Classe Builder interna
  public static class PaymentBuilder {
    private Integer id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    // La chiave esterna che fa riferimento all'ordine associato.
    private Integer orderId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    // Costruttore del Builder (può essere vuoto o con parametri obbligatori)
    public PaymentBuilder() {
    }

    // Metodi per impostare ciascun attributo
    public PaymentBuilder id(Integer id) {
      this.id = id;
      return this; // Restituisce l'istanza del Builder per concatenare le chiamate
    }

    public PaymentBuilder amount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public PaymentBuilder paymentMethod(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
      return this;
    }

    public PaymentBuilder orderId(Integer orderId) {
      this.orderId = orderId;
      return this;
    }

    public PaymentBuilder createdDate(LocalDateTime createdDate) {
      this.createdDate = createdDate;
      return this;
    }

    public PaymentBuilder lastModifiedDate(LocalDateTime lastModifiedDate) {
      this.lastModifiedDate = lastModifiedDate;
      return this;
    }

    // Metodo build() per creare l'oggetto Payment finale
    public Payment build() {
      Payment payment = new Payment();
      payment.id = this.id;
      payment.amount = this.amount;
      payment.paymentMethod = this.paymentMethod;
      payment.orderId = this.orderId;
      payment.createdDate = this.createdDate;
      payment.lastModifiedDate = this.lastModifiedDate;
      return payment;
    }
  }

  // Metodo statico per ottenere un'istanza del Builder
  public static PaymentBuilder builder() {
    return new PaymentBuilder();
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
}
