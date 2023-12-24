package bankDataReader.currencyimpl.currencyInterface;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PutMarks<T> {

    public InlineKeyboardMarkup addButtons(List<T> buttonTexts, List<String> userParams) {
        return this.addButtons(buttonTexts, userParams, 3);
    }

    public InlineKeyboardMarkup addButtons(List<T> buttonTexts, List<String> userParams, int rows) {
        InlineKeyboardMarkup.InlineKeyboardMarkupBuilder inlineKeyboardMarkup = InlineKeyboardMarkup.builder();

        List<InlineKeyboardButton> buttonList = new ArrayList<>();


        for (T button: buttonTexts) {
            String text = button.toString();
            String finalText;
            if (userParams.contains(text)){
                finalText = Checkmark.addCheck(text);
            } else {
                finalText = text;
            }

            InlineKeyboardButton buttonObject = InlineKeyboardButton.builder().text(finalText).callbackData(text).build();
            buttonList.add(buttonObject);
        }

        return inlineKeyboardMarkup.keyboard(Collections.singleton(buttonList)).build();
    }
}
