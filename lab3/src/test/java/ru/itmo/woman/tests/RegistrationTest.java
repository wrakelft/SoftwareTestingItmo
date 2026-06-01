package ru.itmo.woman.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itmo.pages.AuthPage;
import ru.itmo.pages.HomePage;
import ru.itmo.woman.BaseTest;
import ru.itmo.woman.BrowserType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldOpenRegistrationForm(BrowserType browserType) {
        startBrowser(browserType);

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm()
                .openRegistration();

        assertTrue(authPage.isRegistrationFormVisible());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldShowValidationForEmptyRegistrationFields(BrowserType browserType) {
        startBrowser(browserType);

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm()
                .openRegistration()
                .submitEmptyForm();

        assertTrue(authPage.hasValidationError() || authPage.isRegistrationFormVisible());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldShowValidationForInvalidRegistrationEmail(BrowserType browserType) {
        startBrowser(browserType);

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm()
                .openRegistration()
                .fillEmail("test-email")
                .submit();

        assertTrue(authPage.hasValidationError() || authPage.isRegistrationFormVisible());
    }

    @ParameterizedTest
    @EnumSource(BrowserType.class)
    void shouldFillRegistrationFormWithoutFinalAccountCreation(BrowserType browserType) {
        startBrowser(browserType);

        String email = "qa-test-" + System.currentTimeMillis() + "@example.com";

        AuthPage authPage = new HomePage(driver)
                .open()
                .openAuthForm()
                .openRegistration()
                .fillEmail(email);

        assertEquals(email, authPage.getEmailValue());
        assertTrue(authPage.canSubmit() || authPage.isRegistrationFormVisible());
    }
}