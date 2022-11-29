package com.tongweisu.service;

import com.tongweisu.dao.TWS_CategoryMapper;
import com.tongweisu.entity.TWS_Article;
import com.tongweisu.entity.TWS_Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

	int deleteByPrimaryKey(Long id);

	int insert(TWS_Category record);

	int insertSelective(TWS_Category record);

	TWS_Category selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TWS_Category record);

	int updateByPrimaryKey(TWS_Category record);

	List getCategorys(Map query_map);

	List<TWS_Category> getAll(Map query_map);
	
}