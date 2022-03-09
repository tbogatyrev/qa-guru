package hw17.data;

import com.github.javafaker.Faker;
import hw17.models.User;

public class TestData {

    Faker faker = new Faker();

    public User getRandomUser() {
        User user = new User();
        user.setName(faker.name().fullName());
        user.setJob(faker.job().title());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6, 7));
        return user;
    }

    public User getUserForRegistry() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");
        return user;
    }

    public User getUserForLogin() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");
        return user;
    }
}
