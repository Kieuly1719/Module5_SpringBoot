package com.example.blogRest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogRestApplicationTests {
	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}

	@Test
	void restEndpointsReturnData() {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + port;

		ResponseEntity<String> categories = restTemplate.getForEntity(baseUrl + "/api/categories", String.class);
		ResponseEntity<String> blogs = restTemplate.getForEntity(baseUrl + "/api/blogs", String.class);
		ResponseEntity<String> search = restTemplate.getForEntity(baseUrl + "/api/blogs?keyword=AJAX&page=0&size=20", String.class);
		ResponseEntity<String> categoryBlogs = restTemplate.getForEntity(baseUrl + "/api/categories/1/blogs", String.class);
		ResponseEntity<String> blogDetail = restTemplate.getForEntity(baseUrl + "/api/blogs/1", String.class);

		assertThat(categories.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(categories.getBody()).contains("Java");
		assertThat(blogs.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(blogs.getBody()).contains("content", "totalElements", "AJAX voi jQuery");
		assertThat(search.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(search.getBody()).contains("AJAX voi jQuery");
		assertThat(categoryBlogs.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(categoryBlogs.getBody()).contains("Lam quen voi Java");
		assertThat(blogDetail.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(blogDetail.getBody()).contains("Lam quen voi Java");
	}

	@Test
	void blogPageRendersAjaxView() {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + port;

		ResponseEntity<String> page = restTemplate.getForEntity(baseUrl + "/blogs", String.class);

		assertThat(page.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(page.getBody()).contains("Blog AJAX", "search-form", "load-more");
	}

}
