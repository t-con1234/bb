package com.sahabt;

import java.net.MalformedURLException;
import java.net.URL;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;


public class Steps{

    public String URL = "http://192.168.60.63:4444/wd/hub";
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
}
