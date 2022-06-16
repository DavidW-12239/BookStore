package org.bookStore;

import lombok.extern.slf4j.Slf4j;
import org.bookStore.mapper.UserMapper;
import org.bookStore.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class DBTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

/*    @Test
    void testUserMapper(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.getUname());
    }*/

    @Test
    void normalTest(){
        System.out.println("success");
    }
}
