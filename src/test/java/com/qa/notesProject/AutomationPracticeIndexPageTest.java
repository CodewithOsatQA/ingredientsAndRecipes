//package com.qa.notesProject;
//
//import static org.junit.Assert.assertEquals;
//
////import static org.junit.Assert.fail;
////
////import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class AutomationPracticeIndexPageTest {
//
//    private WebDriver driver;
//
//    @Before
//    public void setup() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\otowe\\projects\\SpringBoot\\notesProject\\src\\test\\resources\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().setSize(new Dimension(1366, 768));
//    }
//
//    @Test
//    public void newTestCase(){
//        //step 1
//        driver.get("http://automationpractice.com/index.php%22");
//        try {
//            Thread.sleep(2000);
//            //step 2
//            WebElement target = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a"));
//            target.click();
//            Thread.sleep(1000);
//            //step 3
//            target = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li/div/div[2]/h5/a"));
//            String expectedProduct = target.getText();
//            System.out.println(expectedProduct);
//            target.click();
//            Thread.sleep(1000);
//
//            //step 4
//            WebElement button = driver.findElement(By.id("add_to_cart"));
//            button.click();
//            Thread.sleep(2000);
//
//            //step 5
//            driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a")).click();
//            Thread.sleep(1000);
//
//            String actual = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[2]/p/a")).getText();
//            System.out.println(actual);
//
//            //step 6
//            assertEquals(expectedProduct,actual);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @After
//    public void tearDown() {
//        driver.close();
//    }
//}