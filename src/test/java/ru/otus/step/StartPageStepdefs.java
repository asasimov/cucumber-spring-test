package ru.otus.step;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.page.StartPage;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StartPageStepdefs {

    @Autowired
    private StartPage startPage;

    @Given("открыть стартовую страницу")
    public void openStartPage(){
        startPage.open();
    }

    @And("перейти в раздел {string}")
    public void openSection(String section){
        startPage.openSection(section);
    }

    @Then("проверяем, что заголовок страницы содержит запись {string}")
    public void checkPageTitle(String title){
        assertThat(startPage.getTitle(), containsString(title));
    }

    @Then("проверяем, что изображение логотипа пристуствует на странице")
    public void checkLogoIsPresent() {
        assertThat(startPage.getMenu().getUrlOfImageLogo(), is(not(nullValue())));
        assertThat(startPage.getMenu().getUrlOfImageLogo(), is(not("")));
    }

    @Then("проверяем, что изображение имеет формат {string}")
    public void checkImageFormat(String format){
        assertThat(startPage.getMenu().getUrlOfImageLogo(), endsWith(format));
    }

}