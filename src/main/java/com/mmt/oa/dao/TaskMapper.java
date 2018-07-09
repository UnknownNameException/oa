package com.mmt.oa.dao;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.Task;

@Repository
public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}