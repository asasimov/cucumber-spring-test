package ru.otus.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicePage extends BasePage {

    @Autowired
    private MenuBlock menu;

    @FindBy(xpath = "//a[@class='link ng-binding']")
    private List<WebElement> links;

    public MenuBlock getMenu() {
        return menu;
    }

    public void open() {
        openSection("category");
    }

    public boolean findLinkByText(String text){
        waitForLoader();
        for(WebElement link : links){
            if(getText(link).equalsIgnoreCase(text)){
                return true;
            }
        }
        return false;
    }
}