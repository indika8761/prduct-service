package lk.product.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class OrderItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3806360962864577994L;

	@NotNull
	private Integer productId;

	@NotNull
	private BigDecimal numberOfItemOrder;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public BigDecimal getNumberOfItemOrder() {
		return numberOfItemOrder;
	}

	public void setNumberOfItemOrder(BigDecimal numberOfItemOrder) {
		this.numberOfItemOrder = numberOfItemOrder;
	}

}
