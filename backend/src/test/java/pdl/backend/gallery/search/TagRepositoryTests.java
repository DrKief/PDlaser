package pdl.backend.gallery.search;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.core.JdbcTemplate;

class TagRepositoryTests {

  @Test
  void addKeywordNormalizesAndInsertsOnce() {
    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    // First query: TagRepository checks that the image exists
    when(
      jdbcTemplate.queryForObject(
        eq("SELECT id FROM images WHERE id = ?"),
        eq(Long.class),
        anyLong()
      )
    ).thenReturn(1L);

    TagRepository repo = new TagRepository(jdbcTemplate);

    boolean result = repo.addKeyword(1L, " Big Sun Set ");

    assertThat(result).isTrue();

    ArgumentCaptor<String> keywordCaptor = ArgumentCaptor.forClass(String.class);
    verify(jdbcTemplate).update(
      eq("INSERT INTO imagekeywords (imageid, keyword) VALUES (?, ?) ON CONFLICT DO NOTHING"),
      eq(1L),
      keywordCaptor.capture()
    );

    // normalizeTag: trim, lower-case, whitespace -> underscore
    assertThat(keywordCaptor.getValue()).isEqualTo("big_sun_set");
  }
}
