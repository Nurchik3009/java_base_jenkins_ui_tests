package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            stateCityWrapper = $("#stateCity-wrapper"),
            cityInput = $("#city"),
            submitButton = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();

    @Step("Открыть страницу регистрации /automation-practice-form")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Удалить баннеры и футер")
    public RegistrationPage removeBanners() {
        executeJavaScript("const fix = document.getElementById('fixedban'); if(fix) fix.remove();");
        executeJavaScript("const ft = document.querySelector('footer'); if(ft) ft.remove();");
        return this;
    }

    @Step("Ввести имя: \"{value}\"")
    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Ввести фамилию: \"{value}\"")
    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Ввести email: \"{value}\"")
    public RegistrationPage typeEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Выбрать пол: \"{value}\"")
    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Ввести номер телефона: \"{value}\"")
    public RegistrationPage typePhoneNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Указать дату рождения: {day} {month} {year}")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Ввести предмет: \"{value}\"")
    public RegistrationPage typeSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбрать хобби: \"{value}\"")
    public RegistrationPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Загрузить картинку: \"{value}\"")
    public RegistrationPage uploadPicture(String value) {
        uploadPictureInput.uploadFromClasspath("img/" + value);
        return this;
    }

    @Step("Ввести текущий адрес: \"{value}\"")
    public RegistrationPage typeCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Выбрать штат \"{state}\" и город \"{city}\"")
    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.click();
        stateCityWrapper.$(byText(state)).click();
        cityInput.click();
        stateCityWrapper.$(byText(city)).click();
        return this;
    }

    @Step("Нажать кнопку Submit")
    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    @Step("Проверить, что появилось модальное окно с результатом")
    public RegistrationPage checkRegistrationResult() {
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    @Step("Проверить, что поле \"{key}\" имеет значение \"{value}\"")
    public RegistrationPage checkResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));
        return this;
    }
}