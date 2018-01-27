package org.lgabrielgr.playstation.trophies.client;

import org.lgabrielgr.playstation.trophies.exception.PsnTrophiesException;

import java.io.Closeable;

public interface PsnPublicTrophiesClient {

    String retrievePsnUserData(final String psnOnlineId) throws PsnTrophiesException;

    String retrievePsnUserTrophies(final String psnOnlineId) throws PsnTrophiesException;

}
