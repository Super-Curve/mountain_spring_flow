package com.mountain.web.domain.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mountain.web.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseMapper<User> {
}