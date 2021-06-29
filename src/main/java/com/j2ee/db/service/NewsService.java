package com.j2ee.db.service;


import com.j2ee.db.dao.NewsMapper;
import com.j2ee.db.domain.News;
import com.j2ee.db.domain.NewsExample;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 新闻信息service
 */
@Service
public class NewsService {


    /**
     * mapper of news
     */
    @Resource
    private NewsMapper newsMapper;




    /**
     * 通过id查找
     * @param id 新闻信息id
     * @return News
     */
    public News findById(Integer id) {
        return newsMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }



    /**
     * 通过title精确查找
     * @param title 新闻标题
     * @return list of news
     */
    public News findByTitle(String title) {
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andTitleEqualTo(title);
        example.orderBy("add_time DESC");

        return newsMapper.selectOneByExample(example);
    }



    /**
     * 通过title模糊查找
     * @param title 新闻标题
     * @return list of news
     */
    public List<News> findAllByTitle(String title) {
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andTitleLike("%" + title + "%");
        example.orderBy("add_time DESC");

        return newsMapper.selectByExample(example);
    }




    /**
     * 通过isActive查找
     * @param isActive 启用信息
     * @return list of News
     */
    public List<News> queryByIsActive(Boolean isActive){
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andIsActiveEqualTo(isActive);
        example.orderBy("add_time DESC");

        return newsMapper.selectByExample(example);
    }


    /**
     * 通过title查询
     * @param title 标题
     * @return list of news
     */
    public List<News> queryByTitleIsActive(String title){
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(false);
        criteria.andIsActiveEqualTo(true);
        criteria.andTitleLike("%" + title + "%");
        example.orderBy("add_time DESC");

        return newsMapper.selectByExample(example);
    }



    /**
     * 查询所有新闻信息
     * @return list of News
     */
    public List<News> queryAll(){
        NewsExample example = new NewsExample();
        example.or().andLogicalDeleted(false);
        example.orderBy("add_time DESC");

        return newsMapper.selectByExample(example);
    }




    /**
     * 添加新闻信息
     * @param news 新闻信息
     * @return int
     */
    public int add(News news) {
        if(StringUtils.isEmpty(news.getTitle())){
            return 0;
        }

        if(StringUtils.isEmpty(news.getContent())){
           return 0;
        }

        news.setAddTime(LocalDateTime.now());
        news.setIsActive(true);
        news.setIsDel(false);

        return newsMapper.insertSelective(news);
    }



    /**
     * 添加新闻信息
     * @param news 新闻信息
     * @return int
     */
    public int insert(News news) {
        return add(news);
    }



    /**
     * 更新新闻信息通过id
     *                  只更新参数中不为空的字段
     * @param news 需要更新的新闻信息
     * @return int
     */
    public int updateSelective(News news){
        Integer id = news.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return newsMapper.updateByPrimaryKeySelective(news);
    }



    /**
     * 更新新闻信息通过id
     *                  若参数中的某个属性为null 则数据库中相应字段也更新为null
     * @param news 需要更新的新闻信息
     * @return int
     */
    public int update(News news){
        Integer id = news.getId();
        if (id == null || id == 0) {
            return 0;
        }

        return newsMapper.updateByPrimaryKeySelective(news);
    }



    /**
     * 通过id删除
     *      逻辑删除
     * @param id 新闻信息id
     * @return int
     */
    public int delete(Integer id) {
        return newsMapper.logicalDeleteByPrimaryKey(id);
    }



}
