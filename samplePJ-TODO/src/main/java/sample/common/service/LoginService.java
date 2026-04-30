package sample.common.service;

import sample.common.dao.entity.Login;

//ユーザーの登録処理とユーザー検索処理を必ず作ってなって指示（インターフェース）
public interface LoginService {

    void registerUser(Login login);

    Login findByUserName(String userName);
}