/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.roap.DAO;

import cat.copernic.roap.models.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Adrix
 */
public interface EnvioDAO  extends JpaRepository<Envio,Integer>{
    Envio findByIdpedido(int idpedido);
}
