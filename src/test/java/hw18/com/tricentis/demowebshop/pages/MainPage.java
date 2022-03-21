package hw18.com.tricentis.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    SelenideElement
            shoppingCartRef = $("#topcartlink");

    public ShoppingCartPage shoppingCartRefClick() {
        shoppingCartRef.click();
        return new ShoppingCartPage();
    }
}
