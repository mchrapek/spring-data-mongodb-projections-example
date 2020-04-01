package pl.marek.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.marek.app.Application;

import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = Application.class)
class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    private Film film;

    @BeforeEach
    public void before() {
        this.filmRepository.deleteAll();

        this.film = filmRepository.save(Film.builder()
                .id(UUID.randomUUID().toString())
                .name("Inception")
                .director("Christopher Nolan")
                .build()
        );
    }

    @Test
    public void simpleProjectionInterface() {
        // when
        Collection<FilmProjection> result = filmRepository.findAllFilmNamesInterfaceBy();

        // then
        assertThat(result, hasSize(1));
        assertThat(result.iterator().next().getName(), is(this.film.getName()));
    }

    @Test
    public void simpleProjectionDto() {
        // when
        Collection<FilmDto> result = filmRepository.findAllFilmNamesDtoBy();

        // then
        assertThat(result, hasSize(1));
        assertThat(result.iterator().next().getName(), is(this.film.getName()));
    }


    @Test
    public void dynamicProjectionInterface() {
        // when
        Collection<FilmProjection> result = filmRepository.findAllProjectionBy(FilmProjection.class);

        // then
        assertThat(result, hasSize(1));
        assertThat(result.iterator().next().getName(), is(this.film.getName()));
    }

    @Test
    public void dynamicProjectionDto() {
        // when
        Collection<FilmDto> result = filmRepository.findAllProjectionBy(FilmDto.class);

        // then
        assertThat(result, hasSize(1));
        assertThat(result.iterator().next().getName(), is(this.film.getName()));
    }
}