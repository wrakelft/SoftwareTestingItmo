package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends Page {

    private final By searchInput = By.xpath(
            "//input[@type='search' " +
                    "or contains(@placeholder, 'Поиск') " +
                    "or contains(@placeholder, 'поиск') " +
                    "or contains(@name, 'q') " +
                    "or contains(@name, 'query') " +
                    "or contains(@name, 'search')]"
    );

    private final By resultLinks = By.xpath(
            "//main//a[contains(@href, '/stars/') " +
                    "or contains(@href, '/fashion/') " +
                    "or contains(@href, '/beauty/') " +
                    "or contains(@href, '/health/') " +
                    "or contains(@href, '/psycho/') " +
                    "or contains(@href, '/forum/')]"
    );

    private final By emptySearchMessage = By.xpath(
            "//*[contains(text(), 'Введите') " +
                    "or contains(text(), 'ничего') " +
                    "or contains(text(), 'не найден') " +
                    "or contains(text(), 'запрос')]"
    );

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchInputVisible() {
        return isVisible(searchInput);
    }

    public SearchPage search(String query) {
        typeFirst(query, searchInput);
        pressEnter(searchInput);
        return this;
    }

    public boolean hasResults() {
        return isVisible(resultLinks);
    }

    public boolean hasEmptySearchHandling() {
        return isVisible(emptySearchMessage) || isSearchInputVisible();
    }

}