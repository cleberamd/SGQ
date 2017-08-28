package SGQ.construct.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import SGQ.construct.model.RIS;
import SGQ.construct.model.Obra;

;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario {

	@Id
	@GeneratedValue(generator = "seq")
	private Integer id;
	private String nome;
	private String usuario;
	private String senha;
	private String funcao;
	private Integer perfil;
	private Integer status;
	private String sobrenome;
	
	
	

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public List<RIS> getRIS() {
		return RIS;
	}

	public void setRIS(List<RIS> rIS) {
		RIS = rIS;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Usuario() {
		super();
		RIS = new ArrayList<RIS>();
		Obra = new ArrayList<Obra>();
	}

	@OneToMany(mappedBy = "usuario")
	private List<RIS> RIS;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	 @JoinTable(
	  name = "ObraUser",joinColumns = @JoinColumn(name = "Usuario_id"), inverseJoinColumns = @JoinColumn(name = "Obra_id")
	 )
	private List<Obra> Obra;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Obra> getObra() {
		return Obra;
	}

	public void setObra(List<Obra> obra) {
		Obra = obra;
	}

}
