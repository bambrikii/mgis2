package ru.sovzond.mgis2.registers.persons;

import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.registers.national_classifiers.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Entity
@Table(name = "mgis2_legal_person")
public class LegalPerson extends Person {

	@Column
	private String name;

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

	/**
	 * ОКПО
	 */
	private String okpo;

	/**
	 * ОГРН
	 */
	private String ogrn;

	/**
	 * ОКОГУ
	 */
	@ManyToOne
	private OKOGU okogu;

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
	@JoinColumn(name = "actual_address_id")
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
	@JoinColumn(name = "legal_address_id")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getNameInAForeignLanguage() {
		return nameInAForeignLanguage;
	}

	public void setNameInAForeignLanguage(String nameInAForeignLanguage) {
		this.nameInAForeignLanguage = nameInAForeignLanguage;
	}

	public String getPreviousCompanyName() {
		return previousCompanyName;
	}

	public void setPreviousCompanyName(String previousCompanyName) {
		this.previousCompanyName = previousCompanyName;
	}

	public OKFS getOwnershipForm() {
		return ownershipForm;
	}

	public void setOwnershipForm(OKFS ownershipForm) {
		this.ownershipForm = ownershipForm;
	}

	public Boolean getResident() {
		return resident;
	}

	public void setResident(Boolean resident) {
		this.resident = resident;
	}

	public List<Person> getFounders() {
		return founders;
	}

	public void setFounders(List<Person> founders) {
		this.founders = founders;
	}

	public List<String> getRepresentatives() {
		return representatives;
	}

	public void setRepresentatives(List<String> representatives) {
		this.representatives = representatives;
	}

	public OKVED getActivityType() {
		return activityType;
	}

	public void setActivityType(OKVED activityType) {
		this.activityType = activityType;
	}

	public String getOkpo() {
		return okpo;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	public String getOgrn() {
		return ogrn;
	}

	public void setOgrn(String ogrn) {
		this.ogrn = ogrn;
	}

	public OKOGU getOkogu() {
		return okogu;
	}

	public void setOkogu(OKOGU okogu) {
		this.okogu = okogu;
	}

	public String getKpp() {
		return kpp;
	}

	public void setKpp(String kpp) {
		this.kpp = kpp;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getBik() {
		return bik;
	}

	public void setBik(String bik) {
		this.bik = bik;
	}

	public OKOPF getOrganizationalForm() {
		return organizationalForm;
	}

	public void setOrganizationalForm(OKOPF organizationalForm) {
		this.organizationalForm = organizationalForm;
	}

	public Date getStatusStartDate() {
		return statusStartDate;
	}

	public void setStatusStartDate(Date statusStartDate) {
		this.statusStartDate = statusStartDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationCertificate() {
		return registrationCertificate;
	}

	public void setRegistrationCertificate(String registrationCertificate) {
		this.registrationCertificate = registrationCertificate;
	}

	public Date getEgrulInitialDate() {
		return egrulInitialDate;
	}

	public void setEgrulInitialDate(Date egrulInitialDate) {
		this.egrulInitialDate = egrulInitialDate;
	}

	public OKATO getActualAddressTerritoryOkatoCode() {
		return actualAddressTerritoryOkatoCode;
	}

	public void setActualAddressTerritoryOkatoCode(OKATO actualAddressTerritoryOkatoCode) {
		this.actualAddressTerritoryOkatoCode = actualAddressTerritoryOkatoCode;
	}

	public Address getActualAddress() {
		return actualAddress;
	}

	public void setActualAddress(Address actualAddress) {
		this.actualAddress = actualAddress;
	}

	public OKATO getLegalAddressTerritoryOkatoCode() {
		return legalAddressTerritoryOkatoCode;
	}

	public void setLegalAddressTerritoryOkatoCode(OKATO legalAddressTerritoryOkatoCode) {
		this.legalAddressTerritoryOkatoCode = legalAddressTerritoryOkatoCode;
	}

	public Address getLegalAddress() {
		return legalAddress;
	}

	public void setLegalAddress(Address legalAddress) {
		this.legalAddress = legalAddress;
	}

	public String getContactCityCode() {
		return contactCityCode;
	}

	public void setContactCityCode(String contactCityCode) {
		this.contactCityCode = contactCityCode;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactWebsite() {
		return contactWebsite;
	}

	public void setContactWebsite(String contactWebsite) {
		this.contactWebsite = contactWebsite;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getDirectorFullName() {
		return directorFullName;
	}

	public void setDirectorFullName(String directorFullName) {
		this.directorFullName = directorFullName;
	}

	public String getDirectorPosition() {
		return directorPosition;
	}

	public void setDirectorPosition(String directorPosition) {
		this.directorPosition = directorPosition;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public String getDirectorEmail() {
		return directorEmail;
	}

	public void setDirectorEmail(String directorEmail) {
		this.directorEmail = directorEmail;
	}


	public LegalPerson clone() {
		LegalPerson person = new LegalPerson();
		person.setId(getId());
		person.setName(getName());
		person.setActivityType(activityType != null ? activityType.clone() : null);
		person.setActualAddress(actualAddress != null ? actualAddress.clone() : null);
		person.setActualAddressTerritoryOkatoCode(actualAddressTerritoryOkatoCode != null ? actualAddressTerritoryOkatoCode.clone() : null);
		person.setBik(bik);
		person.setContactCityCode(contactCityCode);
		person.setContactEmail(contactEmail);
		person.setContactFax(contactFax);
		person.setContactPhone(contactPhone);
		person.setContactWebsite(contactWebsite);
		person.setDirectorFullName(directorFullName);
		person.setDirectorEmail(directorEmail);
		person.setDirectorPhone(directorPhone);
		person.setDirectorPosition(directorPosition);
		person.setEgrulInitialDate(egrulInitialDate);
		person.setFounders(founders.stream().map(p -> {
			if (p instanceof LegalPerson) {
				LegalPerson lp = new LegalPerson();
				lp.setId(p.getId());
				lp.setName(((LegalPerson) p).getName());
				return lp;
			} else if (p instanceof NaturalPerson) {
				return p.clone();
			}
			return p.clone();
		}).collect(Collectors.toList()));
		person.setInn(inn);
		person.setKpp(kpp);
		person.setLegalAddress(legalAddress != null ? legalAddress.clone() : null);
		person.setLegalAddressTerritoryOkatoCode(legalAddressTerritoryOkatoCode != null ? legalAddressTerritoryOkatoCode.clone() : null);
		person.setOgrn(ogrn);
		person.setOkogu(okogu != null ? okogu.clone() : null);
		person.setOkpo(okpo);
		person.setOrganizationalForm(organizationalForm != null ? organizationalForm.clone() : null);
		person.setOwnershipForm(ownershipForm != null ? ownershipForm.clone() : null);
		person.setPreviousCompanyName(previousCompanyName);
		person.setRegistrationCertificate(registrationCertificate);
		person.setRegistrationDate(registrationDate);
		for (String representative : representatives) {
			person.getRepresentatives().add(person.getRepresentatives().size(), representative);
		}
		person.setResident(resident);
		person.setNameInAForeignLanguage(nameInAForeignLanguage);
		person.setShortName(shortName);
		person.setStatusStartDate(statusStartDate);
		return person;
	}
}
