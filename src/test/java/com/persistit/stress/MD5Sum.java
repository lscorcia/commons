package com.persistit.stress;

import java.security.DigestException;
import java.security.MessageDigest;

import com.persistit.Exchange;
import com.persistit.Key;
import com.persistit.Util;
import com.persistit.Volume;
import com.persistit.test.AbstractTestRunnerItem;

public class MD5Sum extends AbstractTestRunnerItem {

    private final static String SHORT_DESCRIPTION = "Computes and prints the MD5 sum of all keys and values";

    private final static String LONG_DESCRIPTION = "   Computes and prints the MD5 sum of all keys and values.\n"
            + "This value can be used to test whether the database state is consistent.";

    int _size;
    int _splay;
    String _opflags;

    Exchange _ex;

    private MessageDigest md;

    private String progress = "Not started";

    public MD5Sum() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            printStackTrace(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String shortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String longDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

    }

    @Override
    public void executeTest() throws Exception {
        final Volume volume = _persistit.getVolume("persistit");
        final String[] treeNames = volume.getTreeNames();
        long pairs = 0;
        for (int index = 0; index < treeNames.length; index++) {
            final Exchange exchange = _persistit.getExchange(volume,
                    treeNames[index], false);
            exchange.clear().append(Key.BEFORE);
            while (exchange.next(true)) {
                update(md, exchange.getKey().getEncodedBytes(), exchange
                        .getKey().getEncodedSize());
                update(md, exchange.getValue().getEncodedBytes(), exchange
                        .getValue().getEncodedSize());
                pairs++;
                if (pairs % 10000 == 0) {
                    progress = String.format(
                            "%,10d pairs scanned.  Tree %3d of %4d: %s", pairs,
                            index, treeNames.length, treeNames[index]);
                }
            }
        }
        final byte[] digest = md.digest();
        progress = Util.bytesToHex(digest);
    }

    private void update(final MessageDigest md, final byte[] bytes,
            final int length) throws DigestException {
        md.update(bytes, 0, length);
    }

    @Override
    protected String getProgressString() {
        return progress;
    }

    @Override
    protected double getProgress() {
        // TODO Auto-generated method stub
        return 0;
    }
}
