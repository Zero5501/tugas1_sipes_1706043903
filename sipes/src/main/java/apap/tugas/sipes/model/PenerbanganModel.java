package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.TrueFalseType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPenerbangan;

    @NotNull
    @Size(max = 255)
    private String kodePenerbangan;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode_asal", nullable = false)
    private String kodeAsal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode_tujuan", nullable = false)
    private String kodeTujuan;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "waktu_penerbangan", nullable = false)
    private LocalDate waktuTerbang;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "pesawat_id", referencedColumnName = "id_pesawat", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawatModel;

    public Long getIdPenerbangan() {
        return idPenerbangan;
    }

    public PesawatModel getPesawatModel() {
        return pesawatModel;
    }

    public void setPesawatModel(PesawatModel pesawatModel) {
        this.pesawatModel = pesawatModel;
    }

    public String getKodePenerbangan() {
        return kodePenerbangan;
    }

    public void setKodePenerbangan(String kodePenerbangan) {
        this.kodePenerbangan = kodePenerbangan;
    }

    public LocalDate getWaktuTerbang() {
        return waktuTerbang;
    }

    public void setWaktuTerbang(LocalDate waktuTerbang) {
        this.waktuTerbang = waktuTerbang;
    }

    public String getKodeTujuan() {
        return kodeTujuan;
    }

    public void setKodeTujuan(String kodeTujuan) {
        this.kodeTujuan = kodeTujuan;
    }

    public String getKodeAsal() {
        return kodeAsal;
    }

    public void setKodeAsal(String kodeAsal) {
        this.kodeAsal = kodeAsal;
    }

    public void setIdPenerbangan(Long idPenerbangan) {
        this.idPenerbangan = idPenerbangan;
    }
}
