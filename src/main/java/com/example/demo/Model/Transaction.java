package com.example.demo.Model;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transaction implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2801786434066331623L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    
    @Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionStatus=" + transactionStatus
				+ ", transactionAmount=" + transactionAmount + ", order=" + order + "]";
	}




	@Enumerated(EnumType.STRING)
	@Column(name = "status")
    private TransactionStatus transactionStatus;
	
    private double transactionAmount;
    


    
    public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@OneToOne(mappedBy = "transaction")
//    @JoinColumn(name = "order_id")
    private Order order;
}
