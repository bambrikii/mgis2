package ru.sovzond.mgis2.web.data_exchange.imp;

import java.io.InputStream;

/**
 * Created by Alexander Arakelyan on 29/11/15.
 */
@FunctionalInterface
public interface ImportProcessable {
	String doImport(InputStream inputStream);
}
