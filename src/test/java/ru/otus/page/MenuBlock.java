package ru.otus.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class MenuBlock extends BasePage {

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
        waitForLoader();
        moveToElementAndClick(serviceButton);
    }

    public void clickOnPayButton(){
        waitForLoader();
        click(payButton);
    }

    public void clickOnSupportButton(){
        waitForLoader();
        click(supportButton);
    }

    public void search(String text){
        waitForLoader();
        click(searchIcon);
        type(searchInput, text);
        click(searchButton);
    }

    public String getUrlOfImageLogo(){
        return getAttribute(logoImage, "src");
    }
}