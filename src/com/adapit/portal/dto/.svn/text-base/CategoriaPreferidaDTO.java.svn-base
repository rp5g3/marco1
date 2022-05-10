package com.adapit.portal.dto;

import java.io.Serializable;
import java.util.List;

public class CategoriaPreferidaDTO implements Serializable{

	private static final long serialVersionUID = 2436234243662346L;
	private int categoriaId;
	private long participanteId;
	private List<CategoriaPreferidaDTO> categorias;
	private boolean selected = false;
	private String nomeCategoria;
	private int ordem=0;
	
	public CategoriaPreferidaDTO(int categoriaId, long participanteId,
			boolean selected, String nomeCategoria) {
		super();
		this.categoriaId = categoriaId;
		this.participanteId = participanteId;
		this.selected = selected;
		this.nomeCategoria = nomeCategoria;
	}

	public CategoriaPreferidaDTO(){
		
	}	

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public long getParticipanteId() {
		return participanteId;
	}

	public void setParticipanteId(long participanteId) {
		this.participanteId = participanteId;
	}

	public List<CategoriaPreferidaDTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaPreferidaDTO> categorias) {
		this.categorias = categorias;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	
	public static int getChildrenSize(List<CategoriaPreferidaDTO> list, int size){
		if (list != null && list.size() > 0) {				
			for(CategoriaPreferidaDTO cat: list){
				size++;	
				size+=getChildrenSize(cat.getCategorias(), 0);	
			}
		}
		return size;
	}
	
	public static java.lang.Object[][] getChildrenCategoryValues(List<CategoriaPreferidaDTO> list, String pai){
		int size = getChildrenSize(list, 0);
		System.out.println(size);
		java.lang.Object values[][] = new java.lang.Object[size][4];
		if (list != null && list.size() > 0) {	
			int catn=0;
			int paiindex=1;
			for(CategoriaPreferidaDTO cat: list){
				String str = "  ";
				try {
					for (int o=0; o < cat.getOrdem(); o++) str+="    ";
					values[catn][0] = str+cat.getNomeCategoria();
					values[catn][1] = cat.isSelected();
					if (pai == null || pai.equals("")){
						values[catn][2] = ""+paiindex;					
					}
					else values[catn][2] = pai+"."+paiindex;
					values[catn][3] = cat.getCategoriaId();
					if (cat.getCategorias() != null){
						java.lang.Object va[][] = getChildrenCategoryValues(cat.getCategorias(),(String) values[catn][2]/*cat.getOrdem()*/);	
						for(int i=0; i< va.length; i++){
							catn++;
							values[catn][0] = va[i][0];
							values[catn][1] = va[i][1];
							values[catn][2] = (String)va[i][2];
							values[catn][3] = va[i][3];
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}	
				catn++;
				paiindex++;
			}
		}
		return values;
	}
}
