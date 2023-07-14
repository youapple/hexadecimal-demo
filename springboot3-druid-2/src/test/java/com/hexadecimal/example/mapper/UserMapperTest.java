package com.hexadecimal.example.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hexadecimal.example.config.DataSourceConfig;
import com.hexadecimal.example.model.UserDO;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Resource
    private DataSource dataSource;

    /**
     * 测试单条查询
     */
    @Test
    public void testSelectOne() {
        UserDO user = userMapper.selectById(1L);
        System.out.println(user);
        System.out.println("*****************");

        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        //关闭连接
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        assertThat(userMapper.insert(user)).isGreaterThan(0);
        assertThat(user.getId()).isNotNull();
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        assertThat(userMapper.deleteById(3L)).isGreaterThan(0);
        assertThat(userMapper.delete(new QueryWrapper<UserDO>()
                .lambda().eq(UserDO::getName, "kk"))).isGreaterThan(0);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        UserDO user = userMapper.selectById(2);
        assertThat(user.getAge()).isEqualTo(20);
        assertThat(user.getName()).isEqualTo("Jack");

        userMapper.update(
                null,
                Wrappers.<UserDO>lambdaUpdate().set(UserDO::getEmail, "zz@qq.com").eq(UserDO::getId, 1)
        );
        assertThat(userMapper.selectById(2).getEmail()).isEqualTo("11@qq.com");
    }

    /**
     * 测试查询列表
     */
    @Test
    public void testSelect() {
        List<UserDO> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testPage() {
        Page<UserDO> page = new Page<>(1, 2);
        IPage<UserDO> userIPage = userMapper.selectPage(page, new QueryWrapper<UserDO>()
                .gt("age", 6));
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数: " + userIPage.getTotal());
        System.out.println("当前页数: " + userIPage.getCurrent());
        System.out.println("当前每页显示数: " + userIPage.getSize());
        System.out.println("记录列表: " + userIPage.getRecords());
    }

}
