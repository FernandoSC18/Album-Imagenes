import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Image } from '../model/image';

@Injectable({
    providedIn: 'root'
})

/*
 * Service for request Image endpoints
 */
export class ImageService {

    private url = 'http://localhost:8080/api';

    constructor(private httpClient: HttpClient) { }

    /*
    * IMAGE METHODS
    */

    /*
    * Send specific album and get images whit specific range
    */
    getImagesPagination(chunckSize: number, chunckSection: number, albumId: number, filterName : string) {
        ///images/chunk_detail/{chunkSize}/{chunSection}/album/{albumId}/{filterName}
        const path = '/images/chunk_detail/' + chunckSize + '/'
          + chunckSection + '/album/' + albumId + '/' + filterName

          console.log('path:', path)

        const result = this.httpClient.get(this.url + path)

        return result
    }

    modifyImage(image: Image) {

        const body = {
            "imageId": image.imageId,
            "imageName": image.imageName,
            "imageDescription": image.imageDescription,
            "album": image.album,
            "file": {
                "fileId": image.file.fileId,
                "fileName": image.file.fileName,
                "fileDescription": image.file.fileDescription,
                "fileType": image.file.fileType,
                "fileMimeType": image.file.fileMimeType,
                "fileSize": image.file.fileSize,
                "fileData": image.file.fileData,
                "createdDate": image.file.createdDate,
                "updatedDate": image.file.createdDate
            }
        }

        const path = '/images'
        const result = this.httpClient.put(this.url + path, body)

        return result
    }

    addImage(image: Image) {

        const body = {
            "imageName": image.imageName,
            "imageDescription": image.imageDescription,
            "album": image.album,
            "file": {
                "fileName": image.file.fileName,
                "fileDescription": image.file.fileDescription,
                "fileType": image.file.fileType,
                "fileMimeType": image.file.fileMimeType,
                "fileSize": image.file.fileSize,
                "fileData": image.file.fileData
            }
        }

        console.log('file send')

        const path = '/images'
        const result = this.httpClient.post(this.url + path, body)

        return result
    }

    deleteImage(image: Image) {

        const body = {
            "imageId": image.imageId
        }

        const path = '/images'
        const result = this.httpClient.delete(this.url + path, { body: body })

        return result
    }

}
