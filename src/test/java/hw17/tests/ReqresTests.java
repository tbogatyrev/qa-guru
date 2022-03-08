package hw17.tests;

import hw17.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReqresTests extends TestBase {

    @Test
    @DisplayName("Создание пользователя")
    public void createUserTest() {
        User user = testData.getRandomUser();
        User createdUser = apiSteps.createUser(user);

        assertThat(user.getName()).isEqualTo(createdUser.getName());
        assertThat(user.getJob()).isEqualTo(createdUser.getJob());
    }

    @Test
    @DisplayName("Обновление данных пользователя")
    public void updateUserTest() {
        User createdUser = apiSteps.createUser(testData.getRandomUser());
        User dataToUpdateUser = testData.getRandomUser();
        dataToUpdateUser.setId(createdUser.getId());
        User updatedUser = apiSteps.updateUser(dataToUpdateUser);

        assertThat(updatedUser.getId()).isEqualTo(createdUser.getId());
        assertThat(updatedUser.getName()).isEqualTo(dataToUpdateUser.getName());
        assertThat(updatedUser.getJob()).isEqualTo(dataToUpdateUser.getJob());
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void deleteUserTest() {
        User createdUser = apiSteps.createUser(testData.getRandomUser());
        apiSteps.deleteUser(createdUser);
    }
}
