package org.geekhub.lesson5.task3;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class VotingBoxTest {
    private static final int NON_PAID_CANDIDATE = 0;
    private static final int EXTRA_PAID_CANDIDATE = 1;
    private static final int REGULAR_CANDIDATE = ThreadLocalRandom.current().nextInt(2, 777);

    private Collection<Vote> votingBox;

    @BeforeMethod
    public void setUp() {
        votingBox = new VotingBox(EXTRA_PAID_CANDIDATE, NON_PAID_CANDIDATE);
    }

    @Test
    public void newVoteBoxShouldBeEmpty() {
        Assert.assertTrue(votingBox.isEmpty());
    }

    @Test
    public void voteForNonPaidCandidateShouldNotBeInserted() {
        votingBox.add(new Vote(NON_PAID_CANDIDATE));
        votingBox.add(new Vote(NON_PAID_CANDIDATE));
        votingBox.add(new Vote(NON_PAID_CANDIDATE));

        Assert.assertTrue(votingBox.isEmpty());
    }

    @Test
    public void voteForRegularCandidateShouldBeInsertedOnce() {
        votingBox.add(new Vote(REGULAR_CANDIDATE));
        votingBox.add(new Vote(REGULAR_CANDIDATE));
        votingBox.add(new Vote(REGULAR_CANDIDATE));
        votingBox.add(new Vote(REGULAR_CANDIDATE));

        Assert.assertEquals(votingBox.size(), 4);
    }

    @Test
    public void voteForExtraPaidCandidate_shouldBeDuplicated() {
        votingBox.add(new Vote(EXTRA_PAID_CANDIDATE));
        votingBox.add(new Vote(EXTRA_PAID_CANDIDATE));
        votingBox.add(new Vote(EXTRA_PAID_CANDIDATE));

        Assert.assertEquals(votingBox.size(), 6);
    }

    @Test
    public void addAllShouldAddEachItem() {
        votingBox.addAll(Arrays.asList(
            new Vote(REGULAR_CANDIDATE),
            new Vote(NON_PAID_CANDIDATE),
            new Vote(EXTRA_PAID_CANDIDATE)
        ));

        Assert.assertEquals(votingBox.size(), 3);
    }


    @Test
    public void votingBoxShouldNotContainVoteForNonPaidCandidate() {
        votingBox.addAll(Collections.singletonList(
            new Vote(NON_PAID_CANDIDATE)
        ));

        Assert.assertFalse(votingBox.contains(new Vote(NON_PAID_CANDIDATE)));
    }

    @Test
    public void votingBoxShouldContainInsertedVotes() {
        votingBox.addAll(Arrays.asList(
            new Vote(REGULAR_CANDIDATE),
            new Vote(EXTRA_PAID_CANDIDATE)
        ));

        Assert.assertTrue(votingBox.contains(new Vote(REGULAR_CANDIDATE)));
        Assert.assertTrue(votingBox.contains(new Vote(EXTRA_PAID_CANDIDATE)));
    }

    @Test
    public void votingBoxShouldBeIterable() {
        votingBox.addAll(Arrays.asList(
            new Vote(REGULAR_CANDIDATE),
            new Vote(NON_PAID_CANDIDATE),
            new Vote(EXTRA_PAID_CANDIDATE)
        ));

        int elementsCount = 0;
        for (Vote ignored : votingBox) {
            elementsCount++;
        }

        Assert.assertEquals(elementsCount, 3);
    }

    @Test
    public void votingBoxShouldBeRepresentableAsArray() {
        votingBox.addAll(Arrays.asList(
            new Vote(REGULAR_CANDIDATE),
            new Vote(NON_PAID_CANDIDATE),
            new Vote(EXTRA_PAID_CANDIDATE)
        ));

        Object[] votes = votingBox.toArray();
        Assert.assertEquals(votes.length, 3);
    }

    @Test
    public void votingBoxRemovalShouldRemoveOneVote_whenExistingVoteIsRemoved() {
        votingBox.add(new Vote(EXTRA_PAID_CANDIDATE));

        votingBox.remove(new Vote(EXTRA_PAID_CANDIDATE));

        Assert.assertEquals(votingBox.size(), 1);
    }

    @Test
    public void votingBoxRemoval_ShouldRemoveAllVotes_whenExistingVotesAreRemoved() {
        votingBox.add(new Vote(EXTRA_PAID_CANDIDATE));
        votingBox.add(new Vote(EXTRA_PAID_CANDIDATE));

        votingBox.removeAll(Collections.singletonList(
            new Vote(EXTRA_PAID_CANDIDATE)
        ));

        Assert.assertTrue(votingBox.isEmpty());
    }

    @Test
    public void votingBoxRemovalShouldNotRemoveVote_whenNonExistingVoteRemoved() {
        votingBox.add(new Vote(EXTRA_PAID_CANDIDATE));

        votingBox.remove(new Vote(REGULAR_CANDIDATE));
        votingBox.remove(new Vote(NON_PAID_CANDIDATE));

        Assert.assertEquals(votingBox.size(), 2);
    }

    @Test
    public void votingBoxShouldBeCleanable() {
        votingBox.addAll(Arrays.asList(
            new Vote(EXTRA_PAID_CANDIDATE),
            new Vote(REGULAR_CANDIDATE),
            new Vote(NON_PAID_CANDIDATE)
        ));

        votingBox.clear();

        Assert.assertTrue(votingBox.isEmpty());
    }

    @Test
    public void testRetainAll() {
        votingBox.addAll(Arrays.asList(
            new Vote(EXTRA_PAID_CANDIDATE),
            new Vote(REGULAR_CANDIDATE),
            new Vote(NON_PAID_CANDIDATE)
        ));

        votingBox.retainAll(Collections.singletonList(
            new Vote(REGULAR_CANDIDATE)
        ));

        Assert.assertEquals(votingBox.size(), 1);
    }

    @Test
    public void testContainsAll() {
        votingBox.addAll(Arrays.asList(
            new Vote(REGULAR_CANDIDATE),
            new Vote(NON_PAID_CANDIDATE),
            new Vote(EXTRA_PAID_CANDIDATE)
        ));

        votingBox.containsAll(Arrays.asList(
            new Vote(REGULAR_CANDIDATE),
            new Vote(EXTRA_PAID_CANDIDATE)
        ));
    }
}