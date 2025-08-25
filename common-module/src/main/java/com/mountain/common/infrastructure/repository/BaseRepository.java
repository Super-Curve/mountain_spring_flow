package com.mountain.common.infrastructure.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mountain.common.domain.BaseEntity;

public interface BaseRepository<T extends BaseEntity> extends BaseMapper<T> {
}