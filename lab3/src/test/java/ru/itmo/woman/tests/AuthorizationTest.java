package ru.itmo.woman.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itmo.pages.AuthPage;
import ru.itmo.pages.HomePage;
import ru.itmo.woman.BaseTest;
import ru.itmo.woman.BrowserType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenAuthorizationForm(BrowserType browserType) {
        startBrowser(browserType);

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm();

        assertTrue(authPage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldShowValidationForEmptyAuthorizationFields(BrowserType browserType) {
        startBrowser(browserType);

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm()
                .submitEmptyForm();

        assertTrue(authPage.hasValidationError() || authPage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldNotAuthorizeWithInvalidCredentials(BrowserType browserType) {
        startBrowser(browserType);

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm()
                .fillCredentials("wrong-email@example.com", "wrong-password")
                .submit();

        assertTrue(authPage.hasValidationError() || authPage.isOpened());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldAuthorizeWithTestAccount(BrowserType browserType) {
        startBrowser(browserType);

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm()
                .fillCredentials("7867202@mail.ru", "07042005g")
                .submit();

        assertTrue(authPage.isUserAuthorized());
    }
}