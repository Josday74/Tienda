package com.tienda.service;

import com.tienda.domain.Articulo;
import java.util.List;

/**
 *
 * @author Josday
 */
public interface ArticuloService {
    
    public List<Articulo> getArticulos(boolean cativos);
    
    public void save(Articulo articulo);
    
    public void delete(Articulo articulo);
    
    public Articulo getArticulo(Articulo articulo);
}
