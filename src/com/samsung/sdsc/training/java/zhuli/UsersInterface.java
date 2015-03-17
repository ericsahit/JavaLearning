package com.samsung.sdsc.training.java.zhuli;

import java.util.List;
import com.samsung.sdsc.training.java.zhuli.Users;

public interface UsersInterface {
	
	     public int insert(Users user);//保存Users信息
	     public int delete(String id);//删除
	     public List<Users> queryAll();//查询所有Users信息
	     public Users query(String id);//根据id查询Users信息

}
