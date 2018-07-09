package com.mmt.oa.dao;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.TaskNode;

@Repository
public interface TaskNodeMapper {
    int deleteByPrimaryKey(Integer taskNodeId);

    int insert(TaskNode record);

    int insertSelective(TaskNode record);

    TaskNode selectByPrimaryKey(Integer taskNodeId);

    int updateByPrimaryKeySelective(TaskNode record);

    int updateByPrimaryKey(TaskNode record);
}