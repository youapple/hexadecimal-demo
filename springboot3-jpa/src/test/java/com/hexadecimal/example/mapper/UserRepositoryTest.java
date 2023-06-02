package com.hexadecimal.example.mapper;

import com.hexadecimal.example.model.UserDO;
import com.hexadecimal.example.repository.UserRepository;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    /**
     * 测试单条查询
     */
    @Test
    public void testSelectOne() {
        Optional<UserDO> user = userRepository.findById(1L);
        System.out.println(user);
    }

    /**
     * 测试新增
     */
    @Test
    public void testInsert() {
        UserDO user = new UserDO();
        user.setName("kk");
        user.setAge(3);
        user.setEmail("kk@qq.com");
        UserDO rs = userRepository.save(user);
        assertThat(rs).isNotNull();
        assertThat(user.getId()).isNotNull();
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        userRepository.deleteById(6L);
        System.out.println("deleteById success");
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        Optional<UserDO> user = userRepository.findById(2L);
        assertThat(user.get().getAge()).isEqualTo(20);
        assertThat(user.get().getName()).isEqualTo("Jack");

        user.get().setEmail("zz@qq.com");
        userRepository.save(user.get());
        assertThat(userRepository.findById(2L).get().getEmail()).isEqualTo("11@qq.com");
    }

    /**
     * 测试查询列表
     */
    @Test
    public void testSelect() {
        List<UserDO> userList = userRepository.findAll();
        Assert.assertEquals(5, userList.size());
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testPage() {
        int page = 0, size = 2;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<UserDO> page1 = userRepository.findAll(pageable);
        System.out.println("记录列表1: " + page1.getContent());

        Page<UserDO> page2 = userRepository.findByName("Jack", pageable);
        System.out.println("记录列表2: " + page2.getContent());
    }

    /**
     * 测试根据方法名来自动生成 SQL
     */
    @Test
    public void testFindByNameLikeOrderByAgeAsc() {
        List<UserDO> list = userRepository.findByNameContainingOrderByAgeDesc("a");
        System.out.println("记录列表: " + list);
    }



}
