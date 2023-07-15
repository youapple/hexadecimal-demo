package com.hexadecimal.example;

import com.hexadecimal.example.model.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("cici");
        userDO.setAge(18);
        userDO.setEmail("cici@qq.com");
        redisTemplate.boundValueOps("userKey").set(userDO);

        UserDO rs = (UserDO) redisTemplate.boundValueOps("userKey").get();
        System.out.println("user = " + rs.toString());
    }

}