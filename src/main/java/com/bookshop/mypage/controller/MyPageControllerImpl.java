package com.bookshop.mypage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop.member.vo.MemberVO;
import com.bookshop.mypage.service.MyPageService;
import com.bookshop.order.vo.OrderVO;

@Controller("myPageController")
@RequestMapping(value="/mypage")
public class MyPageControllerImpl implements MyPageController {
	@Autowired
	private MyPageService myPageService;
	@Autowired
	private MemberVO memberVO;

	@Override
	@RequestMapping(value="/myPageMain.do",method=RequestMethod.GET)
	public ModelAndView myPageMain(@RequestParam(value="message",required=false)String message, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("side_menu", "my_page");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO = (MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		List<OrderVO> orderList = myPageService.listMyOrderGoods(member_id);
		mav.addObject("message",message);
		mav.addObject("myOrderList",orderList);
		return mav;
	}

	@Override
	@RequestMapping(value="/myOrderDetail.do",method=RequestMethod.GET)
	public ModelAndView myOrderDetail(@RequestParam("order_id") String order_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();
		MemberVO orderer = (MemberVO)session.getAttribute("memberInfo");
		List<OrderVO> orderList = myPageService.findMyOrderInfo(order_id);
		mav.addObject("orderer",orderer);
		mav.addObject("myOrderList",orderList);
		return mav;
	}

	@Override
	public ModelAndView cancelMyOrder(String order_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	@Override
	public ModelAndView listMyOrderHistory(Map<String, String> dateMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public ResponseEntity modifyMyInfo(String attribute, String value, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

}
