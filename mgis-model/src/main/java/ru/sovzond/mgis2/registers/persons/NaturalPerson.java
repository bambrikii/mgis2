package ru.sovzond.mgis2.registers.persons;

import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.registers.national_classifiers.OKVED;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mgis2_natural_person")
public class NaturalPerson extends Person {

	// Общие

	/**
	 * ФИО
	 */
	@Column
	private String firstName;

	@Column
	private String surname;

	@Column
	private String patronymic;

	/**
	 * Дата рождения
	 */
	@Column
	private Date dateOfBirth;

	/**
	 * ИНН
	 */
	@Column
	private String inn;

	/**
	 * СНИЛС
	 */
	@Column
	private String snils;

	/**
	 * Телефон
	 */
	@Column
	private String phone;

	/**
	 * Эл.адрес
	 */
	@Column
	private String email;

	/**
	 * Адрес (фактический)
	 */
	@ManyToOne
	@JoinColumn(name = "actual_address_id")
	private Address actualAddress;

	/**
	 * Адрес (юридический)
	 */
	@ManyToOne
	@JoinColumn(name = "legal_address_id")
	private Address legalAddress;

	// Паспортные данные

	/**
	 * Серия
	 */
	@Column
	private String passportSeries;

	/**
	 * Номер
	 */
	@Column
	private String passportNumber;

	/**
	 * Дата выдачи
	 */
	@Column
	private Date passportDateOfIssue;

	/**
	 * Кем выдан
	 */
	@Column
	private String passportIssuerDepartment;

	/**
	 * Индивидуальный предприниматель
	 */
	@Column(columnDefinition = "boolean default false")
	private boolean individualEntrepreneur;

	/**
	 * Регистрационный номер ИП
	 */
	@Column
	private String individualEntrepreneurRegNum;

	/**
	 * ОГРН
	 */
	@Column
	private String ogrn;

	/**
	 * Код и наименование вида деятельности (Общероссийскому классификатору видов экономической деятельности)
	 */
	@ManyToOne
	@JoinColumn(name = "activity_type_id")
	private OKVED activityType;

	/**
	 * ГРН и дата внесения в ЕГРИП записи
	 */
	@Column
	private String grn;

	/**
	 * ГРН и дата внесения в ЕГРИП записи
	 */
	@Column
	private Date egripInitialDate;

	/**
	 * Дата регистрации
	 */
	@Column
	private Date registrationDate;

	/**
	 * Дата внесения записи
	 */
	@Column
	private Date issueDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getSnils() {
		return snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getActualAddress() {
		return actualAddress;
	}

	public void setActualAddress(Address actualAddress) {
		this.actualAddress = actualAddress;
	}

	public Address getLegalAddress() {
		return legalAddress;
	}

	public void setLegalAddress(Address legalAddress) {
		this.legalAddress = legalAddress;
	}

	public String getPassportSeries() {
		return passportSeries;
	}

	public void setPassportSeries(String passportSeries) {
		this.passportSeries = passportSeries;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Date getPassportDateOfIssue() {
		return passportDateOfIssue;
	}

	public void setPassportDateOfIssue(Date passportDateOfIssue) {
		this.passportDateOfIssue = passportDateOfIssue;
	}

	public String getPassportIssuerDepartment() {
		return passportIssuerDepartment;
	}

	public void setPassportIssuerDepartment(String passportIssuerDepartment) {
		this.passportIssuerDepartment = passportIssuerDepartment;
	}

	public boolean isIndividualEntrepreneur() {
		return individualEntrepreneur;
	}

	public void setIndividualEntrepreneur(boolean individualEntrepreneur) {
		this.individualEntrepreneur = individualEntrepreneur;
	}

	public String getIndividualEntrepreneurRegNum() {
		return individualEntrepreneurRegNum;
	}

	public void setIndividualEntrepreneurRegNum(String individualEntrepreneurRegNum) {
		this.individualEntrepreneurRegNum = individualEntrepreneurRegNum;
	}

	public String getOgrn() {
		return ogrn;
	}

	public void setOgrn(String ogrn) {
		this.ogrn = ogrn;
	}

	public OKVED getActivityType() {
		return activityType;
	}

	public void setActivityType(OKVED activityType) {
		this.activityType = activityType;
	}

	public String getGrn() {
		return grn;
	}

	public void setGrn(String grn) {
		this.grn = grn;
	}

	public Date getEgripInitialDate() {
		return egripInitialDate;
	}

	public void setEgripInitialDate(Date egripInitialDate) {
		this.egripInitialDate = egripInitialDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	public NaturalPerson clone() {
		NaturalPerson np = new NaturalPerson();
		np.setId(getId());
		np.setName(getName());
		np.setFirstName(firstName);
		np.setSurname(surname);
		np.setPatronymic(patronymic);

		np.setActualAddress(actualAddress != null ? actualAddress.clone() : null);
		np.setDateOfBirth(dateOfBirth);
		np.setEgripInitialDate(egripInitialDate);
		np.setEmail(email);
		np.setGrn(grn);
		np.setIndividualEntrepreneur(individualEntrepreneur);
		np.setIndividualEntrepreneurRegNum(individualEntrepreneurRegNum);
		np.setInn(inn);
		np.setIssueDate(issueDate);
		np.setLegalAddress(legalAddress != null ? legalAddress.clone() : null);
		np.setOgrn(ogrn);
		np.setActivityType(activityType != null ? activityType.clone() : null);
		np.setPassportDateOfIssue(passportDateOfIssue);
		np.setPassportIssuerDepartment(passportIssuerDepartment);
		np.setPassportNumber(passportNumber);
		np.setPassportSeries(passportSeries);
		np.setPhone(phone);
		np.setRegistrationDate(registrationDate);
		np.setSnils(snils);
		return np;
	}

}
