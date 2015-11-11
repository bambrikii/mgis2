package ru.sovzond.mgis2.web.indicators;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruct;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.indicators.TechnicalIndicator;
import ru.sovzond.mgis2.indicators.TechnicalIndicatorBean;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 */
@RestController
@RequestMapping("/indicators/technical-indicators")
@Scope("session")
public class TechnicalIndicatorRESTService {
	@Autowired
	private TechnicalIndicatorBean technicalIndicatorBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<TechnicalIndicator> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return technicalIndicatorBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public TechnicalIndicator save(@PathVariable Long id, @RequestBody CapitalConstruct capitalConstruct) {
		TechnicalIndicator priceType2;
		if (id == 0) {
			priceType2 = new TechnicalIndicator();
		} else {
			priceType2 = technicalIndicatorBean.load(id);
		}
		BeanUtils.copyProperties(capitalConstruct, priceType2, new String[]{"id"});
		technicalIndicatorBean.save(priceType2);
		return priceType2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public TechnicalIndicator read(@PathVariable Long id) {
		return technicalIndicatorBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		technicalIndicatorBean.remove(technicalIndicatorBean.load(id));
	}

}
