package com.hotel;

import com.hotel.dao.UserDao;
import com.hotel.domain.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringRunner.class)
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Test
    public void testAdd() {
        Assertions.assertAll("add",
                () -> Assertions.assertDoesNotThrow(() -> {
                    User goodUser = new User();
                    goodUser.setName("John");
                    goodUser.setEmail("john@hotel.com");
                    goodUser.setPassword("password");
                    goodUser.setTele("666666");
                    goodUser.setQq("666666");
                    goodUser.setSex("男");
                    userDao.add(goodUser);
                }),
                () -> Assertions.assertThrows(Exception.class, () -> {
                    User existUser = new User();
                    existUser.setName("John");
                    existUser.setEmail("john@hotel.com");
                    existUser.setPassword("password");
                    existUser.setTele("666666");
                    existUser.setQq("666666");
                    existUser.setSex("男");
                    userDao.add(existUser);
                }));
    }

    @Test
    public void testUpdate() {
        Assertions.assertAll("update",
                () -> {
                    User user = new User();
                    user.setId(2);
                    user.setName("wangwu");
                    user.setEmail("wangwu@qq.com");
                    user.setPassword("password");
                    user.setTele("666666");
                    user.setQq("888888");
                    user.setSex("男");
                    Assertions.assertEquals(1, userDao.update(user));
                }
        );
    }

    @Test
    public void testDelete() {
        Assertions.assertAll("delete",
                () -> Assertions.assertEquals(1, userDao.deleteById(9)),
                () -> Assertions.assertEquals(0, userDao.deleteById(99)));
    }

    @Test
    public void testGetById() {
        Assertions.assertAll("getById",
                () -> {
                    User user = new User();
                    user.setId(1);
                    user.setName("zhangsan");
                    user.setPassword("123");
                    user.setEmail("zhangsan@qq.com");
                    user.setTele("789");
                    user.setQq("329056266");
                    user.setSex("男");
                    Assertions.assertEquals(user, userDao.getById(1));
                },
                () -> Assertions.assertNull(userDao.getById(99))
        );
    }

    @Test
    public void testCountUser() {
        Assertions.assertAll("countUser",
                () -> Assertions.assertEquals(1, userDao.countSameQQUser("329056266")),
                () -> Assertions.assertEquals(0, userDao.countSameQQUser("329056")),
                () -> Assertions.assertEquals(1, userDao.countSameTeleUser("789")),
                () -> Assertions.assertEquals(1, userDao.countSameEmailUser("zhangsan@qq.com")));
    }

    @Test
    public void testLogin() {
        Assertions.assertAll("login",
                () -> Assertions.assertNotNull(userDao.loginUser("zhangsan", "123")),
                () -> Assertions.assertNotNull(userDao.loginUser("zhangsan@qq.com", "123")),
                () -> Assertions.assertNotNull(userDao.loginUser("789", "123")),
                () -> Assertions.assertNull(userDao.loginUser("zhangsan", "456"))
        );

    }
}
