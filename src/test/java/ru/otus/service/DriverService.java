package ru.otus.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class DriverService {

    private static final Logger logger = LogManager.getLogger(DriverService.class);

    private final WebDriver wd = new ChromeDriver();

    static {
        WebDriverManager.chromedriver().setup();
    }

    public WebDriver initDriver() {
        wd.manage().timeouts()
                .implicitlyWait(10L, SECONDS)
                .setScriptTimeout(5L, SECONDS)
                .pageLoadTimeout(10L, SECONDS);

        wd.manage().window().maximize();
        ((HasCapabilities) wd).getCapabilities().asMap().entrySet().forEach(logger::debug);
        return wd;
    }
}