package pl.marek.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

interface FilmRepository extends MongoRepository<Film, String> {

    /**
     * Simple projection (interface)
     */
    Collection<FilmProjection> findAllFilmNamesInterfaceBy();

    /**
     * Simple projection (dto)
     */
    Collection<FilmDto> findAllFilmNamesDtoBy();

    /**
     * Dynamic projection (interface or dto)
     */
    <T> Collection<T> findAllProjectionBy(Class<T> projection);
}
