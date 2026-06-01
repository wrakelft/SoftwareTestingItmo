package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionPage extends Page {

    private final String sectionPath;

    private final By title = By.xpath("//h1 | //h2");

    private final By contentCards = By.xpath(
            "//main//a[string-length(normalize-space(.)) > 10]"
    );

    public SectionPage(WebDriver driver, String sectionPath) {
        super(driver);
        this.sectionPath = sectionPath;
    }

    public boolean isOpened() {
        return urlContains(sectionPath) || isVisible(title);
    }

    public boolean hasMaterials() {
        return isVisible(contentCards);
    }

    public ArticlePage openFirstArticle() {
        By articleLink = By.xpath(
                "//main//a[contains(@href, '/" + sectionPath + "/')" +
                        " and not(contains(@href, '/forum/'))" +
                        " and string-length(normalize-space(.)) > 10]"
        );

        clickFirst(articleLink, contentCards);
        return new ArticlePage(driver);
    }
}