package com.visionary.crofting;

import com.visionary.crofting.entity.Client;
import com.visionary.crofting.entity.Supplier;
import com.visionary.crofting.repository.ClientRepository;
import com.visionary.crofting.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class VisionaryCroftingApplication implements CommandLineRunner {
	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	ClientRepository clientRepository;
	public static void main(String[] args) {
		SpringApplication.run(VisionaryCroftingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
