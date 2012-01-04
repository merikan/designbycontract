package com.dbc.service.order;

import com.dbc.domain.Order;
import com.dbc.service.email.EmailService;
import com.dbc.service.warehouse.WarehouseService;

public class SimpleOrderService implements OrderService{

	private WarehouseService warehouseService;
	private EmailService emailService;
	
	public SimpleOrderService(WarehouseService warehouseService,
			EmailService emailService) {
		this.warehouseService = warehouseService;
		this.emailService = emailService;
	}

	public void createOrder(Order order)
	{
		int stockLevels = warehouseService.getStockLevelById(order.getId());
		if (stockLevels == 0)
		{
			emailService.send("The item is not in stock [" + order.getId() + "]");
		}
	}
}
