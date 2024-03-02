package org.moldidev.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class CalculatorController {

    @FXML
    private Label operationLabel;
    @FXML
    private TextField firstPolynomialTextField;
    @FXML
    private TextField secondPolynomialTextField;
    @FXML
    private Button computeOperationButton;
    @FXML
    private ChoiceBox<String> operatorChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField resultTextField;
    @FXML
    private Button clearResultButton;
    @FXML
    private Label firstPolynomialErrorLabel;
    @FXML
    private Label secondPolynomialErrorLabel;
    private Timeline firstPolynomialErrorLabelClearTimeline = new Timeline();
    private Timeline secondPolynomialErrorLabelClearTimeline = new Timeline();

    public Label getOperationLabel() {
        return this.operationLabel;
    }

    public TextField getFirstPolynomialTextField() {
        return this.firstPolynomialTextField;
    }

    public TextField getSecondPolynomialTextField() {
        return this.secondPolynomialTextField;
    }

    public Button getComputeOperationButton() {
        return this.computeOperationButton;
    }

    public ChoiceBox<String> getOperatorChoiceBox() {
        return this.operatorChoiceBox;
    }

    public TextField getResultTextField() {
        return this.resultTextField;
    }

    public Button getClearResultButton() {
        return this.clearResultButton;
    }

    public Label getFirstPolynomialErrorLabel() {
        return this.firstPolynomialErrorLabel;
    }

    public Label getSecondPolynomialErrorLabel() {
        return this.secondPolynomialErrorLabel;
    }

    @FXML
    public void initialize() {
        this.operatorChoiceBox.getItems().add("P(x) + Q(x)");
        this.operatorChoiceBox.getItems().add("P(x) - Q(x)");
        this.operatorChoiceBox.getItems().add("P(x) × Q(x)");
        this.operatorChoiceBox.getItems().add("P(x) ÷ Q(x)");
        this.operatorChoiceBox.getItems().add("dP / dx");
        this.operatorChoiceBox.getItems().add("dQ / dx");
        this.operatorChoiceBox.getItems().add("∫ P(x) dx");
        this.operatorChoiceBox.getItems().add("∫ Q(x) dx");

        this.operatorChoiceBox.getSelectionModel().select(0);
    }

    public void onComputeOperationButtonClicked(ActionEvent actionEvent) {

    }

    public void onClearResultButtonClicked(ActionEvent actionEvent) {

    }

    private void setFirstPolynomialTextFieldErrorMessage(String message) {
        this.firstPolynomialErrorLabel.setText(message);

        if (this.firstPolynomialErrorLabelClearTimeline != null) {
            this.firstPolynomialErrorLabelClearTimeline.stop();
        }

        this.firstPolynomialErrorLabelClearTimeline = new Timeline(new KeyFrame(
                Duration.seconds(4),
                event -> {
                    setFirstPolynomialTextFieldErrorMessage("");
                }
        ));

        this.firstPolynomialErrorLabelClearTimeline.play();
    }

    private void setSecondPolynomialTextFieldErrorMessage(String message) {
        this.secondPolynomialErrorLabel.setText(message);

        if (this.secondPolynomialErrorLabelClearTimeline != null) {
            this.secondPolynomialErrorLabelClearTimeline.stop();
        }

        this.secondPolynomialErrorLabelClearTimeline = new Timeline(new KeyFrame(
                Duration.seconds(4),
                event -> {
                    setSecondPolynomialTextFieldErrorMessage("");
                }
        ));

        this.secondPolynomialErrorLabelClearTimeline.play();
    }
}