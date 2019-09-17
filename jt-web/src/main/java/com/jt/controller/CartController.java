package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;

import com.jt.service.DubboCartService;
import com.jt.until.UserThreadLocal;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Reference(timeout = 3000,check = false)
	private DubboCartService cartService;
	/**
	 * 当用户点击购物车按钮时.应该展现用户的购物记录
	 * 业务实现:
	 * 	查询用户购物行为  userId 暂时写死 
	 * @return
	 */

	@RequestMapping("/show")
	public String show(Model model,HttpServletRequest request) {
		
		//User user = (User) request.getAttribute("JT_USER");
		Long userId = UserThreadLocal.get().getId();

		List<Cart> cartList = cartService.findCartListByUserId(userId);
		model.addAttribute("cartList",  cartList);
		return "cart"; //跳转购物车页面
		
	}
	@RequestMapping("/delete/{itemId}")
	public String  deleteCart(Cart cart) {
		
		Long userId = UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cartService.deleteCart(cart);
		return "redirect:/cart/show.html";//应该采用重定向方式获取数据
		
	}
	/**
	 * 该方法利用页面的表单提交获取参数.
	 * 之后应该跳转购物车展现页面
	 * @param cart
	 * @return
	 */

	@RequestMapping("/add/{itemId}")
	public String saveCart(Cart cart) {
		Long userId = UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";
		
	}
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(Cart cart) {
		Long userId = UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cartService.updateCartNum(cart);
		return SysResult.success();
		
	}

}
