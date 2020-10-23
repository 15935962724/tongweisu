package com.tongweisu.service;

import com.tongweisu.entity.TWS_Admin;

import java.util.Map;

public interface AdminService {

	int deleteByPrimaryKey(Long id);

	int insert(TWS_Admin record);

	int insertSelective(TWS_Admin record);

	TWS_Admin selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TWS_Admin record);

	int updateByPrimaryKey(TWS_Admin record);

	TWS_Admin getAdmin(Map query_map);

	
}