package org.lgabrielgr.playstation.trophies;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.lgabrielgr.playstation.trophies.client.PsnPublicTrophiesClient;
import org.lgabrielgr.playstation.trophies.client.impl.PsnPublicTrophiesClientImpl;
import org.lgabrielgr.playstation.trophies.data.PsnUserData;
import org.lgabrielgr.playstation.trophies.data.PsnUserTrophiesData;
import org.lgabrielgr.playstation.trophies.exception.PsnTrophiesException;

import java.io.IOException;

public class PsnUserTrophies {

    private final PsnPublicTrophiesClient psnPublicTrophiesClient = new PsnPublicTrophiesClientImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PsnUserTrophies() {

        init();

    }

    public PsnUserData retrieveUserData(final String psnOnlineId) throws PsnTrophiesException {

        try {

            final String psnResponse = getPsnPublicTrophiesClient().retrievePsnUserData(psnOnlineId);
            return objectMapper.readValue(psnResponse, PsnUserData.class);

        } catch (IOException e) {

            throw new PsnTrophiesException(e.getMessage());

        }

    }

    public PsnUserTrophiesData retrieveUserTrophiesData(final String psnOnlineId) throws PsnTrophiesException {

        try {

            final String psnResponse = getPsnPublicTrophiesClient().retrievePsnUserTrophies(psnOnlineId);
            return objectMapper.readValue(psnResponse, PsnUserTrophiesData.class);

        } catch (IOException e) {

            throw new PsnTrophiesException(e.getMessage());

        }

    }

    private void init() {

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    public PsnPublicTrophiesClient getPsnPublicTrophiesClient() {
        return psnPublicTrophiesClient;
    }

}
