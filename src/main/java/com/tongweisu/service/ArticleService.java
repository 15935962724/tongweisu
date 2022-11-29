package com.tongweisu.service;

import com.tongweisu.entity.TWS_Admin;
import com.tongweisu.entity.TWS_Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

	int deleteByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long []id);

	int insert(TWS_Article record);

	int insertSelective(TWS_Article record);

	TWS_Article selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TWS_Article record);

	int updateByPrimaryKeyWithBLOBs(TWS_Article record);

	int updateByPrimaryKey(TWS_Article record);

	List<TWS_Article> getAll(Map query_map);


	
}