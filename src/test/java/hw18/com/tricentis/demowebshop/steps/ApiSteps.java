package hw18.com.tricentis.demowebshop.steps;

import hw18.com.tricentis.demowebshop.products.Products;

import static hw18.com.tricentis.demowebshop.specs.ApiSpec.spec;
import static org.hamcrest.CoreMatchers.is;

public class ApiSteps {

    String cookies;

    public String getCookies() {
        if (cookies == null) {
            cookies = spec().request("/")
                    .get()
                    .then()
                    .statusCode(200)
                    .extract()
                    .cookie("Nop.customer");
        }
        return cookies;
    }

    public void addProductToCart(Products productName) {
        spec().request("/addproducttocart/details/{productId}/1")
                .pathParam("productId", productName.getProductId())
                .body("product_attribute_80_2_37=112&product_attribute_80_1_38=114&addtocart_80.EnteredQuantity=1")
                .cookie("Nop.customer", getCookies() + ";")
                .post()
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"))
                .body("updatetopcartsectionhtml", is("(1)"));
    }
}
