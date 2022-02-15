package lk.product.service;

import java.util.List;

import lk.product.domain.dto.ItemPricesDto;
import lk.product.domain.dto.OrderItemDto;
import lk.product.domain.dto.ProductDto;

public interface ProductService {

	public ProductDto create(ProductDto dto);

	public ProductDto update(ProductDto dto);

	public List<ProductDto> getAll();

	public ProductDto getById(Integer id);

	public ItemPricesDto priceCalculate(List<OrderItemDto> dto);

}
