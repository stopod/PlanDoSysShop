SELECT
	t1.isbn,
	t1.title,
	t1.author,
	t1.stock,
	t1.price,
-- 注文状態	
	CASE t2.receipt_all_flg
		WHEN 0 THEN '依頼中'
		ELSE '―'
	END AS order_status,	
-- 注文日
	CASE t2.receipt_all_flg
		WHEN 0 THEN t2.order_date
		ELSE ''
	END AS order_date,
-- 注文リンク	
	CASE t2.receipt_all_flg 
		WHEN 0 THEN ''
		ELSE '●'
	END AS order_select,
-- 	納品リンク
	CASE t2.receipt_all_flg
		WHEN 0 THEN '●'
		ELSE ''
	END AS delivery_select,
--	注文番号
	CASE t2.receipt_all_flg
		WHEN 0 THEN t2.order_number
		ELSE ''
	END AS order_number
FROM
	mt_library t1
	LEFT OUTER JOIN mt_order t2 ON
	t1.isbn = t2.isbn and 
	t2.receipt_all_flg = 0
	
WHERE
	/*%if genre != "" */
    	t1.genre = /* genre */'smith'
	/*%end*/

	/*%if isbn != "" */
    	AND t1.isbn LIKE /* @infix(isbn) */'smith'
	/*%end*/
	
	/*%if author != "" */
    	AND t1.author LIKE /* @infix(author) */'smith'
	/*%end*/
	
	/*%if title != "" */
    	AND t1.title LIKE /* @infix(title) */'smith'
	/*%end*/
	
	/*%if receiptAllFlg != "" */
		and t2.receipt_all_flg = /* receiptAllFlg */'smith'
	/*%end*/

ORDER BY
	t1.isbn