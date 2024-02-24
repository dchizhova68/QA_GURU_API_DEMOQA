package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageObject {
    private SelenideElement
            consentRoot = $(".fc-consent-root"),
            consentButton = $(".fc-consent-root").$(byText("Consent")),
            reactTable = $(".ReactTable"),
            deleteButton = $("#delete-record-undefined"),
            okDeleteButton = $("#closeSmallModal-ok");

    public PageObject openPage() {
        open("/profile");
        return this;
    }

    public PageObject consentRoot() {
        if (consentRoot.isDisplayed()) {
            consentButton.click();
        }
        return this;
    }

    public PageObject checkReactTablaHaveBook(String bookName) {
        reactTable. shouldHave(text(bookName));
        return this;
    }

    public PageObject deleteBook () {
        deleteButton.click();
        okDeleteButton.click();
        return this;
    }

    public PageObject checkReactTablaNotHaveBook(String bookName) {
        reactTable.shouldNotHave(text(bookName));
        return this;
    }
}
