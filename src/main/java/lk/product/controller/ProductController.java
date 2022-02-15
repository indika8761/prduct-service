package lk.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.product.domain.dto.ItemPricesDto;
import lk.product.domain.dto.OrderItemDto;
import lk.product.domain.dto.ProductDto;
import lk.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PutMapping
	public ProductDto create(@RequestBody @Valid ProductDto dto) {
		return productService.create(dto);
	}

	@PostMapping
	public ProductDto update(@RequestBody @Valid ProductDto dto) {
		return productService.update(dto);
	}

	@GetMapping
	public List<ProductDto> getAll() {
		return productService.getAll();
	}

	@GetMapping(path = "/{id}")
	public ProductDto getById(@PathVariable Integer id) {
		return productService.getById(id);
	}

	@PostMapping("price/calculate")
	public ItemPricesDto priceCalculate(@RequestBody @Valid List<OrderItemDto> items) {
		return productService.priceCalculate(items);
	}

}
