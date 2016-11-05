package ru.wb.tests;

import org.testng.annotations.Test;
import ru.wb.tests.pages.market.MarketCatalogPage;
import ru.wb.tests.pages.market.MarketMainPage;
import ru.wb.tests.utils.BaseTest;
import ru.wb.tests.utils.Host;

import static org.testng.Assert.assertTrue;

public class MarketTest extends BaseTest {

    public static final String SEARCH_QUERY = "nokia";
    public static final String MOBILE_PHONE_NAME = "Nokia Lumia 520 White";

    @Test(description = "Проверяем наличие определеннго телефона в Яндекс.Маркете с использованием пейджинга")
    public void searchCertainNokiaMobilePhone() {
        //1. Открывает http://market.yandex.ru/
        MarketMainPage marketMainPage = open(Host.MARKET);
        //2. Производит поиск по ключевому слову "nokia"
        MarketCatalogPage marketCatalogPage = marketMainPage.doSearch(SEARCH_QUERY)
        //3. Переходит в категорию "Мобильные телефоны"
                .clickByMobile();
        //4. Находит телефон "Nokia Lumia 520 White" перебором по всем страницам (долгий вариант)
        marketCatalogPage.checkForGoodIsPresent(MOBILE_PHONE_NAME);
        assertTrue(marketCatalogPage.isGoodPresentByTitle(MOBILE_PHONE_NAME),
                String.format("Телефон '%s' должен отображаться на странице", MOBILE_PHONE_NAME));
    }

    @Test(description = "Проверяем наличие определеннго телефона в Яндекс.Маркете с использованием фильтра поиска")
    public void searchCertainNokiaMobilePhoneWithFilter() {
        //1. Открывает http://market.yandex.ru/
        MarketMainPage marketMainPage = open(Host.MARKET);
        //2. Производит поиск по ключевому слову "nokia"
        MarketCatalogPage marketCatalogPage = marketMainPage.doSearch(SEARCH_QUERY)
        //3. Переходит в категорию "Мобильные телефоны"
                .clickByMobile();
        //4. Находит телефон "Nokia Lumia 520 White" с помощью фильтра поиска(быстрый вариант)
        marketCatalogPage.doSearchFilter();
        assertTrue(marketCatalogPage.isGoodPresentByTitle(MOBILE_PHONE_NAME),
                String.format("Телефон '%s' должен отображаться на странице", MOBILE_PHONE_NAME));

    }
}
