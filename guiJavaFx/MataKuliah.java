package prelab14_pakkaton;

public class MataKuliah {
    private String kodeMk;
    private String namaMk;
    private String sks;
    private String fakultas;
    private String prodi;
    public MataKuliah(String kodeMk, String namaMk, String sks, String fakultas, String prodi) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.sks = sks;
        this.fakultas = fakultas;
        this.prodi = prodi;
    }
    public String getKodeMk() {
        return kodeMk;
    }
    public void setKodeMK(String kodeMk) {
        this.kodeMk = kodeMk;
    }
    public String getNamaMk() {
        return namaMk;
    }
    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }
    public String getSks() {
        return sks;
    }
    public void setSks(String sks) {
        this.sks = sks;
    }
    public String getFakultas() {
        return fakultas;
    }
    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }
    public String getProdi() {
        return prodi;
    }
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
}
