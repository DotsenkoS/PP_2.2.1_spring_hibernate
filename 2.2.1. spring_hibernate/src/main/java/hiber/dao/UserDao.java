package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User findUser(String name, int series);

    void cleanUsersTable();

    void deleteUser(long id);

    void deleteTable();

}
