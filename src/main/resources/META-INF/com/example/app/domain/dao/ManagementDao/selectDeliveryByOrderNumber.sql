SELECT
	order_number,
	MAX(delivery_date) AS delivery_date,
	SUM(delibery_quantity) AS delibery_quantity,
	'' as recipient
FROM
	mt_delivery
WHERE
	order_number = /* orderNumber */'smith'
GROUP BY
	order_number;