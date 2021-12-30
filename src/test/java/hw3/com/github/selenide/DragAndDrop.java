package hw3.com.github.selenide;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DragAndDrop {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://the-internet.herokuapp.com";
    }

//    3. (опциональное) Запрограммируйте Drag&Drop с помощью Selenide.actions()
// - Откройте https://the-internet.herokuapp.com/drag_and_drop
// - Перенесите прямоугольник А на место В
// - Проверьте, что прямоугольники действительно поменялись

    @Test
    void testDragAndDrop() {
        open("/drag_and_drop");
        String firstColumnName = "A";
        String secondColumnName = "B";
        String idColumnA = "#column-a";
        String idColumnB = "#column-b";

        //не уверен, возможно проверка ниже избыточна
        $(idColumnA)
                .shouldHave(text(firstColumnName))
                .parent()
                .$(idColumnB)
                .shouldHave(text(secondColumnName));

        $(idColumnA)
                .dragAndDropTo(idColumnB)
                .shouldHave(text(secondColumnName))
                .parent()
                .$(idColumnB)
                .shouldHave(text(firstColumnName));
    }
}
