package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;


public class Controller {
    @FXML
    MenuItem sortir, copiar, tallar, enganxar, desfer, tamany1, tamany2, tamany3, arial, courier, verdana, ajuda, obrir;
    @FXML
    TextArea areaTexto;
    @FXML
    private Button tb_copy, tb_cut, tb_undo, tb_paste;

    //Initialize
    public void initialize(){
        //MOSTRA LES ICONES
        tb_copy.setGraphic(new ImageView("ic_copy.png"));
        tb_cut.setGraphic(new ImageView("ic_cut.png"));
        tb_undo.setGraphic(new ImageView("ic_undo.png"));
        tb_paste.setGraphic(new ImageView("ic_paste.png"));
    }

    //FITXER
    public void obrirDocument(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Obrir document");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        Stage mainStage = new Stage();

        File selectedFile = fileChooser.showOpenDialog(mainStage);
        fileChooser.setTitle(selectedFile.getName());

        try{
            BufferedReader br = new BufferedReader(new FileReader(selectedFile));
            String linea = null;

            while ((linea = br.readLine()) != null) {
                areaTexto.setText(areaTexto.getText() + linea + "\n");
            }
        }
        catch(FileNotFoundException a){}
        catch (IOException b){}
    }

    public void guardarDocument(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Guardar document");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        Stage mainStage = new Stage();

        File selectedFile = fileChooser.showSaveDialog(mainStage);
        fileChooser.setTitle(selectedFile.getName());

        try{
            selectedFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
            bw.write(areaTexto.getText());
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sortirAplicacio(ActionEvent actionEvent) {
        Platform.exit();
    }


    //EDITAR
    public void copiarText(ActionEvent actionEvent) {
        areaTexto.copy();
    }

    public void tallarText(ActionEvent actionEvent) {
        areaTexto.cut();
    }

    public void enganxarText(ActionEvent actionEvent) {
        areaTexto.paste();
    }

    public void desferText(ActionEvent actionEvent) {
        areaTexto.undo();
    }

     //DESHABILITA ELS BOTONS DE EDITAR
    public void deshabilitarButons(Event event) {
            if(areaTexto.getSelectedText().equals("")){
                copiar.setDisable(true);
                enganxar.setDisable(true);
            }
            else{
                copiar.setDisable(false);
                enganxar.setDisable(false);
            }
    }


    //OPCIONS
        //TAMANY
    public void cambiarTamany1(ActionEvent actionEvent) {
        areaTexto.setFont(Font.font(12));
    }

    public void cambiarTamany2(ActionEvent actionEvent) {
        areaTexto.setFont(Font.font(18));
    }

    public void cambiarTamany3(ActionEvent actionEvent) {
        areaTexto.setFont(Font.font(42));
    }

    //FONT
    public void cambiarArial(ActionEvent actionEvent) {
        areaTexto.setFont(new Font("Arial", 18));
    }

    public void cambiarCourier(ActionEvent actionEvent) {
        areaTexto.setFont(new Font("Courier", 18));
    }

    public void cambiarVerdana(ActionEvent actionEvent) {
        areaTexto.setFont(new Font("Verdana", 18));
    }

    //AJUDA
    public void mostrarAjuda(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("About");
        a.setHeaderText("Editor de Text");
        a.setResizable(true);
        //String version = System.getProperty("Editor de Text 1.0");
        String content = String.format("Aquesta es la primera versio del editor de text");
        a.setContentText(content);
        a.showAndWait();
    }

    //BUTTONS TOOLBAR
    public void tbCopy(ActionEvent actionEvent){
        copiarText(actionEvent);
    }

    public void tbCut(ActionEvent actionEvent){
        tallarText(actionEvent);
    }

    public void tbPaste(ActionEvent actionEvent){
        enganxarText(actionEvent);
    }

    public void tbUndo(ActionEvent actionEvent){
        desferText(actionEvent);
    }

}
