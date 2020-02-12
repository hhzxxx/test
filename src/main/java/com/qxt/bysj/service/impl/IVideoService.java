package com.qxt.bysj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.dao.VideoMapper;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class IVideoService implements VideoService {
    @Autowired
    private VideoMapper videoDao;

    @Override
    public int deleteById(Integer id) {
        return videoDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Video record) {
        return videoDao.insert(record);
    }

    @Override
    public int insertSelective(Video record) {
        return videoDao.insertSelective(record);
    }

    @Override
    public Video selectById(Integer id) {
        return videoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateSelective(Video record) {
        return videoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int update(Video record) {
        return videoDao.updateByPrimaryKey(record);
    }

    @Override
    public Video selectByAvid(Integer avid) {
        return videoDao.selectByAvid(avid);
    }

    @Override
    public int dealTaskVideo(JSONObject jsonObj){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer avid = Integer.valueOf(jsonObj.getString("aid"));
        Video video =  new Video();
        Video video1 = videoDao.selectByAvid(avid);
        if(video1 == null) {
            video.setUpdatetime(date);
            video.setStatus(0);
            video.setCreatetime(date);
            video.setUuid(UUID.randomUUID().toString());
            video.setAvid(avid);
            videoDao.insert(video);
        }
        video = videoDao.selectByAvid(avid);
        video.setUpdatetime(date);
        video.setType(jsonObj.get("tname").toString());
        video.setOwner(jsonObj.getJSONObject("owner").get("name").toString());
        video.setOwnerid((Integer) jsonObj.getJSONObject("owner").get("mid"));
        video.setPic(jsonObj.getString("pic"));
        video.setTitle(jsonObj.getString("title"));
        video.setRemark(jsonObj.getString("dynamic"));
        return videoDao.updateByPrimaryKeySelective(video);
    }
}
