package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.capital_constructs.ConstructType;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * Created by Alexander Arakelyan on 11.11.15.
 */
@RestController
@RequestMapping("/capital-constructs/construct-types")
@Scope("session")
public class ConstructTypeRESTService {
	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<ConstructType> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		ConstructType[] values = ConstructType.values();
		return new PageableContainer<>(Arrays.asList(values), values.length, 0, 0);
	}
}
