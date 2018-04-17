package com.demo.shoppingcart;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.shoppingcart.model.ProductInfo;

/**
 * Runs an E2E test of the application.
 * @author 28883
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShoppingCartApplicationTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	/**
	 * Runs test to check if the data is coming with finite filter criteria
	 */
	@Test
	public void testFilterProductsWithValidProductName() {
		final String productName = "Java";
		final double price = 300;
		final String url = String.format("/filterProduct?name=%s&price=%s", productName, price);
		ParameterizedTypeReference<List<ProductInfo>>  parameterizedTypeReference = new ParameterizedTypeReference<List<ProductInfo>>() {};
		ResponseEntity<List<ProductInfo>> response = restTemplate.exchange(createURLWithPort(url), HttpMethod.GET, null, parameterizedTypeReference);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().size()).isGreaterThan(0);
		assertThat(response.getBody().get(0).getName()).contains(productName);
		assertThat(response.getBody().get(0).getPrice()).isLessThan(price);
	}
	
	/**
	 * Runs test to check if the data is not coming for invalid filter criteria
	 */
	@Test
	public void testFilterProductsWithInValidProductName() {
		final String productName = "Cobol";
		final double price = 300;
		final String url = String.format("/filterProduct?name=%s&price=%s", productName, price);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(url), HttpMethod.GET, null, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isEqualTo("No products matches the search critera.");
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
