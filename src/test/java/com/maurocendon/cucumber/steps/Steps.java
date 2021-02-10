package com.maurocendon.cucumber.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.maurocendon.cucumber.util.Util;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
	private WebDriver driver;
	private Util util = new Util();

	@Before
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Given("User is in the login form")
	public void user_is_in_the_login_form() {
		driver.get("https://login-app-1ff05.web.app/login");
	}

	@When("User complete the form with valid credentials")
	public void user_complete_the_form_with_valid_credentials(DataTable dataTable) {
		List<String> data = dataTable.asList();
		user_complete_the_form_with_valid_username_and_password(data.get(0), data.get(1), data.get(2));
	}

	@When("User complete the form with valid {string} and {string} with {string}")
	public void user_complete_the_form_with_valid_username_and_password(String username, String password,
			String displayName) {
		driver.findElement(By.cssSelector("app-login input.form-control[placeholder='Email']")).sendKeys(username);
		driver.findElement(By.cssSelector("app-login input.form-control[type='password']")).sendKeys(password);
		util.setDisplayName(displayName);
	}

	@When("User submit the form")
	public void user_submit_the_form() {
		driver.findElement(By.cssSelector("app-login form")).submit();
	}

	@When("User click the submit button")
	public void user_click_the_submit_button() {
		driver.findElement(By.cssSelector("app-login button.btn[type='submit']")).click();
	}

	@Then("User should see the welcome page")
	public void user_should_see_the_welcome_page() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-welcome app-button[text='Logout']")));

		assertTrue(driver.findElement(By.cssSelector("app-welcome h1")).isDisplayed());
		assertTrue(driver.findElement(By.cssSelector("app-welcome small")).isDisplayed());
		assertTrue(driver.findElement(By.cssSelector("app-welcome app-counter")).isDisplayed());
		assertTrue(driver.findElement(By.cssSelector("app-welcome app-button[text='Logout']")).isDisplayed());
	}
	
	@Then("User should see the user disabled message")
	public void user_should_the_user_disabled_message() throws InterruptedException {
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.cssSelector("#swal2-title")).getText().equals("User disabled"));
	}

	@And("User display name should be in the page title")
	public void user_display_name_should_be_in_the_page_title() {
		assertTrue(driver.findElement(By.cssSelector("app-welcome h1")).getText().contains(util.getDisplayName()));
	}

	@After
	public void closeDriver() {
		driver.close();
	}
}
