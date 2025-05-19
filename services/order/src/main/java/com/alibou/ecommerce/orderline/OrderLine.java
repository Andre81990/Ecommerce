package com.alibou.ecommerce.orderline;


import com.alibou.ecommerce.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "customer_line")
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;

    public OrderLine() {
    }

    public OrderLine(Integer id, Order order, Integer productId, double quantity) {
        this.id = id;
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
    }

    private OrderLine(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
    }

    // Classe Builder statica
    public static class Builder {
        private Integer id;
        private Order order;
        private Integer productId;
        private double quantity;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderLine build() {
            return new OrderLine(this);
        }
    }

    // Metodo statico per ottenere un'istanza del Builder
    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
