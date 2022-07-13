package com.tienda.service;

import com.tienda.dao.ClienteDao;
import com.tienda.dao.CreditoDao;
import com.tienda.domain.Cliente;
import com.tienda.domain.Credito;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//indica que esta capa es de logica de negocio - Modelo

@Service
public class ClienteServiceImpl implements ClienteService {

    //Se crea en tiempo de ejecucion si aun no se ha creado...
    @Autowired
    private ClienteDao clienteDao;//Capa de mas bajo nivel

    @Autowired
    private CreditoDao creditoDao;

    @Override
    @Transactional(readOnly = true)
    //Indica que es una "transaccion" en tiempo real\
    //Es una conexiona a la BD en modo lectura
    public List<Cliente> getClientes() {

        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {

        Credito credito = cliente.getCredito();
        credito = creditoDao.save(credito);
        cliente.setCredito(credito);

        clienteDao.save(cliente);

    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {

        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {

        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

}
