import { Component, OnInit } from '@angular/core';
import { ImageService } from '../services/image.service';
import { AlbumService } from '../services/album.service';
import { ActivatedRoute } from '@angular/router';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Image } from '../model/image';

//base64 from file
const getBase64 = (file : File) => new Promise((resolve, reject) => {
  const reader = new FileReader();
  reader.readAsDataURL(file );
  reader.onload = () => resolve(reader.result);
  reader.onerror = error => reject(error);
});

@Component({
  selector: 'app-images',
  templateUrl: './app-images.component.html',
  styleUrls: ['./app-images.component.css']
})

export class AppImagesComponent implements OnInit {

  images: Image[] = [];
  albumId: number = 0;

  //Number of section to limit image query
  chunckSection: number = 1;
  //Number of elements to load by section or pagination
  chunckSize: number = 10;

  curretImage = new Image();

  //TExt to display in Modal when a validation not success
  currentValidation = ''

  //Flag to show or not an loader when modal wait
  isModalLoading = false

  //word to filter request data from images
  filterName : string = ""

	// Start this component whith Bootstrap modal
  // And ActivedRoute to get url parameter
  // And service to get data from API
	constructor( private route: ActivatedRoute, private modalService: NgbModal,
    private albumService : AlbumService, private service: ImageService) { }

	ngOnInit(): void {
    this.albumId = Number(this.route.snapshot.paramMap.get('album_id'));

    //Firt load of images
    this.loadNextChunkImages()
	}


  loadNextChunkImages (){
    //Get elements whit limit and pagination and section
		this.service.getImagesPagination(this.chunckSize, this.chunckSection, this.albumId, this.filterName)
    .subscribe((response: any) => {
        // code : number, error : string, message : string, result : string
        const { code, error, message, result } = response

        if (error) {
          console.log("code : ", code)
          console.log("error : ", error)
          console.log("message : ", message)
          console.log("result : ", result)
        } else if (!result ){
          console.log("code : ", code)
          console.log("error : ", error)
          console.log("message : ", message)
          console.log("result : ", result)
          console.log("No hay imagenes por cargar ")
        }else {
          this.images.push( ...result)
        }

    });

    //increment for load if next seccion is nesesary
    this.chunckSection += 1
  }


  /*
  * Modal to show details for one image
  * Show details for an image, allow add one new image and allow edit exist image
  */
  openModalImageDetail (content: any, index?: number) {
    //reset message error validations
		this.currentValidation = ''

		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })

		if (index != null && index >= 0) {
			this.curretImage = Image.clone(this.images[index])
		} else {
      console.log('create new Image')
			this.curretImage = new Image();
		}
	}

  async changeImage (e : Event) {
    const htmlFile = <HTMLInputElement>e.target;
    const arrayFiles = htmlFile.files;
    const file = arrayFiles ? arrayFiles[0] : null

    if (!file) return

    const nameSplit = file.name.split('.')
    const type = nameSplit[nameSplit.length - 1]
    const name = file.name.split ( '.' + type, 1)[0]

    this.curretImage.file.fileMimeType = file.type
    this.curretImage.file.fileType = type
    this.curretImage.file.fileName = name.substring(1, 50)
    this.curretImage.file.fileSize = file.size

    //Convert file upload to text in base64
    const base64 = await getBase64(file) as string
    console.log('base64', base64)
    this.curretImage.file.fileData = base64

  }

  async saveChanges (modal : NgbActiveModal){
    //Active loading spinner
    this.isModalLoading = true

    //Validate fields: mandatory name, mandatory image
    let isValid = true;
		this.currentValidation = ''

		if (!this.curretImage.imageName || this.curretImage.imageName == "") {
			this.currentValidation = 'El campo "Nombre" no puede estar vacío\n'
			isValid = false
		}

		if (!this.curretImage.file || !this.curretImage.file.fileData || !this.curretImage.file.fileName ) {
			this.currentValidation += 'Almenos debe de haber una imagen seleccionada\n'
			isValid = false
		}

    //when is validate
    if (isValid){
        //Assign album where this image will save
        //Call album service for get an album by ID
        this.albumService.getAlbumById (this.albumId)
        .subscribe({
          next: (response: any) => {
              this.curretImage.album = response.result
          },
          complete : () => {
            console.log('album get success')
            //Validate if is edit or new record
            if (this.curretImage.imageId){
                //IS EDIT , send modal to close when finish.
                this.modifyImage(modal)
            }else{
                //IS NEW , send modal to close when finish
                this.addNewImage(modal)
            }
          },
          //On error close modal and flag loading modal change
          error: error => this.closeModal(modal, error)
        })
    }else{
        this.isModalLoading = false
    }
  }

  addNewImage (modal : NgbActiveModal){
    //Call image service for add new record
    this.service.addImage(this.curretImage).subscribe(({
        next: (response: any) => {
          //assign this value for get ID and refres update item whit data from database
          this.curretImage = response.result
          this.closeModal(modal, response)
        } ,
        //Insert image complete OK
        complete : () => {
          //add item to fisrt whithout reload
          this.images.unshift(this.curretImage)
          this.closeModal(modal, 'success')
        } ,
        error: error => this.closeModal(modal, error)
    }))
  }

  modifyImage (modal : NgbActiveModal){
    //Call image service for edit a record
    this.service.modifyImage (this.curretImage).subscribe(({
      next: (response: any) => this.closeModal(modal, response),
      //Modify image complete OK
      complete : () => {
        //Clone image to update in array and clean reference of current image
        const modificatedImage = Image.clone(this.curretImage)
        //Find index to apply update
        const idMatch = (i: Image) => i.imageId === this.curretImage.imageId
        const currentIndex = this.images.findIndex(idMatch)
        this.curretImage = new Image()
        this.images[currentIndex] = modificatedImage
        this.closeModal(modal, 'success')
      } ,
      error: error => this.closeModal(modal, error)
  }))
  }

  //Searh mach index by id and delete item from album
  deleteImage (modal: NgbActiveModal){

		this.service.deleteImage(this.curretImage)
      .subscribe((response: any) => {
        // code : number, error : string, message : string, result : string
        const { code, error, message, result } = response

        if (error) {
          console.log("error check network for more details: ", error)
        } else {
          console.log("message : ", message)
          //Alos delete item from current array
          const idMatch = (i: Image) => i.imageId === this.curretImage.imageId;
          const currentIndex = this.images.findIndex(idMatch)
          this.images.splice(currentIndex, 1)
        }

        modal.close()
    });

	}

  //Close modal when request is running, this method is running when one subscription on modal is call
  //Can recibe an detail if it close by an error and show
  closeModal(modal : NgbActiveModal, detail? : string){
    if (detail) console.log('detail close ', detail)
    this.isModalLoading = false
    modal.close()
  }

  //Apply filters search
  applyFilter (){
    console.log('Aplicando filtro')
    //Reset chunk section to get firts elements
    this.chunckSection = 1
    //Clean array
    this.images = []
    //Firt load of images
    this.loadNextChunkImages()
  }

}
