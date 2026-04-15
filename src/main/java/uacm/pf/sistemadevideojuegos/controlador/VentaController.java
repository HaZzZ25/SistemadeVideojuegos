package uacm.pf.sistemadevideojuegos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uacm.pf.sistemadevideojuegos.modelo.*;
import uacm.pf.sistemadevideojuegos.repositorio.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired private VentaRepository ventaRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private VideojuegoRepository videojuegoRepository;
    @Autowired private ConsolaRepository consolaRepository;

    // Muestra la pantalla de ventas
    @GetMapping
    public String mostrarPuntoDeVenta(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("videojuegos", videojuegoRepository.findAll());
        model.addAttribute("consolas", consolaRepository.findAll());
        model.addAttribute("historialVentas", ventaRepository.findAll());
        return "ventas"; // Buscara ventas.html
    }

    // Procesa la venta cuando le dan clic al boton
    @PostMapping("/procesar")
    public String procesarVenta(@RequestParam Integer idCliente,
                                @RequestParam String productoSeleccionado,
                                @RequestParam Integer cantidad) {

        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if(cliente == null) return "redirect:/ventas?error=cliente";

        
        String tipo = productoSeleccionado.split("-")[0];
        Integer idProducto = Integer.parseInt(productoSeleccionado.split("-")[1]);

        String nombreProducto = "";
        double totalVenta = 0.0;

        // Si es un Videojuego
        if (tipo.equals("V")) {
            Videojuego v = videojuegoRepository.findById(idProducto).orElse(null);
            if (v == null || v.getStock() < cantidad) return "redirect:/ventas?error=stock";

            v.setStock(v.getStock() - cantidad); // Restamos el stock
            videojuegoRepository.save(v);

            nombreProducto = v.getNombre();
            totalVenta = v.getPrecio() * cantidad;
        }
        // Si es una Consola
        else if (tipo.equals("C")) {
            Consola c = consolaRepository.findById(idProducto).orElse(null);
            if (c == null || c.getStock() < cantidad) return "redirect:/ventas?error=stock";

            c.setStock(c.getStock() - cantidad); // Restamos el stock
            consolaRepository.save(c);

            nombreProducto = c.getNombre();
            totalVenta = c.getPrecio() * cantidad;
        }

        else {
            return "redirect:/ventas?error=tipoInvalido";
        }

        // Guardamos el ticket en el historial
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFecha(LocalDateTime.now());
        nuevaVenta.setCliente(cliente.getNombre());
        nuevaVenta.setProducto(nombreProducto);
        nuevaVenta.setCantidad(cantidad);
        nuevaVenta.setTotal(totalVenta);
        ventaRepository.save(nuevaVenta);

        return "redirect:/ventas?exito";
    }
}