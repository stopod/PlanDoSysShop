package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.app.controllers.forms.IndexSearchForm;
import com.example.app.controllers.forms.ConfirmOrderForm;
import com.example.app.domain.services.ManagementService;

@Controller
@RequestMapping("/order_confirm")
public class OrderController {
	
	@Autowired
	ManagementService mservice;
	
	/**
	 * 注文処理
	 * @param form
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView orderConfirm(@ModelAttribute ConfirmOrderForm form, ModelAndView mav) {		
		
		//注文マスタへ挿入
		mservice.insertConfirmOrder(form);
		
		//検索フォーム初期化
		mav.addObject("searchForm", new IndexSearchForm());
		
		//値の設定、ビューの設定
		mav.addObject("orderList", mservice.getSelectByOrder());
		mav.setViewName("redirect:/index/");
		
		return mav;
	}
	
}
