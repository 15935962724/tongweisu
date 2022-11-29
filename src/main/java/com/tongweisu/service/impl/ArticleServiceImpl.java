package com.tongweisu.service.impl;


import com.tongweisu.dao.TWS_AdminMapper;
import com.tongweisu.dao.TWS_ArticleMapper;
import com.tongweisu.entity.TWS_Admin;
import com.tongweisu.entity.TWS_Article;
import com.tongweisu.service.AdminService;
import com.tongweisu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private TWS_ArticleMapper articleMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long[] id) {
		return 0;
	}

	@Override
	public int insert(TWS_Article record) {
		return 0;
	}

	@Override
	public int insertSelective(TWS_Article record) {
		return articleMapper.insertSelective(record);
	}

	@Override
	public TWS_Article selectByPrimaryKey(Long id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TWS_Article record) {
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(TWS_Article record) {
		return articleMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(TWS_Article record) {
		return 0;
	}

	@Override
	public List<TWS_Article> getAll(Map query_map) {
		return articleMapper.getAll(query_map);
	}
}
