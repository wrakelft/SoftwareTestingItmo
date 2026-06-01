package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    private static final String URL = "https://www.woman.ru/";

    private final By body = By.xpath("//body");

    private final By loginButton = By.xpath(
            "//*[self::button or self::a]" +
                    "[contains(normalize-space(.), 'Войти') " +
                    "or contains(normalize-space(.), 'Вход') " +
                    "or contains(@aria-label, 'Войти') " +
                    "or contains(@aria-label, 'Вход')]"
    );

    private final By searchButton = By.xpath(
            "//*[self::button or self::a]" +
                    "[contains(@aria-label, 'Поиск') " +
                    "or contains(@title, 'Поиск') " +
                    "or .//*[local-name()='svg']]" +
                    " | " +
                    "//*[local-name()='svg']" +
                    "[ancestor::*[self::button or self::a]]" +
                    "/ancestor::*[self::button or self::a][1]" +
                    " | " +
                    "//*[contains(@class, 'search')]" +
                    "[self::button or self::a or @role='button']"
    );

    private final By searchInput = By.xpath(
            "//input[@type='search' " +
                    "or contains(@placeholder, 'Поиск') " +
                    "or contains(@placeholder, 'поиск')]"
    );

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(URL);
        findVisible(body);
        return this;
    }

    public boolean isOpened() {
        String title = driver.getTitle().toLowerCase();
        String url = driver.getCurrentUrl().toLowerCase();

        return title.contains("woman") || url.contains("woman.ru");
    }

    public AuthPage openAuthForm() {
        clickFirst(loginButton);
        return new AuthPage(driver).waitForOpened();
    }

    public SearchPage openSearchForm() {
        clickFirst(searchButton);
        findVisible(searchInput);
        return new SearchPage(driver);
    }

    public SearchPage search(String query) {
        openSearchForm();
        typeFirst(query, searchInput);
        pressEnter(searchInput);
        return new SearchPage(driver);
    }

    public SectionPage openStarsSection() {
        clickFirst(sectionLink("Звезды"), sectionHref("/stars/"));
        return new SectionPage(driver, "stars");
    }

    public SectionPage openBeautySection() {
        clickFirst(sectionLink("Красота"), sectionHref("/beauty/"));
        return new SectionPage(driver, "beauty");
    }

    public SectionPage openFashionSection() {
        clickFirst(sectionLink("Мода"), sectionHref("/fashion/"));
        return new SectionPage(driver, "fashion");
    }

    public ForumPage openForum() {
        clickFirst(sectionLink("Форум"), sectionHref("/forum/"));
        return new ForumPage(driver);
    }

    private By sectionLink(String text) {
        return By.xpath("//a[contains(normalize-space(.), '" + text + "')]");
    }

    private By sectionHref(String hrefPart) {
        return By.xpath("//a[contains(@href, '" + hrefPart + "')]");
    }
}