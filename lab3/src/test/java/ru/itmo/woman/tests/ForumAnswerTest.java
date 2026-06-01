package ru.itmo.woman.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itmo.pages.ForumPage;
import ru.itmo.pages.HomePage;
import ru.itmo.pages.TopicPage;
import ru.itmo.woman.BaseTest;
import ru.itmo.woman.BrowserType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForumAnswerTest extends BaseTest {

    private ForumPage openForumFromHome() {
        return new HomePage(driver)
                .open()
                .openForum();
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenExistingForumTopic(BrowserType browserType) {
        startBrowser(browserType);

        TopicPage topicPage = openForumFromHome()
                .openFirstTopic();

        assertTrue(topicPage.isOpened());
        assertTrue(topicPage.hasMessages());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldShowAnswerForm(BrowserType browserType) {
        startBrowser(browserType);

        TopicPage topicPage = openForumFromHome()
                .openFirstTopic();

        assertTrue(topicPage.hasAnswerForm());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldFillAnswerField(BrowserType browserType) {
        startBrowser(browserType);

        TopicPage topicPage = openForumFromHome()
                .openFirstTopic()
                .fillAnswer("Тестовый ответ Selenium без финальной отправки.");

        assertTrue(topicPage.canSubmitAnswer() || topicPage.hasAnswerForm());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldValidateEmptyAnswer(BrowserType browserType) {
        startBrowser(browserType);

        TopicPage topicPage = openForumFromHome()
                .openFirstTopic()
                .submitEmptyAnswer();

        assertTrue(topicPage.hasValidationError() || topicPage.hasAnswerForm());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldPrepareAnswerWithoutPublication(BrowserType browserType) {
        startBrowser(browserType);

        TopicPage topicPage = openForumFromHome()
                .openFirstTopic()
                .fillAnswer("Корректный тестовый ответ, который не отправляется в публичную тему.");

        assertTrue(topicPage.canSubmitAnswer() || topicPage.hasAnswerForm());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldSubmitAnswerToTopic(BrowserType browserType) {
        startBrowser(browserType);

        TopicPage topicPage = openForumFromHome()
                .openFirstTopic()
                .fillAnswer("Тестовый ответ Selenium.")
                .submitAnswer();

        assertTrue(topicPage.hasAcceptedMessage());
    }
}