package com.example.demo; 
  
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.AlbumController; 
 
 
@SpringBootTest(classes = DemoApplication.class) 
class DemoApplicationTests {
 

	@Autowired
	private AlbumController albumController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(albumController).isNotNull();
	}

}
