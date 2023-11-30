package cache;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LRUCacheTest {


    @Test
    @DisplayName("캐시를 생성한다.")
    void createTest() {
        //given
        final int size = 5;

        //when
        final CustomLRUCache customCache = new CustomLRUCache(size);

        //then
        assertSoftly(softAssertions -> {
            assertThat(customCache.getMaxSize()).isEqualTo(size);
            assertThat(customCache.getSize()).isZero();
        });
    }

    @Test
    @DisplayName("값을 저장한다.")
    void save() {
        //given
        final CustomLRUCache<Integer, String> cache = new CustomLRUCache<>();
        final int key = 1;
        final String value = "polo";

        //when
        cache.save(key, value);

        //then
        assertSoftly(softAssertions -> {
            assertThat(cache.get(key)).isEqualTo(value);
            assertThat(cache.getSize()).isEqualTo(1);
        });
    }

    @Test
    @DisplayName("키가 같은 값을 저장하면, 덮어써진다.")
    void saveDuplicatedKey() {
        //given
        final CustomLRUCache<Integer, String> cache = new CustomLRUCache<>();
        cache.save(1, "polo");

        //when
        cache.save(1, "fox");

        //then
        assertSoftly(softAssertions -> {
            assertThat(cache.get(1)).isEqualTo("fox");
            assertThat(cache.getTail().getValue()).isEqualTo("fox");
        });
    }

    @Test
    @DisplayName("캐시가 꽉찬 경우 head를 삭제하고, tail에 새로운 값을 넣는다.")
    void saveFull() {
        //given
        final CustomLRUCache<Integer, String> cache = new CustomLRUCache<>(3);
        cache.save(1, "polo");
        cache.save(2, "boxter");
        cache.save(3, "juno");

        //when
        cache.save(4, "fox");

        //then
        assertSoftly(softAssertions -> {
            assertThat(cache.get(4)).isEqualTo("fox");
            assertThat(cache.getHead().getValue()).isEqualTo("boxter");
            assertThat(cache.getTail().getValue()).isEqualTo("fox");
            assertThat(cache.getSize()).isEqualTo(3);
        });
    }

    @Test
    @DisplayName("캐시히트 시 히트한 밸류를 tail로 설정한다.")
    void cacheHitSort() {
        //given
        final CustomLRUCache<Integer, String> cache = new CustomLRUCache<>(3);
        cache.save(1, "polo");
        cache.save(2, "boxter");
        cache.save(3, "juno");

        //when
        final String value = cache.get(1);

        //then
        assertSoftly(softAssertions -> {
            assertThat(value).isEqualTo("polo");
            assertThat(cache.getTail().getKey()).isEqualTo(1);
            assertThat(cache.getTail().getValue()).isEqualTo("polo");
        });
    }
}
