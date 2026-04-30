package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {
	/* TaskServiceImplの最後の行から引っ張ってきた */
	void insertTask(Task task);
	
	 List<Task> selectAllTasks();
	 Task selectTaskById(Integer id);

		/* voidは結果を返さない */
	 void updateTask(Task task);
	 void deleteTask(Integer id);
	 List<Task> findTasksByPage(
				/* @Paramをつけたら、XMLとつながる引数2個以上の場合は必要 */
			    @Param("limit") int limit,
			    @Param("offset") int offset);
		/* データを返す */
	 int countTasks();
	 
}