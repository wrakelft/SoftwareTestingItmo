package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TopicFormPage extends Page {

    private final By form = By.xpath("//form | //*[@role='dialog'] | //*[contains(@class, 'modal') or contains(@class, 'popup')]");

    private final By titleInput = By.xpath(
            "//input[not(@type='hidden') and not(@type='submit') and not(@type='button')]" +
                    "[contains(@placeholder, 'Тема') " +
                    "or contains(@placeholder, 'Заголовок') " +
                    "or contains(@placeholder, 'Название') " +
                    "or contains(@name, 'title') " +
                    "or contains(@name, 'subject')]"
    );

    private final By messageInput = By.xpath(
            "//textarea " +
                    "| //*[@contenteditable='true'] " +
                    "| //div[contains(@class, 'editor')]"
    );

    private final By anyTextFields = By.xpath(
            "//textarea " +
                    "| //*[@contenteditable='true'] " +
                    "| //input[not(@type='hidden') and not(@type='submit') and not(@type='button') and not(@type='checkbox') and not(@type='radio')]"
    );

    private final By submitButton = By.xpath(
            "//*[self::button or self::input or @role='button']" +
                    "[contains(normalize-space(.), 'Отправить') " +
                    "or contains(normalize-space(.), 'Добавить') " +
                    "or contains(normalize-space(.), 'Продолжить') " +
                    "or contains(@value, 'Отправить') " +
                    "or contains(@value, 'Добавить') " +
                    "or contains(@value, 'Продолжить')]"
    );

    private final By validationError = By.xpath(
            "//*[contains(@class, 'error') " +
                    "or contains(@class, 'invalid') " +
                    "or contains(text(), 'обязатель') " +
                    "or contains(text(), 'ошиб') " +
                    "or contains(text(), 'заполн')]"
    );

    private final By moderationMessage = By.xpath(
            "//*[contains(translate(text(), 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ', 'абвгдеёжзийклмнопрстуфхцчшщъыьэюя'), 'модерац') " +
                    "or contains(translate(text(), 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ', 'абвгдеёжзийклмнопрстуфхцчшщъыьэюя'), 'провер') " +
                    "or contains(translate(text(), 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ', 'абвгдеёжзийклмнопрстуфхцчшщъыьэюя'), 'опублик')]"
    );


    public TopicFormPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(form, anyTextFields, submitButton);
    }

    public TopicFormPage submitEmpty() {
        clickFirst(submitButton);
        return this;
    }

    public TopicFormPage fillTopic(String title, String message) {
        if (isVisible(titleInput)) {
            typeFirst(title, titleInput);
        } else {
            typeIntoVisibleFieldByIndex(0, title);
        }

        if (isVisible(messageInput)) {
            typeFirst(message, messageInput);
        } else {
            typeIntoVisibleFieldByIndex(1, message);
        }

        return this;
    }

    private void typeIntoVisibleFieldByIndex(int index, String text) {
        List<WebElement> fields = driver.findElements(anyTextFields)
                .stream()
                .filter(element -> {
                    try {
                        return element.isDisplayed() && element.isEnabled();
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toList();

        if (fields.size() <= index) {
            throw new IllegalStateException("Не найдено поле формы с индексом " + index);
        }

        WebElement field = fields.get(index);
        scrollTo(field);
        highlight(field);
        demoPause();

        field.clear();
        field.sendKeys(text);

        demoStep("Ввод текста в поле формы: " + text);
        demoPause();
    }

    public boolean hasValidationError() {
        return isVisible(validationError);
    }

    public boolean canSubmit() {
        return isClickable(submitButton);
    }

    public TopicFormPage submit() {
        clickFirst(submitButton);
        demoStep("URL после отправки темы: " + driver.getCurrentUrl());
        demoStep("Текст страницы после отправки: " + getPageText());
        return this;
    }

    public boolean hasModerationMessage() {
        boolean result = isVisible(moderationMessage);
        demoStep("Найдено сообщение о модерации: " + result);
        return result;
    }

    public String getPageText() {
        return driver.findElement(By.xpath("//body")).getText();
    }
}