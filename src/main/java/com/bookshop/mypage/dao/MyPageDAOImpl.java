package com.bookshop.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop.member.vo.MemberVO;
import com.bookshop.order.vo.OrderVO;

@Repository("myPage")
public class MyPageDAOImpl implements MyPageDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException {
		List<OrderVO> list = (List)sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList",member_id);
		return list;
	}

	@Override
	public List selectMyOrderInfo(String order_id) throws DataAccessException {
		List<OrderVO> list = (List)sqlSession.selectList("mapper.mypage.selectMyOrderInfo",order_id);
		return list;
	}

	@Override
	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException {
		List<OrderVO> list = (List)sqlSession.selectList("mapper.mypage.selectMyOrderHistoryList",dateMap);
		return list;
	}

	@Override
	public void updateMyInfo(Map memberMap) throws DataAccessException {
		sqlSession.update("mapper.mypage.updateMyInfo",memberMap);
	}

	@Override
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException {
		MemberVO vo = (MemberVO)sqlSession.selectOne("mapper.mypage.selectMyDetailInfo",member_id);
		return vo;
	}

	@Override
	public void updateMyOrderCancel(String order_id) throws DataAccessException {
		sqlSession.update("mapper.mypage.updateMyOrderCancel",order_id);
	}

}
