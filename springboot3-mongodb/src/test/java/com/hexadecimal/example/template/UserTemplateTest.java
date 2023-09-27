package com.hexadecimal.example.template;

import com.hexadecimal.example.model.UserDO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTemplateTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert() {
        UserDO userDO = new UserDO(4L, "sam", 18, "sam@qq.com");
        UserDO rs = mongoTemplate.save(userDO);
        System.out.println("testInsert rs = " + rs);
    }

    @Test
    public void testUpdate() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(4L));
        Update update = Update.update("name", "jack");
        //有则更新，没有则新增
        UpdateResult upsert = mongoTemplate.upsert(query, update, "user");
        System.out.println("testUpdate count = " + upsert.getModifiedCount());
    }

    @Test
    public void testQuery() {
        UserDO userDO = mongoTemplate.findById(4L, UserDO.class);
        System.out.println(userDO);

        List<UserDO> userDOList = mongoTemplate.findAll(UserDO.class);
        System.out.println("testQuery userDOList = " + userDOList);
    }

    @Test
    public void testDelete() {
        UserDO userDO = mongoTemplate.findById(4L, UserDO.class);
        if (userDO != null) {
            DeleteResult dr = mongoTemplate.remove(userDO);
            System.out.println("testDelete count = " + dr.getDeletedCount());
        }
    }

}
