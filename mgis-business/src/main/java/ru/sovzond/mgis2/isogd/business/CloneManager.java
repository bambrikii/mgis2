package ru.sovzond.mgis2.isogd.business;

import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.Document;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.Volume;

public class CloneManager {

	public static Book clone(Book book) {
		Book book2 = new Book();
		book2.setId(book.getId());
		book2.setName(book.getName());
		book2.setVolume(clone(book.getVolume()));
		return book2;
	}

	public static Volume clone(Volume volume) {
		Volume volume2 = new Volume();
		volume2.setId(volume.getId());
		volume2.setName(volume.getName());
		volume2.setSection(clone(volume.getSection()));
		return volume2;
	}

	static Section clone(Section section) {
		Section section2 = new Section();
		section2.setId(section.getId());
		section2.setName(section.getName());
		return section2;
	}

	public static Document clone(Document document) {
		Document document2 = new Document();
		document2.setId(document.getId());
		document2.setName(document.getName());
		document2.setBook(clone(document.getBook()));
		return document2;
	}
}
