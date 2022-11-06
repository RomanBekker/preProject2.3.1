package web.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import web.dao.UserDaoImpl;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    public void update(long id, User newUser) {
        userDao.update(id, newUser);
    }

    @Transactional
    public List<User> back() {
        return userDao.back();
    }

    @Transactional
    public User backByID(@Param("id") Long id) {
        return userDao.backByID(id);
    }

}
