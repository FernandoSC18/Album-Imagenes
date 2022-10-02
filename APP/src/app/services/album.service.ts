import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Album } from '../model/album';

@Injectable({
	providedIn: 'root'
})

/*
* Service for Request albums enpoints
*/
export class AlbumService {

	private url = 'http://localhost:8080/api';

	constructor(private httpClient: HttpClient) { }

  /*
  * ALBUM METHODS
  */

	getAlbums() {
		const path = '/albums'
		const result = this.httpClient.get(this.url + path)

		return result
	}

	getAlbumById(albumId : number) {
		const path = '/albums/id/' + albumId
		const result = this.httpClient.get(this.url + path)

		return result
	}

	modifyAlbum(album: Album) {

		const body = {
			"albumId": album.albumId,
			"albumName": album.albumName,
			"albumDescription": album.albumDescription,
			"createdDate": album.createdDate
		}

		const path = '/albums'
		const result = this.httpClient.put(this.url + path, body)

		return result
	}

	addAlbum(album: Album) {

		const body = {
			"albumName": album.albumName,
			"albumDescription": album.albumDescription
		}

		const path = '/albums'
		const result = this.httpClient.post(this.url + path, body)

		return result
	}

	deleteAlbum(album: Album) {

		const body = {
        "albumId": album.albumId
    }

		const path = '/albums'
		const result = this.httpClient.delete(this.url + path, { body : body} )

		return result
	}

}
