package uacm.pf.sistemadevideojuegos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uacm.pf.sistemadevideojuegos.modelo.Usuario;
import uacm.pf.sistemadevideojuegos.repositorio.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        // Le cambiamos el apodo a "user" para que no choque
        model.addAttribute("user", new Usuario());
        return "formulario_usuario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("user") Usuario user) {
        usuarioRepository.save(user);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("user", usuarioRepository.findById(id).get());
        return "formulario_usuario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}