package com.springboot.springboot_jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.springboot.springboot_jpa.entity.User;
import com.springboot.springboot_jpa.dao.UserRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringbootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
		// ApplicationContext context =
		// SpringApplication.run(SpringbootJpaApplication.class, args);
		// UserRepository userRepository = context.getBean(UserRepository.class);

		// User user1 = new User();
		// user1.setId(1);
		// user1.setName("Pooja Tikhe");
		// user1.setCity("Punawale, Pune");
		// user1.setStatus("Working Professional");

		// // Creating single user - save()
		// userRepository.save(user1);

		// User user2 = new User();
		// user2.setId(2);
		// user2.setName("Aarti Tikhe");
		// user2.setCity("Gahunje Pune");
		// user2.setStatus("Looking for new job");

		// User user3 = new User(3, "Sushant Tikhe", "MCA stadium", "Job in hinjewadi");

		// Create multiple user - saveAll()
		// ArrayList<User> newUsers = new ArrayList<User>();
		// newUsers.add(user2);
		// newUsers.add(user3);

		// Iterable<User> usersListIterable = userRepository.saveAll(newUsers);
		// System.out.println("Printing saved users list by saveAll()");
		// usersListIterable.forEach(user -> System.out.println(user));

		// Getting all users - FindAll;
		// Iterable<User> allUsersList = userRepository.findAll();
		// System.out.println("Printing all users list by findAll()");
		// allUsersList.forEach(user -> System.out.println(user));

		// Updating data
		// Optional<User> optional = userRepository.findById(302);
		// User fetchedUser1 = optional.get();
		// fetchedUser1.setStatus("Software Engineer");
		// System.out.println("Printing Updated user data");
		// System.out.println(userRepository.save(fetchedUser1));

		// delete
		// userRepository.deleteById(302);

		// custom finder methods OR derived query methods
		// System.out.println("Printing custom finder methods output");
		// System.out.println(userRepository.findByName("Aarti Tikhe"));
		// System.out.println(userRepository.findByNameAndCity("Aarti Tikhe",
		// "Punawale"));

		// JPQL
		// List<User> allUsersList = userRepository.getAllUsers();
		// allUsersList.forEach(user -> System.out.println(user));

		// List<String> userNames = userRepository.getAllUsersName();
		// userNames.forEach(name -> System.out.println(name));

		// System.out.println("--------------------------------------------------------------------------------");
		// List<User> userNames = userRepository.getUserDataByName("Sushant Tikhe", "MCA
		// stadium");
		// userNames.forEach(name -> System.out.println(name));
		// System.out.println("--------------------------------------------------------------------------------");

		// List<User> allUsersByNativeQuery = userRepository.getAllUsersByNativeQuery();
		// allUsersByNativeQuery.forEach(user -> System.out.println(user));

	}

}
