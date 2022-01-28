package hw9.jenkins;

import com.github.javafaker.Faker;

public class TestData {

    private static final Faker dataFaker = new Faker();

    public static String firstName = dataFaker.name().firstName(),
            lastName = dataFaker.name().lastName(),
            studentEmail = dataFaker.internet().emailAddress(),
            address = dataFaker.address().fullAddress(),
            studentPhoneNumber = dataFaker.phoneNumber().subscriberNumber(10);
}
