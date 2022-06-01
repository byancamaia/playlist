package br.anhembi.playlist;

import br.anhembi.playlist.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PlaylistApplication {

	@Autowired
	private static StorageService storageService;

	public static void main(String[] args) {
		ApplicationContext context = (ApplicationContext) SpringApplication.run(PlaylistApplication.class, args);
		
		StorageService storageService = context.getBean(StorageService.class);

		System.out.println(storageService.getSongFileNames());

	}

}
