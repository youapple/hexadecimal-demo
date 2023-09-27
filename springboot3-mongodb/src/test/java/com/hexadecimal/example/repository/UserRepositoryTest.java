package com.hexadecimal.example.repository;

import com.hexadecimal.example.model.UserDO;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    /**
     * 插入单条数据
     */
    @Test
    public void testInsert() {
        UserDO userDO = new UserDO(1L, "tony", 18, "tony@qq.com");
        UserDO rs = userRepository.insert(userDO);
        System.out.println("testInsert rs = " + rs);
    }

    /**
     * 插入多条数据
     */
    @Test
    public void testInsertMany() {
        UserDO student1 = new UserDO(2L, "jack", 19, "jack@qq.com");
        UserDO student2 = new UserDO(3L, "allen", 20, "allen@qq.com");
        List<UserDO> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        List<UserDO> rs = userRepository.insert(list);
        System.out.println("testInsertMany rs = " + rs);
    }

    /**
     * 修改数据
     */
    @Test
    public void testUpdate() {
        Optional<UserDO> updateUserDO = userRepository.findById(1L);
        UserDO userDO = updateUserDO.get();
        userDO.setAge(25);
        UserDO rs = userRepository.save(userDO);
        System.out.println("testUpdate rs = " + rs);
    }

    /**
     * 查询数据
     */
    @Test
    public void testQuery() {
        //根据Id查询单个对象
        Optional<UserDO> userDOOptional = userRepository.findById(1L);
        System.out.println("testQuery userDOOptional = " + (userDOOptional.isEmpty() ? null : userDOOptional.get()));

        //根据字段查询单个对象
        UserDO userDO = new UserDO();
        userDO.setAge(25);
        Optional<UserDO> userDO1 = userRepository.findOne(Example.of(userDO));
        System.out.println("testQuery userDO1 = " + (userDO1.isEmpty() ? null : userDO1.get()));

        //根据id列表查询多个对象
        Iterable<UserDO> iterable = userRepository.findAllById(Arrays.asList(1L, 2L));
        Iterator<UserDO> itor = iterable.iterator();
        while (itor.hasNext()) {
            System.out.println("testQuery itor = " + itor.next());
        }

        //查询所有并排序
        List<UserDO> list1 = userRepository.findAll(Sort.by(Sort.Order.desc("id"), Sort.Order.asc("name")));
        System.out.println("testQuery list1 = " + list1);

        //根据条件查询所有并排序
        UserDO userDO2 = new UserDO();
        userDO2.setName("tony");
        List<UserDO> list2 = userRepository.findAll(Example.of(userDO2), Sort.by(Sort.Order.desc("id"), Sort.Order.asc("name")));
        System.out.println("testQuery list2 = " + list2);

        //根据条件查询所有并排序，且分页
        Pageable pageable = PageRequest.of(0, 2);
        Page<UserDO> list3 = userRepository.findAll(pageable);
        List<UserDO> content = list3.getContent();
        System.out.println("testQuery page = " + content);
    }

    /**
     * 其他操作
     */
    @Test
    public void testOther() {
        long count = userRepository.count();
        System.out.println("testOther count = " + count);

        UserDO userDO = new UserDO();
        userDO.setAge(25);
        long count1 = userRepository.count(Example.of(userDO));
        System.out.println("testOther count1 = " + count1);

        boolean exists = userRepository.exists(Example.of(userDO));
        System.out.println("testOther exists = " + exists);

        boolean existsById = userRepository.existsById(1L);
        System.out.println("testOther existsById = " + existsById);
    }

    /**
     * 删除操作
     */
    @Test
    public void testDelete() {
        //根据id删除单个对象
        userRepository.deleteById(1L);

        //根据字段删除
        UserDO userDO1 = new UserDO();
        userDO1.setAge(19);
        Optional<UserDO> optionalUserDO1 = userRepository.findOne(Example.of(userDO1));
        if (!optionalUserDO1.isEmpty()) {
            userRepository.delete(optionalUserDO1.get());
        }

        //根据字段删除多个
        UserDO userDO2 = new UserDO();
        userDO2.setName("allen");
        List<UserDO> list2 = userRepository.findAll(Example.of(userDO2));
        if (!CollectionUtils.isEmpty(list2)) {
            userRepository.deleteAll(list2);
        }
    }

}
