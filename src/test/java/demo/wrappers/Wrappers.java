package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.invoke.StringConcatFactory;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public static void enterText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void radioButton(ChromeDriver driver, String radioButtonText) {
        try {
            WebElement element = driver
                    .findElement(By.xpath("//span[contains(@class,'OvPDhc') and contains(text(), '" + radioButtonText
                            + "' )]/../../..//div[@class='vd3tt']"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkbox(ChromeDriver driver, String checkboxText) {
        try {
            WebElement element = driver.findElement(By.xpath(
                    "//span[contains(@class,'n5vBHf') and contains(text(), '" + checkboxText
                            + "')]/../../preceding-sibling::div[contains(@id,'i')]"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropdownClick(ChromeDriver driver, String dropdownText) {
        try {
            WebElement element = driver
                    .findElement(
                            By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']/div/span[text()='"+dropdownText+"']"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickOnElement(ChromeDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDateSevenDaysAgo() {

        LocalDate currenDate = LocalDate.now();
        // Subtract 7 Days
        LocalDate dateMinus7Days = currenDate.minusDays(7);
        // Define the desired foramt
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format the current date
        String formattedDate = dateMinus7Days.format(formatter);

        return formattedDate;
    }

    public static String getEpochTimeAString() {
        // create epoch time string
        long epochTime = System.currentTimeMillis() / 1000;
        String epochTimeString = String.valueOf(epochTime);
        return epochTimeString;
    }

    public static boolean handleAlert(ChromeDriver driver) {
        // create epoch time string
        boolean status = false;
        driver.switchTo().alert().dismiss();
        status = true;

        return status;
    }

}
