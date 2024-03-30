package org.moldidev.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.moldidev.model.Polynomial;
import org.moldidev.interfaces.PolynomialOperations;

import java.util.HashSet;
import java.util.Set;

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
    private Button switchPolynomialsButton;
    @FXML
    private Label firstPolynomialErrorLabel;
    @FXML
    private Label secondPolynomialErrorLabel;
    private Timeline firstPolynomialErrorLabelClearTimeline = new Timeline();
    private Timeline secondPolynomialErrorLabelClearTimeline = new Timeline();

    @FXML
    public void initialize() {
        this.operatorChoiceBox.getItems().add("P(x) + Q(x)");
        this.operatorChoiceBox.getItems().add("P(x) - Q(x)");
        this.operatorChoiceBox.getItems().add("P(x) × Q(x)");
        this.operatorChoiceBox.getItems().add("P(x) ÷ Q(x)");
        this.operatorChoiceBox.getItems().add("P'(x)");
        this.operatorChoiceBox.getItems().add("Q'(x)");
        this.operatorChoiceBox.getItems().add("∫ P(x) dx");
        this.operatorChoiceBox.getItems().add("∫ Q(x) dx");

        this.operatorChoiceBox.getSelectionModel().select(0);
    }

    public void onComputeOperationButtonClicked() {
        int selectedOperationId = this.operatorChoiceBox.getSelectionModel().getSelectedIndex();

        if (validateInputs(selectedOperationId)) {
            Polynomial p = new Polynomial(this.firstPolynomialTextField.getText().toLowerCase());
            Polynomial q = new Polynomial(this.secondPolynomialTextField.getText().toLowerCase());
            String resultPolynomial;

            switch (selectedOperationId) {
                case 0:
                    resultPolynomial = PolynomialOperations.addPolynomials(p, q);
                    break;
                case 1:
                    resultPolynomial = PolynomialOperations.subtractPolynomials(p, q);
                    break;
                case 2:
                    resultPolynomial = PolynomialOperations.multiplyPolynomials(p, q);
                    break;
                case 3:
                    resultPolynomial = PolynomialOperations.dividePolynomials(p, q);
                    break;
                case 4:
                    resultPolynomial = PolynomialOperations.differentiatePolynomial(p);
                    break;
                case 5:
                    resultPolynomial = PolynomialOperations.differentiatePolynomial(q);
                    break;
                case 6:
                    resultPolynomial = PolynomialOperations.integratePolynomial(p);
                    break;
                case 7:
                    resultPolynomial = PolynomialOperations.integratePolynomial(q);
                    break;
                default:
                    resultPolynomial = "INVALID OPERATION";
                    break;
            }

            this.resultTextField.setText(resultPolynomial);
        }
    }

    public void onClearResultButtonClicked() {
        this.resultTextField.setText("");
    }

    public void onSwitchPolynomialsButtonClicked() {
        String firstPolynomialInput = this.firstPolynomialTextField.getText();
        String secondPolynomialInput = this.secondPolynomialTextField.getText();
        String temp;

        temp = firstPolynomialInput;
        firstPolynomialInput = secondPolynomialInput;
        secondPolynomialInput = temp;

        this.firstPolynomialTextField.setText(firstPolynomialInput);
        this.secondPolynomialTextField.setText(secondPolynomialInput);
    }

    private void setFirstPolynomialTextFieldErrorMessage(String message) {
        this.firstPolynomialErrorLabel.setText(message);

        if (this.firstPolynomialErrorLabelClearTimeline != null) {
            this.firstPolynomialErrorLabelClearTimeline.stop();
        }

        this.firstPolynomialErrorLabelClearTimeline = new Timeline(new KeyFrame(
                Duration.seconds(4),
                event -> {
                    this.firstPolynomialErrorLabel.setText("");
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
                    this.secondPolynomialErrorLabel.setText("");
                }
        ));

        this.secondPolynomialErrorLabelClearTimeline.play();
    }

    private void setPolynomialTextFieldErrorMessage(int selectedTextField, String message) {
        if (selectedTextField == 1) {
            setFirstPolynomialTextFieldErrorMessage(message);
        }

        else {
            setSecondPolynomialTextFieldErrorMessage(message);
        }
    }

    /*
     * @param selectedOperationId
     * @return boolean
     *
     * Check the required input fields and return true if they're valid or false if they're not
     */
    private boolean validateInputs(int selectedOperationId) {
        // If the user wants to perform P(X) + Q(X) or P(X) - Q(X) or P(X) * Q(X) or P(X) / Q(X)
        if (selectedOperationId == 0 || selectedOperationId == 1 || selectedOperationId == 2 || selectedOperationId == 3) {
            if (!validateInputField(1) || !validateInputField(2)) {
                return false;
            }
        }

        // If the user wants to perform dP/dx
        else if (selectedOperationId == 4) {
            if (!validateInputField(1)) {
                return false;
            }
        }

        // If the user wants to perform dQ/dx
        else if (selectedOperationId == 5) {
            if (!validateInputField(2)) {
                return false;
            }
        }

        // If the user wants to integrate P(X)
        else if (selectedOperationId == 6) {
            if (!validateInputField(1)) {
                return false;
            }
        }

        // If the user wants to integrate Q(X)
        else if (selectedOperationId == 7) {
            if (!validateInputField(2)) {
                return false;
            }
        }

        // If all the conditions are matched, the given input is valid, so the input string can be processed
        return true;
    }

    /*
     * @param selectedTextField
     * @return boolean
     *
     * Validate an input field, returning true if it's valid, false if it's not
     * If an input field is invalid, a custom error message is displayed
     */
    private boolean validateInputField(int selectedTextField) {
        // The regex consists of numbers, '-' and '+' signs, 'x', 'X' and '^'
        String inputRegex = "^[xX\\-+^\\d\\s]*$";

        String inputField = selectedTextField == 1 ? this.firstPolynomialTextField.getText().replaceAll("\\s", "").toLowerCase() : this.secondPolynomialTextField.getText().replaceAll("\\s", "").toLowerCase();

        // If the input field is empty or blank
        if (inputField.isEmpty() || inputField.isBlank()) {
            setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! The text field can't be empty, nor blank!");
            return false;
        }

        // If the input field doesn't follow the regex pattern
        else if (!inputField.matches(inputRegex)) {
            setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! The only allowed characters are digits, '-', '+', spaces, '^', 'x' and 'X'!");
            return false;
        }

        // If the input field contains negative exponents
        else if (inputField.contains("^-")) {
            setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! A monomial can't have a negative exponent!");
            return false;
        }

        // If the input field contains + after ^ (e.g. x^+2)
        else if (inputField.contains("^+")) {
            setPolynomialTextFieldErrorMessage(selectedTextField, "Invalid input! You don't have to specify the sign of an exponent!");
            return false;
        }

        // If the input field has a coefficient after 'x'
        else if (inputField.contains("x0")
                || inputField.contains("x1")
                || inputField.contains("x2")
                || inputField.contains("x3")
                || inputField.contains("x4")
                || inputField.contains("x5")
                || inputField.contains("x6")
                || inputField.contains("x7")
                || inputField.contains("x8")
                || inputField.contains("x9")) {
            setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! A monomial's coefficient should be before the 'x' term!");
            return false;
        }

        // If the input field has '+', '-' or '^' signs near each other
        else if (inputField.contains("^^") || inputField.contains("-+") || inputField.contains("+-") || inputField.contains("--") || inputField.contains("++")) {
            setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! The signs '-', '+' or '^' shouldn't be consecutive!");
            return false;
        }

        // If the input field has at least 2 'x' inputs near each other
        else if (inputField.contains("xx")) {
            setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! No two 'x'-es should be consecutive! Use instead 'x^2'!");
            return false;
        }

        // If the input field doesn't have a '+' or '-' sign after the power (e.g. 2x^2x instead of 2x^2 + x)
        else if (inputField.matches("^[x\\-+^\\d\\s]*(\\^\\d+x)[x\\-+^\\d\\s]*$")) {
            setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! The only allowed characters after an exponent is specified are '+' or '-'!");
            return false;
        }

        // If the input field contains 'x' as a power, not a number (e.g. 2x^x)
        if (inputField.contains("^x")) {
            setPolynomialTextFieldErrorMessage(selectedTextField, "Invalid input! The exponent must be a nonnegative integer!");
            return false;
        }

        // If the input field contains number ^ power instead of x ^ power
        for (int i = 0; i < inputField.toCharArray().length - 2; i++) {
            char character = inputField.toCharArray()[i];
            char nextCharacter = inputField.toCharArray()[i + 1];
            char nextNextCharacter = inputField.toCharArray()[i + 2];

            if (character >= '0' && character <= '9' && nextCharacter == '^' && nextNextCharacter >= '0' && nextNextCharacter <= '9') {
                setPolynomialTextFieldErrorMessage(selectedTextField,"Invalid input! Only 'x' can be raised to an exponent!");
                return false;
            }
        }

        // If the input fields starts or ends with with ^
        if (inputField.toCharArray()[0] == '^' || inputField.toCharArray()[inputField.toCharArray().length - 1] == '^') {
            setPolynomialTextFieldErrorMessage(selectedTextField, "Invalid input! The polynomial can't start or end with '^'!");
            return false;
        }

        // If all the criterias are met, then the given input field is valid
        return true;
    }
}