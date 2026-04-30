package sample.common.service;

import java.util.List;

import sample.common.dao.entity.Task;

/*外とのやり取りが多い場合にインターフェースを使う、＠serviceは書かない、設計書（ルールのみ）記載*/
public interface TaskService {
	/* registerTask 登録する、（型、変数名） */
	void registerTask(Task task);

	 List<Task> findAllTasks(); 
	 Task findTaskById(Integer id); 
	 void updateTask(Task task);
	 void deleteTask(Integer id);

		/* SQLに送るデータを入れている */
	 List<Task> findTasksByPage(int limit, int offset);
	 int countTasks();
	 
}
