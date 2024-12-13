package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.http.WebSocket;
import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */

    @Test
    public void testCase01() throws InterruptedException {
        // Navigate to google form

        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");

        // NAME INPUT
        WebElement nameInputBox = waitForElementToBeVisible(By.xpath("(//input[contains(@jsname,'YPqjbf')])[1]"));

        System.out.println("Step 1 - Enter the Name");
        Thread.sleep(3000);
        Wrappers.enterText(nameInputBox, "Crio Learner");

        // PRACTICE AUTOMATION
        WebElement practicingAutomationTextArea = waitForElementToBeVisible(
                By.xpath("//textarea[contains(@jsname,'YPqjbf')]"));
        String practicingAutomationText = "I want to be the best QA Engineer!";
        String epochtimeString = Wrappers.getEpochTimeAString();
        Thread.sleep(3000);
        System.out.println("Step 2 -  Practicing Automation");
        Wrappers.enterText(practicingAutomationTextArea, practicingAutomationText + " " + epochtimeString);

        // RADIO BUTTON
        Thread.sleep(3000);
        System.out.println("Step 3 -  Selecting the Radio Button");
        Wrappers.radioButton(driver, "6 - 10");

        // CHECKBOX FOR SKILLSETS
        Thread.sleep(3000);
        System.out.println("Step 4 -  Selecting the Skill Set");

        Wrappers.checkbox(driver, "Java");
        Wrappers.checkbox(driver, "Selenium");
        Wrappers.checkbox(driver, "TestNG");

        // DEALING WITH THE DROPDOWNS

        WebElement DropDownElement = driver.findElement(By.xpath("//div[contains(@class,'DEh1R')]"));
        Wrappers.clickOnElement(driver, DropDownElement);
        Thread.sleep(3000);
        Wrappers.dropdownClick(driver, "Mrs");
        System.out.println("Step 5 -  Salutations Selection");

        // Enter 7 Days Ago Date
        WebElement dateInputBox = driver.findElement(By.xpath("//input[@type='date']"));
        String sevenDaysBeforedate = Wrappers.getDateSevenDaysAgo();
        Thread.sleep(3000);
        System.out.println("Step 6 -  Date Selection");
        Wrappers.enterText(dateInputBox, sevenDaysBeforedate);

        // enter current time in HH:MM

        WebElement hourElement = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minElement = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        WebElement submitbtn = driver.findElement(By.xpath("//div[@class='lRwqcd']/div"));

        Wrappers.enterText(hourElement, "07");
        Wrappers.enterText(minElement, "40");
        Wrappers.clickOnElement(driver, submitbtn);

        Thread.sleep(3000);

        System.out.println("Step 7 -  Check if Response Submitted");

        WebElement Successmessage = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        String expectedsuccessmessage = "Thanks for your response, Automation Wizard!";
        Assert.assertEquals(Successmessage.getText().trim(), expectedsuccessmessage);

    }

    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }

    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}