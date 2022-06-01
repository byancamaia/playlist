package br.anhembi.playlist.repository;

import br.anhembi.playlist.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {

    boolean existsSongByTitle(String title);
}
