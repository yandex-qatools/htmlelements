package ru.yandex.qatools.htmlelements.element;

import java.util.Map;

/**
 * Implement this interface in blocks, that may be used inside html forms.
 * */
public interface Fillable {
    /**
     * Performs filling of the block.
     * Used by {@link FormBase#fill(Map)} to fill all blocks with corresponding map values
     * */
    void fill(String value);
}
