package com.mmt.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mmt.oa.dao.model.Task;
import com.mmt.oa.dao.model.TaskTemplate;

@Repository
public interface TaskMapper {
	int deleteByPrimaryKey(Integer taskId);

	int insert(Task record);

	int insertSelective(Task record);

	Task selectByPrimaryKey(Integer taskId);

	int updateByPrimaryKeySelective(Task record);

	int updateByPrimaryKey(Task record);

	Integer addNewTask(Task task);

	Task queryTaskInfoById(Task task);

	List<TaskTemplate> queryTaskInfoByModel(Task task);

	Integer modifyTaskInfoByModel(Task task);

	Integer modifyTaskInfoById(Task task);
}