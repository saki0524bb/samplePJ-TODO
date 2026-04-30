package sample.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.service.TaskService;



@Service
/* TaskServiceに書いた約束を、ここで実際に実装します */
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskMapper taskMapper;
	
	/* Controllerから受け取ったTaskを、Mapperに渡してDB保存する */
	@Override
	public void registerTask(Task task) {
	    taskMapper.insertTask(task);
	    
	}
	@Override
	public List<Task> findAllTasks() {
	    return taskMapper.selectAllTasks();
	}
	
	
	@Override
	public Task findTaskById(Integer id) {
	    return taskMapper.selectTaskById(id);
	}
	
	@Override
	public void updateTask(Task task) {
		taskMapper.updateTask(task);
	}
		@Override
		public void deleteTask(Integer id) {
			taskMapper.deleteTask(id);
	}
		@Override
		public List<Task> findTasksByPage(int limit, int offset) {
			return taskMapper.findTasksByPage(limit,offset);
	
}
		@Override
		public int countTasks() {
		    return taskMapper.countTasks();
}
}
