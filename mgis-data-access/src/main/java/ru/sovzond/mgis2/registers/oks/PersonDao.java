package ru.sovzond.mgis2.registers.oks;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableCRUDDaoBase;
import ru.sovzond.mgis2.registers.oks.rights.Person;

@Repository
public class PersonDao extends PageableCRUDDaoBase<Person> {

}
