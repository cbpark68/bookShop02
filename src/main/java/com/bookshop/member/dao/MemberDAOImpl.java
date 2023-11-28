package com.bookshop.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO login(Map loginMap) throws DataAccessException {
		MemberVO vo = (MemberVO)sqlSession.selectOne("mapper.member.login",loginMap);
		return vo;
	}

	@Override
	public void insertNewMember(MemberVO memberVO) throws DataAccessException {
		sqlSession.insert("mapper.member.insertNewMember",memberVO);
	}

	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String rslt = sqlSession.selectOne("mapper.member.selectOverlappedID",id);
		return rslt;
	}

}
