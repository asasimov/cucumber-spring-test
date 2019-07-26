package ru.otus.step;

import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.page.ServicePage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServicePageStepdefs {

    @Autowired
    private ServicePage servicePage;

    @Then("проверяем, что категория с названием {string} доступна в списке усгул")
    public void checkThatTheCategoryIsListed(String text){
        assertThat(servicePage.findLinkByText(text), is(true));
    }

}