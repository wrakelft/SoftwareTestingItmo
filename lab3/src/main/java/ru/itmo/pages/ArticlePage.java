package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticlePage extends Page {

    private final By title = By.xpath("//h1");

    private final By bodyText = By.xpath(
            "//article//p | //main//p[string-length(normalize-space(.)) > 30]"
    );

    private final By additionalElements = By.xpath(
            "//time | //article//img | //main//img | " +
                    "//*[contains(text(), 'Похожие') or contains(text(), 'Читайте')]"
    );

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(title);
    }

    public boolean hasTitle() {
        return isVisible(title) && !getText(title).isBlank();
    }

    public boolean hasBodyText() {
        return isVisible(bodyText);
    }

    public boolean hasAdditionalElements() {
        return isVisible(additionalElements);
    }
}