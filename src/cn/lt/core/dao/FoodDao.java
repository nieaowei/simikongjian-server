package cn.lt.core.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lt.core.po.Test;
/**
 * 用户DAO层接口
 */
public interface FoodDao {
	/**
	 * 通过账号和密码查询用户
	 */
	public int addFood(Test food);
	
	public List<Test> findAllFood();

	public void delFood(int id);
	
	public Test findFoodById(int id);
	
	public int updateFood(Test food);

	public Test findFoodByName(@Param("name")String name);
}
