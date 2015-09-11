package ru.sovzond.mgis2.registers.persons;

import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKFS;
import ru.sovzond.mgis2.registers.national_classifiers.OKOPF;
import ru.sovzond.mgis2.registers.national_classifiers.OKVED;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "mgis2_legal_person")
public class LegalPerson extends Person {

	/**
	 * Сокращенное наименование юридического лица
	 */
	@Column
	private String shortName;

	/**
	 * Название на иностранном языке
	 */
	@Column
	private String nameInAForeignLanguage;

	/**
	 * Предыдущее наименование предприятия
	 */
	@Column
	private String previousCompanyName;

	/**
	 * Форма собственности (ОКФС)
	 */
	@ManyToOne
	private OKFS ownershipForm;

	/**
	 * Резидент
	 */
	@Column
	private Boolean resident;

	/**
	 * Учредители
	 */
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "mgis2_legal_person_founders")
	private List<Person> founders = new ArrayList<>();


	/**
	 * Лица, действующие от имени организации
	 */
	@ElementCollection
	@CollectionTable(name = "representatives")
	private List<String> representatives = new ArrayList<>();

	/**
	 * Вид деятельности (ОКВЭД)
	 */
	@ManyToOne
	private OKVED activityType;

//	/**
//	 * ОКПО
//	 */
//	private OKPO okpo;
//
//	/**
//	 * ОГРН
//	 */
//	private OGRN ogrn;
//
//	/**
//	 * ОКОГУ
//	 */
//	private OKOGU okogu;

	/**
	 * КПП организации
	 */
	@Column
	private String kpp;

	/**
	 * ИНН
	 */
	@Column
	private String inn;

	/**
	 * БИК
	 */
	@Column
	private String bik;

	/**
	 * Организационно-правовая форма
	 */
	@ManyToOne
	private OKOPF organizationalForm;

	/**
	 * Дата начала действия статуса организации
	 */
	@Column
	private Date statusStartDate;

	/**
	 * Дата регистрации предприятия
	 */
	@Column
	private Date registrationDate;

	/**
	 * Свидетельство о регистрации предприятия
	 */
	@Column
	private String registrationCertificate;

	/**
	 * Дата внесения записи "Наименование" в ЕГРЮЛ
	 */
	@Column
	private Date egrulInitialDate;

	// Фактический адрес предприятия

	/**
	 * Код территории по ОКАТО
	 */
	@ManyToOne
	private OKATO actualAddressTerritoryOkatoCode;

	/**
	 * Фактический адрес
	 */
	@ManyToOne
	private Address actualAddress;

	// Юридический адрес

	/**
	 * Код территории по ОКАТО
	 */
	@ManyToOne
	private OKATO legalAddressTerritoryOkatoCode;

	/**
	 * Юридический адрес
	 */
	@ManyToOne
	private Address legalAddress;

	// Контактная информация

	/**
	 * Код города
	 */
	@Column
	private String contactCityCode;

	/**
	 * Телефон
	 */
	@Column
	private String contactPhone;

	/**
	 * Факс
	 */
	@Column
	private String contactFax;

	/**
	 * Веб сайт
	 */
	@Column
	private String contactWebsite;

	/**
	 * Электронный адрес
	 */
	@Column
	private String contactEmail;

	// Контактная информация руководителя предприятия

	/**
	 * Ф.И.О.
	 */
	@Column
	private String directorFullName;

	/**
	 * Должность
	 */
	@Column
	private String directorPosition;

	/**
	 * Телефон
	 */
	@Column
	private String directorPhone;

	/**
	 * Электронный адрес
	 */
	@Column
	private String directorEmail;


	public LegalPerson clone() {
		LegalPerson person = new LegalPerson();
		person.setId(getId());
		person.setName(getName());
		return person;
	}
}
