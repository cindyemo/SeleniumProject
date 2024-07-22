import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class KeyPressesTest {
    WebDriver driver;

    @Parameters({"browserParam"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        //open page
        String url = "https://the-internet.herokuapp.com/key_presses";
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
    public void keypressTest(){
        WebElement input = driver.findElement(By.xpath("//*[@id=\"target\"]"));
        input.sendKeys(Keys.BACK_SPACE);

        WebElement result = driver.findElement(By.cssSelector("p#result"));
        String actualResultMessage = result.getText();
        String expectedResultMessage = "You entered: BACK_SPACE";

        Assert.assertEquals(expectedResultMessage,actualResultMessage);

    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

}
