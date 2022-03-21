package hw18.com.tricentis.demowebshop.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage {

    SelenideElement
            removeFromCartCheckbox = $("[name=removefromcart]"),
            updateCartButton = $("[name=updatecart]"),
            emptyCartText = $(".order-summary-content");

    public void removeFromCartCheckboxClick() {
        removeFromCartCheckbox.click();
    }

    public void updateCartButtonClick() {
        updateCartButton.click();
    }

    public void checkCartContent() {
        emptyCartText.shouldHave(Condition.text("Your Shopping Cart is empty!"));
    }

}
