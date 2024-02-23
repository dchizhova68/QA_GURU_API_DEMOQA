package api;

import models.loginModel.LoginRequestModel;
import models.loginModel.LoginResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.Specs.loginRequest;
import static specs.Specs.successfulLoginResponse;

public class AuthorisationApi {
    public static LoginResponseModel authResponse(String login, String password) {
        LoginRequestModel userData = new LoginRequestModel();
        userData.setUserName(login);
        userData.setPassword(password);

      LoginResponseModel authResponse = given(loginRequest)
                        .body(userData)
                        .when()
                        .post()
                        .then()
                        .log().all()
                        .spec(successfulLoginResponse)
                        .extract().as(LoginResponseModel.class);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));

        return authResponse;

    }
}
