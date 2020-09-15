package com.example.app.domain.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.app.domain.dto.SelectByOrderDto;
import com.example.app.domain.entity.DeliveryEntity;
import com.example.app.domain.entity.LibraryEntity;
import com.example.app.domain.entity.OrderEntity;

@Dao
@ConfigAutowireable
public interface ManagementDao {
	
	/**
	 * 在庫管理画面一覧表示検索
	 * @return
	 */
	@Select
	List<SelectByOrderDto> selectByOrder();
	
	/**
	 *　在庫管理画面検索表示 
	 * @param genre
	 * @param isbn
	 * @param author
	 * @param title
	 * @param receiptAllFlg
	 * @return
	 */

	@Select
	List<SelectByOrderDto> selectBySearchForm(String genre, 
			String isbn, String author, String title, String receiptAllFlg);
	
	/**
	 * 詳細表示検索
	 * @param isbn
	 * @return
	 */
	@Select
	List<LibraryEntity> selectLibraryByIsbn(String isbn);
	
	/**
	 * 納品画面_注文表示検索
	 * @param isbn
	 * @return
	 */
	@Select
	List<OrderEntity> selectOrderByIsbn(String isbn);
	
	/**
	 * 納品画面_納品表示検索
	 * @param orderNumber
	 * @return
	 */
	@Select
	List<DeliveryEntity> selectDeliveryByOrderNumber(String orderNumber);
	
	/**
	 * 注文マスタ注文番号検索
	 * @param orderNumber
	 * @return
	 */
	@Select
	List<OrderEntity> selectOrderByOrderNumber(String orderNumber);
	
	/**
	 * 注文登録
	 * @param order
	 * @return
	 */
	@Insert(excludeNull = true)
	int insertOrder(OrderEntity order);
	
	@Insert(excludeNull = true)
	int insertDelivery(DeliveryEntity delivery);
	
	/**
	 * 文庫マスタ更新
	 * @param library
	 * @return
	 */
	@Update
	int updateLibrary(LibraryEntity library);
	
	/**
	 * 注文マスタ更新
	 * @param order
	 * @return
	 */
	@Update
	int updateOrder(OrderEntity order);
	
}
