import { Album } from "../model/album";
import { File } from "../model/file";

export class Image {

  imageId : number;
  imageName : string;
  imageDescription : string;
  createdDate : string;
  updatedDate : string;
  album : Album;
  file : File;

  constructor () {
    this.imageId = NaN
    this.imageName = ''
    this.imageDescription = ''
    this.createdDate = ''
    this.updatedDate = ''
    this.album = new Album()
    this.file = new File()
  }

  static clone(image: Image): Image {
    const objectString = JSON.stringify(image)

    return JSON.parse(objectString);
  }

}
