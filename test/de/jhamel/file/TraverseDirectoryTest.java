package de.jhamel.file;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TraverseDirectoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionIfBasedirIsNotADirectory() {
        TraverseDirectory traverseDirectory = new TraverseDirectory("this is not an existing directory",null);
    }

    @Test
    public void giveMeAName() {
        //TraverseDirectory fileTraverse = new TraverseDirectory(getClass().,null);
    }

}
