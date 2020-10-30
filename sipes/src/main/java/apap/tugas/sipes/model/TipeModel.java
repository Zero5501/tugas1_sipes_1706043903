package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import java.io.Serializable;

@Entity
@Table(name = "tipe")
public class TipeModel implements Serializable {
     @Id
     @Column(name = "id")
    private Long idTipe;

     @NotNull
     @Size(max = 255)
     @Column(name = "nama",nullable = false)
    private String namaTipe;

     @NotNull
     @Size(max = 255)
     @Column(name = "deskripsi",nullable = false)
     private String deskripsi;

     @OneToMany(mappedBy = "tipeModel",fetch = FetchType.LAZY, cascade =
     CascadeType.ALL)
     private List<PesawatModel> listPesawat;

    public Long getIdTipe() {
        return idTipe;
    }

    public List<PesawatModel> getListPesawat() {
        return listPesawat;
    }

    public void setListPesawat(List<PesawatModel> listPesawat) {
        this.listPesawat = listPesawat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    public void setIdTipe(Long idTipe) {
        this.idTipe = idTipe;
    }
}
