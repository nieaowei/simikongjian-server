package cn.lt.core.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lt.core.dao.FoodDao;
import cn.lt.core.po.Test;
import cn.lt.core.service.FoodService;
/**
 * 
 */
@Service("foodService")
@Transactional
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDao foodDao;

	@Override
	public boolean addFood(Test food) {
		return this.foodDao.addFood(food)>0 ? true:false;
	}


	@Override
	public List<Test> findAllFood() {
		return this.foodDao.findAllFood();
	}


	@Override
	public void delFood(int id) {
		this.foodDao.delFood(id);
	}


	@Override
	public Test findFoodById(int id) {
		return this.foodDao.findFoodById(id);
	}


	@Override
	public boolean updateFood(Test food) {
		return this.foodDao.updateFood(food)>0 ? true:false;
	}


	@Override
	public Test findFoodByName(String name) {
		return this.foodDao.findFoodByName(name);
	}
	

}
