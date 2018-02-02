package com.github.bordertech.corpdir.web.ui.store;

import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.web.ui.dataapi.PositionApi;
import com.github.bordertech.corpdir.web.ui.flux.CorpCrudTreeStore;

/**
 * Position Store with backing API.
 *
 * @author jonathan
 */
public interface PositionStore extends CorpCrudTreeStore<Position, PositionApi> {
}