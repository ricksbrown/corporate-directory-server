package com.github.bordertech.corpdir.web.ui.smart.card;

import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.web.ui.CardType;
import com.github.bordertech.corpdir.web.ui.dumb.panel.PositionTypePanel;
import com.github.bordertech.corpdir.web.ui.flux.view.impl.DefaultCorpCrudSmartView;
import com.github.bordertech.corpdir.web.ui.flux.view.impl.DefaultCorpSecureCrudCardView;

/**
 * Position type crud view.
 *
 * @author jonathan
 */
public class PositionTypeCardView extends DefaultCorpSecureCrudCardView<PositionType> {

	public PositionTypeCardView() {
		super("PT", CardType.POSITION_TYPE, new DefaultCorpCrudSmartView<PositionType>("SV", "Position Type", new PositionTypePanel("PL")));
	}

}
