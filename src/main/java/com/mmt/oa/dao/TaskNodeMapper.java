package com.mmt.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.TaskNode;
import com.mmt.oa.dao.model.TaskNodeTemplate;

@Repository
public interface TaskNodeMapper {
	int deleteByPrimaryKey(Integer taskNodeId);

	int insert(TaskNode record);

	int insertSelective(TaskNode record);

	TaskNode selectByPrimaryKey(Integer taskNodeId);

	int updateByPrimaryKeySelective(TaskNode record);

	int updateByPrimaryKey(TaskNode record);

	Integer addNewTaskNode(TaskNode node);

	Integer approveTaskNode(TaskNode node);

	List<TaskNodeTemplate> queryTaskNodeInfoByModel(TaskNode node);
}