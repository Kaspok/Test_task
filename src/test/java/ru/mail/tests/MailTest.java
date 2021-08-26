package ru.mail.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.pages.MailPage;

public class MailTest {


    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://mail.ru";
        Configuration.startMaximized = true;
    }

    @Test
    void sendingMessageTest() {
        MailPage mail = new MailPage();
        mail.openPage("")
                .inputLogin()
                .inputPassword()
                .openFormSendMessage()
                .sendMessage("test.ures@mail.ru", "Тест", "This is a test")
                .checkSendingMessage();
    }
}
