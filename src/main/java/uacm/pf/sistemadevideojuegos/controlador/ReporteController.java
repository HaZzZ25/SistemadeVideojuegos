package uacm.pf.sistemadevideojuegos.controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uacm.pf.sistemadevideojuegos.modelo.Venta;
import uacm.pf.sistemadevideojuegos.repositorio.VentaRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping
    public String verReporteDiario(
            // ¡Truco infalible! Lo recibimos como String para que Spring no lo rompa
            @RequestParam(value = "fecha", required = false) String fechaStr,
            Model model) {

        LocalDate fecha;
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            fecha = LocalDate.now();
        } else {
            fecha = LocalDate.parse(fechaStr); // Convertimos el texto a fecha manualmente
        }

        List<Venta> ventasDelDia = ventaRepository.buscarVentasPorDia(fecha);
        double totalDelDia = ventasDelDia.stream().mapToDouble(Venta::getTotal).sum();

        model.addAttribute("fechaSeleccionada", fecha);
        model.addAttribute("ventas", ventasDelDia);
        model.addAttribute("totalDelDia", totalDelDia);

        return "reportes";
    }

    // --- NUEVO: MÉTODO PARA GENERAR EL PDF ---
    @GetMapping("/pdf")
    public void descargarPDF(@RequestParam("fecha") String fechaStr, HttpServletResponse response) {
        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            List<Venta> ventas = ventaRepository.buscarVentasPorDia(fecha);
            double total = ventas.stream().mapToDouble(Venta::getTotal).sum();

            // Configuramos el navegador para que descargue un archivo PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Ventas_" + fecha + ".pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Título del PDF
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.DARK_GRAY);
            Paragraph titulo = new Paragraph("Reporte Diario de Ventas - " + fecha, fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Tabla del PDF
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.addCell("Ticket ID");
            tabla.addCell("Hora");
            tabla.addCell("Cliente");
            tabla.addCell("Producto");
            tabla.addCell("Total");

            for (Venta v : ventas) {
                tabla.addCell(v.getIdVenta().toString());
                tabla.addCell(v.getFecha().toLocalTime().toString().substring(0, 5));
                tabla.addCell(v.getCliente());
                tabla.addCell(v.getProducto());
                tabla.addCell("$" + v.getTotal());
            }
            document.add(tabla);

            // Total del PDF
            Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.RED);
            Paragraph pTotal = new Paragraph("Ingresos Totales: $" + total, fontTotal);
            pTotal.setAlignment(Element.ALIGN_RIGHT);
            pTotal.setPaddingTop(20);
            document.add(pTotal);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}