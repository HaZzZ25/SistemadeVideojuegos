package uacm.pf.sistemadevideojuegos.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {

        // 1. Datos para la Complejidad Ciclomatica REALES
        model.addAttribute("complejidadVentas", 8);
        model.addAttribute("complejidadUsuarios", 1);
        model.addAttribute("complejidadClientes", 1);
        model.addAttribute("complejidadVideojuegos", 1);
        model.addAttribute("complejidadConsolas", 1);

        // Promedio general real (8+1+1+1+1 = 12 / 5 controladores = 2.4)
        model.addAttribute("complejidadPromedio", 2.4);

        // 2. Datos para el WIP (El KPI principal)
        model.addAttribute("wipActual", 2);

        return "dashboard";
    }
}