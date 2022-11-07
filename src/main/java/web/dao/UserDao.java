package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    void delete(User user);

    void update(long id, User newUser);

    List<User> back();

    User backByID(Long id);
}
