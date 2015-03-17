package com.samsung.sdsc.training.java.zhuli;

import java.util.ArrayList;
import java.util.List;
import com.samsung.sdsc.training.java.zhuli.Users;

public class UserImplement implements UsersInterface{

	@Override
	public int insert(Users user) {
		// TODO Auto-generated method stub
	
		if(!user.getId().equals("")&&!user.getName().equals("")&&!user.getName().equals(""))
		{
			return 1;	
		}else{
			return 0;
		}
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Users> queryAll() {
		// TODO Auto-generated method stub
		Users  user=new Users();
		user.setId("han.hua");
		user.setName("海华");
		user.setDesc(" 帅哥");
		
		Users  user2=new Users();
		user2.setId("b.bing");
		user2.setName("冰冰");
		user2.setDesc("帅哥");
		List<Users> list=new ArrayList<Users>();
		list.add(user);
		list.add(user2);
		
	
		return  list;
	}

	@Override
	public Users query(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void main(String[] args)
	
	{
		Users  user=new Users();
		user.setId("ning.duan");
		user.setName("段宁");
		user.setDesc("帅哥");
		UserImplement userImplement=new  UserImplement();
		if(userImplement.insert(user)==1) 
		{
		System.out.println("插入数据成功。");	
		}
		 
		List<Users> userAll=userImplement.queryAll();
		
		for(int i=0;i<userAll.size();i++)
		{
			System.out.println("ID :"+userAll.get(i).getId()+"  NAME:"+userAll.get(i).getName()+"  DESC:"+userAll.get(i).getDesc());	
		}
	    
		System.out.println("一共查询出"+userAll.size()+"数据。");
		
		
	 
	}

}
