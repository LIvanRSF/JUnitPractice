import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InvestValueSourceTests extends BaseTestClass {

    double criticalLowPrice = 100.1d;

    @DisplayName("Стоимость акций должна быть больше определенного значения")
    @ValueSource(strings = {"SBER", "SBER_p"})
    @ParameterizedTest(name = "Стоимость акций {0}")
    public void stockPriceTest(String stockName) {
        //Вводим в поиске название акции
        $(".searchText").setValue(stockName).pressEnter();

        //Из результатов поиска выбираем нужную строку по точному названию
        $$("div .searchSectionMain .second").find(Condition.exactText(stockName)).click();

        //Находим на странице актуальную цену, преобразовываем ее в числовое значение
        double actualPrice = Double.parseDouble(
            $("[data-test=instrument-price-last]").getText().replace(",", "."));

        //Сравниваем, что текущая цена больше определенной
        Assertions.assertTrue(actualPrice > criticalLowPrice);
    }
}
