package com.maxima.teste_jr;

import com.maxima.teste_jr.model.User;
import com.maxima.teste_jr.service.UserService;
import com.maxima.teste_jr.service.serviceImpl.UserServiceImpl;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;


class TesteJrApplicationTests {

//	private final UserService userService = new UserServiceImpl();
//
//	private final String testName = "José";
//	private final int testAge = 20;
//	private final String testCpf = "866.315.050-46";
//
//
//	@Test
//	public void dropUsersTable() {
//		try {
//			userService.dropUsersTable();
//			userService.dropUsersTable();
//		} catch (Exception e) {
//			Assert.fail("An exception occurred while testing drop table\n" + e);
//		}
//	}
//
//	@Test
//	public void createUsersTable() {
//		try {
//			userService.dropUsersTable();
//			userService.createUsersTable();
//		} catch (Exception e) {
//			Assert.fail("An exception occurred while testing to create a user table\n" + e.getMessage());
//		}
//	}
//
//	@Test
//	public void saveUser() {
//		try {
//			userService.dropUsersTable();
//			userService.createUsersTable();
//			userService.saveUser(testName, testAge, testCpf);
//
//			User user = userService.getAllUsers().getFirst();
//
//			if (!testName.equals(user.getNome())
//					|| testAge != user.getIdade()
//					|| !testCpf.equals(user.getCpf())
//			) {
//				Assert.fail("User was incorrectly added to the database");
//			}
//
//		} catch (Exception e) {
//			Assert.fail("An exception occurred while testing user save\n" + e);
//		}
//	}
//
//	@Test
//	public void removeUserById() {
//		try {
//			userService.dropUsersTable();
//			userService.createUsersTable();
//			userService.saveUser(testName, testAge, testCpf);
//			userService.removeUserById(1L);
//		} catch (Exception e) {
//			Assert.fail("An exception occurred while testing deleting a user by id\n" + e);
//		}
//	}
//
//	@Test
//	public void getAllUsers() {
//		try {
//			userService.dropUsersTable();
//			userService.createUsersTable();
//			userService.saveUser(testName, testAge, testCpf);
//			List<User> userList = userService.getAllUsers();
//
//			if (userList.size() != 1) {
//				Assert.fail("Check if the save/delete or create table method works correctly");
//			}
//		} catch (Exception e) {
//			Assert.fail("An exception occurred while trying to get all users from the database\n" + e);
//		}
//	}
//
//	@Test
//	public void cleanUsersTable() {
//		try {
//			userService.dropUsersTable();
//			userService.createUsersTable();
//			userService.saveUser(testName, testAge, testCpf);
//			userService.cleanUsersTable();
//
//			if (!userService.getAllUsers().isEmpty()) {
//				Assert.fail("The method of clearing the user table is implemented incorrectly");
//			}
//		} catch (Exception e) {
//			Assert.fail("An exception occurred while testing clearing the users table\n" + e);
//		}
//	}

}
