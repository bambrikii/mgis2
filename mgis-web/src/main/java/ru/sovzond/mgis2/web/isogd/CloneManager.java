package ru.sovzond.mgis2.web.isogd;

import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.Volume;

public class CloneManager {

	static Book clone(Book book) {
		Book book2 = new Book();
		book2.setId(book.getId());
		book2.setName(book.getName());
		book2.setVolume(book.getVolume());
		return book2;
	}

	static Volume clone(Volume volume) {
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
}
