package pt.brunojesus.simpleui.filechooser;

import lombok.Builder;
import lombok.Data;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.List;

@Data
@Builder
public class FileChooserParameters {
    private final List<FileNameExtensionFilter> extensionFilters;
    private final FileNameExtensionFilter defaultExtensionFilter;
    private final String dialogTitle;
    private final String currentDirectory;
    private final FileChooserDialogType fileChooserDialogType;
    private final Component parent;
}

