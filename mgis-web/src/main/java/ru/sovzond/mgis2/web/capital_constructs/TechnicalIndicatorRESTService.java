package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.capital_construct.TechnicalIndicatorBean;

/**
 * Created by Alexander Arakelyan on 11.11.15.
 */
@RestController
@RequestMapping("/capital-constructs/constructs")
@Scope("session")
public class TechnicalIndicatorRESTService {
	@Autowired
	private TechnicalIndicatorBean technicalIndicatorBean;
}
