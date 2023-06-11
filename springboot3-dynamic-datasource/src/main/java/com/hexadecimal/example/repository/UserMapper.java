package com.hexadecimal.example.repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hexadecimal.example.model.UserDO;

@DS("slave")
public interface UserMapper extends BaseMapper<UserDO> {
}
