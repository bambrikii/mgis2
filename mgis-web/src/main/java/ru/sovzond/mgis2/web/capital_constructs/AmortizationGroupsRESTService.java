package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.capital_constructs.characteristics.economical.AmortizationGroup;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 */
@RestController
@RequestMapping("/capital-constructs/amortization-groups")
@Scope("session")
public class AmortizationGroupsRESTService {

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<AmortizationGroup> list(@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		AmortizationGroup[] values = AmortizationGroup.values();
		return new PageableContainer<>(Arrays.asList(values), values.length, 0, 0);
	}

}
