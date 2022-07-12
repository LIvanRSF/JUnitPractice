import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class InvestEnumTests {

    @DisplayName("Инвест тесты с ENUM")
    @EnumSource(NasdaqStocks.class)
    @ParameterizedTest()
    public void enumInvestTest(NasdaqStocks stockName) {
        //Открываем сайт
        Selenide.open("https://ru.investing.com/");

        //Вводим в поиске название акции
        $(".searchText").setValue(stockName.desc).pressEnter();

        //Из результатов поиска выбираем нужную строку по точному названию
        $$("div .searchSectionMain .second").find(Condition.exactText(stockName.desc)).click();

        //Находим на странице актуальную цену, проверяем ее на видимость
        $("[data-test=instrument-price-last]").shouldBe(Condition.visible);

        //приходится закрывать ВебДрайвер перед вторым тестом, т.к. на сайте срабатывает проверка "на робота"
        closeWebDriver();
    }
}
