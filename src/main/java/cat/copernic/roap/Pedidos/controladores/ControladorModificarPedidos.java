/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.roap.Pedidos.controladores;

import cat.copernic.roap.DAO.EnvioDAO;
import cat.copernic.roap.DAO.PedidosDAO;
import cat.copernic.roap.DAO.PrendaDAO;
import cat.copernic.roap.DAO.ProductAddedDAO;
import cat.copernic.roap.DAO.ProductoDAO;
import cat.copernic.roap.Encargos.serveis.PrendaService;
import cat.copernic.roap.Pedidos.servicios.PedidosService;
import cat.copernic.roap.Pedidos.servicios.ProductAddService;
import cat.copernic.roap.Pedidos.servicios.ProductosService;
import cat.copernic.roap.models.Pedidos;
import cat.copernic.roap.models.Prenda;
import cat.copernic.roap.models.ProductAdded;
import cat.copernic.roap.models.Producto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adrix
 */
@Controller
public class ControladorModificarPedidos {
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private ProductoDAO productodao;
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private PrendaDAO prendadao;

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosDAO al controlador
    private PrendaService prendaService;
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private ProductosService ProductosService;
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private ProductAddService ProductAddService;
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private PedidosService PedidosService;
    
    

    @PostMapping("/modificadopedido")
    public String guardarPedido(@ModelAttribute("pedidos") Pedidos pedidos,
            @RequestParam("selector") int productoId,
            @RequestParam("cantidad") int cantidad, BindingResult result) {
        // Actualizar las unidades disponibles del producto
        Prenda prenda = prendadao.findByid(productoId);
        int unidadesDisponibles = prenda.getUnidades() - cantidad;
        prenda.setUnidades(unidadesDisponibles);
        prendaService.anadirPrenda(prenda);
        //Crear un nuevo pedido en la tabla pedidos
        float preciototal = prenda.getPrecio() * cantidad;
        int preciototali = (int) preciototal;
        pedidos.setPrecioTotal(preciototali);
        PedidosService.addPedidos(pedidos);
        // Crear un nuevo registro en la tabla productosañadidos
        ProductAdded productoadd = new ProductAdded();
        productoadd.setAddproductid(pedidos.getID());
        productoadd.setPrendaid(productoId);
        productoadd.setPedidoid(pedidos.getID());
        productoadd.setCantidad(cantidad);
//        ProductAddService.addProductAdd(productoadd);  
        // Redirigir al usuario a una página de confirmación
        return "redirect:/pedidos";
    }
}
