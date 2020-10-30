package apap.tugas.sipes.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pesawat_teknisi")
public class PesawatTeknisiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPesawatTeknisi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pesawat_id", referencedColumnName = "id_pesawat", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawatModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "teknisi_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TeknisiModel teknisiModel;

    public TeknisiModel getTeknisiModel() {
        return teknisiModel;
    }

    public Long getIdPesawatTeknisi() {
        return idPesawatTeknisi;
    }

    public void setIdPesawatTeknisi(Long idPesawatTeknisi) {
        this.idPesawatTeknisi = idPesawatTeknisi;
    }

    public PesawatModel getPesawatModel() {
        return pesawatModel;
    }

    public void setPesawatModel(PesawatModel pesawatModel) {
        this.pesawatModel = pesawatModel;
    }

    public void setTeknisiModel(TeknisiModel teknisiModel) {
        this.teknisiModel = teknisiModel;
    }

}
