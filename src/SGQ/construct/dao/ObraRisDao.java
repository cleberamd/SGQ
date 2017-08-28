package SGQ.construct.dao;


import java.util.List;

import SGQ.construct.model.Obra;
import SGQ.construct.model.ObraRis;
import SGQ.construct.model.RIS;


public interface ObraRisDao {
	
	public void salvar(ObraRis obraris);
	
	public List<ObraRis> getObraRis(RIS ris);
	
	public void deletar(List<ObraRis> ObraRis);
	
	public List<RIS> getListaRis(Obra obra);
}
