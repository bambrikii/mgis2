package ru.sovzond.mgis.integration.data_exchange.imp;

import java.io.*;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
public class LandsImporter implements Importable {
	public void imp(File file) {
		try (InputStream is = new FileInputStream(file)) {
			imp(is);
		} catch (FileNotFoundException e) {
			// TODO:
			e.printStackTrace();
		} catch (IOException e) {
			// TODO:
			e.printStackTrace();
		}
	}

	public void imp(InputStream inputStream) {

	}
}
