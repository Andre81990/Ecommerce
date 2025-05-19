package com.alibou.ecommerce.order;

import com.alibou.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true, nullable = false)
  private String reference;

  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  private String customerId;

  @OneToMany(mappedBy = "order")
  private List<OrderLine> orderLines;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;

  // Costruttore vuoto (necessario per JPA)


  // Getter e Setter

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public List<OrderLine> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(List<OrderLine> orderLines) {
    this.orderLines = orderLines;
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




  public static class Builder {
    private Integer id;

    private String reference;
    private PaymentMethod paymentMethod;
    private String customerId;

    public Builder id(Integer id) {
      this.id = id;
      return this;
    }


    public Builder reference(String reference) {
      this.reference = reference;
      return this;
    }

    public Builder paymentMethod(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
      return this;
    }

    public Builder customerId(String customerId) {
      this.customerId = customerId;
      return this;
    }


    public Order build() {
      Order order = new Order();
      order.setId(this.id);
      order.setReference(this.reference);
      order.setPaymentMethod(this.paymentMethod);
      order.setCustomerId(this.customerId);
      return order;
    }


  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Order order)) return false;
    return Objects.equals(getId(), order.getId()) && Objects.equals(getReference(), order.getReference()) && Objects.equals(getTotalAmount(), order.getTotalAmount()) && getPaymentMethod() == order.getPaymentMethod() && Objects.equals(getCustomerId(), order.getCustomerId()) && Objects.equals(getOrderLines(), order.getOrderLines()) && Objects.equals(getCreatedDate(), order.getCreatedDate()) && Objects.equals(getLastModifiedDate(), order.getLastModifiedDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getReference(), getTotalAmount(), getPaymentMethod(), getCustomerId(), getOrderLines(), getCreatedDate(), getLastModifiedDate());
  }

  @Override
  public String toString() {
    return "Order{" +
            "id=" + id +
            ", reference='" + reference + '\'' +
            ", totalAmount=" + totalAmount +
            ", paymentMethod=" + paymentMethod +
            ", customerId='" + customerId + '\'' +
            ", orderLines=" + orderLines +
            ", createdDate=" + createdDate +
            ", lastModifiedDate=" + lastModifiedDate +
            '}';
  }
}
