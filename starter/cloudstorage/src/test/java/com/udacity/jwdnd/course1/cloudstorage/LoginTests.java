package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = CloudStorageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private LoginPage login;
	private SignupPage signup;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		login 		= new LoginPage(driver);
		signup 		= new SignupPage(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null)
			driver.quit();
	}

	private boolean tryLogin(String username, String password) {
		driver.get("http://localhost:" + this.port + "/login");

		login.setUsername(username);
		login.setPassword(password);
		login.submit();

		return login.success();
	}

	private boolean tryCreateUser(String firstName, String lastName, String username, String password) {
		driver.get("http://localhost:" + this.port + "/signup");

		signup.setUsername(username);
		signup.setPassword(password);
		signup.setLastName(firstName);
		signup.setFirstName(lastName);
		signup.submit();

		return signup.success();
	}

	@Test
	public void nonexistentUserLoginFailure() {
		Assertions.assertFalse(tryLogin("harry.potter", "gryffindor"));
	}

	@Test
	public void loginWithSuccess() {
		Assertions.assertTrue(
			tryCreateUser(
			"Harry",
			"Potter",
			"harry.potter",
			"gryffindor"
			)
		);

		Assertions.assertTrue(tryLogin("harry.potter", "gryffindor"));
		Assertions.assertEquals("Home", driver.getTitle());
	}

}
