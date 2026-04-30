package sample.thymeleaf.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sample.common.dao.entity.Task;
import sample.common.service.TaskService;

@Controller
public class TaskController {
	

	/* 型、変数名 */
	@Autowired
	private TaskService taskService;

	// 新規作成ボタンを押されたら
	@GetMapping("/tasks/new")
	public ModelAndView showRegister(ModelAndView mv) {
		mv.setViewName("tasks/form-new");
		return mv;
	}

	@PostMapping(value = "/tasks/new")
	public ModelAndView user_registration(
			@RequestParam("title") String title, 
			@RequestParam("content") String content,
			@RequestParam("name") String name, 
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,

			ModelAndView mv) {

		// バリデーション処理 title == nullは、箱がない場合（HTMLで箱を作成しているが、必ずサーバー側に来るとは限らないため確認をしている
		// userName.trim().isEmpty() 半角と全角の空白とデータ未入力のとき、エラー内容を表示させる
		if (title == null || title.trim().isEmpty()) {
			mv.addObject("error", "タイトルを入力してください");
			mv.setViewName("tasks/form-new");
			return mv;
		}
		
		if (startDate == null || startDate.trim().isEmpty()) {
			mv.addObject("error", "開始日を入力してください");
			mv.setViewName("tasks/form-new");
			return mv;
		}
		if (endDate == null || endDate.trim().isEmpty()) {
		    mv.addObject("error", "終了日を入力してください");
		    mv.setViewName("tasks/form-new");
		    return mv;
		}

		Task task = new Task();
		task.setTitle(title);
		task.setContent(content);
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		

		taskService.registerTask(task);

		/* もう一度更新してって感じ */
		mv.setViewName("redirect:/tasks");
		return mv;
	}
	
	@GetMapping("/tasks/edit/{id}")
	public ModelAndView showEdit(
		/*どのデータを編集するかなので、@PathVariable*/
		@PathVariable("id") Integer id,
		ModelAndView mv) {
		
		 Task task = taskService.findTaskById(id);
		 
	    mv.addObject("task", task);
		mv.setViewName("tasks/form-edit");
		return mv;
	}
	@PostMapping("/tasks/edit")
	public ModelAndView updateTask(
			  @RequestParam("id")Integer id,
			  @RequestParam("title") String title,
			  @RequestParam("content") String content,
			  @RequestParam("name") String name,
			  @RequestParam("startDate") String startDate,
			  @RequestParam("endDate") String endDate,
			  ModelAndView mv) {

		Task task = new Task();
		task.setId(id);
		task.setTitle(title);
		task.setContent(content);
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		
		taskService.updateTask(task);
		
		mv.setViewName("redirect:/tasks");
	    return mv;
}
	@GetMapping("/tasks/delete/{id}")
	public String deleteTask(@PathVariable("id") Integer id) {
		taskService.deleteTask(id);
	    return "redirect:/tasks"; // 一覧に戻る
	}
	@GetMapping("/logout")
	public String logout() {
	    return "redirect:/login";
	}
	
	/* ページング */
	@GetMapping("/tasks")
	public ModelAndView showTasks(
	        @RequestParam(name = "page", defaultValue = "1") int page,
	        ModelAndView mv) {

	    int pageSize = 10;
	    int offset = (page - 1) * pageSize;

	    List<Task> taskList = taskService.findTasksByPage(pageSize, offset);
	    int totalCount = taskService.countTasks();
	    int totalPages = (int) Math.ceil((double) totalCount / pageSize);

	    mv.addObject("taskList", taskList);
	    mv.addObject("currentPage", page);
	    mv.addObject("totalPages", totalPages);
	    mv.setViewName("tasks/tasks");

	    return mv;
	}
	}
