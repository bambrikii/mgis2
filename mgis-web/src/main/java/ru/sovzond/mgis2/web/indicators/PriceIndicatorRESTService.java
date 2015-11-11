package ru.sovzond.mgis2.web.indicators;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruct;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.indicators.PriceIndicator;
import ru.sovzond.mgis2.indicators.PriceIndicatorBean;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 */
@RestController
@RequestMapping("/indicators/price-indicators")
@Scope("session")
public class PriceIndicatorRESTService {
	@Autowired
	private PriceIndicatorBean priceTypeBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<PriceIndicator> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return priceTypeBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public PriceIndicator save(@PathVariable Long id, @RequestBody CapitalConstruct capitalConstruct) {
		PriceIndicator priceType2;
		if (id == 0) {
			priceType2 = new PriceIndicator();
		} else {
			priceType2 = priceTypeBean.load(id);
		}
		BeanUtils.copyProperties(capitalConstruct, priceType2, new String[]{"id"});
		priceTypeBean.save(priceType2);
		return priceType2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PriceIndicator read(@PathVariable Long id) {
		return priceTypeBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		priceTypeBean.remove(priceTypeBean.load(id));
	}

}
