package ru.itmo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AuthPage extends Page {

    private final By authDialog = By.xpath(
            "//*[contains(normalize-space(.), 'Вход') " +
                    "and contains(normalize-space(.), 'Регистрация') " +
                    "and .//input[contains(@placeholder, 'Почта') or @type='email']]"
    );

    private final By registrationButton = By.xpath(
            "//*[self::button or self::a]" +
                    "[contains(normalize-space(.), 'Регистрация') " +
                    "or contains(normalize-space(.), 'Зарегистрироваться')]"
    );

    private final By emailInput = By.xpath(
            "(" +
                    "//*[contains(normalize-space(.), 'Вход') " +
                    "and contains(normalize-space(.), 'Регистрация')]" +
                    "//input[@type='email' " +
                    "or contains(@placeholder, 'Почта') " +
                    "or contains(@placeholder, 'почта') " +
                    "or contains(@name, 'email')]" +
                    ")[1]"
    );

    private final By passwordInput = By.xpath(
            "(" +
                    "//*[contains(normalize-space(.), 'Вход') " +
                    "and contains(normalize-space(.), 'Регистрация')]" +
                    "//input[@type='password' " +
                    "or contains(@placeholder, 'Пароль') " +
                    "or contains(@placeholder, 'пароль') " +
                    "or contains(@name, 'password')]" +
                    ")[1]"
    );

    private final By submitButton = By.xpath(
            "(" +
                    "//*[contains(normalize-space(.), 'Вход') " +
                    "and contains(normalize-space(.), 'Регистрация')]" +
                    "//*[self::button or self::input or @role='button']" +
                    "[contains(normalize-space(.), 'Войти') " +
                    "or contains(normalize-space(.), 'Продолжить') " +
                    "or contains(normalize-space(.), 'Зарегистрироваться') " +
                    "or contains(@value, 'Войти') " +
                    "or contains(@value, 'Продолжить') " +
                    "or contains(@value, 'Зарегистрироваться')]" +
                    ")[1]"
    );

    private final By validationError = By.xpath(
            "//*[contains(@class, 'error') " +
                    "or contains(@class, 'invalid') " +
                    "or contains(text(), 'ошиб') " +
                    "or contains(text(), 'Ошибка') " +
                    "or contains(text(), 'обязатель') " +
                    "or contains(text(), 'некоррект')]"
    );

    private final By profileElement = By.xpath(
            "//*[contains(@class, 'profile') " +
                    "or contains(@class, 'avatar') " +
                    "or contains(text(), 'Профиль') " +
                    "or contains(text(), 'Выйти')]"
    );

    private final By registrationByEmailButton = By.xpath(
            "//*[contains(normalize-space(.), 'Регистрация')]" +
                    "/following::*[self::button or self::a or @role='button']" +
                    "[contains(normalize-space(.), 'Почта')][1]"
    );

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(authDialog, emailInput);
    }

    public AuthPage openRegistration() {
        clickFirst(registrationButton);

        demoStep("Выбор регистрации через почту");
        clickFirst(registrationByEmailButton);

        findVisible(emailInput);

        return this;
    }

    public boolean isRegistrationFormVisible() {
        return isVisible(emailInput);
    }

    public AuthPage submitEmptyForm() {
        acceptAgreements();
        clickFirst(submitButton);
        return this;
    }

    public AuthPage fillEmail(String email) {
        typeFirst(email, emailInput);
        return this;
    }

    public AuthPage fillPassword(String password) {
        typeFirst(password, passwordInput);
        return this;
    }

    public AuthPage fillCredentials(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        return this;
    }

    public AuthPage submit() {
        acceptAgreements();
        clickFirst(submitButton);
        return this;
    }

    public AuthPage acceptAgreements() {
        Object result = ((JavascriptExecutor) driver).executeScript(
                """
                const phrases = ['Даю согласие', 'Политикой'];
                const clicked = [];
    
                function isVisibleInViewport(el) {
                    const rect = el.getBoundingClientRect();
                    const style = window.getComputedStyle(el);
    
                    return rect.width > 0 &&
                           rect.height > 0 &&
                           rect.top >= 0 &&
                           rect.left >= 0 &&
                           rect.bottom <= window.innerHeight &&
                           rect.right <= window.innerWidth &&
                           style.display !== 'none' &&
                           style.visibility !== 'hidden' &&
                           style.opacity !== '0';
                }
    
                function dispatchRealClick(el) {
                    const rect = el.getBoundingClientRect();
                    const x = Math.round(rect.left + rect.width / 2);
                    const y = Math.round(rect.top + rect.height / 2);
    
                    const target = document.elementFromPoint(x, y) || el;
    
                    target.dispatchEvent(new MouseEvent('mouseover', {
                        bubbles: true,
                        cancelable: true,
                        view: window,
                        clientX: x,
                        clientY: y
                    }));
    
                    target.dispatchEvent(new MouseEvent('mousedown', {
                        bubbles: true,
                        cancelable: true,
                        view: window,
                        clientX: x,
                        clientY: y
                    }));
    
                    target.dispatchEvent(new MouseEvent('mouseup', {
                        bubbles: true,
                        cancelable: true,
                        view: window,
                        clientX: x,
                        clientY: y
                    }));
    
                    target.dispatchEvent(new MouseEvent('click', {
                        bubbles: true,
                        cancelable: true,
                        view: window,
                        clientX: x,
                        clientY: y
                    }));
                }
    
                for (const phrase of phrases) {
                    const candidates = Array.from(document.querySelectorAll('label, div, span, p'))
                        .filter(el => isVisibleInViewport(el))
                        .filter(el => (el.innerText || el.textContent || '').includes(phrase))
                        .filter(el => !['BODY', 'HTML'].includes(el.tagName));
    
                    if (candidates.length === 0) {
                        clicked.push('NOT_FOUND: ' + phrase);
                        continue;
                    }
    
                    candidates.sort((a, b) => {
                        const ar = a.getBoundingClientRect();
                        const br = b.getBoundingClientRect();
    
                        // Берём самый компактный элемент, а не огромный контейнер.
                        const areaA = ar.width * ar.height;
                        const areaB = br.width * br.height;
    
                        return areaA - areaB;
                    });
    
                    const textElement = candidates[0];
    
                    // Если текст лежит внутри label — кликаем label.
                    const label = textElement.closest('label');
                    if (label && isVisibleInViewport(label)) {
                        dispatchRealClick(label);
                        clicked.push('LABEL: ' + phrase);
                        continue;
                    }
    
                    // Если рядом есть checkbox внутри ближайших родителей — кликаем его.
                    let current = textElement;
                    let checkbox = null;
    
                    for (let i = 0; i < 5 && current; i++) {
                        checkbox = current.querySelector?.('input[type="checkbox"], [role="checkbox"]');
                        if (checkbox) {
                            break;
                        }
                        current = current.parentElement;
                    }
    
                    if (checkbox) {
                        dispatchRealClick(checkbox);
                        clicked.push('CHECKBOX: ' + phrase);
                        continue;
                    }
    
                    // Последний вариант — кликаем сам видимый текст.
                    dispatchRealClick(textElement);
                    clicked.push('TEXT: ' + phrase);
                }
    
                return clicked.join('; ');
                """
        );

        demoStep("Результат нажатия согласий: " + result);
        demoPause();

        demoStep("Кнопка входа активна после чекбоксов: " + isClickable(submitButton));

        return this;
    }

    public boolean hasValidationError() {
        return isVisible(validationError);
    }

    public boolean canSubmit() {
        return isClickable(submitButton);
    }

    public boolean isUserAuthorized() {
        return isVisible(profileElement);
    }

    public String getEmailValue() {
        return getValue(emailInput);
    }



    public AuthPage waitForOpened() {
        findVisible(authDialog);
        findVisible(emailInput);
        return this;
    }
}