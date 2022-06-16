package org.bookStore.service.impl;


import org.bookStore.mapper.UserMapper;
import org.bookStore.pojo.User;
import org.bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String uname, String pwd) {
        return userMapper.getUser(uname,pwd);
    }

    @Override
    public void register(User user) {
        userMapper.addUser(user);
    }

    @Override
    public Boolean checkEmail(String email) {
        User user = userMapper.getUserByEmail(email);
        if(user != null){
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Boolean checkUserName(String uname) {
        if (userMapper.getUserByName(uname) == null){
            return true;
        }
        return false;
    }
}
