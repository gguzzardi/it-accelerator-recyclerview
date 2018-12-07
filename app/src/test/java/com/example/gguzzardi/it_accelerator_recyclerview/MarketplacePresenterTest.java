package com.example.gguzzardi.it_accelerator_recyclerview;

import com.example.gguzzardi.it_accelerator_recyclerview.apis.MercadoLibreApi;
import com.example.gguzzardi.it_accelerator_recyclerview.apis.MercadolibreService;
import com.example.gguzzardi.it_accelerator_recyclerview.model.Marketplace;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.MarketplacePresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.MarketplaceItemsView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarketplacePresenterTest {

    @Mock
    private Marketplace mMockedMarketplace;

    @Mock
    private MarketplaceItemsView mMockedView;

    @Mock
    private MercadolibreService mMeliService;

    private MarketplacePresenter mMarketplacePresenter;

    @Before
    public void initPresenter() {
        mMarketplacePresenter = new MarketplacePresenter(mMockedView, mMockedMarketplace);
    }

    @Test
    public void onItemClickedCallsViewIOnItemClickedMethod() {
        mMarketplacePresenter.onItemClicked("1");
        verify(mMockedView, times(1)).onItemClicked("1");
    }

    @Test
    public void getItemsListCallsMarketplaceGetItemsMethod() {
        mMarketplacePresenter.getItems();
        verify(mMockedMarketplace, times(1)).getItems();
    }
}
