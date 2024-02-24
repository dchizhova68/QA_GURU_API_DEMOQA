package tests;

import api.AuthorisationApi;
import api.BooksApi;
import models.loginModel.LoginResponseModel;
import org.junit.jupiter.api.Test;
import pages.PageObject;
import static io.qameta.allure.Allure.step;

public class CollectionTests extends TestBase {
    PageObject pageObject = new PageObject();
    @Test
    void deleteBookTest() {

        LoginResponseModel authResponse = step("Отправляем запрос на авторизацию", () ->
                AuthorisationApi.authResponse("studentDemoQA11", "PassW0rd!")
        );

        step("Удаляем все книги из Profile", () ->
                BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Добавляем книгу в Profile", () ->
                BooksApi.addBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Принимаем соглашение о куках", () -> {
            pageObject.consentRoot();
        });

        step("Открываем Profile", () ->
                pageObject.openPage()
        );

        step("Проверяем, что книга есть в корзине", () ->
                pageObject.checkReactTablaHaveBook("Understanding ECMAScript 6")
        );


        step("Удаляем книгу", () ->
                pageObject.deleteBook()
        );


        step("Проверяем, что книги нет в profile", () ->
                pageObject.checkReactTablaNotHaveBook("Understanding ECMAScript 6")
        );
    }
}
