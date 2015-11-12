package ru.sovzond.mgis2.web.indicators;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.indicators.PriceIndicator;
import ru.sovzond.mgis2.indicators.PriceIndicatorBean;
import ru.sovzond.mgis2.national_classifiers.OKEIBean;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 */
@RestController
@RequestMapping("/indicators/price-indicators")
@Scope("session")
public class PriceIndicatorRESTService {
	@Autowired
	private PriceIndicatorBean priceIndicatorBean;

	@Autowired
	private OKEIBean okeiBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<PriceIndicator> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return priceIndicatorBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public PriceIndicator save(@PathVariable Long id, @RequestBody PriceIndicator priceIndicator) {
		PriceIndicator priceIndicator2;
		if (id == 0) {
			priceIndicator2 = new PriceIndicator();
		} else {
			priceIndicator2 = priceIndicatorBean.load(id);
		}
		BeanUtils.copyProperties(priceIndicator, priceIndicator2, new String[]{"id", "unitOfMeasure"});
		priceIndicator2.setUnitOfMeasure(priceIndicator.getUnitOfMeasure() != null ? okeiBean.load(priceIndicator.getUnitOfMeasure().getId()) : null);
		priceIndicatorBean.save(priceIndicator2);
		return priceIndicator2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PriceIndicator read(@PathVariable Long id) {
		return priceIndicatorBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		priceIndicatorBean.remove(priceIndicatorBean.load(id));
	}

}
