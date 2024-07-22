import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

     @Test
    public void logintest(){
        //open page
        String url = "https://the-internet.herokuapp.com/login";
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();


        //enter username
         WebElement usernameImput = driver.findElement(By.id("username"));
         usernameImput.sendKeys("tomsmith");

        //enete rpassword
         WebElement passwordImput = driver.findElement(By.name("password"));
         passwordImput.sendKeys("SuperSecretPassword!");

        //click login
         WebElement loginButton = driver.findElement(By.tagName("button"));
         loginButton.click();

        //chek landing url
         String expectedUrl = "https://the-internet.herokuapp.com/secure";
         String actualUrl = driver.getCurrentUrl();
         Assert.assertEquals(expectedUrl,actualUrl);


        //chek logout is present
         WebElement logoutButton = driver.findElement(By.linkText("Logout"));
         Assert.assertTrue(logoutButton.isDisplayed());

        //chek success login message
         WebElement succesAlert = driver.findElement(By.className("flash"));
         String expectedMessage = "You logged into a secure area";
         String actualMessage = succesAlert.getText();
         Assert.assertTrue(actualMessage.contains(expectedMessage));

         //close page
         driver.close();

    }

}
