package pt.brunojesus.simpleui.commandprompt;

import lombok.Builder;
import lombok.Data;

import java.util.function.BiConsumer;

@Data
@Builder
public class CommandPromptParameters {

    private String viewText;
    private BiConsumer<CommandPrompt, String> onInputEvent;
}
