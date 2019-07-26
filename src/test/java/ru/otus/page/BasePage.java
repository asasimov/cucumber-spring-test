package ru.otus.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

public abstract class BasePage {
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    @Autowired
    protected WebDriver wd;

    @PostConstruct
    public void init() {
        PageFactory.initElements(wd, this);
    }

    @Value("${sut.url}")
    protected String URL;

    private final By ELEMENT_LOADER = By.xpath("//div[@id='main-throbber']");
    private final Long DEFAULT_TIME_OUT = 10L;

    public void openSection(String section){
        wd.navigate().to(URL + section);
    }

    public String getTitle(){
        return wd.getTitle();
    }

    public WebElement get(String cucumberElementName) {
        Class<?> clazz = this.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NameOfElement.class)) {
                NameOfElement nameOfElementAnnotation = field.getAnnotation(NameOfElement.class);
                if (nameOfElementAnnotation.value().equals(cucumberElementName)) {
                    try {
                        return (WebElement) field.get(this);
                    } catch (IllegalAccessException ex) {
                        logger.error("Is no such element with name {} at page {}", cucumberElementName, this.getClass().getName());
                    }
                }
            }
        }
        throw new IllegalArgumentException("ERROR: there is no such element with name " + cucumberElementName + " at page " + this.getClass().getName());
    }

    protected void type(By locator, String text){
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void type(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator){
        wd.findElement(locator).click();
    }

    protected void click(WebElement element){
        element.click();
    }

    protected String getValue(By locator){
        return wd.findElement(locator).getAttribute("value");
    }

    protected String getValue(WebElement element){
        return element.getAttribute("value");
    }

    protected String getText(By locator){
        return wd.findElement(locator).getText();
    }

    protected String getText(WebElement element){
        return element.getText();
    }

    protected String getAttribute(By locator, String attribute){
        return wd.findElement(locator).getAttribute(attribute);
    }

    protected String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public void waitForLoader(){
        new WebDriverWait(wd, DEFAULT_TIME_OUT).until(ExpectedConditions.invisibilityOfElementLocated(ELEMENT_LOADER));
    }

    public void moveToElementAndClick(WebElement element){
        new Actions(wd).moveToElement(element).click(element).perform();
    }

    public void waitForElementAndClick(WebElement element){
       new WebDriverWait(wd, DEFAULT_TIME_OUT).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

}