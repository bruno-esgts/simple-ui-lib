package pt.brunojesus.simpleui.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WindowSize {
    private final int width;
    private final int height;
}
