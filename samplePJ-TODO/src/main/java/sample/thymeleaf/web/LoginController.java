package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sample.common.dao.entity.Login;
import sample.common.service.LoginService;

@Controller
public class LoginController {
	
	 @Autowired
	    private LoginService loginService;

	  //ユーザー登録のHTMLを表示
    @GetMapping("/register")
    public ModelAndView showRegister(ModelAndView mv) {
        mv.setViewName("register");
        return mv;
    }
    //ユーザー登録で名前とパスワードが入力されたら
    @PostMapping(value = "/register")
    public ModelAndView user_registration(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            ModelAndView mv) {

    	//バリデーション処理　userName == nullは、箱がない場合（HTMLで箱を作成しているが、必ずサーバー側に来るとは限らないため確認をしている
    	//userName.trim().isEmpty() 半角と全角の空白とデータ未入力のとき、エラー内容を表示させる
    	if (userName == null || userName.trim().isEmpty()) {
            mv.addObject("error", "ユーザー名を入力してください");
            mv.setViewName("register");
            return mv;
        }
    	//半角英数字じゃなかったらエラーを表示
        if (!userName.matches("^[a-zA-Z0-9]+$")) {
            mv.addObject("error", "ユーザー名は半角英数字で入力してください");
            mv.setViewName("register");
            return mv;
        }

        if (password == null || password.trim().isEmpty()) {
            mv.addObject("error", "パスワードを入力してください");
            mv.setViewName("register");
            return mv;
        }
        //existingUserは、LoginServiceImpl.javaに書いている、DBにユーザーを探しにいってる、そのユーザーがいたら、エラーを表示
        Login existingUser = loginService.findByUserName(userName);
        if (existingUser != null) {
            mv.addObject("error", "そのユーザー名は既に使われています");
            mv.setViewName("register");
            return mv;
        }

        Login login = new Login();
        login.setUserName(userName);
        login.setPassword(password);

        //serviceに登録処理（DBに保存）を依頼している→Loginserviceimpl→mapper→XML
        loginService.registerUser(login);

        mv.addObject("message", "ユーザー登録が完了しました。ログインしてください。");
        mv.setViewName("login");
        return mv;
    }

   @GetMapping("/login")
    public ModelAndView showlogin(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
   }
   
   @PostMapping("/login")
   public ModelAndView dologin(
	   @RequestParam("userName") String userName,
       @RequestParam("password") String password,
       ModelAndView mv) {
	   
   //Loginクラス　login変数名　データベースから情報をとってきて、ログインに入れてる
	   Login login = loginService.findByUserName(userName);
	   //データがなかったら
	   if (login == null) {
	        mv.addObject("error", "ユーザーが存在しません");
	        mv.setViewName("login");
	        return mv;
	    }
	   
	   //==は同じ場所かどうかの確認なのでequalを使う、DBのパスワードと入力されたパスワードが違ったら
	   if(!login.getPassword().equals(password)) {
			   mv.addObject("error","パスワードが間違えています");
			   mv.setViewName("login");
			   return mv;
}
	   mv.setViewName("redirect:/tasks");
	   return mv;
}
}

