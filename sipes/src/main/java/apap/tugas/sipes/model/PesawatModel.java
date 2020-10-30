package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable {
    @Id
    @Column(name = "id_pesawat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPesawat;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_pesawat", nullable = false)
    private String jenis;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDate tanggal;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_dibuat", nullable = false)
    private String tempatDibuat;

    @NotNull
    @Size(max = 255)
    @Column(name = "maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomor_seri", nullable = false)
    private String nomorSeri;

    @OneToMany(mappedBy = "pesawatModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @OneToMany(mappedBy = "pesawatModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> listPesawatTeknisi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipe_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipeModel;

    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public void nomorGenerator(){
        String varJenis;
        String varTipe;
        String varTahun1;
        StringBuilder varTahun2 = new StringBuilder();
        String varTahun3;
        String random;
        Integer temp;
        Integer temp2;
        if(getJenis().equals("Komersial")){
            varJenis = Integer.toString(1);
        }
        else{
            varJenis = Integer.toString(2);
        }
        if(tipeModel.getIdTipe() == 1){
            varTipe = "BO";
        }
        else if(tipeModel.getIdTipe() == 2){
            varTipe = "AT";
        }
        else if(tipeModel.getIdTipe() == 3){
            varTipe = "AB";
        }
        else{
            varTipe = "BB";
        }
        temp = getTanggal().getYear();
        varTahun1 = Integer.toString(temp);
        varTahun2.append(varTahun1);
        varTahun2 = varTahun2.reverse();
        varTahun1 = varTahun2.toString();
        temp2 = getTanggal().getYear() + 8;
        varTahun3 = Integer.toString(temp2);
        random = getSaltString();
        setNomorSeri(varJenis + varTipe + varTahun1 + varTahun3 + random);
    }

    public Long getIdPesawat() {
        return idPesawat;
    }

    public String getNomorSeri() {
        return nomorSeri;
    }

    public void setNomorSeri(String nomorSeri) {
        this.nomorSeri = nomorSeri;
    }

    public String getTempatDibuat() {
        return tempatDibuat;
    }

    public void setTempatDibuat(String tempatDibuat) {
        this.tempatDibuat = tempatDibuat;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return listPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> listPesawatTeknisi) {
        this.listPesawatTeknisi = listPesawatTeknisi;
    }

    public TipeModel getTipeModel() {
        return tipeModel;
    }

    public void setTipeModel(TipeModel tipeModel) {
        this.tipeModel = tipeModel;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setIdPesawat(Long idPesawat) {
        this.idPesawat = idPesawat;
    }


}
