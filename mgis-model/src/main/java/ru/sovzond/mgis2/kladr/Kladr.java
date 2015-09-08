package ru.sovzond.mgis2.kladr;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 08.09.15.
 * <p/>
 * http://www.ksoft.ru/opis_kladr.htm
 */
@Entity
@Table(name = "kladr_kladr")
public class Kladr {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "kladr_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Наименование объекта
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Сокращенное наименование типа объекта
	 */
	@Column(name = "socr")
	private String socr;

	/**
	 * Код
	 * <p/>
	 * СС РРР ГГГ ППП АА, где
	 * СС – код субъекта Российской Федерации (региона), коды регионов представлены в Приложении 2 к Описанию классификатора адресов Российской Федерации (КЛАДР);
	 * РРР – код района;
	 * ГГГ – код города;
	 * ППП – код населенного пункта,
	 * АА – признак актуальности наименования адресного объекта.
	 */
	@Column(name = "code")
	private String code;

	/**
	 * Почтовый индекс
	 */
	@Column(name = "index")
	private String index;

	/**
	 * Код ИФНС
	 */
	@Column(name = "gninmb")
	private String gninmb;

	/**
	 * Код территориального участка ИФНС
	 */
	@Column(name = "uno")
	private String uno;

	/**
	 * Код ОКАТО
	 */
	@Column(name = "ocatd")
	private String ocatd;

	/**
	 * Статус объекта" (признак центра)
	 */
	@Column(name = "status")
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocr() {
		return socr;
	}

	public void setSocr(String socr) {
		this.socr = socr;
	}

	public String getGninmb() {
		return gninmb;
	}

	public void setGninmb(String gninmb) {
		this.gninmb = gninmb;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
