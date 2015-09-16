package ru.sovzond.mgis2.settings;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 16.09.15.
 */

@Entity
@Table(name = "mgis2_layer")
public class Layer implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_layer_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String url;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Layer clone() {
		Layer layer = new Layer();
		layer.setCode(code);
		layer.setId(id);
		layer.setUrl(url);
		return layer;
	}

}
