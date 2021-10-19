package com.study.notice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.notice.NoticeServiceImpl")
public class NoticeServiceImpl implements NoticeService {
  @Autowired
  private NoticeMapper mapper;
  @Override
  public int create(NoticeDTO dto) {
    // TODO Auto-generated method stub
    return mapper.create(dto);
  }
 
  @Override
  public List<NoticeDTO> list(Map map) {
    // TODO Auto-generated method stub
    return mapper.list(map);
  }
 
  @Override
  public int total(Map map) {
    // TODO Auto-generated method stub
    return mapper.total(map);
  }
 
}