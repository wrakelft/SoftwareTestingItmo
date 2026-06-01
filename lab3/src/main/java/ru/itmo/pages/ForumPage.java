package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ForumPage extends Page {

    private static final String URL = "https://www.woman.ru/forum/";

    private final By forumTitle = By.xpath(
            "//h1[contains(., 'Форум')] | //h1 | //h2[contains(., 'Форум')]"
    );

    private final By forumContent = By.xpath(
            "//a[contains(@href, '/forum/')] " +
                    "| //*[contains(normalize-space(.), 'Добавить тему')] " +
                    "| //*[contains(normalize-space(.), 'Форум')]"
    );

    private final By addTopicButton = By.xpath(
            "//*[self::button or self::a or @role='button']" +
                    "[contains(normalize-space(.), 'Добавить тему') " +
                    "or contains(normalize-space(.), 'Создать тему') " +
                    "or contains(normalize-space(.), 'Новая тема')]"
    );

    public ForumPage(WebDriver driver) {
        super(driver);
    }

    public ForumPage open() {
        driver.get(URL);
        findVisible(forumContent);
        return this;
    }

    public boolean isOpened() {
        return driver.getCurrentUrl().contains("/forum/") || isVisible(forumTitle, forumContent);
    }

    public boolean hasForumContent() {
        return isVisible(forumContent) || isVisible(addTopicButton);
    }

    public TopicFormPage openTopicForm() {
        clickFirst(addTopicButton);
        return new TopicFormPage(driver);
    }

    public TopicPage openFirstTopic() {
        WebElement topic = findFirstTopicLink();

        scrollTo(topic);
        highlight(topic);

        String text = safe(topic.getText());
        String href = safe(topic.getAttribute("href"));

        demoStep("Открытие темы форума: " + text);
        demoStep("URL темы: " + href);
        demoPause();

        if (href.isBlank()) {
            throw new IllegalStateException("Не удалось получить ссылку на тему форума");
        }

        driver.get(href);

        wait.until(d -> d.getCurrentUrl().contains("thread-"));
        demoPause();

        return new TopicPage(driver);
    }

    private WebElement findFirstTopicLink() {
        return wait.until(d -> {
            scrollToBlock("Новое сегодня");

            List<WebElement> links = d.findElements(By.xpath(
                    "//*[contains(normalize-space(.), 'Новое сегодня')]" +
                            "/following::a[contains(@href, 'thread-')][string-length(normalize-space(.)) > 10]"
            ));

            for (WebElement link : links) {
                try {
                    if (!link.isDisplayed() || !link.isEnabled()) {
                        continue;
                    }

                    String text = safe(link.getText());
                    String href = safe(link.getAttribute("href")).toLowerCase();

                    if (isTopicCandidate(text, href)) {
                        return link;
                    }
                } catch (Exception ignored) {
                }
            }

            return null;
        });
    }

    private boolean isTopicCandidate(String text, String href) {
        if (text.length() < 10) {
            return false;
        }

        String upperText = text.toUpperCase();

        if (upperText.contains("МОЙ ФОРУМ")) return false;
        if (upperText.contains("НОВОЕ")) return false;
        if (upperText.contains("ПОПУЛЯРНОЕ")) return false;
        if (upperText.contains("ЭКСПЕРТЫ")) return false;
        if (upperText.contains("ПРЯМОЙ ЭФИР")) return false;
        if (upperText.contains("ДОБАВИТЬ ТЕМУ")) return false;
        if (upperText.contains("НОВЫЕ ТЕМЫ")) return false;
        if (upperText.contains("ВСЕ РАЗДЕЛЫ")) return false;

        return href.contains("woman.ru")
                && href.contains("thread-")
                && href.matches(".*id\\d+.*");
    }

    private void scrollToBlock(String blockTitle) {
        WebElement block = findVisible(By.xpath(
                "//*[contains(normalize-space(.), '" + blockTitle + "')]"
        ));

        scrollTo(block);
        demoStep("Переход к блоку тем: " + blockTitle);
        demoPause();
    }
}