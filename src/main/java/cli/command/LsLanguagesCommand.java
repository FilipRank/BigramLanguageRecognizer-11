package cli.command;

import languages.LanguagesEnum;

public class LsLanguagesCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Here is a list of languages available for recognition: \n");

        for (LanguagesEnum language : LanguagesEnum.values()) {
            System.out.printf("- %s\n", language.getFormattedName());
        }
    }
}
