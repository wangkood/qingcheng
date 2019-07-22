package cn.wangxing.qing.pojo.goods;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * template实体类
 * @author Administrator
 *
 */
@Table(name="tb_template")
public class Template implements Serializable{

	@Id
	private Integer id;//ID


	

	private String name;//模板名称

	private Integer specNum;//规格数量

	private Integer paraNum;//参数数量

	@Transient
	private List<Spec> specList;
	@Transient
	private List<Para> paraList;

	@Override
	public String toString() {
		return "Template{" +
				"id=" + id +
				", name='" + name + '\'' +
				", specNum=" + specNum +
				", paraNum=" + paraNum +
				", specList=" + specList +
				", paraList=" + paraList +
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

	public Integer getSpecNum() {
		return specNum;
	}
	public void setSpecNum(Integer specNum) {
		this.specNum = specNum;
	}

	public Integer getParaNum() {
		return paraNum;
	}
	public void setParaNum(Integer paraNum) {
		this.paraNum = paraNum;
	}

	public List<Spec> getSpecList() {
		return specList;
	}

	public void setSpecList(List<Spec> specList) {
		this.specList = specList;
	}

	public List<Para> getParaList() {
		return paraList;
	}

	public void setParaList(List<Para> paraList) {
		this.paraList = paraList;
	}
}
