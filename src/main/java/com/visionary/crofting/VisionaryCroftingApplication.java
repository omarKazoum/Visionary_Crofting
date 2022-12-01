package com.visionary.crofting;

import com.visionary.crofting.repository.ClientRepository;
import com.visionary.crofting.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VisionaryCroftingApplication  {
	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	ClientRepository clientRepository;
	public static void main(String[] args) {
		SpringApplication.run(VisionaryCroftingApplication.class, args);
	}
}
