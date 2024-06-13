package com.example.ravi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BootPropertyConfig {

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	RestTemplate restTemplate;

	// You can now use the autowired RestTemplate in your configuration or methods
}
