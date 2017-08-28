package SGQ.construct.model;


import java.util.Date;
import java.util.List;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;




@Entity
@SequenceGenerator(sequenceName="seq_ris", allocationSize=1,
                   name="seq",initialValue=1)
public class RIS {
	
	@Id
	@GeneratedValue(generator="seq")
	private Integer id;
	private String nome;
	private String numero;
	private Date data;
	private Integer rev;
	private boolean Ativa;
	
	
	
	public boolean isAtiva() {
		return Ativa;
	}

	public void setAtiva(boolean ativa) {
		Ativa = ativa;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	
	@OneToMany(mappedBy="ris")
	
	private List<Itens> Itens;
	
	
	
	@ManyToOne
    @JoinColumn(name="its_id")
    private Its its;
	
	
	@OneToMany(mappedBy = "ris")
	 private List<ObraRis> obras;
	 
	

	public List<ObraRis> getObra() {
		return obras;
	}

	public void setObra(List<ObraRis> obras) {
		this.obras = obras;
	}
	

	public RIS( ) {
		super();
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Its getIts() {
		return its;
	}

	public void setIts(Its its) {
		this.its = its;
	}

	public void setItens(List<Itens> itens) {
		Itens = itens;
	}

	public List<Itens> getItens() {
		return Itens;
	}

	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Usuario getUsuarioCriacao() {
		return usuario;
	}
	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		this.usuario = usuarioCriacao;
	}
}
