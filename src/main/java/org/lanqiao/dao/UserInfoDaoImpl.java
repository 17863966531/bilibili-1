package org.lanqiao.dao;

import org.lanqiao.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl extends BaseDao<UserInfo> implements UserInfoDao {
    //    更新个人信息
    public int updateUserInfo(UserInfo user) {
        return executeUpdate("update UserInfo set nickname = ?,userMark = ?,userSex = ? where userId = ?", new Object[]{user.getNickname(), user.getUserMark(), user.getUserSex(), user.getUserId()});
    }

    //个人信息页展示信息
    public List<UserInfo> showUserInfo(int userId) {
        return executeQuery("SELECT * FROM UserInfo WHERE userId = ?", new Object[]{userId});
    }

    public List<UserInfo> showUserInfo(int pageNum, int pageSize) {
        return executeQuery("SELECT * FROM UserInfo ORDER BY userId LIMIT ?, ?", new Object[]{(pageNum - 1) * pageSize, pageSize});
    }


    //    登录验证
    public List<UserInfo> checkUser(UserInfo userInfo) {
        List<UserInfo> userList = executeQuery("select * from UserInfo where userName=?", new Object[]{userInfo.getUserName()});
        return  userList;

    }

    //注册验证
    public int insertUser(UserInfo userInfo) {
        return executeUpdate("insert into UserInfo(userName,nickName,password,userIdentity) values(?,?,?,?)", new Object[]{userInfo.getUserName(), userInfo.getNickname(), userInfo.getPassword(),0});
    }

    //    获得需要封装入session的信息
    public UserInfo getUserInfo(String userName) {
        List<UserInfo> userList = executeQuery("SELECT userId,userName,password,nickname,userSex,userMark,userImage,userIdentity,userLocked FROM UserInfo WHERE userName = ?", new Object[]{userName});
        return userList.get(0);
    }


    public List<UserInfo> CheckUserName(String userName) {
        return executeQuery("select * from UserInfo where userName=?", new Object[]{userName});
    }

    //更新个人头像
    public int updateUserImage(String userImage, int userId) {
        return executeUpdate("update UserInfo set userImage = ? where userId = ?", new Object[]{userImage, userId});
    }

    public int CountUser() {
        return getRecordCount("SELECT count(*) FROM UserInfo");
    }


    public int ChangeLocked(int userId) {
        return executeUpdate("update UserInfo set userLocked = date_add(sysdate(), interval 1 day) where userId = ?",
                new Object[]{userId});
    }

    public static void main(String[] args) {
//        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
//        int ret = userInfoDao.ChangeLocked(1);
//        System.out.println(ret);
    }
}
