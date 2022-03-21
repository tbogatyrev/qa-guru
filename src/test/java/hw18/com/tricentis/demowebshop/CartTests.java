package hw18.com.tricentis.demowebshop;

import com.codeborne.selenide.Selenide;
import hw18.com.tricentis.demowebshop.pages.ShoppingCartPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static hw18.com.tricentis.demowebshop.config.ConfigHelper.getBaseUrl;
import static hw18.com.tricentis.demowebshop.products.Products.PHONE_COVER;
import static io.qameta.allure.Allure.step;

public class CartTests extends TestBase {

    ShoppingCartPage shoppingCartPage;

    @Test
    void removeProductFromCart() {
        step("Предусловие: добаление товара в корзину", () -> {
            apiSteps.addProductToCart(PHONE_COVER);
        });
        step("Открываю главную страницу", () -> {
            openMainPage(getBaseUrl());
        });
        step("Перехожу в корзину", () -> {
            shoppingCartPage = mainPage.shoppingCartRefClick();
        });
        step("Выбираю товар для удаления из корзины", () -> {
            shoppingCartPage.removeFromCartCheckboxClick();
        });
        step("Удаляю товар из корзины", () -> {
            shoppingCartPage.updateCartButtonClick();
        });
        step("Товары в корзине отсутствуют", () -> {
            shoppingCartPage.checkCartContent();
        });
    }

    private void openMainPage(String url) {
        Cookie cookie = new Cookie("Nop.customer", apiSteps.getCookies(), "/");
        open("http://demowebshop.tricentis.com/content/images/thumbs/0000015_25-virtual-gift-card_300.jpeg");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(cookie);
        open(url);
    }
}
