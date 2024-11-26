package lk.ijse.util.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public  static boolean isTextFieldValid(TextField textField, String text){
         String filed ="";

        switch (textField) {
            case ID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case NAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case ADDRESS:
                filed =".+";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;

            case CONTACT:
                filed ="^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;

            case NUMBER:
                filed ="^[0-9]+$";


        }
        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-text-fill: Black;  -fx-border-color: green;");
       return true;
        } else {
            textField.setStyle("-fx-text-fill: red;  -fx-border-color: red;");

            return false;
        }

    }

    }


