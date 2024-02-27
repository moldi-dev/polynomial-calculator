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
    private ChoiceBox<String> operatorChoiceBox;
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

    @FXML
    public void initialize() {
        operatorChoiceBox.getItems().removeAll(operatorChoiceBox.getItems());

        operatorChoiceBox.getItems().add("P(x) + Q(x)");
        operatorChoiceBox.getItems().add("P(x) - Q(x)");
        operatorChoiceBox.getItems().add("P(x) × Q(x)");
        operatorChoiceBox.getItems().add("P(x) ÷ Q(x)");
        operatorChoiceBox.getItems().add("dP / dx");
        operatorChoiceBox.getItems().add("∫ P(x) dx");

        operatorChoiceBox.getSelectionModel().select(0);
    }

    public void onComputeOperationButtonClicked(ActionEvent actionEvent) {
        setFirstPolynomialTextFieldErrorMessage("test error label yayawydyawy");
        setSecondPolynomialTextFieldErrorMessage("test error label 2 addawdawdawdawdwad");

        System.out.println(actionEvent.getEventType().getName());
    }

    public void onClearResultButtonClicked(ActionEvent actionEvent) {

    }

    private void setFirstPolynomialTextFieldErrorMessage(String message) {
        this.firstPolynomialErrorLabel.setText(message);

        firstPolynomialErrorLabelClearTimeline.stop();

        firstPolynomialErrorLabelClearTimeline = new Timeline(new KeyFrame(
                Duration.seconds(4),
                event -> {
                    setFirstPolynomialTextFieldErrorMessage("");
                }
        ));

        firstPolynomialErrorLabelClearTimeline.play();
    }

    private void setSecondPolynomialTextFieldErrorMessage(String message) {
        this.secondPolynomialErrorLabel.setText(message);

        secondPolynomialErrorLabelClearTimeline.stop();

        secondPolynomialErrorLabelClearTimeline = new Timeline(new KeyFrame(
                Duration.seconds(4),
                event -> {
                    setSecondPolynomialTextFieldErrorMessage("");
                }
        ));

        secondPolynomialErrorLabelClearTimeline.play();
    }
}