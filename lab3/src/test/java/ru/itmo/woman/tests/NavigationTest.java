package ru.itmo.woman.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itmo.pages.HomePage;
import ru.itmo.pages.SectionPage;
import ru.itmo.woman.BaseTest;
import ru.itmo.woman.BrowserType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenStarsSection(BrowserType browserType) {
        startBrowser(browserType);

        SectionPage sectionPage = new HomePage(driver)
                .open()
                .openStarsSection();

        assertTrue(sectionPage.isOpened());
        assertTrue(sectionPage.hasMaterials());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenBeautySection(BrowserType browserType) {
        startBrowser(browserType);

        SectionPage sectionPage = new HomePage(driver)
                .open()
                .openBeautySection();

        assertTrue(sectionPage.isOpened());
        assertTrue(sectionPage.hasMaterials());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenFashionSection(BrowserType browserType) {
        startBrowser(browserType);

        SectionPage sectionPage = new HomePage(driver)
                .open()
                .openFashionSection();

        assertTrue(sectionPage.isOpened());
        assertTrue(sectionPage.hasMaterials());
    }
}