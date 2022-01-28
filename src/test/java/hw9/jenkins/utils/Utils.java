package hw9.jenkins.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static java.nio.file.Files.readString;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

public class Utils {

    public static String getFileContent(String fileName) {
        String content = null;
        try {
            content = readString(get(requireNonNull(Utils.class.getClassLoader().getResource(fileName)).toURI()), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Невозможно прочитать содержимое файла " + fileName + " или файл отсутствует");
            e.printStackTrace();
        }
        return content;
    }

    public static <T> T convertResponseToModel(String browserResponse, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T model = null;
        try {
            model = objectMapper.readValue(browserResponse, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return model;
    }


    public static String getBrowserResponseExecutingJs(String jsCode) {
        String browserResponse = executeJavaScript(getFileContent(jsCode));
        System.out.println("browserResponse:\n" + browserResponse);
        return browserResponse;
    }
}
