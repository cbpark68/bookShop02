package com.bookshop.admin.member.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.admin.member.dao.AdminMemberDAO;
import com.bookshop.member.vo.MemberVO;

@Service("adminMemberService")
public class AdminMemberServiceImpl implements AdminMemberService {
	@Autowired
	private AdminMemberDAO adminMemberDAO;

	@Override
	public ArrayList<MemberVO> listMember(HashMap condMap) throws Exception {
		return adminMemberDAO.listMember(condMap);
	}

	@Override
	public MemberVO memberDetail(String member_id) throws Exception {
		return adminMemberDAO.memberDetail(member_id);
	}

	@Override
	public void modifyMemberInfo(HashMap memberMap) throws Exception {
		adminMemberDAO.modifyMemberInfo(memberMap);
	}

}
