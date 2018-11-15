package com.sahabt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Then;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class Steps{

    public String URL = "http://hub.testinium.io/wd/hub";
    public static RemoteWebDriver driver;

    @Before
    public void before() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("key", System.getProperty("key"));

        try {
            driver = new RemoteWebDriver(new URL(URL),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterMethod(){
       driver.quit();
    }

    @Given("^Go to \"(.*)\"")
    public void goTo(String url) {
        driver.navigate().to(url);
    }

    @Then("^Wait \"([^\"]*)\" ms$")
    public void waitMs(String time) throws Throwable {
        try {
            Thread.sleep(Integer.valueOf(time));
        } catch (InterruptedException e) {
        }
    }

    @Then("^Click random link$")
    public void clickRandomLink() {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");

        List<WebElement> elements = driver.findElements(By.tagName("a"));
        for (WebElement webElement : elements) {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
            WebElement waitElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            if(waitElement != null) {
                try {
                    webElement.click();
                    break;
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }
}
