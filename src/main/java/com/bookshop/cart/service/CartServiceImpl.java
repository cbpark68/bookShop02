package com.bookshop.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.cart.dao.CartDAO;
import com.bookshop.cart.vo.CartVO;
import com.bookshop.goods.vo.GoodsVO;

@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;

	@Override
	public Map<String, List> myCartList(CartVO cartVO) throws Exception {
		Map<String,List> map = new HashMap<String,List>();
		List<CartVO> cartList = cartDAO.selectCartList(cartVO);
		if(cartList.size()==0)
			return null;
		List<GoodsVO> goodsList = cartDAO.selectGoodsList(cartList);
		map.put("myCartList", cartList);
		map.put("myGoodsList", goodsList);
		return map;
	}

	@Override
	public boolean findCartGoods(CartVO cartVO) throws Exception {
		return cartDAO.selectCountInCart(cartVO);
	}

	@Override
	public void addGoodsInCart(CartVO cartVO) throws Exception {
		cartDAO.insertGoodsInCart(cartVO);
	}

	@Override
	public boolean modifyCartQty(CartVO cartVO) throws Exception {
		boolean rslt = true;
		cartDAO.updateCartGoodsQty(cartVO);
		return rslt;
	}

	@Override
	public void removeCartGoods(int cart_id) throws Exception {
		cartDAO.deleteCartGoods(cart_id);
	}

}
