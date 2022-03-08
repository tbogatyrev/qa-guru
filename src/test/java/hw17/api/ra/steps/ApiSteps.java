package hw17.api.ra.steps;

import hw17.models.User;

import static hw17.api.ra.specs.ApiSpec.spec;

public class ApiSteps {

    public User createUser(User user) {
        return spec().request("/api/users")
                .body(user)
                .post()
                .then()
                .statusCode(201)
                .extract()
                .as(User.class);
    }

    public User updateUser(User user) {
        return spec().request("/api/users/{id}")
                .pathParam("id", user.getId())
                .body(user)
                .put()
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);
    }

    public void deleteUser(User user) {
        spec().request("/api/users/{id}")
                .pathParam("id", user.getId())
                .delete()
                .then()
                .statusCode(204);
    }
}
