package pt.brunojesus.simpleui.menu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuItem {

    private final String title;
    private final Runnable selectionAction;
    private final Runnable singleClickAction;
    private final Runnable doubleClickAction;
    private final Runnable rightClickAction;

    @Override
    public String toString() {
        return title;
    }
}
