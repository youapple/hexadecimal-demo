package com.hexadecimal.example.repository;

import com.hexadecimal.example.model.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 部分使用示例
 */
public interface UserRepository extends JpaRepository<UserDO, Long> {

    UserDO findByName(String userName);

    UserDO findByNameOrEmail(String username, String email);

    Long countByName(String userName);

    List<UserDO> findByNameLike(String email);

    UserDO findByNameIgnoreCase(String userName);

    List<UserDO> findByNameOrderByAgeAsc(String email);

    Page<UserDO> findByName(String name,Pageable pageable);

}
