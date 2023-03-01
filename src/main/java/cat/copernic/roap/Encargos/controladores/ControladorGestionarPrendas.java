/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.roap.Encargos.controladores;

import cat.copernic.roap.DAO.PrendaDAO;
import cat.copernic.roap.Pedidos.controladores.*;
import cat.copernic.roap.models.Pedidos;
import cat.copernic.roap.models.Prenda;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Adrix
 */
@Controller
public class ControladorGestionarPrendas {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosDAO al controlador
    private PrendaDAO PrendaDAO;

    @GetMapping("/gestionarPrendas")
    public String inici(Model model) { //Aquest és el mètode que generarà la resposta (recurs a retornar)
        Optional<Prenda> prenda = PrendaDAO.findById(1);
        prenda.ifPresent(System.out::println);
        model.addAttribute("prenda", PrendaDAO.findAll());
        //log.info("Executant el controlador Spring MVC"); //Afegeix al log el missatge passat com a paràmetre.
        return "Encargos/GestionarPrendas"; //Retorn de la pàgina Login.html.
    }
}
