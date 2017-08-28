package SGQ.construct.dao;


import java.util.ArrayList;
import java.util.List;

import SGQ.construct.model.RIS;
import SGQ.construct.model.Obra;


public interface ObraDao {

	public void salvar(Obra Obra);

	public ArrayList<Obra> getObra();
	public List<RIS> getListRIS(String[] id);

	public Obra getObraunq(int id);

	public void deletar(Obra Obra);

	public Obra getObraunq(RIS ris);
}
