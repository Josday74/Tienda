package com.tienda.service;

import com.tienda.dao.ArticuloDao;
import com.tienda.domain.Articulo;
import com.tienda.service.ArticuloService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    //Se crea en tiempo de ejecución si aún no se ha creado...
    @Autowired
    private ArticuloDao articuloDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Articulo> getArticulos(boolean activos) {
        
        var lista =(List<Articulo>) articuloDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        
        return lista;
    }

    @Override
    @Transactional
    public void save(Articulo articulo) {
        articuloDao.save(articulo);
    }

    @Override
    @Transactional
    public void delete(Articulo articulo) {
        articuloDao.delete(articulo);
    }

    @Override
    @Transactional(readOnly = true)
    public Articulo getArticulo(Articulo articulo) {
        return articuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }
    
}
