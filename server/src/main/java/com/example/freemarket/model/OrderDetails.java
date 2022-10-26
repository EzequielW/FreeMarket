package com.example.freemarket.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@Data
public class OrderDetails {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotNull
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_details_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<OrderItem> orderItems;

    // Set to pending before passing checkout
    // an user can only have 1 order pending at a time
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="paymentStatus")
    private EnumPaymentStatus paymentStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

    public OrderDetails(BigDecimal total, User user, List<OrderItem> orderItems){
        this.total = total;
        this.user = user;
        this.orderItems = orderItems;
    }
}
