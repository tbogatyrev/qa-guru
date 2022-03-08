package hw17.data;

import com.github.javafaker.Faker;
import hw17.models.User;

public class TestData {

    Faker faker = new Faker();

    public User getRandomUser() {
        User user = new User();
        user.setName(faker.name().fullName());
        user.setJob(faker.job().title());
        return user;
    }
}
