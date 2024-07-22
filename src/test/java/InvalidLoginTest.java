import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class InvalidLoginTest {
    WebDriver driver;

    @Parameters({"browserParam"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional ("chrome") String browser){
        //open page
        String url = "https://the-internet.herokuapp.com/login";
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


     @Parameters({"usernameParam","passwordParam","errorMessageParam"})
    @Test
    public void invalidLogin(String username, String password, String errorMessage){
        //enter username
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(username);

        //enter password
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        //click Login
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        //check error message
        WebElement errorAlert = driver.findElement(By.id("flash"));
        String expectedErrorMessage = errorMessage;
        String actualErrorMessage = errorAlert.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

//    @Test
//    public void invalidPasswordTest(){
//        //enter username
//        WebElement usernameInput = driver.findElement(By.id("username"));
//        usernameInput.sendKeys("tomsmith");
//
//        //enter password
//        WebElement passwordInput = driver.findElement(By.name("password"));
//        passwordInput.sendKeys("incorrect");
//
//        //click Login
//        WebElement loginButton = driver.findElement(By.tagName("button"));
//        loginButton.click();
//
//        //check error message
//        WebElement errorAlert = driver.findElement(By.id("flash"));
//        String expectedErrorMessage = "Your password is invalid!";
//        String actualErrorMessage = errorAlert.getText();
//        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
//    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}