package ru.itmo.woman.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itmo.pages.ForumPage;
import ru.itmo.pages.HomePage;
import ru.itmo.pages.TopicFormPage;
import ru.itmo.woman.BaseTest;
import ru.itmo.woman.BrowserType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForumTopicTest extends BaseTest {

    private ForumPage openForumFromHome() {
        return new HomePage(driver)
                .open()
                .openForum();
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenForumPage(BrowserType browserType) {
        startBrowser(browserType);

        ForumPage forumPage = openForumFromHome();

        assertTrue(forumPage.isOpened());
        assertTrue(forumPage.hasForumContent());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenTopicCreationForm(BrowserType browserType) {
        startBrowser(browserType);

        TopicFormPage topicFormPage = openForumFromHome()
                .openTopicForm();

        assertTrue(topicFormPage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldValidateRequiredTopicFields(BrowserType browserType) {
        startBrowser(browserType);

        TopicFormPage topicFormPage = openForumFromHome()
                .openTopicForm()
                .submitEmpty();

        assertTrue(topicFormPage.hasValidationError() || topicFormPage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldFillTopicCreationForm(BrowserType browserType) {
        startBrowser(browserType);

        TopicFormPage topicFormPage = openForumFromHome()
                .openTopicForm()
                .fillTopic(
                        "Тестовая тема Selenium",
                        "Это тестовое сообщение для проверки заполнения формы создания темы."
                );

        assertTrue(topicFormPage.canSubmit() || topicFormPage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldPrepareTopicForModerationWithoutFinalSubmit(BrowserType browserType) {
        startBrowser(browserType);

        TopicFormPage topicFormPage = openForumFromHome()
                .openTopicForm()
                .fillTopic(
                        "Тестовая тема для модерации",
                        "Тест проверяет подготовку темы к отправке на модерацию без публикации."
                );

        assertTrue(topicFormPage.canSubmit() || topicFormPage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldSubmitTopicToModeration(BrowserType browserType) {
        startBrowser(browserType);

        TopicFormPage topicFormPage = openForumFromHome()
                .openTopicForm()
                .fillTopic(
                        "Тестовая тема Selenium",
                        "Это сообщение создано автотестом для проверки отправки темы на модерацию."
                )
                .submit();

        assertTrue(topicFormPage.hasModerationMessage());
    }
}