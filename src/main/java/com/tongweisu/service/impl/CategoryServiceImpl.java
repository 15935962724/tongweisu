package com.tongweisu.service.impl;


import com.tongweisu.dao.TWS_ArticleMapper;
import com.tongweisu.dao.TWS_CategoryMapper;
import com.tongweisu.entity.TWS_Article;
import com.tongweisu.entity.TWS_Category;
import com.tongweisu.entity.TWS_CategoryTree;
import com.tongweisu.service.ArticleService;
import com.tongweisu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private TWS_CategoryMapper categoryMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(TWS_Category record) {
        return 0;
    }

    @Override
    public int insertSelective(TWS_Category record) {
        return 0;
    }

    @Override
    public TWS_Category selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TWS_Category record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TWS_Category record) {
        return 0;
    }

    @Override
    public List getCategorys(Map query_map) {
        return categoryMapper.getCategorys(query_map);
    }

    @Override
    public List<TWS_Category> getAll(Map query_map) {
        return  TWS_CategoryTree.buildByRecursive(categoryMapper.getAll(query_map));
    }
}
