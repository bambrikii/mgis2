package ru.sovzond.mgis2.kladr;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 08.09.15.
 */
@Entity
@Table(name = "kladr_doma")
public class Doma {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "kladr_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Номер дома, владения
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Корпус дома
	 */
	@Column(name = "korp")
	private String korp;

	/**
	 * Сокращенное наименование типа объекта
	 */
	@Column(name = "socr")
	private String socr;

	/**
	 * Код
	 * <p/>
	 * СС РРР ГГГ ППП УУУУ ДДДД, где
	 * СС – код субъекта Российской Федерации (региона), коды регионов представлены в Приложении 2 к Описанию классификатора адресов Российской Федерации (КЛАДР);
	 * РРР – код района;
	 * ГГГ – код города;
	 * ППП – код населенного пункта;
	 * УУУУ – код улицы (если адрес не содержит наименования улицы, т.е. дома привязаны непосредственно к городу или населенному пункту, то код улицы будет содержать нули – 0000);
	 * ДДДД – порядковый номер позиции классификатора с обозначениями домов.
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKorp() {
		return korp;
	}

	public void setKorp(String korp) {
		this.korp = korp;
	}

	public String getSocr() {
		return socr;
	}

	public void setSocr(String socr) {
		this.socr = socr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getGninmb() {
		return gninmb;
	}

	public void setGninmb(String gninmb) {
		this.gninmb = gninmb;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getOcatd() {
		return ocatd;
	}

	public void setOcatd(String ocatd) {
		this.ocatd = ocatd;
	}
}
