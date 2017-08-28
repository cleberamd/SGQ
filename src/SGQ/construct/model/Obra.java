package SGQ.construct.model;


import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;



@Entity
@SequenceGenerator(sequenceName="seq_obra", allocationSize=1,
                   name="seq",initialValue=1)
public class Obra {
	
	@Id
	@GeneratedValue(generator="seq")
	private Integer id;
	private String numero;
	private String nome;
	private String descricao;
	private Integer status;
	private Integer codcolig;
	public Obra() {
		super();
		codcolig = 1;
	}
	
	@OneToMany(mappedBy = "obra")
	 private List<ObraRis> ris;
	
	
	
	
	
	public int getCodcolig() {
		return codcolig;
	}
	public void setCodcolig() {
		this.codcolig = 1;
	}
	public List<ObraRis> getRis() {
		return ris;
	}
	public void setRis(List<ObraRis> ris) {
		this.ris = ris;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
