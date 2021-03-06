package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.TagMapper;
import com.qxt.bysj.domain.Article;
import com.qxt.bysj.domain.Tag;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.service.TagService;
import com.qxt.bysj.service.TagXarticleService;
import com.qxt.bysj.service.TagXvideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {
    @Autowired
    private TagMapper tagDao;
    @Autowired
    private TagXvideoService tagXvideoService;
    @Autowired
    private TagXarticleService tagXarticleService;
    @Override
    public int dealTaskVideoTag(Video video) {
        Integer videoId = video.getId();
        if(video.getRemark()!=null && video.getRemark().length()>0){
            String remark = video.getRemark();

             Pattern p1= Pattern.compile("#(.*?)#");

             Matcher m = p1.matcher(remark);
             StringBuilder stringBuilder = new StringBuilder();
             ArrayList<String> list = new ArrayList<String>();
             while (m.find()) {
                list.add(m.group().trim().replace("#",""));
             }
            for(int i=0;i<list.size();i++){
                String tag = list.get(i);
                if(tag.length()>0 && tag.length()<10){
                    Tag obj = tagDao.selectByTagname(tag);
                    if(obj==null){
                        try{
                            obj = new Tag();
                            obj.setTagname(tag);
                            tagDao.insert(obj);
                        }catch (Exception e){
                            return 0;
                        }finally {
                            obj = tagDao.selectByTagname(tag);
                            tagXvideoService.dealTaskTagXvideo(obj,video);
                        }
                    }else {
                        tagXvideoService.dealTaskTagXvideo(obj,video);
                    }
                }
            }
        }
        return 1;
    }

    @Override
    public int dealTaskArticleTag(Article article) {
        Integer articleId = article.getId();
        if(article.getRemark()!=null && article.getRemark().length()>0){
            String remark = article.getRemark();

            Pattern p1= Pattern.compile("#(.*?)#");

            Matcher m = p1.matcher(remark);
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> list = new ArrayList<String>();
            while (m.find()) {
                list.add(m.group().trim().replace("#",""));
            }
            for(int i=0;i<list.size();i++){
                String tag = list.get(i);
                if(tag.length()>0 && tag.length()<10){
                    Tag obj = tagDao.selectByTagname(tag);
                    if(obj==null){
                        try{
                            obj = new Tag();
                            obj.setTagname(tag);
                            tagDao.insert(obj);
                        }catch (Exception e){
                            return 0;
                        }finally {
                            obj = tagDao.selectByTagname(tag);
                            tagXarticleService.dealTaskTagXarticle(obj,article);
                        }
                    }else {
                        tagXarticleService.dealTaskTagXarticle(obj,article);
                    }
                }
            }
        }
        return 1;
    }

    @Override
    public Tag selectByTagname(String tagname) {
        return tagDao.selectByTagname(tagname);
    }

    @Override
    public List<Tag> firstTagChoose() {
        return tagDao.firstTagChoose();
    }

    @Override
    public List<Tag> findRankTag(Integer objType) {
        return tagDao.findRankTag( objType);
    }
}
