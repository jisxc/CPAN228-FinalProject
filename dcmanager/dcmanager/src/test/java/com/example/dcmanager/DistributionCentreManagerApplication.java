package com.example.dcmanager;

import com.example.dcmanager.model.Brand;
import com.example.dcmanager.model.DistributionCentre;
import com.example.dcmanager.model.DcItem;
import com.example.dcmanager.repository.DistributionCentreRepository;
import com.example.dcmanager.repository.DcItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DistributionCentreManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DistributionCentreManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner data(DistributionCentreRepository centreRepo, DcItemRepository itemRepo) {
		return args -> {
			DistributionCentre c1 = new DistributionCentre("North Centre", 43.7000, -79.4000);
			DistributionCentre c2 = new DistributionCentre("East Centre", 43.7000, -79.3000);
			DistributionCentre c3 = new DistributionCentre("South Centre", 43.6500, -79.4000);
			DistributionCentre c4 = new DistributionCentre("West Centre", 43.7000, -79.4500);
			centreRepo.save(c1);
			centreRepo.save(c2);
			centreRepo.save(c3);
			centreRepo.save(c4);

			itemRepo.save(new DcItem("Jacket", Brand.ZARA, 2022, 1200, 10, c1));
			itemRepo.save(new DcItem("Sneakers", Brand.NIKE, 2022, 2200, 5, c2));
			itemRepo.save(new DcItem("T-shirt", Brand.ADIDAS, 2023, 1500, 20, c3));
			itemRepo.save(new DcItem("Hoodie", Brand.ZARA, 2023, 1800, 15, c4));
		};
	}
}
