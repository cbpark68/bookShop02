package com.bookshop.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop.member.vo.MemberVO;
import com.bookshop.order.service.OrderService;
import com.bookshop.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl implements OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderVO orderVO;

	@Override
	@RequestMapping(value="/orderEachGoods.do",method=RequestMethod.POST)
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO")OrderVO _orderVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Boolean isLogOn = (Boolean)session.getAttribute("isLogOn");
		String action = (String)session.getAttribute("action");
		if(isLogOn==null||isLogOn==false) {
			session.setAttribute("orderInfo", _orderVO);
			session.setAttribute("action", "/order/orderEachGoods.do");
			return new ModelAndView("redirect:/member/loginForm.do");
		}else {
			if(action!=null&&action.equals("/order/orderEachGoods.do")) {
				orderVO = (OrderVO)session.getAttribute("orderInfo");
				session.removeAttribute("action");
			}else {
				orderVO = _orderVO;
			}
		}
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		orderList.add(orderVO);
		MemberVO mVO =(MemberVO)session.getAttribute("memberInfo");
		session.setAttribute("myOrderList", orderList);
		session.setAttribute("ordered", mVO);
		return mav;
	}

	@Override
	public ModelAndView orderAllCartGoods(String[] cart_goods_qty, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public ModelAndView payToOrderGoods(Map<String, String> orderMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
