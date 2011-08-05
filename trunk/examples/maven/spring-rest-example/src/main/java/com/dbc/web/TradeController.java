package com.dbc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.model.Trade;
import com.dbc.service.TradeService;

@Controller
public class TradeController {
 
	@Autowired
	TradeService service;
 
	@RequestMapping(value = "/trade/{reference}")
	public ModelAndView getTradeByReferemce(@PathVariable String reference) {
		Trade trade = service.getTradeByReference(reference);
		ModelAndView mav = new ModelAndView("tradeView", BindingResult.MODEL_KEY_PREFIX + "trade", trade);
		return mav;
	}
}