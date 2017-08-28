package SGQ.construct.model;






import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(sequenceName="seq_itens", allocationSize=1,
                   name="seq",initialValue=1)
public class Itens {
	
	
	@Id
	@GeneratedValue(generator="seq")
	private Integer id;
	private Integer num;
	private String inspecao;
	private String verificacao;
	private String tolerancia;
	
	@OneToMany(mappedBy="itens")
	private List<PItem> pitens;
	
	
	public Itens() {
		super();
		this.pitens = null;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	public List<PItem> getPitens() {
		return pitens;
	}
	
	public void setPitens(List<PItem> pitens) {
		this.pitens = pitens;
	}
	
	

	@ManyToOne
	@JoinColumn(name = "ris_id")
	private RIS ris;
	
	
	public RIS getRis() {
		return ris;
	}
	public void setRis(RIS ris) {
		this.ris = ris;
	}
	public Integer getId() {
		return id;
	}
	
	public String getInspecao() {
		return inspecao;
	}
	public void setInspecao(String inspecao) {
		this.inspecao = inspecao;
	}
	public String getVerificacao() {
		return verificacao;
	}
	public void setVerificacao(String verificacao) {
		this.verificacao = verificacao;
	}
	public String getTolerancia() {
		return tolerancia;
	}
	public void setTolerancia(String tolerancia) {
		this.tolerancia = tolerancia;
	}

}
