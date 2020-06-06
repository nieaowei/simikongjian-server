package cn.lt.core.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lt.core.po.Test;
import cn.lt.core.service.FoodService;

@Controller
public class FoodController {
	
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model){
		List<Test> foods = null;
		foods = foodService.findAllFood();
		model.addAttribute("foods",foods);
		return "index";
	}
	
	@RequestMapping(value = "/addFood" ,method = RequestMethod.POST)
	public String sendMail(Model model,String name ,String price,String msg,
			 HttpServletRequest request, HttpServletResponse response) {
		int success =0;
		
			Test food = new Test();
			food.setMsg(msg);
			food.setName(name);
			food.setPrice(price);
			Date date=new Date(); 
			SimpleDateFormat df=new SimpleDateFormat("yyyy年M月d日 HH:mm:ss"); 
			food.setDate(df.format(date));
			if (foodService.addFood(food)) {
				success=1;
			}
			
		try {
			response.getWriter().write("{\"success\":"+success+"}");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:index";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String del(int id){
		foodService.delFood(id);
		return "redirect:index";
	}
	
	
	
	@RequestMapping(value="/findFoodByName", method=RequestMethod.GET)
	@ResponseBody
	public List<Test> findFoodByid(String name,Model model){
		Test food=null;
		System.out.println(name);
		List<Test> foods = new ArrayList<Test>();
		food = foodService.findFoodByName(name);
		if (food!=null) {
			foods.add(food);
		}else {
			foods = foodService.findAllFood();
		}
		System.out.println(foods);
		model.addAttribute("foods",foods);
		return foods;
	}
//	
//	@RequestMapping(value="/findFoodByid", method=RequestMethod.GET)
//	public String editFoodByid(int id,Model model){
//		Food food=null;
//		food = foodService.finFoodById(id);
//		model.addAttribute("food",food);
//		return "editFood";
//	}
	
	@RequestMapping(value="/editFood", method=RequestMethod.GET)
	public String editUser(int param ,String name ,String price,String msg,int id,Model model){
		Test food=new Test();
		try {
			if(param == 0){
				food = foodService.findFoodById(id);
				model.addAttribute("food",food);
				return "editFood";
			}else if(param == 1){
				food.setId(id+"");
				food.setMsg(msg);
				food.setName(name);
				food.setPrice(price);
				Date date=new Date(); 
				SimpleDateFormat df=new SimpleDateFormat("yyyy骞碝M鏈坉d鏃� HH:mm:ss"); 
				food.setDate(df.format(date));
				System.out.println(food);
				Boolean aBoolean =  foodService.updateFood(food);
				System.out.println(aBoolean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:index";
	}
}
