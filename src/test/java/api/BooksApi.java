package api;


import models.booksModel.AddBookBodyModel;
import models.booksModel.AddBookResponseModel;
import models.booksModel.IsbnBookModel;
import models.loginModel.LoginRequestModel;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static specs.Specs.*;

public class BooksApi {
    public static void deleteAllBooks(String token, String userId) {
        given(booksRequest)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete()
                .then()
                .log().all()
                .spec(successDeleteAllBooksResponse);
    }

    public static AddBookResponseModel addBooks(String token, String userId) {
        IsbnBookModel isbnBook = new IsbnBookModel();
        isbnBook.setIsbn("9781593277574");
        ArrayList collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(isbnBook);

        AddBookBodyModel dataBook = new AddBookBodyModel();
        dataBook.setCollectionOfIsbns(collectionOfIsbns);
        dataBook.setUserId(userId);

        return given(booksRequest)
                .header("Authorization", "Bearer " + token)
                .body(dataBook)
                .when()
                .post()
                .then()
                .log().all()
                .spec(successAddBooksResponse)
                .extract().as(AddBookResponseModel.class);
    }
}
