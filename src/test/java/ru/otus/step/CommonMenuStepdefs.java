package ru.otus.step;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.page.MenuBlock;

public class CommonMenuStepdefs {

    @Autowired
    private MenuBlock menu;

    @When("я ищу {string}")
    public void search(String text) {
        menu.search(text);
    }

    @And("кликнуть на элемент меню с именем {string}")
    public void clickOnElementNamed(String elementName) {
        menu.moveToElementAndClick(menu.get(elementName));
    }

}