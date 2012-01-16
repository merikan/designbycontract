package com.dbc.service.audit;

import com.dbc.domain.Trade;

public interface AuditService {

	public void logNewTrade(Trade trade);
}
