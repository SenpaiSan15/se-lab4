
package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    /* Példa a @BeforeEach-re, ami minden teszt előtt lefutna, de itt nem használjuk, 
    mert minden tesztben külön példányt hozunk létre.
    @BeforeEach
    void setUp() {
        store = new TorpedoStore(3, 0.0);
    }    
    */

    @Test
    void fire_Success() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        boolean result = store.fire(1);

        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_Failure() {
        TorpedoStore store = new TorpedoStore(3, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedoes() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_EmptyStore() {
        TorpedoStore store = new TorpedoStore(1, 0.0);
        store.fire(1);

        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes() {
        TorpedoStore store = new TorpedoStore(3);

        assertThrows(IllegalArgumentException.class,
                () -> store.fire(0));
    }

    @Test
    void fire_MoreTorpedoesThanAvailable() {
        TorpedoStore store = new TorpedoStore(2);

        assertThrows(IllegalArgumentException.class,
                () -> store.fire(3));
    }

    @Test
    void fire_FromEmptyStore() {
        TorpedoStore store = new TorpedoStore(0);

        assertThrows(IllegalArgumentException.class,
                () -> store.fire(1));
    }

    @Test
    void isEmpty_NotEmpty() {
        TorpedoStore store = new TorpedoStore(2);

        assertFalse(store.isEmpty());
    }
}
