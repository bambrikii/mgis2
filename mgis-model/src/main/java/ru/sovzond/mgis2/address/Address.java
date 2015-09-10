package ru.sovzond.mgis2.address;

import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRStreet;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.persistence.*;

/**
 * Описание раздела «Адрес (описание местоположения)»
 * Код элемента Содержание элемента Тип Формат Наименование Дополнительная информация Address (Адрес (описание местоположения)) Тип tAddressOut
 * <p/>
 * OKATO Н Т(11) ОКАТО Ограничение на тип строка OKATOType.
 * KLADR Н Т(20) КЛАДР
 * OKTMO Н Т(11) ОКТМО Ограничение на тип строка OKTMOType.
 * PostalCode Н Т(6) Почтовый индекс Ограничение на тип строка PostalCodeRFType.
 * Region О К(2) Код региона По справочнику dRegionsRF «Субъекты РФ»
 * District Н SА Район Тип tName. См. описание типа ниже в данной таблице.
 * City Н SА Муниципальное образование Тип tName. См. описание типа ниже в данной таблице.
 * UrbanDistrict Н SА Городской район Тип tUrbanDistrict. См. описание типа ниже в данной таблице.
 * SovietVillage Н SА Сельсовет Тип tSovietVillage. См. описание типа ниже в данной таблице.
 * Locality Н SА Населённый пункт Тип tName. См. описание типа ниже в данной таблице.
 * Street Н SА Улица Тип tName. См. описание типа ниже в данной таблице.
 * Level1 Н SА Дом Тип tLevel1. См. описание типа ниже в данной таблице.
 * Level2 Н SА Корпус Тип tLevel2. См. описание типа ниже в данной таблице.
 * Level3 Н SА Строение Тип tLevel3. См. описание типа ниже в данной таблице.
 * Apartment Н SА Квартира Тип tApartment. См. описание типа ниже в данной таблице.
 * Other Н Т(2500) Иное
 * Note Н Т(4000) Неформализованное описание
 * <p/>
 * Описание комплексных типов
 * Тип tName (Наименование) tName Name ОА Т(255) Наименование tName Type ОА Т(255) Тип
 * Тип tUrbanDistrict (Городской район) tUrbanDistrict Name ОА Т(255) Наименование tUrbanDistrict Type ОА Т Тип По справочнику dUrbanDistrict «Городской район»
 * Тип tSovietVillage (Сельсовет) tSovietVillage Name ОА Т(255) Наименование tSovietVillage Type ОА Т Тип По справочнику dSovietVillage «Сельсовет»
 * Тип tLevel1 (Дом) tLevel1 Type ОА К Тип По справочнику dLocationLevel1Type «Тип адресного элемента первого уровня» tLevel1 Value ОА Т(255) Значение
 * Тип tLevel2 (Корпус) tLevel2 Type ОА К Тип По справочнику dLocationLevel2Type «Тип адресного элемента второго уровня» tLevel2 Value ОА Т(255) Значение
 * Тип tLevel3 (Строение) tLevel3 Type ОА Т Тип По справочнику dLocationLevel3Type «Тип адресного элемента третьего уровня» tLevel3 Value ОА Т(255) Значение
 * Тип tApartment (Квартира) tApartment Type ОА Т Тип По справочнику dApartmentType «Тип адресного элемента четвертого уровня» tApartment Value ОА Т(255) Значение
 */
@Entity
@Table(name = "mgis2_address")
public class Address implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_address_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * ОКАТО
	 * Ограничение на тип строка OKATOType.
	 */
	@ManyToOne
	@JoinColumn(name = "okato")
	private OKATO okato;

	/**
	 *
	 */
	@ManyToOne
	@JoinColumn(name = "kladr")
	private KLADRLocality kladr;

	/**
	 * ОКТМО
	 * Ограничение на тип строка OKTMOType.
	 */
	@ManyToOne
	@JoinColumn(name = "oktmo")
	private OKTMO oktmo;

	@Column(name = "postal_code")
	private String postalCode;


	@ManyToOne
	@JoinColumn(name = "subject")
	private KLADRLocality subject;

	@ManyToOne
	@JoinColumn(name = "region")
	private KLADRLocality region;

	@ManyToOne
	@JoinColumn(name = "locality")
	private KLADRLocality locality;

	@ManyToOne
	@JoinColumn(name = "street")
	private KLADRStreet street;

	@Column(name = "home")
	private String home;

	@Column(name = "housing")
	private String housing;

	@Column(name = "building")
	private String building;

	@Column(name = "apartment")
	private String apartment;

	@Column(name = "other")
	private String other;

	@Column(name = "note")
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KLADRLocality getRegion() {
		return region;
	}

	public void setRegion(KLADRLocality region) {
		this.region = region;
	}

	public Address clone() {
		Address address = new Address();
		address.setId(id);
		address.setOkato(okato != null ? okato.clone() : null);
		address.setKladr(kladr != null ? kladr.clone() : null);
		address.setOktmo(oktmo != null ? oktmo.clone() : null);
		address.setPostalCode(postalCode);
		address.setSubject(subject != null ? subject.clone() : null);
		address.setRegion(region != null ? region.clone() : null);
		address.setLocality(locality != null ? locality.clone() : null);
		address.setStreet(street != null ? street.clone() : null);
		address.setHome(home);
		address.setHousing(housing);
		address.setBuilding(building);
		address.setApartment(apartment);
		address.setNote(note);
		address.setOther(other);
		return address;
	}

	public OKATO getOkato() {
		return okato;
	}

	public void setOkato(OKATO okato) {
		this.okato = okato;
	}

	public KLADRLocality getKladr() {
		return kladr;
	}

	public void setKladr(KLADRLocality kladr) {
		this.kladr = kladr;
	}

	public OKTMO getOktmo() {
		return oktmo;
	}

	public void setOktmo(OKTMO oktmo) {
		this.oktmo = oktmo;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public KLADRLocality getLocality() {
		return locality;
	}

	public void setLocality(KLADRLocality locality) {
		this.locality = locality;
	}

	public KLADRStreet getStreet() {
		return street;
	}

	public void setStreet(KLADRStreet street) {
		this.street = street;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getHousing() {
		return housing;
	}

	public void setHousing(String housing) {
		this.housing = housing;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public KLADRLocality getSubject() {
		return subject;
	}

	public void setSubject(KLADRLocality subject) {
		this.subject = subject;
	}
}
