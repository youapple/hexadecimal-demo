package com.hexadecimal.example.mapper;

import com.hexadecimal.example.model.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectOne() {
        UserDO user = userMapper.selectById(1L);
        System.out.println(user);
    }

}
