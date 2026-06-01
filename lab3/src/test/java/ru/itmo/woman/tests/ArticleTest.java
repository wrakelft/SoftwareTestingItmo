package ru.itmo.woman.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itmo.pages.ArticlePage;
import ru.itmo.pages.HomePage;
import ru.itmo.pages.SectionPage;
import ru.itmo.woman.BaseTest;
import ru.itmo.woman.BrowserType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticleTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenArticleFromStarsSection(BrowserType browserType) {
        startBrowser(browserType);

        ArticlePage articlePage = new HomePage(driver)
                .open()
                .openStarsSection()
                .openFirstArticle();

        assertTrue(articlePage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldShowArticleTitle(BrowserType browserType) {
        startBrowser(browserType);

        ArticlePage articlePage = new HomePage(driver)
                .open()
                .openStarsSection()
                .openFirstArticle();

        assertTrue(articlePage.hasTitle());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldShowArticleBodyText(BrowserType browserType) {
        startBrowser(browserType);

        ArticlePage articlePage = new HomePage(driver)
                .open()
                .openStarsSection()
                .openFirstArticle();

        assertTrue(articlePage.hasBodyText());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldShowAdditionalArticleElements(BrowserType browserType) {
        startBrowser(browserType);

        ArticlePage articlePage = new HomePage(driver)
                .open()
                .openStarsSection()
                .openFirstArticle();

        assertTrue(articlePage.hasAdditionalElements());
    }
}