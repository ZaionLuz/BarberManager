package com.barbermanager.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane conteudoCentral;

    @FXML
    public void initialize() {
        abrirClientes();
    }

    @FXML
    private void abrirClientes() {
        carregarTela("/view/cliente-view.fxml");
    }

    @FXML
    private void avisoEmBreve() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Módulo em Desenvolvimento");
        alert.setHeaderText("Entidade aguardando camada DAO");
        alert.setContentText("Assim que a dupla finalizar o Model e o DAO dessa entidade, conectaremos a tela aqui.");
        alert.showAndWait();
    }

    private void carregarTela(String caminhoFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFxml));
            Node tela = loader.load();
            conteudoCentral.getChildren().setAll(tela);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao carregar tela");
            alert.setHeaderText("Falha ao abrir " + caminhoFxml);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}