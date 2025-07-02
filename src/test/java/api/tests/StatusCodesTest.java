package api.tests;

import api.clients.StatusCodesClient;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

@Epic("API Тесты")
@ExtendWith(AllureJunit5.class)
public class StatusCodesTest {

    private final static String RESPONSE_TEMPLATE = "This page returned a %d status code";
    private final static int RESPONSE_TIME = 2000;

    @ParameterizedTest
    @CsvSource({
            "200, 200",
            "500, 500",
            "404, 404"
    })
    @Story("Проверка status code")
    @DisplayName("Status code")
    @Description("Проверка, что код ответа совпадает с ожидаемым. Проверка времени ответа и заголовка Content-Type")
    @Severity(SeverityLevel.NORMAL)
    public void statusCodeTest(int testCode, int expectedCode) {

        Response response = new StatusCodesClient().getStatusCodeResponse(testCode);
        String body = response.body().asString();

        assertAll(
                () -> assertEquals(expectedCode, response.statusCode(), "Неверный статус-код"),
                () -> assertTrue(body.contains(String.format(RESPONSE_TEMPLATE, expectedCode)), "Тело ответа не содержит код"),
                () -> assertTrue(response.time() < RESPONSE_TIME, String.format("Время ответа более %d мс", RESPONSE_TIME)),
                () -> assertTrue(response.header("Content-Type").contains("text/html"), "Content-Type не содержит text/html")
        );

    }

}
