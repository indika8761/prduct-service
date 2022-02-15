package lk.product.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ItemPricesDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7728421511512785770L;

	private BigDecimal totalPrice = new BigDecimal(0);

	private List<ItemPriceDto> items;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ItemPriceDto> getItems() {
		return items;
	}

	public void setItems(List<ItemPriceDto> items) {
		this.items = items;
	}

}
