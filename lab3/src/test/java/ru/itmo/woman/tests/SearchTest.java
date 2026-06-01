package ru.itmo.woman.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itmo.pages.HomePage;
import ru.itmo.pages.SearchPage;
import ru.itmo.woman.BaseTest;
import ru.itmo.woman.BrowserType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenSearchForm(BrowserType browserType) {
        startBrowser(browserType);

        SearchPage searchPage = new HomePage(driver)
                .open()
                .openSearchForm();

        assertTrue(searchPage.isSearchInputVisible());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldSearchByFashionQuery(BrowserType browserType) {
        startBrowser(browserType);

        SearchPage searchPage = new HomePage(driver)
                .open()
                .search("мода");

        assertTrue(searchPage.hasResults());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldSearchByHealthQuery(BrowserType browserType) {
        startBrowser(browserType);

        SearchPage searchPage = new HomePage(driver)
                .open()
                .search("здоровье");

        assertTrue(searchPage.hasResults());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldHandleEmptySearch(BrowserType browserType) {
        startBrowser(browserType);

        SearchPage searchPage = new HomePage(driver)
                .open()
                .openSearchForm()
                .search("");

        assertTrue(searchPage.hasEmptySearchHandling());
    }
}