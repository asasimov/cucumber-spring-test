package ru.otus.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchResultPage extends BasePage {

    @Autowired
    private MenuBlock menu;

    @FindBy(css = "div.list.ng-scope")
    private List<WebElement> searchResultItems;

    public MenuBlock getMenu() {
        return menu;
    }

    public int getNumberOfResults(){
        return searchResultItems.size();
    }
}