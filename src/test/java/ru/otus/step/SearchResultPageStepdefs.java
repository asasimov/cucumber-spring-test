package ru.otus.step;

import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.page.SearchResultPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class SearchResultPageStepdefs {

    @Autowired
    private SearchResultPage resultPage;

    @Then("в результате поиска больше чем {int} элементов")
    public void checkNumberOfResultsIsGreaterThanSpecified(int count) {
        assertThat(resultPage.getNumberOfResults(), greaterThan(count));
    }

    @Then("в результате поиска ровно {int} элементов")
    public void checkNumberOfResultsCorrespondsToSpecified(int count) {
        assertThat(resultPage.getNumberOfResults(), equalTo(count));
    }

}