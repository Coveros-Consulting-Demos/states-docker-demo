package com.coveros.tutorial.functionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Selenium test for the States page.
 */
public class StatesTest {
    /**
     * URL to start testing at, passed in as a system property.
     */
    private static final String TEST_URL = System.getProperty("testUrl");

    /**
     * URL for the Selenium Grid hub.
     */
    private static final String HUB_URL = "http://hub:4444/wd/hub";

    private WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
    }

    @After
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    @Test
    public void testStates() {
        driver.get(TEST_URL);

        assertEquals("US States", driver.getTitle());
        assertEquals("Abbreviation", driver.findElement(By.cssSelector("th")).getText());
        assertEquals("Wyoming", driver.findElement(By.xpath("//tr[td='WY']/td[2]")).getText());
    }
}
