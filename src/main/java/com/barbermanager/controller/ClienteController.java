package com.barbermanager.controller;

import com.barbermanager.model.Cliente;
import com.barbermanager.service.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import java.util.List;

public class ClienteController {

    @FXML private TextField txtNome;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtBusca;

    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, String> colTelefone;

    private final ClienteService clienteService = new ClienteService();
    private final ObservableList<Cliente> listaMaster = FXCollections.observableArrayList();
    private Cliente clienteEmEdicao = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        FilteredList<Cliente> listaFiltrada = new FilteredList<>(listaMaster, p -> true);
        txtBusca.textProperty().addListener((obs, antigo, novo) -> {
            String termo = novo == null ? "" : novo.toLowerCase().trim();
            listaFiltrada.setPredicate(cliente -> {
                if (termo.isEmpty()) return true;
                return cliente.getNome().toLowerCase().contains(termo)
                        || cliente.getTelefone().toLowerCase().contains(termo);
            });
        });

        tabelaClientes.setItems(listaFiltrada);
        carregarClientes();
    }

    @FXML
    private void salvarCliente() {
        try {
            if (clienteEmEdicao != null) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Edição Pendente no DAO",
                        "O formulário já carregou o cliente ID " + clienteEmEdicao.getId()
                                + ". Falta apenas a dupla adicionar o método atualizar(Cliente) no ClienteDAO.");
                return;
            }

            clienteService.cadastrarCliente(txtNome.getText(), txtTelefone.getText());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cliente cadastrado com sucesso!");
            limparCampos();
            carregarClientes();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao Salvar", e.getMessage());
        }
    }

    @FXML
    private void carregarClientes() {
        try {
            List<Cliente> clientes = clienteService.listarClientes();
            listaMaster.setAll(clientes);
            int total = clientes.size();
            lblTotalClientes.setText(total + (total == 1 ? " cliente" : " clientes"));
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de Consulta", e.getMessage());
        }
    }

    @FXML
    private void prepararEdicao() {
        Cliente selecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um cliente na tabela para editar.");
            return;
        }
        clienteEmEdicao = selecionado;
        txtNome.setText(selecionado.getNome());
        txtTelefone.setText(selecionado.getTelefone());
    }

    @FXML
    private void excluirSelecionado() {
        Cliente selecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione um cliente na tabela para excluir.");
            return;
        }
        mostrarAlerta(Alert.AlertType.INFORMATION, "Exclusão Pendente no DAO",
                "Cliente selecionado: " + selecionado.getNome() + " (ID " + selecionado.getId() + ").\n"
                        + "Peça para sua dupla adicionar o método excluir(int id) no ClienteDAO para ativar esta ação.");
    }

    @FXML
    private void limparCampos() {
        clienteEmEdicao = null;
        txtNome.clear();
        txtTelefone.clear();
        tabelaClientes.getSelectionModel().clearSelection();
    }

    @FXML
    private Label lblTotalClientes;

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}