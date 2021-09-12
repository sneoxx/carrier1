package com.haulmont.carrier.web.screens.historycost;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.HistoryCost;

@UiController("carrier_HistoryCost.browse")
@UiDescriptor("history-cost-browse.xml")
@LookupComponent("historyCostsTable")
@LoadDataBeforeShow
public class HistoryCostBrowse extends StandardLookup<HistoryCost> {
}