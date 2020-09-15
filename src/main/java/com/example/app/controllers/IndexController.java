package com.example.app.controllers;


import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.app.controllers.forms.IndexDeliveryForm;
import com.example.app.controllers.forms.IndexOrderForm;
import com.example.app.controllers.forms.IndexSearchForm;
import com.example.app.domain.entity.DeliveryEntity;
import com.example.app.domain.entity.LibraryEntity;
import com.example.app.domain.entity.OrderEntity;
import com.example.app.domain.services.ManagementService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	ManagementService mservice;
	
	/**
	 * 管理画面初期表示
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		
		  /**
		   * selectの表示に使用するアイテム
		   */
		  final Map<String, String> SELECT_ITEMS =
		    Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
			/**
				 * 
				 */
				private static final long serialVersionUID = 8695526210129222723L;

			{
		      put("日本文学", "日本文学");
		      put("文学", "文学");
		      put("思想", "思想");
		      put("コンピュータ・IT", "コンピュータ・IT");
		      put("アンソロジー", "アンソロジー");
		    }
		  });
		
		//一覧表示
		mav.addObject("orderList", mservice.getSelectByOrder());
		mav.addObject("selectItems", SELECT_ITEMS);
		
		//表示
		mav.setViewName("Stock/index");
		return mav;
	}
	
	/**
	 * 検索処理表示
	 * @param mav
	 * @param isbn
	 * @return
	 */
	
	@RequestMapping(value="/search" , method = RequestMethod.POST)
	public ModelAndView search(ModelAndView mav, @ModelAttribute IndexSearchForm form) {		

		/**
		   * selectの表示に使用するアイテム
		   */
		  final Map<String, String> SELECT_ITEMS =
		    Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
			/**
				 * 
				 */
				private static final long serialVersionUID = 8695526210129222723L;

			{
		      put("日本文学", "日本文学");
		      put("文学", "文学");
		      put("思想", "思想");
		      put("コンピュータ・IT", "コンピュータ・IT");
		      put("アンソロジー", "アンソロジー");
		    }
		  });
		
		//一覧表示
		  mav.addObject("orderList", mservice.getSelectBySearchForm(form.getGenre(),
					form.getIsbn(), form.getAuthor(), form.getTitle(), form.getReceiptAllFlg()));
		
		
		mav.addObject("selectItems", SELECT_ITEMS);
		
		mav.setViewName("Stock/index");
		
		return mav;
	}
	
	
	
	/**
	 * 詳細画面表示
	 * @param mav
	 * @param isbn
	 * @return
	 */
	@RequestMapping(value="/details/{isbn}")
	public ModelAndView details(ModelAndView mav, @PathVariable("isbn") String isbn) {		
		
		List<LibraryEntity> details = mservice.getSelectLibraryByIsbn(isbn);
		mav.addObject("details", details);
		
		mav.setViewName("Stock/details");
		
		return mav;
	}
	
	/**
	 * 注文画面表示
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/order", method = RequestMethod.POST)
	public ModelAndView order(ModelAndView mav,
			@ModelAttribute IndexOrderForm form, String isbn) {
		
		//日付の取得
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String today=sdf.format(date);
		
		//値とビューの設定
		mav.addObject("orderDe", form);
		mav.addObject("today", today);
		mav.setViewName("Stock/order");
		
		return mav;
	}
	
	/**
	 * 納品画面表示
	 * @param mav
	 * @param isbn
	 * @return
	 */
	@RequestMapping(value="/delivery", method = RequestMethod.POST)
	public ModelAndView delivery(ModelAndView mav, 
			@ModelAttribute IndexDeliveryForm form) {
		
		//在庫管理画面から取得した値をセット
		mav.addObject("deliveryDe",form);
		
		/**
		 * システム日付の取得、設定
		 */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String confirmDeliveryDate = sdf.format(date);
		
		mav.addObject("confirmDeliveryDate", confirmDeliveryDate);
		
		/**
		 * 注文個数、日付の取得、セット
		 */
		List<OrderEntity> orderEntity = mservice.getSelectOrderByIsbn(form.getIsbn());
		
		mav.addObject("orderQuantity", orderEntity.get(0).getOrderQuantity());
		mav.addObject("orderDate", orderEntity.get(0).getOrderDate());

		
		/**
		 * 納品個数、日付の取得、セット
		 */
		List<DeliveryEntity> deliveryEntity = 
				mservice.getSelectDeliveryByOrderNumber(form.getOrderNumber());

		mav.addObject("deliveryQuantity", deliveryEntity.get(0).getDeliberyQuantity());
		mav.addObject("deliveryDate", deliveryEntity.get(0).getDeliveryDate());
				
		
		//ビューの設定
		mav.setViewName("Stock/delivery");
		
		return mav;
	}
	
}
