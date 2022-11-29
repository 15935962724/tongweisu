package com.tongweisu.service.impl;


import com.tongweisu.dao.TWS_AdminMapper;
import com.tongweisu.entity.TWS_Admin;
import com.tongweisu.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private TWS_AdminMapper adminMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public TWS_Admin selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKey(TWS_Admin record) {
		return 0;
	}

	@Override
	public TWS_Admin getAdmin(Map query_map) {
		return adminMapper.getAdmin(query_map);
	}

	@Override
	public TWS_Admin getCurrent() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			TWS_Admin admin = (TWS_Admin) SecurityUtils.getSubject().getPrincipal();
			if (admin!=null){
				return admin;
			}
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TWS_Admin record) {
		return 0;
	}

	@Override
	public int insertSelective(TWS_Admin record) {
		return 0;
	}

	@Override
	public int insert(TWS_Admin record) {
		return 0;
	}
}
