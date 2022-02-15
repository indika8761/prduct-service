package lk.product.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemPriceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1021540813285276577L;

	private BigDecimal price;

	private Integer productId;

	public ItemPriceDto(BigDecimal price, Integer productId) {
		super();
		this.price = price;
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
