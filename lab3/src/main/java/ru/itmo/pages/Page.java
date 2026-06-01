package ru.itmo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public abstract class Page {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement findVisible(By... locators) {
        return wait.until(d -> {
            for (By locator : locators) {
                List<WebElement> elements = d.findElements(locator);
                for (WebElement element : elements) {
                    try {
                        if (element.isDisplayed()) {
                            return element;
                        }
                    } catch (StaleElementReferenceException ignored) {
                    }
                }
            }
            return null;
        });
    }

    protected WebElement findClickable(By... locators) {
        return wait.until(d -> {
            for (By locator : locators) {
                List<WebElement> elements = d.findElements(locator);
                for (WebElement element : elements) {
                    try {
                        if (element.isDisplayed() && element.isEnabled()) {
                            return element;
                        }
                    } catch (StaleElementReferenceException ignored) {
                    }
                }
            }
            return null;
        });
    }

    protected void clickFirst(By... locators) {
        WebElement element = findClickable(locators);
        scrollTo(element);

        demoStep("Клик по элементу: " + describeElement(element));
        highlight(element);
        demoPause();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        demoPause();
    }

    protected void typeFirst(String text, By... locators) {
        WebElement element = findVisible(locators);
        scrollTo(element);

        demoStep("Ввод текста: " + text);
        highlight(element);
        demoPause();

        element.clear();
        element.sendKeys(text);

        demoPause();
    }

    protected void pressEnter(By... locators) {
        WebElement element = findVisible(locators);

        demoStep("Нажатие Enter");
        highlight(element);
        demoPause();

        element.sendKeys(Keys.ENTER);

        demoPause();
    }

    protected boolean isVisible(By... locators) {
        try {
            findVisible(locators);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isClickable(By... locators) {
        try {
            findClickable(locators);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected String getText(By... locators) {
        return findVisible(locators).getText();
    }

    protected String getValue(By... locators) {
        return findVisible(locators).getAttribute("value");
    }

    protected int countVisible(By locator) {
        return driver.findElements(locator)
                .stream()
                .filter(element -> {
                    try {
                        return element.isDisplayed();
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }
                })
                .toList()
                .size();
    }

    protected void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    protected boolean urlContains(String part) {
        return wait.until(d -> d.getCurrentUrl().toLowerCase().contains(part.toLowerCase()));
    }

    private static final boolean DEMO_MODE =
            Boolean.parseBoolean(System.getProperty("demo", "false"));

    protected void demoStep(String text) {
        if (DEMO_MODE) {
            System.out.println("[DEMO] " + text);
        }
    }

    protected void highlight(WebElement element) {
        if (!DEMO_MODE) {
            return;
        }

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.outline='4px solid red';" +
                        "arguments[0].style.backgroundColor='rgba(255, 255, 0, 0.35)';",
                element
        );
    }

    protected void demoPause() {
        if (DEMO_MODE) {
            new Actions(driver)
                    .pause(Duration.ofMillis(700))
                    .perform();
        }
    }

    private String describeElement(WebElement element) {
        String text = safe(element.getText());
        if (!text.isBlank()) {
            return text;
        }

        String ariaLabel = safe(element.getAttribute("aria-label"));
        if (!ariaLabel.isBlank()) {
            return ariaLabel;
        }

        String title = safe(element.getAttribute("title"));
        if (!title.isBlank()) {
            return title;
        }

        String placeholder = safe(element.getAttribute("placeholder"));
        if (!placeholder.isBlank()) {
            return placeholder;
        }

        String href = safe(element.getAttribute("href"));
        if (!href.isBlank()) {
            return href;
        }

        return element.getTagName();
    }

    protected String safe(String value) {
        return value == null ? "" : value.trim();
    }
}