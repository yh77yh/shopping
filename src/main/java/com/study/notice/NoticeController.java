package com.study.notice;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.utility.Utility;

@Controller
public class NoticeController {
	  @Autowired
	  @Qualifier("com.study.notice.NoticeServiceImpl")
	  private NoticeService service;
	
	  @GetMapping("create")
	  public String create() {
	 
	    return "/create";
	  }
	 
	  @PostMapping("create")
	  public String create(NoticeDTO dto) {
	 
	    if (service.create(dto) == 1) {
	      return "redirect:list";
	    } else {
	      return "/error";
	    }
	 
	  }
	 
	  @RequestMapping("list")
	  public String list(HttpServletRequest request) {
	    // 검색관련------------------------
	    String col = Utility.checkNull(request.getParameter("col"));
	    String word = Utility.checkNull(request.getParameter("word"));
	 
	    if (col.equals("total")) {
	      word = "";
	    }
	 
	    // 페이지관련-----------------------
	    int nowPage = 1;// 현재 보고있는 페이지
	    if (request.getParameter("nowPage") != null) {
	      nowPage = Integer.parseInt(request.getParameter("nowPage"));
	    }
	    int recordPerPage = 3;// 한페이지당 보여줄 레코드갯수
	 
	    // DB에서 가져올 순번-----------------
	    int sno = ((nowPage - 1) * recordPerPage) ;
	    // int eno = nowPage * recordPerPage;
	 
	    Map map = new HashMap();
	    map.put("col", col);
	    map.put("word", word);
	    map.put("sno", sno);
	    map.put("cnt", recordPerPage);
	 
	    int total = service.total(map);
	 
	    List<NoticeDTO> list = service.list(map);
	 
	    String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
	 
	    // request에 Model사용 결과 담는다
	    request.setAttribute("list", list);
	    request.setAttribute("nowPage", nowPage);
	    request.setAttribute("col", col);
	    request.setAttribute("word", word);
	    request.setAttribute("paging", paging);
	 
	    // view페이지 리턴
	    return "/list";
	  }
	}