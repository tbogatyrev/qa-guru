package hw17.api.ra.filters;

import io.qameta.allure.restassured.AllureRestAssured;

public class LogFilter {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private static class InitLogFilter {
        private static final LogFilter logFilter = new LogFilter();
    }

    private LogFilter() {
    }

    public static LogFilter filters() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }
}