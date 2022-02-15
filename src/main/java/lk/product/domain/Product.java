package lk.product.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lk.product.domain.dto.ProductDto;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2072858953273980812L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "number_of_items_in_box", nullable = false)
	private BigDecimal numberOfItemsInBox;

	@Column(name = "number_of_items", nullable = false)
	private BigDecimal numberOfItems;

	@Column(name = "box_price", nullable = false)
	private BigDecimal boxPrice;

	public Product() {
		super();
	}

	public Product(String name, BigDecimal numberOfItemsInBox, BigDecimal numberOfItems, BigDecimal boxPrice) {
		super();
		this.name = name;
		this.numberOfItemsInBox = numberOfItemsInBox;
		this.numberOfItems = numberOfItems;
		this.boxPrice = boxPrice;
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

	public ProductDto buidDto() {

		return new ProductDto(id, name, numberOfItemsInBox, numberOfItems, boxPrice, price());
	}

	public BigDecimal price() {
		BigDecimal itemCost = boxPrice.divide(numberOfItemsInBox);
		return new BigDecimal(itemCost.multiply(new BigDecimal(1.3)).doubleValue()).setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boxPrice == null) ? 0 : boxPrice.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberOfItems == null) ? 0 : numberOfItems.hashCode());
		result = prime * result + ((numberOfItemsInBox == null) ? 0 : numberOfItemsInBox.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (boxPrice == null) {
			if (other.boxPrice != null)
				return false;
		} else if (!boxPrice.equals(other.boxPrice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfItems == null) {
			if (other.numberOfItems != null)
				return false;
		} else if (!numberOfItems.equals(other.numberOfItems))
			return false;
		if (numberOfItemsInBox == null) {
			if (other.numberOfItemsInBox != null)
				return false;
		} else if (!numberOfItemsInBox.equals(other.numberOfItemsInBox))
			return false;
		return true;
	}

	public void update(ProductDto dto) {
		this.name = dto.getName();
		this.boxPrice = dto.getBoxPrice();
		this.numberOfItems = dto.getNumberOfItems();
		this.numberOfItemsInBox = dto.getNumberOfItemsInBox();
	}

}
