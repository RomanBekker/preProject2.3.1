package web.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    @Override
    public void update(long id, User newUser) {
        userDao.update(id, newUser);
    }

    @Override
    public List<User> back() {
        return userDao.back();
    }

    @Override
    public User backByID(@Param("id") Long id) {
        return userDao.backByID(id);
    }

}
