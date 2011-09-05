package com.dbc.repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dbc.model.Trade;

public class HibernateTradeRepository  extends HibernateDaoSupport implements TradeRepository{

	@Override
	public Trade getTradeByReference(String reference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long createTrade(Trade trade) {
		return (Long) this.getHibernateTemplate().save(trade);
	}

}
