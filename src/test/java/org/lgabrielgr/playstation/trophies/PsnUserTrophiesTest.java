package org.lgabrielgr.playstation.trophies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lgabrielgr.playstation.trophies.client.PsnPublicTrophiesClient;
import org.lgabrielgr.playstation.trophies.data.PsnUserData;
import org.lgabrielgr.playstation.trophies.data.PsnUserTrophiesData;
import org.lgabrielgr.playstation.trophies.exception.PsnTrophiesException;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PsnUserTrophiesTest {

    @Mock
    private PsnPublicTrophiesClient psnPublicTrophiesClient;

    @Spy
    private final PsnUserTrophies psnUserTrophies = new PsnUserTrophies();

    @Before
    public void before() {

        doReturn(psnPublicTrophiesClient).when(psnUserTrophies).getPsnPublicTrophiesClient();

    }

    @Test
    public void shouldCallPsnClientToRetrieveUserDataAndTransformResultToJavaBean() throws PsnTrophiesException {

        // given:
        final String psnOnlineId = "someguy";
        when(psnPublicTrophiesClient.retrievePsnUserData(psnOnlineId)).thenReturn(
                "{\"handle\":\"someguy\",\"avatarUrl\":\"//user_image.png\",\"isPlusUser\":\"0\",\"curLevel\":\"1\"," +
                        "\"progress\":\"1\",\"trophies\":{\"bronze\":\"1\",\"gold\":\"2\",\"platinum\":\"0\",\"" +
                        "silver\":\"3\"},\"totalLevel\":\"\"}");

        // when:
        final PsnUserData psnUserData = psnUserTrophies.retrieveUserData(psnOnlineId);

        // then:
        assertEquals(psnOnlineId, psnUserData.getHandle());
        assertEquals(0, psnUserData.getIsPlusUser());
        assertEquals(1, psnUserData.getCurLevel());
        assertEquals(1, psnUserData.getTrophies().getBronze());
        assertEquals(2, psnUserData.getTrophies().getGold());
        assertEquals(3, psnUserData.getTrophies().getSilver());
        assertEquals(0, psnUserData.getTrophies().getPlatinum());
    }

    @Test
    public void shouldCallPsnClientToRetrieveUserTrophiesListAndTransformResultToJavaBean() throws PsnTrophiesException {

        // given:
        final String psnOnlineId = "someguy";
        when(psnPublicTrophiesClient.retrievePsnUserTrophies(psnOnlineId)).thenReturn("{\"handle\":\"someguy\"," +
                "\"avatarUrl\":\"//user_image.png\",\"isPlusUser\":\"0\",\"totalResults\":\"2\",\"curLevel\":\"1\"," +
                "\"overallprogress\":\"1\",\"list\":[{\"platform\":\"ps4\",\"progress\":100,\"trophies\":{\"bronze\":34," +
                "\"silver\":15,\"gold\":1,\"platinum\":1},\"imgUrl\":\"//game_image.PNG\",\"title\":\"Call of Duty\"," +
                "\"gameId\":\"NPWR11068_00\"}]}\n");

        // when:
        final PsnUserTrophiesData psnUserTrophiesData = psnUserTrophies.retrieveUserTrophiesData(psnOnlineId);

        // then:
        assertEquals(psnOnlineId, psnUserTrophiesData.getHandle());
        assertEquals("ps4", psnUserTrophiesData.getList().get(0).getPlatform());
        assertEquals(100, psnUserTrophiesData.getList().get(0).getProgress());
        assertEquals(34, psnUserTrophiesData.getList().get(0).getTrophies().getBronze());
        assertEquals("Call of Duty", psnUserTrophiesData.getList().get(0).getTitle());

    }
}
