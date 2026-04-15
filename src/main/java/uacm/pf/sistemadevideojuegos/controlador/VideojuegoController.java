package uacm.pf.sistemadevideojuegos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uacm.pf.sistemadevideojuegos.modelo.Videojuego;
import uacm.pf.sistemadevideojuegos.repositorio.VideojuegoRepository;

@Controller
@RequestMapping("/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @GetMapping
    public String listarVideojuegos(Model model) {
        model.addAttribute("listaVideojuegos", videojuegoRepository.findAll());
        return "videojuegos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistrar(Model model) {
        model.addAttribute("videojuego", new Videojuego());
        return "formulario_videojuego";
    }

    @PostMapping("/guardar")
    public String guardarVideojuego(@ModelAttribute("videojuego") Videojuego videojuego) {
        videojuegoRepository.save(videojuego);
        return "redirect:/videojuegos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Integer id, Model model) {
        Videojuego juego = videojuegoRepository.findById(id).get();
        model.addAttribute("videojuego", juego);
        return "formulario_videojuego";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVideojuego(@PathVariable Integer id) {
        videojuegoRepository.deleteById(id);
        return "redirect:/videojuegos";
    }
}