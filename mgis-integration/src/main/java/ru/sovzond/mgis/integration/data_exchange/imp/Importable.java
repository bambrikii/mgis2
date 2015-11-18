package ru.sovzond.mgis.integration.data_exchange.imp;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
public interface Importable {
	void imp(File file);

	void imp(InputStream inputStream);
}
