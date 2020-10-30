package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

import java.io.Serializable;

@Entity
@Table(name = "teknisi")
public class TeknisiModel implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeknisi;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "nomor_telepon", nullable = false)
    private Long noTelepon;

    @OneToMany(mappedBy = "teknisiModel", fetch = FetchType.LAZY, cascade =
    CascadeType.ALL)
    private List<PesawatTeknisiModel> listPesawatTeknisi;

    public Long getIdTeknisi() {
        return idTeknisi;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return listPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> listPesawatTeknisi) {
        this.listPesawatTeknisi = listPesawatTeknisi;
    }

    public Long getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(Long noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setIdTeknisi(Long idTeknisi) {
        this.idTeknisi = idTeknisi;
    }


}
