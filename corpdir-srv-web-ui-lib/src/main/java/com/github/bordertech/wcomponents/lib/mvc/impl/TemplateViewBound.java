package com.github.bordertech.wcomponents.lib.mvc.impl;

import com.github.bordertech.wcomponents.WebUtilities;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.mvc.ViewBound;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 *
 * @param <T> the view bean
 */
public class TemplateViewBound<T> extends TemplateView implements ViewBound<T> {

	public TemplateViewBound(final String templateName, final Dispatcher dispatcher) {
		this(templateName, dispatcher, null);
	}

	public TemplateViewBound(final String templateName, final Dispatcher dispatcher, final String qualifier) {
		super(templateName, dispatcher, qualifier);
		setSearchAncestors(false);
		setBeanProperty(".");
	}

	@Override
	public void updateViewBean() {
		WebUtilities.updateBeanValue(this);
	}

	@Override
	public T getViewBean() {
		return (T) getBean();
	}

	@Override
	public void setViewBean(final T viewBean) {
		setBean(viewBean);
	}
}