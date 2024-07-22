import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class UploadTest {
    WebDriver driver;
    @Parameters({"browserParam"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        //open page
        String url = "https://the-internet.herokuapp.com/upload";
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
    public void uploadTest(){
        WebElement chooseFileButton = driver.findElement(By.id("file-upload"));
        chooseFileButton.sendKeys("C:\\Users\\Szende Mitrea\\IdeaProjects\\SeleniumProject\\src\\test\\resources\\myFile.txt");

        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        String expectedMessage = "File Uploaded!";
        Assert.assertTrue(successMessage.getText().contains(expectedMessage));

        WebElement uploadedFile = driver.findElement(By.id("uploaded-files"));
        String expectedText = "myFile.txt";
        Assert.assertTrue(uploadedFile.getText().contains(expectedText));

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
