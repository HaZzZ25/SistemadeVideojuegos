package uacm.pf.sistemadevideojuegos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uacm.pf.sistemadevideojuegos.modelo.Cliente;
import uacm.pf.sistemadevideojuegos.repositorio.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("listaClientes", clienteRepository.findAll());
        return "clientes"; // Buscara clientes.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistrar(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formulario_cliente"; // Buscara formulario_cliente.html
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteRepository.findById(id).get();
        model.addAttribute("cliente", cliente);
        return "formulario_cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}