package SGQ.construct.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;




@Entity
@SequenceGenerator(sequenceName="seq_pitens", allocationSize=1,
                   name="seq",initialValue=1)
public class PItem {
	
	
	@Id
	@GeneratedValue(generator="seq")
	private int id;
	private String local;
	private Date data;
	private int conformidade;
	private String descricao;
	private String foto;
	private int obraId;
	
	
	public int getObraId() {
		return obraId;
	}
	public void setObraId(int obraId) {
		this.obraId = obraId;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getConformidade() {
		return conformidade;
	}
	public void setConformidade(int conformidade) {
		this.conformidade = conformidade;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	
	public PItem() {
		super();
		
	}

	@ManyToOne
	@JoinColumn(name = "itens_id")
	private Itens itens;
	
	
	public Itens getItem() {
		return itens;
	}
	public void setItem(Itens item) {
		this.itens = item;
	}
	
}
