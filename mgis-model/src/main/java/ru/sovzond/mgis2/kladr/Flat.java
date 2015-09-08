package ru.sovzond.mgis2.kladr;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 08.09.15.
 */
@Entity
@Table(name = "kladr_flat")
public class Flat {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "kladr_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Код
	 * <p/>
	 * СС РРР ГГГ ППП УУУУ ДДДД КККК, где
	 * СС – код субъекта Российской Федерации (региона), коды регионов представлены в Приложении 2 к Описанию классификатора адресов Российской Федерации (КЛАДР);
	 * РРР – код района;
	 * ГГГ – код города;
	 * ППП – код населенного пункта;
	 * УУУУ – код улицы;
	 * ДДДД – порядковый номер позиции классификатора с обозначениями домов;
	 * КККК – порядковый номер позиции классификатора с обозначениями квартир.
	 */
	@Column(name = "code")
	private String code;

	/**
	 * Номер подъезда дома
	 */
	@Column(name = "np")
	private String np;

	/**
	 * Код ИФНС
	 */
	@Column(name = "gninmb")
	private String gninmb;

	/**
	 * ? Номер квартиры
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Почтовый индекс
	 */
	@Column(name = "index")
	private String index;

	/**
	 * Код территориального участка ИФНС
	 */
	@Column(name = "uno")
	private String uno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNp() {
		return np;
	}

	public void setNp(String np) {
		this.np = np;
	}

	public String getGninmb() {
		return gninmb;
	}

	public void setGninmb(String gninmb) {
		this.gninmb = gninmb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}
}
