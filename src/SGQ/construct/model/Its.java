package SGQ.construct.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;



@Entity

@SequenceGenerator(sequenceName="seq_its", allocationSize=1,
                   name="seq",initialValue=1)
public class Its {
	@Id
	@GeneratedValue(generator="seq")

	private Integer id;
	private String descric;
	private String nome;
	private String link;
	private String num;
	
	
	@OneToMany(mappedBy="its")
	 private List<RIS>ris;
	
	
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getId() {
		return id;
	}
	
	public String getDescric() {
		return descric;
	}
	public void setDescric(String descric) {
		this.descric = descric;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


}
