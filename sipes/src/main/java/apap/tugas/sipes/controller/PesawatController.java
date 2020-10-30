package apap.tugas.sipes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.service.PenerbanganService;
import apap.tugas.sipes.service.PesawatService;
import apap.tugas.sipes.service.TeknisiService;
import apap.tugas.sipes.service.TipeService;
import apap.tugas.sipes.model.PesawatTeknisiModel;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

    //routing URL

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping(value="/pesawat/tambah")
    public String addPesawat(@ModelAttribute TipeModel tipe,@ModelAttribute TeknisiModel teknisi,Model model) {
        PesawatModel pesawatModel = new PesawatModel();
        List<TipeModel> tipes = tipeService.getTipeList();
        List<TeknisiModel> teknisis = teknisiService.getTeknisiList();
        if (pesawatModel.getListPesawatTeknisi() == null) {
            pesawatModel.setListPesawatTeknisi(new ArrayList<PesawatTeknisiModel>());
        }
        pesawatModel.getListPesawatTeknisi().add(new PesawatTeknisiModel());
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("tipes", tipes);
        model.addAttribute("teknisis", teknisis);
        return "form-add-pesawat";
    }

    @PostMapping("/pesawat/tambah")
    public String addPesawatSubmit(@ModelAttribute PesawatModel pesawat, Model model){
        pesawatService.addPesawat(pesawat);
        model.addAttribute("idPesawat", pesawat.getIdPesawat());
        return "add-pesawat";
    }

    private boolean isPesawatExists(Long idPesawat) {
        return pesawatService.getPesawatByIdPesawat(idPesawat).isPresent();
    }

    @GetMapping({"/pesawat/ubah/{idPesawat}","/pesawat/ubah"})
    private String changePesawatForm(@PathVariable(required = false) Long idPesawat, @ModelAttribute TipeModel tipe,
    Model model ){
        if(idPesawat != null && isPesawatExists(idPesawat)){
            PesawatModel pesawat = pesawatService.getPesawatByIdPesawat(idPesawat).get();
            List<TipeModel> tipes = tipeService.getTipeList();
            model.addAttribute("pesawat", pesawat);
            model.addAttribute("tipes", tipes);
            return "form-update-pesawat";
        }
        model.addAttribute("msg", "Nomor Pesawat Tidak Ditemukan");
        return "error";
    }

    @PostMapping("/pesawat/ubah")
    private String changePesawatSubmit(@ModelAttribute PesawatModel pesawat, Model model){
        PesawatModel updatedPesawat = pesawatService.updatePesawat(pesawat);
        model.addAttribute("pesawat", updatedPesawat);
        return "update-pesawat";
    }

    @PostMapping(value = "/pesawat/tambah", params = { "addRow" })
    public String addRow(@ModelAttribute PesawatModel pesawat, BindingResult bindingResult, Model model) {
        if (pesawat.getListPesawatTeknisi() == null) {
            pesawat.setListPesawatTeknisi(new ArrayList<PesawatTeknisiModel>());
        }
        List<TipeModel> tipes = tipeService.getTipeList();
        List<TeknisiModel> teknisis = teknisiService.getTeknisiList();
        pesawat.getListPesawatTeknisi().add(new PesawatTeknisiModel());
        model.addAttribute("tipes", tipes);
        model.addAttribute("teknisis", teknisis);
        model.addAttribute("pesawat", pesawat);
        return "form-add-pesawat";
    }
  
    @PostMapping(value = "/pesawat/tambah", params = { "removeRow" })
    public String removeRow(@ModelAttribute PesawatModel pesawat, final BindingResult bindingResult,
            final HttpServletRequest req, Model model) {
        final int rowIndex = Integer.valueOf(req.getParameter("removeRow"));
        pesawat.getListPesawatTeknisi().remove(rowIndex);
        model.addAttribute("pesawat", pesawat);
        return "form-add-pesawat";
    }
  
    @PostMapping(value = "/pesawat/tambah", params = { "save" })
    private String addPesawatSubmitForm(@ModelAttribute PesawatModel pesawat, Model model) {
        for(PesawatTeknisiModel pesawatTeknisi : pesawat.getListPesawatTeknisi()){
            pesawatTeknisi.setPesawatModel(pesawat);
        }
        pesawatService.addPesawat(pesawat);
        model.addAttribute("idPesawat", pesawat.getNomorSeri());
        return "add-pesawat";
    }

    @GetMapping("/pesawat")
    public String listPesawat(Model model) {
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
        model.addAttribute("listPesawat", listPesawat);

        return "viewall-pesawat";
    }

    @GetMapping("/pesawat/{idPesawat}")
    public String viewDetailPesawat(
            @PathVariable(value = "idPesawat") Long idPesawat,
            Model model
    ) {
        if (idPesawat != null && hasPesawat(idPesawat)) {
            PesawatModel pesawat = pesawatService.getPesawatByIdPesawat(idPesawat).get();
            List<PenerbanganModel> listPenerbangan = pesawat.getListPenerbangan();
            List<PesawatTeknisiModel> listTeknisi = pesawat.getListPesawatTeknisi();
            List<PenerbanganModel> listPenerbanganTersedia = penerbanganService.getPenerbanganList();

            model.addAttribute("pesawat", pesawat);
            model.addAttribute("title", "Daftar Teknisi Pesawat:");
            model.addAttribute("title2", "Daftar Penerbangan");
            model.addAttribute("penerbangan", listPenerbanganTersedia);
            
            if (listTeknisi.size() > 0) model.addAttribute("listTeknisi", listTeknisi);
            else model.addAttribute("title", "Resep belum memiliki daftar teknisi");

            if(listPenerbangan.size() >0) model.addAttribute("listPenerbangan", listPenerbangan);
            else model.addAttribute("title2", "Pesawat belum memiliki daftar penerbangan");

            return "view-tambah-penerbangan";
        }
        model.addAttribute("msg", "Nomor Pesawat Tidak Ditemukan atau Nomor Pesawat Tidak Ada!");

        return "asdf";
    }

    private boolean hasPesawat(Long idPesawat) {
        return pesawatService.getPesawatByIdPesawat(idPesawat).get().getListPesawatTeknisi().size() != 0;
    }
    @PostMapping("/pesawat/{idPesawat}/tambah-penerbangan")
    public String viewDetailPesawatAddPenerbangan(
            @PathVariable(value = "idPesawat") Long idPesawat,@RequestParam Long penerbanganModel, Model model) {
                if(idPesawat != null && penerbanganModel != null){
                    PesawatModel pesawat = pesawatService.getPesawatByIdPesawat(idPesawat).get();
                    PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(penerbanganModel);
                    penerbangan.setPesawatModel(pesawat);
                    penerbanganService.changePenerbangan(penerbangan);
                    model.addAttribute("penerbangan", penerbangan);
                    return "hasil-penerbangan";
                }
                return "error";
            }
}
