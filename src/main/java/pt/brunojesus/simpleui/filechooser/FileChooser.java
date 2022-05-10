package pt.brunojesus.simpleui.filechooser;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.File;
import java.util.function.Function;

public class FileChooser implements Function<FileChooserParameters, File> {


    @Override
    public File apply(FileChooserParameters parameters) {
        JFileChooser fileChooser = new JFileChooser();


        if (parameters.getDefaultExtensionFilter() != null) {
            fileChooser.setFileFilter(parameters.getDefaultExtensionFilter());
        }

        if (parameters.getExtensionFilters() != null && parameters.getExtensionFilters().size() > 0) {
            parameters.getExtensionFilters().stream()
                    .filter(extensionFilter -> parameters.getDefaultExtensionFilter() == null
                            || !parameters.getDefaultExtensionFilter().getDescription().equals(extensionFilter.getDescription()))
                    .forEach(fileChooser::addChoosableFileFilter);
        }

        if (StringUtils.isNotBlank(parameters.getDialogTitle())) {
            fileChooser.setDialogTitle(parameters.getDialogTitle());
        }

        if (StringUtils.isBlank(parameters.getCurrentDirectory())) {
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        } else {
            fileChooser.setCurrentDirectory(new File(parameters.getCurrentDirectory()));
        }

        int result = JFileChooser.CANCEL_OPTION;

        if (parameters.getFileChooserDialogType() == null ||
                parameters.getFileChooserDialogType() == FileChooserDialogType.OPEN) {
            result = fileChooser.showOpenDialog(parameters.getParent());
        } else if (parameters.getFileChooserDialogType() == FileChooserDialogType.SAVE) {
            result = fileChooser.showSaveDialog(parameters.getParent());
        }

        File selectedFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }

        return selectedFile;
    }
}
