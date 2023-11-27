package com.bookshop.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop.goods.dao.GoodsDAO;
import com.bookshop.goods.vo.GoodsVO;
import com.bookshop.goods.vo.ImageFileVO;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDAO goodsDAO;

	@Override
	public Map<String, List<GoodsVO>> listGoods() throws Exception {
		Map<String,List<GoodsVO>> map = new HashMap<String,List<GoodsVO>>();
		List<GoodsVO> list = goodsDAO.selectGoodsList("bestseller");
		map.put("bestseller", list);
		list = goodsDAO.selectGoodsList("newbook");
		map.put("newbook", list);
		list = goodsDAO.selectGoodsList("steadyseller");
		map.put("steadyseller", list);
		return map;
	}

	@Override
	public Map goodsDetail(String _goods_id) throws Exception {
		Map map = new HashMap();
		GoodsVO vo = goodsDAO.selectGoodsDetail(_goods_id);
		map.put("goodsVO", vo);
		List<ImageFileVO> list = goodsDAO.selectGoodsDetailImage(_goods_id);
		map.put("imageList", list);
		return map;
	}

	@Override
	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list = goodsDAO.selectKeywordSearch(keyword);
		return list;
	}

	@Override
	public List<GoodsVO> searchGoods(String searchWord) throws Exception {
		List list = goodsDAO.selectGoodsBySearchWord(searchWord);
		return list;
	}

}
