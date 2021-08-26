package ru.mail.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MailPage {

    private static final String LOGIN = "test.ures";
    private static final String PASSWORD = "qwe789asd321";

    @Step("Открываем начальную страницу")
    public MailPage openPage(String page) {
        open(page);
        return this;
    }

    @Step("Вводим логин от почты")
    public MailPage inputLogin() {
        $("[name=login]").setValue(LOGIN);
        return this;
    }

    @Step("Вводим пароль от почты и авторизуемся")
    public MailPage inputPassword() {
        $("[data-testid=enter-password]").click();
        $("[name=password]").setValue(PASSWORD).pressEnter();
        return this;
    }

    @Step("Открываем форму для отправки письма")
    public MailPage openFormSendMessage() {
        $(".sidebar__full").$("[title='Написать письмо']").click();
        return this;
    }

    @Step("Вводим адрес - {mail}, тему - {subject}, тело сообщения {message}. Отправляем сообщение")
    public MailPage sendMessage(String mail, String subject, String message) {
        $(".head_container--3W05z").$(".container--H9L5q").setValue(mail);
        $(".subject__wrapper--2mk6m").$("[name=Subject]").setValue(subject);
        $(".cke_editable_inline").$("div").sendKeys(message);
        $(".compose-app__footer").$(byText("Отправить")).click();
        return this;
    }

    @Step("Проверяем, что вышло сообщение 'Письмо отправлено'")
    public MailPage checkSendingMessage() {
        $(".layer-sent-page").$(".layer__link").shouldHave(exactTextCaseSensitive("Письмо отправлено"));
        return this;
    }
}
