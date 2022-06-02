package br.anhembi.playlist.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
public class Song implements Serializable {

    @Id
    private String id;

    private String fileName;

    private String title;

    private String artist;

    private boolean isFavorited;

}
