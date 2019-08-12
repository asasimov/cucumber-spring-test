package ru.otus.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.service.ElementResolver;

@Component
public class MenuBlock extends BasePage {

    @Autowired
    private ElementResolver resolver;

    @NameOfElement(value = "ссылка услуги")
    @FindBy(css = "a[href='/category']")
    private WebElement serviceButton;

    @NameOfElement(value = "ссылка оплата")
    @FindBy(css = "a[href='/pay']")
    private WebElement payButton;

    @NameOfElement(value = "ссылка поддержка")
    @FindBy(css = "a[href='/help']")
    private WebElement supportButton;

    @FindBy(css = "span.search-ico")
    private WebElement searchIcon;

    @FindBy(xpath = "//input[@placeholder='что вы ищете?']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='btn-base medium blue']")
    private WebElement searchButton;

    @FindBy(xpath = "//img[@alt='Госуслуги']")
    private WebElement logoImage;

    @NameOfElement(value = "логотип-ссылка")
    @FindBy(xpath = "//a[@class='main-app-logo']/..")
    private WebElement logoLink;

    public void clickOnServiceButton(){
        waitForElementLoader();
        new Actions(wd)
                .moveToElement(serviceButton)
                .click(serviceButton)
                .perform();
    }

    public void clickOnPayButton(){
        waitForElementLoader();
        payButton.click();
    }

    public void clickOnSupportButton(){
        waitForElementLoader();
        supportButton.click();
    }

    public void search(String text){
        waitForElementLoader();
        searchIcon.click();
        searchInput.clear();
        searchInput.sendKeys(text);
        searchButton.click();
    }

    public String getUrlOfImageLogo(){
        return logoImage.getAttribute("src");
    }

    public void moveToElementAndClick(String elementName) {
        WebElement element = resolver.getElementByName(elementName, this);
        new Actions(wd)
                .moveToElement(element)
                .click(element)
                .perform();
    }
}