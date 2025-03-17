package com.academico.models.service;

import java.util.List;

public interface GenericService<T,O,ID> {
	
	public List<O> listar(); 
	
	public O save(T entity);
	
	public O update(ID id, T entity);
	
	public void deleteById(ID id);
	
	public O findById(ID id);
	
	

}
