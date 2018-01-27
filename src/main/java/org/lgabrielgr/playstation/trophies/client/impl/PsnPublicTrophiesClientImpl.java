package org.lgabrielgr.playstation.trophies.client.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.lgabrielgr.playstation.trophies.client.PsnPublicTrophiesClient;
import org.lgabrielgr.playstation.trophies.exception.PsnTrophiesException;

import java.io.IOException;

public class PsnPublicTrophiesClientImpl implements PsnPublicTrophiesClient {

    private static final String PSN_USER_PROFILE_URL =
            "https://io.playstation.com/playstation/psn/profile/public/userData?onlineId=%s";
    private static final String PSN_USER_TROPHIES_URL =
            "https://io.playstation.com/playstation/psn/public/trophies/?onlineId=%s";
    private static final String ORIGIN_REQUEST_HEADER = "Origin";
    private static final String ORIGIN_PLAYSTATION_URL = "https://www.playstation.com";
    private static final String NOT_OK_STATUS_ERROR_MESSAGE =
            "Invalid response from server [HTTP status: %d, message: %s]";
    private static final String NO_RESPONSE_MESSAGE = "<no message>";
    private static final String EMPTY_PSN_ONLINE_ID = "PSN online ID must not be null";

    @Override
    public String retrievePsnUserData(final String psnOnlineId) throws PsnTrophiesException {

        if (StringUtils.isEmpty(psnOnlineId)) {

            throw new PsnTrophiesException(EMPTY_PSN_ONLINE_ID);

        }

        return executeRequestToPsn(String.format(PSN_USER_PROFILE_URL, psnOnlineId));
    }

    @Override
    public String retrievePsnUserTrophies(final String psnOnlineId) throws PsnTrophiesException {

        if (StringUtils.isEmpty(psnOnlineId)) {

            throw new PsnTrophiesException(EMPTY_PSN_ONLINE_ID);

        }

        return executeRequestToPsn(String.format(PSN_USER_TROPHIES_URL, psnOnlineId));
    }

    private String executeRequestToPsn(final String psnUrl) throws PsnTrophiesException {

        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            final HttpGet httpGet = new HttpGet(psnUrl);
            httpGet.setHeader(ORIGIN_REQUEST_HEADER, ORIGIN_PLAYSTATION_URL);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

                return parseResponse(response);

            }

        } catch (IOException e) {

            throw new PsnTrophiesException(e.getMessage());

        }

    }

    private String parseResponse(final CloseableHttpResponse response) throws IOException, PsnTrophiesException {

        if (response.getStatusLine() == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

            throw new PsnTrophiesException(String.format(NOT_OK_STATUS_ERROR_MESSAGE,
                    (response.getStatusLine() == null)
                            ? 0
                            : response.getStatusLine().getStatusCode(),
                    (response.getStatusLine() == null)
                            ? NO_RESPONSE_MESSAGE
                            : response.getStatusLine().getReasonPhrase()));

        }

        return EntityUtils.toString(response.getEntity());

    }


}
