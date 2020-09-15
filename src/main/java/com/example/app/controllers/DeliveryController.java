package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.app.controllers.forms.ConfirmDeliveryForm;
import com.example.app.controllers.forms.IndexSearchForm;
import com.example.app.domain.services.ManagementService;

@Controller
@RequestMapping("/delivery_confirm")
public class DeliveryController {
	
	@Autowired
	ManagementService mservice;
	
	/**
	 * 納品処理
	 * @param form
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView deliveryConfirm(@ModelAttribute ConfirmDeliveryForm form,
			ModelAndView mav) {		
		
		//挿入処理
		mservice.insertDelivery(form);
		
		//検索フォーム初期化
		mav.addObject("searchForm", new IndexSearchForm());
		//一覧表示
		mav.addObject("orderList", mservice.getSelectByOrder());
		//表示
		mav.setViewName("redirect:/index/");
		return mav;
	}
	
}
