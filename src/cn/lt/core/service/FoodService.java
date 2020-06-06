package cn.lt.core.service;
import java.util.List;

import cn.lt.core.po.Test;

public interface FoodService {
	
	public List<Test> findAllFood();

	public boolean addFood(Test food);

	public void delFood(int id);
	
	public Test findFoodById(int id);
	
	public boolean updateFood(Test food);

	public Test findFoodByName(String name);
}
