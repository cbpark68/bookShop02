package com.bookshop.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop.common.base.BaseController;
import com.bookshop.member.service.MemberService;
import com.bookshop.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl extends BaseController implements MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	@Autowired
	private MemberService memberService;
	private MemberVO memberVO;

	@Override
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView login(Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(loginMap);
		if(memberVO!=null&&memberVO.getMember_id()!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", memberVO);
			String action = (String)session.getAttribute("action");
			if(action!=null&&action.equals("/order/orderEachGoods.do")) {
				mav.setViewName("forward:"+action);
			}else {
				mav.setViewName("redirect:/main/main.do");
			}
		}else {
			String msg = "아이디나 비번이 틀립니다. 다시 로그인해주세요.";
			mav.addObject("message",msg);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}

	@Override
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo");
		mav.setViewName("redirect:/main/main.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/addMember.do",method=RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String msg = null;
		ResponseEntity entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html;charset=utf-8");
		try {
			memberService.addMember(_memberVO);
			msg = "<script>";
			msg += " alert('회원가입을 마쳤습니다. 로그인창으로 이동합니다.');";
			msg += " location.href='"+request.getContextPath()+"/member/loginForm.do';";
			msg += " </script>";
		}catch(Exception e) {
			msg = "<script>";
			msg += " alert('작업중 오류가 발생했습니다. 다시 시도해주세요');";
			msg += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
			msg += " </script>";
			e.printStackTrace();
		}
		entity = new ResponseEntity(msg,headers,HttpStatus.OK);
		return entity;
	}

	@Override
	@RequestMapping(value="/overlapped.do",method=RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResponseEntity entity = null;
		String rslt = memberService.overlapped(id);
		entity = new ResponseEntity(rslt,HttpStatus.OK);
		return entity;
	}

}
