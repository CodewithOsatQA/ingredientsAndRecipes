package com.qa.notesProject.selenium;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

//import static org.junit.Assert.fail;
//
//import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;




public class FrontendTesting {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\otowe\\projects\\SpringBoot\\notesProject\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
    }
    
    
    @Test
    public void test() {
    driver.get("http://127.0.0.1:5501/HTML/index.html");
   
    //Test if user is on page by asserting value of h1
    //index
    String actualString = "Welcome to Eat with Os";
    assertEquals(driver.findElement(By.xpath("/html/body/div/h1")).getText(), actualString);
    //Index to create Recipe
    driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[4]/a")).click();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[4]/div/a[1]")).click();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    //Recipe Create
    //recipe
    driver.findElement(By.xpath("/html/body/div/form/div[1]/input")).sendKeys("Pasta Bake");
    driver.findElement(By.xpath("/html/body/div/form/div[2]/select")).sendKeys("Easy");
    driver.findElement(By.xpath("/html/body/div/form/button")).click();
    
    
    //driver.navigate().to("http://127.0.0.1:5501/HTML/allRecipes.html");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    
    
    
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[2]")).getText(), "Pasta Bake");
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[3]")).getText(), "Easy");
    
    
    driver.navigate().to("http://127.0.0.1:5501/HTML/allRecipes.html");
    actualString = "View all Recipes Here";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    assertEquals(driver.findElement(By.xpath("/html/body/div/h1")).getText(), actualString);
    
    //Recipe Read
    
    
    //Recipe Update
    driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[6]/a")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.xpath("/html/body/div/form/div[2]/input")).clear();
    driver.findElement(By.xpath("/html/body/div/form/div[2]/input")).sendKeys("Stewed Chicken");
    driver.findElement(By.xpath("/html/body/div/form/div[3]/select")).sendKeys("Hard");
    driver.findElement(By.xpath("/html/body/div/form/button")).click();
    
    driver.navigate().to("http://127.0.0.1:5501/HTML/allRecipes.html");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[2]")).getText(), "Stewed Chicken");
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[3]")).getText(), "Hard");
    
    
    //Create Ingredient
    //Index to create Ingredient
    driver.navigate().to("http://127.0.0.1:5501/HTML/index.html");
    driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[4]/a")).click();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[4]/div/a[2]")).click();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.findElement(By.xpath("/html/body/div/form/div[1]/input")).sendKeys("Chicken");
    driver.findElement(By.xpath("/html/body/div/form/div[2]/select")).sendKeys("Protein");
    driver.findElement(By.xpath("/html/body/div/form/div[4]/input")).sendKeys("12.3");
    driver.findElement(By.xpath("/html/body/div/form/div[5]/input")).sendKeys("500.0");
    driver.findElement(By.xpath("/html/body/div/form/button")).click();
    //ingredient Read
    driver.navigate().to("http://127.0.0.1:5501/HTML/allIngredients.html");
    actualString = "View all Ingredients Here";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    assertEquals(driver.findElement(By.xpath("/html/body/div/h1")).getText(), actualString);
   
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[2]")).getText(),"Chicken");
    
    //Update Ingredient
    driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[6]/a")).click();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.findElement(By.xpath("/html/body/div/form/div[2]/input")).clear();
    driver.findElement(By.xpath("/html/body/div/form/div[2]/input")).sendKeys("Gravy");
    driver.findElement(By.xpath("/html/body/div/form/div[3]/select")).sendKeys("Dairy");
    
    driver.findElement(By.xpath("/html/body/div/form/div[5]/input")).clear();
    driver.findElement(By.xpath("/html/body/div/form/div[5]/input")).sendKeys("56");
    
    driver.findElement(By.xpath("/html/body/div/form/div[6]/input")).clear();
    driver.findElement(By.xpath("/html/body/div/form/div[6]/input")).sendKeys("67");
    
    driver.findElement(By.xpath("/html/body/div/form/button")).click();
    
    //Go to view all Ingredients
    
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[3]/a")).click();
    
    //Ensure navigation worked
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    assertEquals(driver.findElement(By.xpath("/html/body/div/h1")).getText(), actualString);
    
    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[2]")).getText(),"Gravy");
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[3]")).getText(),"Dairy");
    			
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[4]")).getText(),"£56");
    assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[5]")).getText(),"67");
    //Delete Ingredient
    
    driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[6]/a")).click();
    
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    
    //assertEquals(driver.findElement(By.xpath("/html/body/div/form/div[2]/input")).getText(), "Gravy");
    driver.findElement(By.xpath("/html/body/div/form/button[2]")).click();
    
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.get("http://127.0.0.1:5501/HTML/allIngredients.html");
    actualString = "View all Ingredients Here";
    //wait.until(ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.xpath("/html/body/div/h1")), true));
    		
    assertEquals(driver.findElement(By.xpath("/html/body/div/h1")).getText(), actualString);
    
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    //Delete recipe
    //Navigate Back to all Recipe page
    driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[2]/a")).click();
    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    
    driver.findElement(By.xpath("/html/body/div/table/thead/tr[2]/td[6]/a")).click();
    
    
    driver.findElement(By.xpath("/html/body/div/form/button[2]")).click();
    //Check if the page is the one we want
    
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    
    assertEquals(driver.findElement(By.xpath("/html/body/div/h1")).getText(), "View all Recipes Here");
    
    
//    WebElement target = driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[4]/a"));
//    target.click();
//    driver.findElement(By.xpath("/html/body/header/nav/div/ul/li[4]/div/a[1]"));
//    target.click();
//    target = driver.findElement(By.xpath("/html/body/div/form/div[1]/input"));
//    target.sendKeys("Pasta Bake");
//    WebDriverWait wait = new WebDriverWait(driver, 5);
//    
//    driver.findElement(By.xpath("/html/body/div/form/div[2]/select")).sendKeys("Easy");
//    
//    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/form/button")));
//    driver.findElement(By.xpath("/html/body/div/form/button")).click();
    
    }
//    @Test
//    public void seeAllIngredients(){
//        
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

    @After
    public void tearDown() {
        driver.close();
    }
}