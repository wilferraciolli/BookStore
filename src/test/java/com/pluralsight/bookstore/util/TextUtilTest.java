package com.pluralsight.bookstore.util;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * The type Text util unit test.
 */
public class TextUtilTest {

    /**
     * Should sanitize.
     */
    @Test
    public void shouldSanitize() {
        Assert.assertEquals("lorem ipsum dolor sit.", new TextUtil().sanitize("lorem  ipsum   dolor \n sit."));
        Assert.assertEquals("lorem ipsum dolor sit.", new TextUtil().sanitize("lorem ipsum dolor sit."));
        Assert.assertEquals("lorem ipsum dolor sit.", new TextUtil().sanitize("lorem ipsum dolor  sit."));
    }

}
