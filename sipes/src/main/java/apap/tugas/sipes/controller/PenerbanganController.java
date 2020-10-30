package apap.tugas.sipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.service.PenerbanganService;
import apap.tugas.sipes.service.PesawatService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PenerbanganController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    public String listPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "viewall-penerbangan";
    }

    @GetMapping("/penerbangan/{idPenerbangan}")
    public String viewDetailPenerbangan(
            @PathVariable(value = "idPenerbangan") Long idPenerbangan,
            Model model
    ) {
        if (idPenerbangan != null && hasPesawat(idPenerbangan)) {
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
            model.addAttribute("penerbangan", penerbangan);
            model.addAttribute("title", penerbangan.getPesawatModel().getNomorSeri());

            return "view-penerbangan";
        }
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        model.addAttribute("title", "Belum diassign pesawat");
        return "view-penerbangan";
    }

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganForm(Model model){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    public String addPenerbanganSubmit(@ModelAttribute PenerbanganModel penerbangan,  Model model){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "add-penerbangan";
    }

    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    private String changePenerbanganForm(@PathVariable Long idPenerbangan, Model model) {
        PenerbanganModel existPenerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", existPenerbangan);
        return "form-update-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    private String changePenerbanganSubmit(@ModelAttribute PenerbanganModel penerbangan, Model model){
        PenerbanganModel newPenerbanganData = penerbanganService.changePenerbangan(penerbangan);
        model.addAttribute("penerbangan", newPenerbanganData);
        return "update-penerbangan";
    } 

    private boolean hasPesawat(Long idPenerbangan) {
        return penerbanganService.getPenerbanganById(idPenerbangan).getPesawatModel() != null;
    }
    @PostMapping({"/penerbangan/hapus/{idPenerbangan}"})
    public String deleteResep(
            @PathVariable(value = "idPenerbangan", required = false) Long idPenerbangan,
            Model model
    ) {
        if (idPenerbangan != null) {
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
            model.addAttribute("penerbangan", penerbangan);
            penerbanganService.deletePenerbangan(idPenerbangan);
            return "delete-penerbangan";
        }
        return "error";
    }
}
