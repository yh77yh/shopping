package com.study.notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	  int create(NoticeDTO dto);
	  List<NoticeDTO> list(Map map);
	  int total(Map map);
	}
