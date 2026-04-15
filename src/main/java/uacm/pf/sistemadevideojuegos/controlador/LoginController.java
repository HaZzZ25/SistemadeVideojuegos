package uacm.pf.sistemadevideojuegos.controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uacm.pf.sistemadevideojuegos.modelo.Usuario;
import uacm.pf.sistemadevideojuegos.repositorio.UsuarioRepository;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        Usuario usuarioEncontrado = usuarioRepository.findByUsuarioAndPassword(usuario, password);

        if (usuarioEncontrado != null) {
            // ¡Magia! Guardamos al usuario en la memoria de la sesión
            session.setAttribute("usuarioLogueado", usuarioEncontrado);
            return "redirect:/principal";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
            return "login";
        }
    }

    @GetMapping("/principal")
    public String mostrarPrincipal(HttpSession session, Model model) {
        // Recuperamos quién inició sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        // Si alguien intenta entrar a /principal sin iniciar sesión, lo pateamos al login
        if (usuario == null) {
            return "redirect:/login";
        }

        // Mandamos los datos del usuario a la vista HTML
        model.addAttribute("usuario", usuario);
        return "principal";
    }

    // Ruta para cerrar sesión correctamente
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Destruye la sesión
        return "redirect:/login";
    }
}