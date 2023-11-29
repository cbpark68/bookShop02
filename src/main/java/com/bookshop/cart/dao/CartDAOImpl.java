package com.bookshop.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop.cart.vo.CartVO;
import com.bookshop.goods.vo.GoodsVO;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException {
		List<CartVO> list = (List)sqlSession.selectList("mapper.cart.selectCartList",cartVO);
		return list;
	}

	@Override
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException {
		List<GoodsVO> list = (List)sqlSession.selectList("mapper.cart.selectGoodsList",cartList);
		return list;
	}

	@Override
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
		String rslt = sqlSession.selectOne("mapper.cart.selectCountInCart",cartVO);
		return Boolean.parseBoolean(rslt);
	}

	@Override
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException {
		int cart_id = selectMaxCartId();
		cartVO.setCart_id(cart_id);
		sqlSession.insert("mapper.cart.insertGoodsInCart",cartVO);
	}

	@Override
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException {
		sqlSession.insert("mapper.cart.updateCartGoodsQty",cartVO);
	}

	@Override
	public void deleteCartGoods(int cart_id) throws DataAccessException {
		sqlSession.insert("mapper.cart.deleteCartGoods",cart_id);
	}
	
	private int selectMaxCartId() throws DataAccessException{
		return sqlSession.selectOne("mapper.cart.selectMaxCartId");
	}

}
