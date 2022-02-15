package lk.product.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.product.domain.Product;
import lk.product.domain.dto.ItemPriceDto;
import lk.product.domain.dto.ItemPricesDto;
import lk.product.domain.dto.OrderItemDto;
import lk.product.domain.dto.ProductDto;
import lk.product.exception.ProductException;
import lk.product.repository.ProductRepository;
import lk.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public ProductDto create(ProductDto dto) {
		Product product = new Product(dto.getName(), dto.getNumberOfItemsInBox(), dto.getNumberOfItems(),
				dto.getBoxPrice());
		productRepository.save(product);
		return product.buidDto();
	}

	@Override
	@Transactional
	public ProductDto update(ProductDto dto) {
		Optional<Product> product = productRepository.findById(dto.getId());
		if (!product.isPresent()) {
			throw new ProductException("Invalid product Id " + dto.getId());
		}
		product.get().update(dto);
		productRepository.save(product.get());
		return product.get().buidDto();
	}

	@Override
	public List<ProductDto> getAll() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> dtos = new ArrayList<>();
		products.forEach(one -> dtos.add(one.buidDto()));
		return dtos;
	}

	@Override
	public ProductDto getById(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		return product.get().buidDto();
	}

	@Override
	public ItemPricesDto priceCalculate(List<OrderItemDto> dto) {
		ItemPricesDto itemPricesDto = new ItemPricesDto();
		List<ItemPriceDto> itemPriceDtos = new ArrayList<>();
		BigDecimal totalprice = new BigDecimal(0);
		dto.forEach(one -> {
			ItemPriceDto priceDto = new ItemPriceDto(totalprice.add(calculateProductPrice(one)), one.getProductId());
			itemPriceDtos.add(priceDto);
			itemPricesDto.setTotalPrice(itemPricesDto.getTotalPrice().add(priceDto.getPrice()).setScale(2, RoundingMode.HALF_EVEN));
		});
		itemPricesDto.setItems(itemPriceDtos);
		return itemPricesDto;
	}

	private BigDecimal calculateProductPrice(OrderItemDto one) {
		Optional<Product> product = productRepository.findById(one.getProductId());
		if (!product.isPresent()) {
			throw new ProductException("Invalid product Id " + one.getProductId());
		}

		if (one.getNumberOfItemOrder().doubleValue() > product.get().getNumberOfItems().doubleValue()) {
			throw new ProductException(
					"Number of order itmes can not be exceed " + product.get().getNumberOfItems().doubleValue());
		}

		Integer numberOfBoxOrder = (int) (one.getNumberOfItemOrder().doubleValue()
				/ product.get().getNumberOfItemsInBox().doubleValue());
		Integer numberOfItemOrder = (int) (one.getNumberOfItemOrder().doubleValue()
				% product.get().getNumberOfItemsInBox().doubleValue());

		BigDecimal price = product.get().price().multiply(new BigDecimal(numberOfItemOrder));
		price = price.add(product.get().getBoxPrice().multiply(new BigDecimal(numberOfBoxOrder)));

		if (numberOfBoxOrder >= 3) {
			price = price.subtract(product.get().getBoxPrice().multiply(new BigDecimal(0.1)));
		}
		return price;
	}

}
