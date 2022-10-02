export class File {

  fileId : number;
  fileName : string;
  fileDescription : string;
  fileType : string;
  fileMimeType : string;
  fileSize : number;
  fileData : string;
  createdDate : string;
  updatedDate : string;

  constructor () {
    this.fileId = NaN
    this.fileName = ''
    this.fileDescription = ''
    this.fileType = ''
    this.fileMimeType = ''
    this.fileSize = NaN
    this.fileData = ''
    this.createdDate = ''
    this.updatedDate = ''
  }

}
