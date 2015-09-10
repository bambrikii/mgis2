package ru.sovzond.mgis2.persons;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.registers.persons.Person;

@Repository
public class PersonDao extends CRUDDaoBase<Person> {

}
