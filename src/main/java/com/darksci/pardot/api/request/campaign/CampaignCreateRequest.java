package com.pardot.api.request.campaign;

import com.pardot.api.request.BaseRequest;
import com.pardot.api.response.campaign.Campaign;

/**
 * For creating new Campaigns using Pardot's API.
 */
public class CampaignCreateRequest extends BaseRequest<CampaignCreateRequest> {
    @Override
    public String getApiEndpoint() {
        return "campaign/do/create";
    }

    /**
     * Define the campaign you want to create in pardot.
     * @param campaign The campaign you want to create in pardot.
     */
    public CampaignCreateRequest withCampaign(final Campaign campaign) {
        setParam("name", campaign.getName());
        setParam("cost", campaign.getCost());

        // This is an optional paramter.
        setParam("folder_id", campaign.getFolderId());
        return this;
    }
}