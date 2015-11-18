package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sovzond.mgis2.lands.LandBean;

import java.io.*;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@Component
public class LandsImporter implements Importable {
	@Autowired
	private LandBean landBean;

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
		throw new UnsupportedOperationException("Not yet implemented.");
	}
}
