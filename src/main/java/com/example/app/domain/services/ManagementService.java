package com.example.app.domain.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.controllers.forms.ConfirmDeliveryForm;
import com.example.app.controllers.forms.ConfirmOrderForm;
import com.example.app.domain.dao.ManagementDao;
import com.example.app.domain.dto.SelectByOrderDto;
import com.example.app.domain.entity.DeliveryEntity;
import com.example.app.domain.entity.LibraryEntity;
import com.example.app.domain.entity.OrderEntity;

@Service
public class ManagementService {
	
	@Autowired
    private ManagementDao mdao;
	
	/**
	 * 管理画面一覧検索
	 * @return
	 */
    public List<SelectByOrderDto> getSelectByOrder() {
        return mdao.selectByOrder();
    }
    
    /**
     * 管理画面検索
     * @param genre
     * @param isbn
     * @param author
     * @param title
     * @param receiptAllFlg
     * @return
     */
    public List<SelectByOrderDto> getSelectBySearchForm(String genre, 
			String isbn, String author, String title, String receiptAllFlg){
    	
    	return mdao.selectBySearchForm(genre,isbn,author,title,receiptAllFlg);
    }
    
    /**
     * 詳細画面表示検索
     * @param isbn
     * @return
     */
    public List<LibraryEntity> getSelectLibraryByIsbn(String isbn){
		return mdao.selectLibraryByIsbn(isbn);
    }
    
    /**
     * 納品画面_注文表示検索
     * @param isbn
     * @param orderNumber
     * @return
     */
    public List<OrderEntity> getSelectOrderByIsbn(String isbn){
    	return mdao.selectOrderByIsbn(isbn);
    }
    
    /**
     * 納品画面_納品表示検索
     * @param orderNumber
     * @return
     */
    public List<DeliveryEntity> getSelectDeliveryByOrderNumber(String orderNumber){
    	return mdao.selectDeliveryByOrderNumber(orderNumber);
    }
    
    /**
     * 注文登録
     * @param form
     * @return
     */
    public OrderEntity insertConfirmOrder(ConfirmOrderForm form) {
		
    	/**
    	 * 共通項目の設定
    	 */
    	
    	//注文番号の生成
    	UUID uuid = UUID.randomUUID();
        String orderNumber = uuid.toString();
        
        //日付の取得
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String today = sdf.format(date);
        
    	/**
    	 * 注文マスタ挿入
    	 */
        
    	//インスタンス化
    	OrderEntity order = new OrderEntity();
        
        //挿入するレコードの値をセット
    	order.setIsbn(form.getIsbn().toString());
    	order.setOrderDate(today);
    	order.setOrderNumber(orderNumber);
    	order.setOrderQuantity(form.getOrderQuantity());
    	order.setReceiptAllFlg(0);
    	
    	//クエリ実行
    	mdao.insertOrder(order);
    	
    	/**
    	 * 納品マスタ挿入
    	 */
    	//インスタンス化
    	DeliveryEntity delivery = new DeliveryEntity();
    	
    	//挿入するレコードの値をセット
    	delivery.setOrderNumber(orderNumber);
    	delivery.setDeliberyQuantity(0);
    	delivery.setDeliveryDate("");
    	delivery.setRecipient("");
    	
    	//クエリ実行
    	mdao.insertDelivery(delivery);
    	
		return order;
    }
    
    /**
     * 納品登録
     * @param form
     * @return
     */
    public DeliveryEntity insertDelivery(ConfirmDeliveryForm form) {
    	
    	
    	/**
    	 * 納品マスタ挿入
    	 */
    	//インスタンス化
    	DeliveryEntity delivery = new DeliveryEntity();
    	//挿入するレコードの値をセット
    	delivery.setOrderNumber(form.getOrderNumber());
    	delivery.setDeliveryDate(form.getConfirmDeliveryDate());
    	delivery.setDeliberyQuantity(form.getConfirmDeliveryQuantity());
    	delivery.setRecipient(form.getConfirmRecipient());
    	//クエリ実行
    	mdao.insertDelivery(delivery);
    	
    	/**
    	 * 文庫マスタ更新
    	 */
    	//インスタンス化
    	LibraryEntity libraryEntity = new LibraryEntity();
    	//更新レコードの取得
    	List<LibraryEntity> library = mdao.selectLibraryByIsbn(form.getIsbn());
    	//在庫の追加
    	int addStock = library.get(0).getStock() + form.getConfirmDeliveryQuantity();
    	//挿入する値をセット
    	libraryEntity = library.get(0);
    	libraryEntity.setStock(addStock);
    	//クエリ実行
    	mdao.updateLibrary(libraryEntity);
    	
    	/**
    	 * 注文マスタ更新
    	 */
    	//注文個数 <= 累計納品数 + 今回の納品数だった場合
    	if(form.getOrderQuantity() <= (form.getDeliveryQuantity() + form.getConfirmDeliveryQuantity())) {
    		
    		List<OrderEntity> order = mdao.selectOrderByOrderNumber(form.getOrderNumber());
    		OrderEntity orderEntity = new OrderEntity();
    		
    		orderEntity = order.get(0);
    		orderEntity.setReceiptAllFlg(1);
    		
    		//クエリ実行
    		mdao.updateOrder(orderEntity);
    	}
    	
		return delivery;
    }
    
}
