package com.github.bordertech.wcomponents.lib.app.view;

import com.github.bordertech.flux.wc.view.View;
import java.util.List;

/**
 * List view.
 *
 * @param <T> the item type
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface ListView<T> extends View<List<T>> {

	void setItems(final List<T> items);

	List<T> getItems();

	void refreshItems(final List<T> items);
}
