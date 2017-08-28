package SGQ.construct.model;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(sequenceName="seq_obraris", allocationSize=1,
name="seq",initialValue=1)
public class ObraRis {
	
	@Id
	@GeneratedValue(generator="seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="obra_id")
	private Obra obra;
	
	@ManyToOne
	@JoinColumn(name="ris_id")
	private RIS ris;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	public RIS getRis() {
		return ris;
	}
	public void setRis(RIS ris) {
		this.ris = ris;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getId() {
		return id;
	}

	
	
}
