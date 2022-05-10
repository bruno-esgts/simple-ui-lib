package pt.brunojesus.simpleui.menu;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
@Builder
public class MenuParameters {

    private final List<MenuItem> menuItems;
    private final Integer fontSize;
    private final String title;
    private final Dimension dimension;
    private final Component parent;
}
