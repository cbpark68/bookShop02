package com.bookshop.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop.common.base.BaseController;
import com.bookshop.goods.service.GoodsService;
import com.bookshop.goods.vo.GoodsVO;

@Controller("goodsController")
@RequestMapping(value = "/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {
	private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
	@Autowired
	private GoodsService goodsService;

	@Override
	@RequestMapping(value = "/goodsDetail.do", method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		Map map = goodsService.goodsDetail(goods_id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsMap", map);
		GoodsVO vo = (GoodsVO) map.get("goodsVO");
		logger.info("goodsDetail viewName=" + viewName);
		addGoodsInQuick(goods_id, vo, session);
		return mav;
	}

	@Override
	@RequestMapping(value="/keywordSearch.do",method=RequestMethod.GET,produces="application/text;charset=utf-8")
	@ResponseBody
	public String keywordSearch(@RequestParam("keyword") String keyword, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		if(keyword==null||keyword.equals(""))
			return null;
		keyword = keyword.toUpperCase();
		List<String> list = goodsService.keywordSearch(keyword);
		JSONObject json = new JSONObject();
		json.put("keyword", list);
		String jsonInfo = json.toString();
		return jsonInfo;
	}

	@Override
	@RequestMapping(value="/searchGoods.do",method=RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List<GoodsVO> list = goodsService.searchGoods(searchWord);
		logger.info("searchWord="+searchWord);
		logger.info("viewName="+viewName);
		logger.info("searchGoods="+list);
		mav.addObject("goodsList",list);
		return mav;
	}

	private void addGoodsInQuick(String goods_id, GoodsVO vo, HttpSession session) {
		boolean already_existed = false;
		List<GoodsVO> list = (ArrayList<GoodsVO>) session.getAttribute("quickGoodsList");
		if (list != null) {
			if (list.size() < 4) {
				for (int i = 0; i < list.size(); i++) {
					GoodsVO _vo = (GoodsVO) list.get(i);
					if (goods_id.equals(Integer.toString(_vo.getGoods_id()))) {
						already_existed = true;
						return;
					}
				}
				if (already_existed == false) {
					list.add(vo);
				}
			}
		} else {
			list = new ArrayList<GoodsVO>();
			list.add(vo);
		}
		session.setAttribute("quickGoodsList", list);
		session.setAttribute("quickGoodsListNum", list.size());
	}
}
