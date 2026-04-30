package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

//ログインserviceの設計書
@Service
public class LoginServiceImpl implements LoginService {

	//@AutowiredはNEWを作らなくていい、ログインmapperを用意
    @Autowired
    private LoginMapper loginMapper;

    //ログインの中身をDBに保存してってmapperにお願いしている
    public void registerUser(Login login) {
        loginMapper.insertUser(login);
    }

    //ユーザーをデータベースの中から探して
    public Login findByUserName(String userName) {
        return loginMapper.selectByUserName(userName);
    }

}