package prelab14_pakkaton;

import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelMataKuliah extends TableView<MataKuliah> {

    private int recordPointer;
    FileTeks file = new FileTeks("src/preLab14_pakkaton/DataMatkul.csv");

    public TabelMataKuliah() {
        setKolom();
        getRow();
        this.setOnMousePressed(e -> this.recordPointer
                = this.getSelectionModel().getSelectedIndex());
    }

    public MataKuliah getMhs() {
        return this.getItems().get(recordPointer);
    }

    public void tambahMatkul(MataKuliah matkul) {
        this.getItems().add(matkul);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText("Menyumpan Data Berhasil");
        alert.setContentText(
                "Terimakasih");
        alert.showAndWait();
    }

    public void ubahMatkul(MataKuliah mhs) {
        this.getItems().set(recordPointer, mhs);
    }

    public void hapusMatkul() {
        ObservableList<MataKuliah> pilih, semua;
        semua = this.getItems();
        pilih = this.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfimrasi");
        alert.setHeaderText("Mohon Konfirmasi");
        alert.setContentText("Apakah Anda yakin untuk Menghapus ?");

        Optional<ButtonType> jwb = alert.showAndWait();
        if (jwb.get() == ButtonType.OK) {
            pilih.forEach(semua::remove);
            file.tulis(this.getMatkul());
        } else if (jwb.get() == ButtonType.OK) {
            System.out.println("Pengguna setuju");
        } else {
            System.out.println("Pengguna menolak");
        }
    }

    void setKolom() {

        TableColumn<MataKuliah, String> kolKode = new TableColumn("Kode Matkul");
        kolKode.setCellValueFactory(new PropertyValueFactory<>("kodeMk"));
        kolKode.setMinWidth(150);
        kolKode.setStyle("-fx-alignment:center");

        TableColumn<MataKuliah, String> kolNama = new TableColumn("Nama");
        kolNama.setCellValueFactory(new PropertyValueFactory<>("namaMk"));
        kolNama.setMinWidth(165);

        TableColumn<MataKuliah, String> kolSks = new TableColumn("Total SKS");
        kolSks.setCellValueFactory(new PropertyValueFactory<>("sks"));
        kolSks.setMinWidth(155);
        kolSks.setStyle("-fx-alignment:center");

        TableColumn<MataKuliah, String> kolFakultas = new TableColumn("Fakultas");
        kolFakultas.setCellValueFactory(new PropertyValueFactory<>("fakultas"));
        kolFakultas.setMinWidth(150);
        kolFakultas.setStyle("-fx-alignment:center");

        TableColumn<MataKuliah, String> kolProdi = new TableColumn("Prodi");
        kolProdi.setCellValueFactory(new PropertyValueFactory<>("prodi"));
        kolProdi.setMinWidth(160);
        kolProdi.setStyle("-fx-alignment:center");

        this.getColumns().addAll(kolKode, kolNama, kolSks, kolFakultas, kolProdi);
    }

    public void getRow() {
        FileTeks file = new FileTeks("src/preLab14_pakkaton/DataMatkul.csv");
        String[] arrCvv = file.bacaBaris();
        String[] row;
        String kodeMk, namaMk, sks, fakultas, prodi;
        for (int i = 0; i < arrCvv.length; i++) {
            row = arrCvv[i].split(",");
            kodeMk = row[0];
            namaMk = row[1];
            sks = row[2];
            fakultas = row[3];
            prodi = row[4];
            this.getItems().add(new MataKuliah(kodeMk, namaMk, sks, fakultas, prodi));
        }
    }

    public String getMatkul() {
        String semua = "";

        for (int i = 0; i < this.getItems().size(); i++) {
            String kodeMk = String.valueOf(this.getItems().get(i).getKodeMk());
            String namaMk = String.valueOf(this.getItems().get(i).getNamaMk());
            String sks = String.valueOf(this.getItems().get(i).getSks());
            String fakultas = String.valueOf(this.getItems().get(i).getFakultas());
            String prodi = String.valueOf(this.getItems().get(i).getProdi());
            semua += kodeMk + "," + namaMk + "," + sks + "," + fakultas + "," + prodi + "\n";

        }
        return semua;
    }

    void saveFile() {

        file.tulis(getMatkul());
    }

}
