package ru.sovzond.mgis2.registers.oks;

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
@Table(name = "rosreg_oks_address")
public class OKSAddress implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
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

	/**
	 * Почтовый индекс
	 * Ограничение на тип строка PostalCodeRFType.
	 */
	@Column(name = "postal_code")
	private String postalCode;

	/**
	 * Код региона
	 * По справочнику dRegionsRF «Субъекты РФ»
	 */
	@ManyToOne
	@JoinColumn(name = "region")
	private KLADRLocality region;

	/**
	 * Район
	 * Тип tName. См. описание типа ниже в данной таблице.
	 */
	@ManyToOne
	@JoinColumn(name = "district")
	private KLADRLocality district;

	/**
	 * Муниципальное образование
	 * Тип tName. См. описание типа ниже в данной таблице.
	 */
	@ManyToOne
	@JoinColumn(name = "city")
	private KLADRLocality city;

	/**
	 * Городской район
	 * Тип tUrbanDistrict. См. описание типа ниже в данной таблице.
	 */
	@ManyToOne
	@JoinColumn(name = "urban_district")
	private KLADRLocality urbanDistrict;

	/**
	 * Сельсовет
	 * Тип tSovietVillage. См. описание типа ниже в данной таблице.
	 */
	@ManyToOne
	@JoinColumn(name = "soviet_village")
	private KLADRLocality sovietVillage;

	/**
	 * Населённый пункт
	 * Тип tName. См. описание типа ниже в данной таблице.
	 */
	@ManyToOne
	@JoinColumn(name = "locality")
	private KLADRLocality locality;

	/**
	 * Улица
	 * Тип tName. См. описание типа ниже в данной таблице.
	 */
	@ManyToOne
	@JoinColumn(name = "street")
	private KLADRStreet street;

	/**
	 * Дом
	 * Тип tLevel1. См. описание типа ниже в данной таблице.
	 */
	@Column(name = "level1")
	private String level1;

	/**
	 * Корпус
	 * Тип tLevel2. См. описание типа ниже в данной таблице.
	 */
	@Column(name = "level2")
	private String level2;

	/**
	 * Строение
	 * Тип tLevel3. См. описание типа ниже в данной таблице.
	 */
	@Column(name = "level3")
	private String level3;

	/**
	 * Квартира
	 * Тип tApartment. См. описание типа ниже в данной таблице.
	 */
	@Column(name = "apartment")
	private String apartment;

	/**
	 * Иное
	 */
	@Column(name = "other")
	private String other;

	/**
	 * Неформализованное описание
	 */
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

	public OKSAddress clone() {
		OKSAddress address = new OKSAddress();
		address.setId(id);
		address.setRegion(region);
		// TODO:
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

	public KLADRLocality getDistrict() {
		return district;
	}

	public void setDistrict(KLADRLocality district) {
		this.district = district;
	}

	public KLADRLocality getCity() {
		return city;
	}

	public void setCity(KLADRLocality city) {
		this.city = city;
	}

	public KLADRLocality getUrbanDistrict() {
		return urbanDistrict;
	}

	public void setUrbanDistrict(KLADRLocality urbanDistrict) {
		this.urbanDistrict = urbanDistrict;
	}

	public KLADRLocality getSovietVillage() {
		return sovietVillage;
	}

	public void setSovietVillage(KLADRLocality sovietVillage) {
		this.sovietVillage = sovietVillage;
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

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	public String getLevel3() {
		return level3;
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	}
}
