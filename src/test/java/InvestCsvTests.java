import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class InvestCsvTests {

    @DisplayName("Тесты с CSV хардкод")
    @CsvSource(value = {
        "SBER, 100",
        "SBER_p, 90"
    })
    @ParameterizedTest(name = "Стоимость акции {0} должна быть выше значения {1}")
    public void csvInvestingTests(String stockName, double criticalLowPrice) {
        //Открываем сайт
        Selenide.open("https://ru.investing.com/");

        //Вводим в поиске название акции
        $(".searchText").setValue(stockName).pressEnter();

        //Из результатов поиска выбираем нужную строку по точному названию
        $$("div .searchSectionMain .second").find(Condition.exactText(stockName)).click();

        //Находим на странице актуальную цену, преобразовываем ее в числовое значение
        double actualPrice = Double.parseDouble(
            $("[data-test=instrument-price-last]").getText().replace(",", "."));

        //Сравниваем, что текущая цена больше определенной
        Assertions.assertTrue(actualPrice > criticalLowPrice);

        //приходится закрывать ВебДрайвер перед вторым тестом, т.к. на сайте срабатывает проверка "на робота"
        closeWebDriver();
    }

    @DisplayName("Тесты с CSV из файла")
    @CsvFileSource (resources = "stocks.csv")
    @ParameterizedTest(name = "Стоимость акции {0} должна быть выше значения {1}")
    public void csvFileInvestingTests(String stockName, double criticalLowPrice) {
        //Открываем сайт
        Selenide.open("https://ru.investing.com/");

        //Вводим в поиске название акции
        $(".searchText").setValue(stockName).pressEnter();

        //Из результатов поиска выбираем нужную строку по точному названию
        $$("div .searchSectionMain .second").find(Condition.exactText(stockName)).click();

        //Находим на странице актуальную цену, преобразовываем ее в числовое значение
        double actualPrice = Double.parseDouble(
            $("[data-test=instrument-price-last]").getText().replace(",", "."));

        //Сравниваем, что текущая цена больше определенной
        Assertions.assertTrue(actualPrice > criticalLowPrice);

        //приходится закрывать ВебДрайвер перед вторым тестом, т.к. на сайте срабатывает проверка "на робота"
        closeWebDriver();
    }
}
