//package com.qa.notesProject;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class GooglePageTest {
//	
//	
//	public static ChromeOptions chromeCfg() {
//		 Map<String, Object> prefs = new HashMap<String, Object>();
//		 ChromeOptions cOptions = new ChromeOptions();
//		  
//		 // Settings
//		 prefs.put("profile.default_content_setting_values.cookies", 2);
//		 prefs.put("network.cookie.cookieBehavior", 2);
//		 prefs.put("profile.block_third_party_cookies", true);
//
//		 // Create ChromeOptions to disable Cookies pop-up
//		 cOptions.setExperimentalOption("prefs", prefs);
//		 cOptions.setHeadless(true);
//		 return cOptions;
//		 }
//
//    private WebDriver driver;
//
//    @Before
//    public void setup() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\otowe\\projects\\SpringBoot\\notesProject\\src\\test\\resources\\chromedriver.exe");
//        driver = new ChromeDriver(chromeCfg());
//        driver.manage().window().setSize(new Dimension(1366, 768));
//
//    }
//
//    @Test
//    public void test() throws InterruptedException {
//        driver.get("https://www.google.com");
//        Thread.sleep(10000);
//        assertEquals("Google", driver.getTitle());
//
//    }
//
//
//
//    @After
//    public void tearDown() {
//        driver.close();
//    }
//}