package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
//@RequestMapping(value="/guest")
public class PhoneController {
	
	//필드
	
	//생성자
	
	//메소드gs
	
	//메소드일반
	
	
	//수정
	@RequestMapping(value= "update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("update 진입");
		
		System.out.println(personVo);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		
		return "redirect:/list";
	}
	
	
	//수정폼
	@RequestMapping(value= "updateForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(Model model, @RequestParam("no")int no) {
		System.out.println("updateForm 진입");
		
		System.out.println(no);
		
		//Dao로 no에 맞는 사람 정보 불러오기
		PhoneDao phoneDao = new PhoneDao();
		
		//불러온 정보 Vo에 담기
		PersonVo personVo = new PersonVo();
		personVo = phoneDao.getPerson(no);
		
		//attribute에 담아서 보내기
		model.addAttribute("personVo", personVo);
		
		return "WEB-INF/views/updateForm.jsp";
	}
	
	
	
	//전화번호 삭제
	@RequestMapping(value= "delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no")int no) {
		System.out.println("delete 진입");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(no);
		
		return "redirect:/list";
	}
	
	
	//전화번호 리스트
	@RequestMapping(value="list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list 진입");
		
		//dao로 리스트가져오기
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		
		//ds로 보냄 -> ds: request attribute에 넣기
		model.addAttribute("personList", personList);
		
		
		return "/WEB-INF/views/list.jsp";
		
	}
	
	//전화번호 등록
	@RequestMapping(value="write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("write 진입");
		
		
		//vo로 묶기
		System.out.println(personVo);

		//dao로 보내서 insert하기
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
	
		//리다이렉트
		
		return "redirect:/list";
	}
	
	
	
	@RequestMapping(value="write2", method= {RequestMethod.GET, RequestMethod.POST})
	public String write2(@RequestParam("name")String name,
			@RequestParam("hp")String hp,
			@RequestParam("company")String company) {
		System.out.println("write2 진입");
		
		
		System.out.println(name + hp + company);
		
		//vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		
		System.out.println(personVo);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
		
		return "redirect:/list";
	}
	
	
	
	//전화번호 등록 폼
	@RequestMapping(value="writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("writeForm 진입");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	
	
	
	
	
	
	//테스트
	@RequestMapping(value="/test", method={RequestMethod.GET, RequestMethod.POST})
	public String test() {
		
		System.out.println("test 진입");
		
		return "/WEB-INF/views/test.jsp";
	}
	


}
