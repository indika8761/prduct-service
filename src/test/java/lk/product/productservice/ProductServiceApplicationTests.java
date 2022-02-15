package lk.product.productservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lk.product.domain.dto.ItemPricesDto;
import lk.product.domain.dto.OrderItemDto;
import lk.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceApplicationTests {

	@Autowired
	ProductService productService;
	
	@Test
	public void contextLoads() {
		List<OrderItemDto> dto =new ArrayList<>();
		OrderItemDto itemDto1=new OrderItemDto();
		itemDto1.setProductId(1);
		itemDto1.setNumberOfItemOrder(new BigDecimal(20));
		
		OrderItemDto itemDto2=new OrderItemDto();
		itemDto2.setProductId(2);
		itemDto2.setNumberOfItemOrder(new BigDecimal(5));
		
		dto.add(itemDto1);
		
		dto.add(itemDto2);
		
		ItemPricesDto itemPricesDto=  productService.priceCalculate(dto);
		System.out.print(itemPricesDto.getTotalPrice());
		assertThat(itemPricesDto.getTotalPrice()).isEqualTo(new BigDecimal(1000.00).setScale(2, RoundingMode.HALF_EVEN));
		
		
		List<OrderItemDto> dto2 =new ArrayList<>();
		OrderItemDto itemDto3=new OrderItemDto();
		itemDto3.setProductId(1);
		itemDto3.setNumberOfItemOrder(new BigDecimal(21));
		
		OrderItemDto itemDto4=new OrderItemDto();
		itemDto4.setProductId(2);
		itemDto4.setNumberOfItemOrder(new BigDecimal(5));
		
		dto2.add(itemDto3);
		
		dto2.add(itemDto4);
		
		ItemPricesDto itemPricesDto2=  productService.priceCalculate(dto2);
		
		assertThat(itemPricesDto2.getTotalPrice()).isEqualTo(new BigDecimal(1011.38).setScale(2, RoundingMode.HALF_EVEN));
		
		
	}

}
