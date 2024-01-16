package org.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomIteratorTest {
    private static final String[] surnames = {"Вальверде", "Модрич", "Кроос"};
    private static final CustomIterator<String> iterator = new CustomIterator<>(surnames);

    @DisplayName("Проверка итерирования массива имён и выброса исключения при достижении конца массива")
    @Test
    void shouldBeTrueWhileIteratorHasNextAndThrowNoSuchElementExceptionIfArrayIsOver() {
        int index = 0;
        while (iterator.hasNext()) {
            assertEquals(surnames[index++], iterator.next());
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}