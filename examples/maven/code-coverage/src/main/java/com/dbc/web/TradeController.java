package com.dbc.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.model.Trade;
import com.dbc.service.TradeService;

@Controller
public class TradeController {
 
	TradeService service;
	
	public TradeController(TradeService service)
	{
		this.service = service;
	}
 	
	@RequestMapping(value = "/find/trade/{id}")
	public ModelAndView findTradeById(@PathVariable Long id) {
		Trade trade = service.findTradeById(id);
		ModelAndView mav = new ModelAndView("tradeView", 
				BindingResult.MODEL_KEY_PREFIX + "trade", trade);
		return mav;
	}
	
	@RequestMapping(value = "/create/trade/{id}")
	public ModelAndView createTrade(@PathVariable Long id) {
		Trade trade = new Trade(id); 
		service.createTrade(trade);
		ModelAndView mav = new ModelAndView("tradeView", BindingResult.MODEL_KEY_PREFIX + "trade", trade);
		return mav;
	}
	
}