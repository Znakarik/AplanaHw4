
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SberTest {

    public static WebDriver webDriver = null;

    @BeforeClass
    public static void TestUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public static void CleanUp() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void RGS() {
        webDriver.get("http://www.sberbank.ru/ru/person");
        webDriver.findElement(By.xpath("//a[@class='hd-ft-region']")).click();
        webDriver.findElement(By.xpath("//input[contains(@class, 'kit-input__control') and contains(@type, 'search')]")).sendKeys("ниже");

        webDriver.findElement(By.xpath("//a[contains(text(),'Нижего')]")).click();

        WebElement city = webDriver.findElement(By.xpath("//div[contains(@class,'paste-region__region header__region header__region_52')]//div[contains(@class,'hd-ft-region__title')]/span[contains(text(),'Нижегородская')]"));
        Assert.assertEquals("Нижегородская область", city.getText());

        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        List<WebElement> webElements = new ArrayList<>();
        webElements.add(webDriver.findElement(By.xpath("//span[@class='footer__social_logo footer__social_fb']")));
        webElements.add(webDriver.findElement(By.xpath("//span[@class='footer__social_logo footer__social_tw']")));
        webElements.add(webDriver.findElement(By.xpath("//span[@class='footer__social_logo footer__social_yt']")));
        webElements.add(webDriver.findElement(By.xpath("//span[@class='footer__social_logo footer__social_ins']")));
        webElements.add(webDriver.findElement(By.xpath("//span[@class='footer__social_logo footer__social_vk']")));
        webElements.add(webDriver.findElement(By.xpath("//span[@class='footer__social_logo footer__social_ok']")));

        webElements.forEach(webElement -> Assert.assertTrue(webElement.isDisplayed()));
    }
}