package org.firstinspires.ftc.teamcode.common.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Screen<T> {
    private List<T> options;
    private String text;
    private int currentSelection;

    public Screen(String text, List<T> options) {
        this.options = options;
        this.text = text;
        currentSelection = 0;
    }

    public Screen(String text) {
        this(text, new ArrayList<>());
    }

    public Screen removeOption(T option) {
        options.remove(option);
        return this;
    }

    public String summary() {
        return String.format("%s: %s", text, options.get(currentSelection));
    }

    public Screen addOption(T option) {
        options.add(option);
        return this;
    }

    public T getSelected() {
        return options.get(currentSelection);
    }

    public void scrollUp() {
        currentSelection = Math.min(currentSelection + 1, options.size() - 1);
    }

    public void scrollDown() {
        currentSelection = Math.max(currentSelection - 1, 0);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(text);
        for (int i = 0; i < options.size(); i++) {
            builder.append('\n');
            builder.append(String.format(i == currentSelection ? ">%s<": "\u2002%s\u2002 ", options.get(i)));
        }
        return builder.toString();
    }
}