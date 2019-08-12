package ru.otus.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public abstract class BasePage {

    @Autowired
    protected WebDriver wd;

    @PostConstruct
    protected void init() {
        PageFactory.initElements(wd, this);
    }

    @Value("${sut.url}")
    private String URL;

    private final By ELEMENT_LOADER = By.xpath("//div[@id='main-throbber']");

    public void openSection(String section){
        wd.navigate().to(URL + section);
    }

    protected void waitForElementLoader(){
        new WebDriverWait(wd, 10L).until(ExpectedConditions.invisibilityOfElementLocated(ELEMENT_LOADER));
    }

}