package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopicPage extends Page {

    private final By title = By.xpath("//h1 | //h2");

    private final By messages = By.xpath(
            "//main//p | //*[contains(@class, 'message') or contains(@class, 'comment')]"
    );

    private final By answerInput = By.xpath(
            "//textarea | //*[@contenteditable='true']"
    );

    private final By answerButton = By.xpath(
            "//*[self::button or self::a]" +
                    "[contains(normalize-space(.), 'Ответить') " +
                    "or contains(normalize-space(.), 'Добавить ответ') " +
                    "or contains(normalize-space(.), 'Написать ответ')]"
    );

    private final By submitButton = By.xpath(
            "//*[self::button or self::input]" +
                    "[contains(normalize-space(.), 'Добавить ответ') " +
                    "or contains(normalize-space(.), 'Отправить') " +
                    "or contains(normalize-space(.), 'Продолжить') " +
                    "or contains(@value, 'Отправить') " +
                    "or contains(@value, 'Добавить')]"
    );

    private final By validationError = By.xpath(
            "//*[contains(@class, 'error') " +
                    "or contains(@class, 'invalid') " +
                    "or contains(text(), 'обязатель') " +
                    "or contains(text(), 'ошиб') " +
                    "or contains(text(), 'заполн')]"
    );

    private final By acceptedMessage = By.xpath(
            "//*[contains(text(), 'добавлен') " +
                    "or contains(text(), 'принят') " +
                    "or contains(text(), 'модерац') " +
                    "or contains(text(), 'провер')]"
    );

    private final By continueButton = By.xpath(
            "//*[self::button or self::input or @role='button']" +
                    "[contains(normalize-space(.), 'Продолжить') " +
                    "or contains(@value, 'Продолжить')]"
    );

    private final By finalAddAnswerButton = By.xpath(
            "//*[self::button or self::input or @role='button']" +
                    "[contains(normalize-space(.), 'Добавить ответ') " +
                    "or contains(@value, 'Добавить ответ')]"
    );

    private final By answerModal = By.xpath(
            "//*[contains(normalize-space(.), 'Добавить ответ') " +
                    "and (contains(@class, 'modal') or ancestor::*[contains(@class, 'modal')] or ancestor::*[@role='dialog'])] " +
                    "| //*[@role='dialog']"
    );

    public TopicPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(title);
    }

    public boolean hasMessages() {
        return isVisible(messages);
    }

    public boolean hasAnswerForm() {
        return isVisible(answerInput) || isVisible(answerButton);
    }

    public TopicPage openAnswerFormIfNeeded() {
        if (!isVisible(answerInput) && isVisible(answerButton)) {
            clickFirst(answerButton);
        }
        return this;
    }

    public TopicPage fillAnswer(String text) {
        openAnswerFormIfNeeded();
        typeFirst(text, answerInput);
        return this;
    }

    public TopicPage submitEmptyAnswer() {
        openAnswerFormIfNeeded();
        clickFirst(submitButton);
        return this;
    }

    public boolean hasValidationError() {
        return isVisible(validationError);
    }

    public boolean canSubmitAnswer() {
        return isClickable(submitButton);
    }

    public TopicPage submitAnswer() {
        openAnswerFormIfNeeded();

        if (isClickable(continueButton)) {
            clickFirst(continueButton);
            demoStep("Нажата кнопка «Продолжить», открывается окно подтверждения ответа.");
        } else {
            clickFirst(submitButton);
            demoStep("Нажата основная кнопка отправки ответа.");
        }

        if (isVisible(answerModal) || isClickable(finalAddAnswerButton)) {
            demoStep("Открыто окно подтверждения добавления ответа.");

            clickFirst(finalAddAnswerButton);
            demoStep("Нажата финальная кнопка «Добавить ответ».");
        }

        return this;
    }

    public boolean hasAcceptedMessage() {
        return isVisible(acceptedMessage)
                || driver.getPageSource().toLowerCase().contains("ответ")
                || driver.getPageSource().toLowerCase().contains("модерац")
                || driver.getPageSource().toLowerCase().contains("провер");
    }
}