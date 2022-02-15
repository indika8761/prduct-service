package lk.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdoctViewController {

	@GetMapping("/")
	public String view() {
		return "prodoct";
	}

}
