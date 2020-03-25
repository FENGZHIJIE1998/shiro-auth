package com.itaem.crazy.shirodemo.modules.shiro.dao;

import com.itaem.crazy.shirodemo.modules.shiro.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 大誌
 * @Date 2019/3/30 22:05
 * @Version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
