package lk.product.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8268394676359482774L;

	private Integer id;

	@NotNull
	@NotBlank
	@NotEmpty
	private String name;

	@NotNull
	private BigDecimal numberOfItemsInBox;

	@NotNull
	private BigDecimal numberOfItems;

	@NotNull
	private BigDecimal boxPrice;

	private BigDecimal ItemPrice;

	public ProductDto(Integer id, String name, BigDecimal numberOfItemsInBox, BigDecimal numberOfItems,
			BigDecimal boxPrice, BigDecimal itemPrice) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfItemsInBox = numberOfItemsInBox;
		this.numberOfItems = numberOfItems;
		this.boxPrice = boxPrice;
		ItemPrice = itemPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNumberOfItemsInBox() {
		return numberOfItemsInBox;
	}

	public void setNumberOfItemsInBox(BigDecimal numberOfItemsInBox) {
		this.numberOfItemsInBox = numberOfItemsInBox;
	}

	public BigDecimal getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(BigDecimal numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public BigDecimal getBoxPrice() {
		return boxPrice;
	}

	public void setBoxPrice(BigDecimal boxPrice) {
		this.boxPrice = boxPrice;
	}

	public BigDecimal getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		ItemPrice = itemPrice;
	}

}
