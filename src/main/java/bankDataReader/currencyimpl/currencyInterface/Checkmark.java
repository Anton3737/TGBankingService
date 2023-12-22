package bankDataReader.currencyimpl.currencyInterface;

public class Checkmark {
    /**
     * метод addCheck додає "✔" до назви кнопки
     * метод deleteCheck видаляє "✔" з назви кнопки
     * @param buttonName
     * @return
     */
    public String addCheck(String buttonName){
        if(!buttonName.startsWith("✔")) {
            buttonName = "✔" + buttonName;
        }
        return buttonName;
    }

    public String deleteCheck(String buttonName) {
        String res = "";
        if(buttonName.startsWith("✔")) {
            res = buttonName.replaceAll("✔","");
        }
        return res;
    }
}
