# Simple UI Lib

## Example application
```java
package pt.brunojesus.simpleui;

import pt.brunojesus.simpleui.commandprompt.CommandPrompt;
import pt.brunojesus.simpleui.commandprompt.CommandPromptParameters;
import pt.brunojesus.simpleui.filechooser.FileChooser;
import pt.brunojesus.simpleui.filechooser.FileChooserDialogType;
import pt.brunojesus.simpleui.filechooser.FileChooserParameters;
import pt.brunojesus.simpleui.menu.Menu;
import pt.brunojesus.simpleui.menu.MenuItem;
import pt.brunojesus.simpleui.menu.MenuParameters;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        commandPrompt();
    }

    private static void commandPrompt() {
        final CommandPrompt commandPrompt = new CommandPrompt(
                CommandPromptParameters.builder()
                        .viewText("Hello World!")
                        .onInputEvent((prompt, input) -> {
                            prompt.cleanInput();
                            prompt.appendToView("> " + input);
                            prompt.scrollToBottom();

                            if (input.equals("menu")) {
                                menu();
                            } else if (input.equals("file")) {
                                final File file = fileChooser();
                                if (file != null) {
                                    try (InputStream inputStream = new FileInputStream(file)) {
                                        prompt.appendToView(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
                                        prompt.scrollToBottom();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        })
                        .build()
        );

        commandPrompt.setVisible(true);
    }

    private static void menu() {
        final List<MenuItem> items = List.of(
                MenuItem.builder()
                        .title("Bruno")
                        .selectionAction(() -> System.out.println("Selection Bruno"))
                        .singleClickAction(() -> System.out.println("Single click Bruno"))
                        .rightClickAction(() -> System.out.println("Right click Bruno"))
                        .doubleClickAction(() -> System.out.println("Double click Bruno"))
                        .build(),
                MenuItem.builder()
                        .title("Maria")
                        .selectionAction(() -> System.out.println("Selection Maria"))
                        .singleClickAction(() -> System.out.println("Single click Maria"))
                        .rightClickAction(() -> System.out.println("Right click Maria"))
                        .doubleClickAction(() -> System.out.println("Double click Maria"))
                        .build()
        );

        final Menu menu = new Menu(
                MenuParameters.builder()
                        .menuItems(items)
                        .fontSize(24)
                        .dimension(new Dimension(200, 400))
                        .build()
        );
        menu.setVisible(true);
        menu.setResizable(false);
    }

    private static File fileChooser() {
        File selectedFile = new FileChooser().apply(
                FileChooserParameters.builder()
                        .extensionFilters(
                                List.of(
                                        new FileNameExtensionFilter("XML Files", "xml"),
                                        new FileNameExtensionFilter("Text Files",  "txt", "md")
                                )
                        )
                        .defaultExtensionFilter(new FileNameExtensionFilter("Text Files", "txt", "md"))
                        .fileChooserDialogType(FileChooserDialogType.SAVE)
                        .dialogTitle("Save me")
                        .build()
        );

        if (selectedFile != null) {
            System.out.printf("Selected file is: %s", selectedFile.getAbsolutePath());
        }

        return selectedFile;
    }
}
```