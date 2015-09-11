package ru.sovzond.mgis2.registers.persons;

import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.registers.national_classifiers.LandOwnershipForm;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mgis2_legal_person")
public class LegalPerson extends Person {

	/**
	 * Сокращенное наименование юридического лица
	 */
	private String shortName;

	/**
	 * Название на иностранном языке
	 */
	private String nameInAForeignLanguage;

	/**
	 * Предыдущее наименование предприятия
	 */
	private String previousCompanyName;

	/**
	 * Форма собственности (ОКФС)
	 */
	private LandOwnershipForm ownershipForm;

	/**
	 * Резиденты
	 */
	private List<NaturalPerson> residents = new ArrayList<>();

	/**
	 * Учредители
	 */
	private List<NaturalPerson> founders = new ArrayList<>();


	/**
	 * Лица, действующие от имени организации
	 */
	private List<NaturalPerson> representatives = new ArrayList<>();

	/**
	 * Вид деятельности (ОКВЭД)
	 */
	private ActivityType activityType;

	/**
	 * ОКПО
	 */
	private OKPO okpo;

	/**
	 * ОГРН
	 */
	private OGRN ogrn;

	/**
	 * ОКОГУ
	 */
	private OKOGU okogu;

	/**
	 * КПП организации
	 */
	private String kpp;

	/**
	 * ИНН
	 */
	private String inn;

	/**
	 * БИК
	 */
	private String bik;

	/**
	 * Организационно-правовая форма
	 */
	private OrganizationalForm organizationalForm;

	/**
	 * Дата начала действия статуса организации
	 */
	private Date statusStartDate;

	/**
	 * Дата регистрации предприятия
	 */
	private Date registrationDate;

	/**
	 * Свидетельство о регистрации предприятия
	 */
	private RegistrationCertificate registrationCertificate;

	/**
	 * ВИК
	 */
	private VIK vik;

	/**
	 * Головное предприятие
	 */
	private LegalPerson headOrganization;

	/**
	 * Основное предприятие
	 */
	private LegalPerson mainOrganization;

	/**
	 * Дочернее предприятие
	 */
	private LegalPerson childOrganization;

	/**
	 * Дата внесения записи "Наименование" в ЕГРЮЛ
	 */
	private Date egrulInitialDate;

	/**
	 * Организации, входящие в холдинг (группу компаний)
	 */
	private List<LegalPerson> holdingOrganizations = new ArrayList<>();

	// Фактический адрес предприятия

	/**
	 * Код территории по ОКАТО
	 */
	private OKATO actualAddressTerritoryOkatoCode;

	/**
	 * Фактический адрес
	 */
	private Address actualAddress;

	// Юридический адрес

	/**
	 * Код территории по ОКАТО
	 */
	private OKATO legalAddressTerritoryOkatoCode;

	/**
	 * Юридический адрес
	 */
	private Address legalAddress;

	// Контактная информация

	/**
	 * Код города
	 */
	private String contactCityCode;

	/**
	 * Телефон
	 */
	private String contactPhone;

	/**
	 * Факс
	 */
	private String contactFax;

	/**
	 * Веб сайт
	 */
	private String contactWebsite;

	/**
	 * Электронный адрес
	 */
	private String contactEmail;

	// Контактная информация руководителя предприятия

	/**
	 * Ф.И.О.
	 */
	private String directorFullName;

	/**
	 * Должность
	 */
	private String directorPosition;

	/**
	 * Телефон
	 */
	private String directorPhone;

	/**
	 * Электронный адрес
	 */
	private String directorEmail;


	public LegalPerson clone() {
		LegalPerson person = new LegalPerson();
		person.setId(getId());
		person.setName(getName());
		return person;
	}
}
