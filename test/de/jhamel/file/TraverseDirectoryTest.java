package de.jhamel.file;

import org.junit.Test;

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
