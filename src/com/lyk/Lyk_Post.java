package com.lyk;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Lyk_Post {
	
	


		public static int i;
		public static WebDriver driver;
		public static ChromeOptions options;
		public static String url = "https://www.lykapp.com";
		
		public static void launchApp() {
			System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
			options = new ChromeOptions();
			options.addArguments("window-size=1400,800");
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(url);
		}
		
		public static void userLogin() {
			driver.findElement(By.id("InputEmail")).sendKeys("8777083284");	//9123965668
			driver.findElement(By.id("InputPassword")).sendKeys("1234");
			driver.findElement(By.id("submitBtn")).click();
			System.out.println("login succesfully");
		}
		
		public static void createPost() throws IOException, InterruptedException {
			// Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("createbtn")).click();
			driver.findElement(By.xpath("//a[contains(text(),'New Post ')]")).click();
			// Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@class='ng-tns-c7-2 mat-button mat-warn']")).click();
			
			Runtime.getRuntime().exec("C:\\Users\\Admin\\Desktop\\Auto_IT\\Post.exe");
			
			Thread.sleep(6000);
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@class='float-right']")).click();
			System.out.println("post succesfully");
		}
		
		public static void closeApp() throws InterruptedException {
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.id("dropdownBasic1")).click();
			driver.findElement(By.xpath("//*[text()=' Sign Out']")).click();
			driver.findElement(By.xpath("//button[@class='btn btn-lg btn-light']")).click();
			System.out.println("logout succesfully");
		}
		
		public static void URLTest(String linkUrl) {
			try {
				URL url = new URL(linkUrl);
				HttpURLConnection huc = (HttpURLConnection) url.openConnection();
				huc.setConnectTimeout(3000);
				huc.connect();
				if (huc.getResponseCode() == 200) {
					System.out.println(i + " - " + linkUrl + " - " + huc.getResponseMessage());
				} else if (huc.getResponseCode() != 200) {
					System.out.println(i + " - " + linkUrl + " - " + huc.getResponseCode() + " - "
							+ huc.getResponseMessage() + huc.HTTP_NOT_FOUND);
			}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public static void main(String[] args) throws IOException, InterruptedException {
			Lyk_Post.launchApp();
			Lyk_Post.userLogin();
			Lyk_Post.createPost();
			Lyk_Post.closeApp();
			Lyk_Post.URLTest(url);
			
			String currentUrl = driver.getCurrentUrl();
			//	Thread.sleep(6000);
			System.out.println("URL: " + currentUrl);
		
			if (currentUrl.equals("https://www.lykapp.com/lyk/home/feed")) {
				System.out.println("Start");
				List<WebElement> list = driver.findElements(By.tagName("a"));
				System.out.println("Total links are " + list.size());
				for (i = 0; i < list.size(); i++) {
				// System.out.println("----"+i);
				
					WebElement we = list.get(i);
					String ss = we.getAttribute("href");
				// System.out.println("-----22----"+ss);
					URLTest(ss);
				}
			} else {
				System.out.println("END");
			}
		}
			
}
