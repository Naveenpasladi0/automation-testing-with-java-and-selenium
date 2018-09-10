package com.in28minutes;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumStandAloneTest {
	
	@Test
	public void basic() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/pages/index.html");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		driver.quit();
	}
	
	@Test
	public void standalone() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capabilites = new DesiredCapabilities();
		
		//chrome, firefox, htmlunit, internet explorer, iphone, opera
		capabilites.setBrowserName("chrome");
		
		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
		WebDriver remoteDriver = new RemoteWebDriver(
				new URL("http://localhost:4444/wd/hub"), capabilites);
		
		//RemoteWebDriver
		//	Location of Standaloneserver
		//  Which Browser? Which OS? => Capabilities
		
		remoteDriver.get("http://localhost:8080/pages/index.html");
		System.out.println(remoteDriver.getCurrentUrl());
		System.out.println(remoteDriver.getTitle());
		Thread.sleep(15000);
		remoteDriver.quit();
	}
	
	//selenium-standalone start -- -role hub

	//Nodes should register to http://192.168.8.69:4444/grid/register/
	//Clients should connect to http://192.168.8.69:4444/wd/hub
	
	//selenium-standalone start -- -role node -hub http://192.168.8.69:4444/grid/register/ 
	//selenium-standalone start -- -role node -port 5556 -hub http://192.168.8.69:4444/grid/register/ 

	@Test(threadPoolSize=2, invocationCount=4)
	public void hub() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capabilites = new DesiredCapabilities();
		
		//chrome, firefox, htmlunit, internet explorer, iphone, opera
		capabilites.setBrowserName("chrome");
		
		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
		WebDriver remoteDriver = new RemoteWebDriver(
				new URL("http://localhost:4444/wd/hub"), capabilites);
		
		//RemoteWebDriver
		//	Location of Standaloneserver
		//  Which Browser? Which OS? => Capabilities
		
		remoteDriver.get("http://localhost:8080/pages/index.html");
		System.out.println(remoteDriver.getCurrentUrl());
		System.out.println(remoteDriver.getTitle());
		Thread.sleep(10000);
		remoteDriver.quit();
	}

	
}