package tests;

import api.AuthorisationApi;
import api.BooksApi;
import models.loginModel.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CollectionTests extends TestBase {
    @Test
    void deleteBookTest() {

        LoginResponseModel authResponse = step("Отправляем запрос на авторизацию", () ->
                AuthorisationApi.authResponse("studentDemoQA11","PassW0rd!")
        );

        step("Удаляем все книги из Profile", () ->
                BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Добавляем книгу в Profile", () ->
                BooksApi.addBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Открываем Profile", () ->
                open("/profile")
        );
        step("Проверяем, что книга есть в корзине", () ->
                $(".ReactTable").shouldHave(text("Understanding ECMAScript 6"))
        );

        step("Открываем корзину", () ->
                $("#delete-record-undefined").click()
        );

        step("Удаляем книгу", () ->
                $("#closeSmallModal-ok").click()
        );

        step("Проверяем, что книги нет в корзине", () ->
                $(".ReactTable").shouldNotHave(text("Understanding ECMAScript 6"))
        );

    }
}
