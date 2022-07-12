import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class InvestMethodSourceTests {

    static Stream<Arguments> investTestDataProvider() {
        return Stream.of(
            Arguments.of("AAPL"),
            Arguments.of("TSLA")
        );
    }

    @DisplayName("Инвест тест с данными из Method Source")
    @MethodSource(value = "investTestDataProvider")
    @ParameterizedTest(name = "На странице акции {0}  должен быть видна ее стоимость")
    public void methodSourceInvestTest(String stockName) {
        //Открываем сайт
        Selenide.open("https://ru.investing.com/");

        //Вводим в поиске название акции
        $(".searchText").setValue(stockName).pressEnter();

        //Из результатов поиска выбираем нужную строку по точному названию
        $$("div .searchSectionMain .second").find(Condition.exactText(stockName)).click();

        //Находим на странице актуальную цену, проверяем ее на видимость
        $("[data-test=instrument-price-last]").shouldBe(Condition.visible);

        //приходится закрывать ВебДрайвер перед вторым тестом, т.к. на сайте срабатывает проверка "на робота"
        closeWebDriver();

    }
}
