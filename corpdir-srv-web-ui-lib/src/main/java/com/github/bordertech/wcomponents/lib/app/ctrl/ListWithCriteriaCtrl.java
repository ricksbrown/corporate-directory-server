package com.github.bordertech.wcomponents.lib.app.ctrl;

import com.github.bordertech.wcomponents.lib.app.event.ActionEventType;
import com.github.bordertech.wcomponents.lib.app.model.RequiresServiceModel;
import com.github.bordertech.wcomponents.lib.app.model.ServiceModel;
import com.github.bordertech.wcomponents.lib.app.view.CriteriaView;
import com.github.bordertech.wcomponents.lib.app.view.ListView;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.flux.Event;
import com.github.bordertech.wcomponents.lib.flux.Listener;
import com.github.bordertech.wcomponents.lib.flux.impl.DefaultController;
import com.github.bordertech.wcomponents.lib.flux.impl.WView;
import com.github.bordertech.wcomponents.lib.polling.PollingEventType;
import com.github.bordertech.wcomponents.lib.polling.PollingServiceView;
import java.util.List;

/**
 * Controller for a Criteria View and List View.
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <T> the result type
 */
public class ListWithCriteriaCtrl<S, T> extends DefaultController implements RequiresServiceModel<S, List<T>> {

	public ListWithCriteriaCtrl(final Dispatcher dispatcher) {
		this(dispatcher, null);
	}

	public ListWithCriteriaCtrl(final Dispatcher dispatcher, final String qualifier) {
		super(dispatcher, qualifier);

		// Listeners
		// Search EVENT
		Listener listener = new Listener() {
			@Override
			public void handleEvent(final Event event) {
				S criteria = (S) event.getData();
				handleSearchEvent(criteria);
			}
		};
		registerCtrlListener(listener, ActionEventType.SEARCH);

		// Polling - FAIL
		listener = new Listener() {
			@Override
			public void handleEvent(final Event event) {
				Exception excp = event.getException();
				handleSearchFailedEvent(excp);
			}
		};
		registerCtrlListener(listener, PollingEventType.ERROR);

		// Polling - COMPLETE
		listener = new Listener() {
			@Override
			public void handleEvent(final Event event) {
				List<T> entities = (List<T>) event.getData();
				handleSearchCompleteEvent(entities);
			}
		};
		registerCtrlListener(listener, PollingEventType.COMPLETE);
	}

	@Override
	protected void checkConfig() {
		super.checkConfig();
		if (getPollingView() == null) {
			throw new IllegalStateException("A polling view has not been set.");
		}
		if (getCriteriaView() == null) {
			throw new IllegalStateException("A criteria view has not been set.");
		}
		if (getListView() == null) {
			throw new IllegalStateException("A list view has not been set.");
		}
		if (getServiceModel() == null) {
			throw new IllegalStateException("A search service has not been set.");
		}
	}

	@Override
	public void configViews() {
		super.configViews();
		getPollingView().makeContentInvisible();
		getListView().makeContentInvisible();
	}

	@Override
	public void configAjax(final WView view) {
		super.configAjax(view);
		view.addEventTarget(getViewMessages());
		view.addEventTarget(getPollingView());
		view.addEventTarget(getListView());
	}

	public final CriteriaView<S> getCriteriaView() {
		return getComponentModel().criteriaView;
	}

	public final void setCriteriaView(final CriteriaView<S> criteriaView) {
		getOrCreateComponentModel().criteriaView = criteriaView;
		addView(criteriaView);
	}

	public final PollingServiceView<S, List<T>> getPollingView() {
		return getComponentModel().pollingView;
	}

	public final void setPollingView(final PollingServiceView<S, List<T>> pollingView) {
		getOrCreateComponentModel().pollingView = pollingView;
		addView(pollingView);
	}

	public final ListView<T> getListView() {
		return getComponentModel().listView;
	}

	public final void setListView(final ListView<T> listView) {
		getOrCreateComponentModel().listView = listView;
		addView(listView);
	}

	@Override
	public ServiceModel<S, List<T>> getServiceModel() {
		return getComponentModel().serviceModel;
	}

	@Override
	public void setServiceModel(final ServiceModel<S, List<T>> serviceModel) {
		getOrCreateComponentModel().serviceModel = serviceModel;
	}

	protected void handleSearchEvent(final S criteria) {
		// Setup polling view
		PollingServiceView pollingView = getPollingView();
		pollingView.reset();
		pollingView.setPollingCriteria(criteria);
		pollingView.setServiceModel(getServiceModel());
		pollingView.makeContentVisible();

		// Reset Listview
		ListView listView = getListView();
		listView.reset();
		listView.makeContentInvisible();
	}

	protected void handleSearchFailedEvent(final Exception excp) {
		getPollingView().makeContentInvisible();
		getViewMessages().error(excp.getMessage());
	}

	protected void handleSearchCompleteEvent(final List<T> result) {
		getPollingView().makeContentInvisible();
		if (result == null || result.isEmpty()) {
			getViewMessages().info("No records found");
		} else {
			ListView listView = getListView();
			listView.setViewBean(result);
			listView.makeContentVisible();
		}
	}

	@Override
	protected CriteriaListModel newComponentModel() {
		return new CriteriaListModel();
	}

	@Override
	protected CriteriaListModel getComponentModel() {
		return (CriteriaListModel) super.getComponentModel();
	}

	@Override
	protected CriteriaListModel getOrCreateComponentModel() {
		return (CriteriaListModel) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of the edit view.
	 */
	public static class CriteriaListModel<S, T> extends CtrlModel {

		private CriteriaView<S> criteriaView;

		private PollingServiceView<S, List<T>> pollingView;

		private ListView<T> listView;

		private ServiceModel<S, List<T>> serviceModel;
	}

}
