package sample.common.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import sample.common.dao.entity.Login;

// @Mapper → MyBatis用（JavaとDBをつなぐための仕組み） Login → データを入れるクラス
//データベースとつながるクラス　mapperは作る、Autowiredは取り出す
@Mapper
public interface LoginMapper {

	//ユーザー登録をする操作
    void insertUser(Login login);

    //ユーザー名で検索する操作
    Login selectByUserName(String userName);
}