package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;


public class DropdownTest {
    WebDriver driver;

    @Parameters({"browserParam"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        //open page
        String url = "https://the-internet.herokuapp.com/dropdown";
        //driver = new ChromeDriver();

        switch (browser){
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            default: driver = new ChromeDriver();
        }

        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void dropdownTest(){
        WebElement dropdown = driver.findElement(By.xpath("/html//select[@id='dropdown']"));
        Select option = new Select(dropdown);

        option.selectByValue("2");

        WebElement option2 = driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[3]"));

        Assert.assertTrue(option2.isSelected());


    }


    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public void wait(int milliseconds) {
        try {
            driver.wait(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}