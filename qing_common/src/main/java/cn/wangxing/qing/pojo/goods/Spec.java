package cn.wangxing.qing.pojo.goods;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * spec实体类
 * @author Administrator
 *
 */
@Table(name="tb_spec")
public class Spec implements Serializable{

	@Id
	private Integer id;//ID

	private String 	name;//名称

	private String 	options;//规格选项

	private Integer seq;//排序

	private Integer template_id;//模板ID

	@Transient
	private Template template;

	@Override
	public String toString() {
		return "Spec{" +
				"id=" + id +
				", name='" + name + '\'' +
				", options='" + options + '\'' +
				", seq=" + seq +
				", template_id=" + template_id +
				", template=" + template +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
}
