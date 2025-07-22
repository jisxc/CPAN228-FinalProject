package com.example.Assignment1;

import com.example.Assignment1.model.Brand;
import com.example.Assignment1.model.Item;
import com.example.Assignment1.model.Role;
import com.example.Assignment1.model.User;
import com.example.Assignment1.repository.ItemRepository;
import com.example.Assignment1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);
	}

	@Bean
	public CommandLineRunner data(ItemRepository ir, UserRepository ur, PasswordEncoder enc) {
		return args -> {
			ir.save(new Item("Jacket", Brand.ZARA, 2022, 1200));
			ir.save(new Item("Sneakers", Brand.NIKE, 2022, 2200));
			ir.save(new Item("T-shirt", Brand.ADIDAS, 2023, 1500));
			ir.save(new Item("Hoodie", Brand.ZARA, 2023, 1800));
			ir.save(new Item("Shorts", Brand.NIKE, 2022, 1300));

			if (ur.findByUsername("admin") == null) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(enc.encode("adminpass"));
				admin.setRole(Role.ADMIN);
				ur.save(admin);
			}

			if (ur.findByUsername("employee") == null) {
				User emp = new User();
				emp.setUsername("employee");
				emp.setPassword(enc.encode("employeepass"));
				emp.setRole(Role.EMPLOYEE);
				ur.save(emp);
			}

			if (ur.findByUsername("user") == null) {
				User user = new User();
				user.setUsername("user");
				user.setPassword(enc.encode("userpass"));
				user.setRole(Role.USER);
				ur.save(user);
			}
		};
	}
}