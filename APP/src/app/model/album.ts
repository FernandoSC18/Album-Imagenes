export class Album {

    albumId: number;
    albumName: string;
    albumDescription: string;
    createdDate: string;
    updatedDate: string;

    constructor () {
        this.albumId = NaN ;
        this.albumName = '';
        this.albumDescription = '';
        this.createdDate = '';
        this.updatedDate = '';
    }


    static clone (album : Album ) : Album {
        const objectString = JSON.stringify(album)
        return JSON.parse(objectString);
    }

}
