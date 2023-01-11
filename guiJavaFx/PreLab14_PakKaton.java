package prelab14_pakkaton;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreLab14_PakKaton extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    TableView<MataKuliah> matkul;
    TabelMataKuliah tabMatkul = new TabelMataKuliah();

    Label lblKode = new Label("Kode MK");
    TextField txtKode = new TextField();
    Label lblNama = new Label("Nama MK");
    TextField txtNama = new TextField();
    Label lblSks = new Label("Jumlah SKS");
    TextField txtSks = new TextField();
    Label lblFakultas = new Label("Fakultas");
    TextField txtFakutas = new TextField();
    Label lblProdi = new Label("Prodi");
    TextField txtProdi = new TextField();
    VBox vb = new VBox(10);
    HBox hb1 = new HBox(10);
    HBox hb2 = new HBox(20);
    Button btnNewSave = new Button("_New");
    Button btnSave = new Button("_Save");
    Button btnEditUndo = new Button("_Edit");
    Button btnDelete = new Button("_Delete");
    Button btnClose = new Button("_Close");
    Region reg = new Region();

    GridPane grid = new GridPane();

    private void setGrid() {

        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setHgap(10);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));
        txtKode.setPromptText("Kode");
        txtKode.setMaxWidth(55);
        txtNama.setPromptText("Nama");
        txtNama.setMinWidth(350);
        txtSks.setPromptText("SKS");
        txtSks.setMaxWidth(55);
        txtFakutas.setPromptText("Fakultas");
        txtFakutas.setMaxWidth(150);
        txtProdi.setPromptText("Prodi");
        txtProdi.setMaxWidth(150);
        grid.add(lblKode, 0, 0);
        grid.add(txtKode, 1, 0);
        grid.add(lblNama, 0, 1);
        grid.add(txtNama, 1, 1);
        grid.add(lblSks, 0, 2);
        grid.add(txtSks, 1, 2);
        grid.add(lblFakultas, 0, 3);
        hb1.getChildren().addAll(txtFakutas, lblProdi, txtProdi);
        grid.add(hb1, 1, 3);
        HBox.setHgrow(reg, Priority.ALWAYS);
        hb2.getChildren().addAll(btnNewSave, btnEditUndo, btnDelete);
        grid.add(btnClose, 3, 5, 1, 1);
        grid.add(hb2, 0, 5, 1, 1);

        vb.setPadding(new Insets(10));
    }

    void buka() {
        txtKode.setEditable(true);
        txtNama.setEditable(true);
        txtSks.setEditable(true);
        txtFakutas.setEditable(true);
        txtProdi.setEditable(true);
        txtKode.requestFocus();
        btnNewSave.setText("_Save");
        btnEditUndo.setText("_Undo");
        btnDelete.setDisable(true);
        btnClose.setDisable(true);
    }

    void tutup() {
        txtKode.setEditable(false);
        txtNama.setEditable(false);
        txtSks.setEditable(false);
        txtFakutas.setEditable(true);
        txtProdi.setEditable(false);
        btnNewSave.setText("_New");
        btnEditUndo.setText("_Edit");
        btnDelete.setDisable(false);
        btnClose.setDisable(false);
    }

    void tampil() {
        MataKuliah mt = tabMatkul.getMhs();
        txtKode.setText(mt.getKodeMk());
        txtNama.setText(mt.getNamaMk());
        txtSks.setText(String.valueOf(mt.getSks()));
        txtFakutas.setText(mt.getFakultas());
        txtProdi.setText(mt.getProdi());
    }
    Boolean baru;

    void ButtonNewSave() {
        if (btnNewSave.getText().equals("_New")) {
            baru = true;
            txtKode.clear();
            txtNama.clear();
            txtSks.clear();
            txtFakutas.clear();
            txtProdi.clear();
            buka();
        } else {
            String kodeMk, sks, namaMk, fakultas, prodi;
            kodeMk = txtKode.getText();
            namaMk = txtNama.getText();
            sks = txtSks.getText();
            fakultas = txtFakutas.getText();
            prodi = txtProdi.getText();

            MataKuliah mt = new MataKuliah(kodeMk, namaMk, sks, fakultas, prodi);
            if (baru) {
                tabMatkul.tambahMatkul(mt);
                tabMatkul.saveFile();
            } else {
                tabMatkul.ubahMatkul(mt);

            }
            tutup();

        }
    }

    void editUndo() {
        if (btnEditUndo.getText().equals("_Edit")) {
            baru = false;
            buka();
        } else {
            tampil();
            tutup();
        }
    }

    void delete() {
        tabMatkul.hapusMatkul();
        tampil();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setGrid();
        vb.getChildren().addAll(grid, tabMatkul);
        tampil();
        Scene sc = new Scene(vb, 800, 600);
        primaryStage.setScene(sc);
        primaryStage.setTitle("QUICK SORT");
        primaryStage.show();
        btnNewSave.setOnAction(e -> ButtonNewSave());
        tabMatkul.setOnMouseClicked(e -> tampil());
        btnEditUndo.setOnAction(e -> editUndo());
        btnDelete.setOnAction(e -> delete());
        btnClose.setOnAction(e -> System.exit(0));

    }

}
