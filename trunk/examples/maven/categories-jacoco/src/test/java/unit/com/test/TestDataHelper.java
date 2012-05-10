package com.test;

import java.util.ArrayList;
import java.util.List;

import com.domain.Trade;

public class TestDataHelper {

	public List<Trade> getTrades() {
		List<Trade> tradeList = new ArrayList<Trade>();
		tradeList.add(new Trade(1L, "Trade 1 Description", "Reference 1"));
		tradeList.add(new Trade(2L, "Trade 2 Description", "Reference 2"));
		tradeList.add(new Trade(3L, "Trade 3 Description", "Reference 3"));
		return tradeList;
	}

}
