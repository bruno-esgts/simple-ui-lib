package pt.brunojesus.simpleui.commandprompt;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommandPrompt extends JFrame {

    private JTextArea viewContent;
    private JPanel mainPanel;
    private JScrollPane ViewScrollPane;
    private JPanel ViewPanel;
    private JTextField textInput;

    private CommandPromptParameters parameters;

    public CommandPrompt(CommandPromptParameters parameters) {
        this.parameters = parameters;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUi();
        initListeners();
    }

    private void initUi() {
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(650, 600));

        viewContent.setText(parameters.getViewText());
    }

    private void initListeners() {
        textInput.addActionListener(e -> {
            parameters.getOnInputEvent().accept(this, e.getActionCommand());
        });
    }

    public void cleanInput() {
        textInput.setText(null);
    }

    public void appendToView(String text) {
        if (StringUtils.isNotEmpty(viewContent.getText())) {
            text = "\n" + text;
        }

        viewContent.append(text);
    }

    public void scrollToBottom() {
        viewContent.setCaretPosition(
                viewContent.getDocument().getLength()
        );
    }
}
