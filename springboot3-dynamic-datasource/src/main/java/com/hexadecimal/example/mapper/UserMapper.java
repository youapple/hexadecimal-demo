package com.hexadecimal.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hexadecimal.example.model.UserDO;
import org.springframework.beans.factory.annotation.Qualifier;

@DS("slave")
public interface UserMapper extends BaseMapper<UserDO> {
}
