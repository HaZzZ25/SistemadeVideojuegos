package uacm.pf.sistemadevideojuegos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uacm.pf.sistemadevideojuegos.modelo.Consola;
import uacm.pf.sistemadevideojuegos.repositorio.ConsolaRepository;

@Controller
@RequestMapping("/consolas")
public class ConsolaController {

    @Autowired
    private ConsolaRepository consolaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaConsolas", consolaRepository.findAll());
        return "consolas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("consola", new Consola());
        return "formulario_consola";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("consola") Consola consola) {
        consolaRepository.save(consola);
        return "redirect:/consolas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("consola", consolaRepository.findById(id).get());
        return "formulario_consola";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        consolaRepository.deleteById(id);
        return "redirect:/consolas";
    }
}