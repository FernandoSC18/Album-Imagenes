import { Component, OnInit } from '@angular/core';
import { Album } from '../model/album';
import { AlbumService } from '../services/album.service';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
	selector: 'app-albums',
	templateUrl: './app-albums.component.html',
	styleUrls: ['./app-albums.component.css']
})
export class AppAlbumsComponent implements OnInit {

  //All albums to show to user
	albums: Album[] = [ ];

  //Current album select when modal is open
	currentAlbum = new Album();
  //Message when error field validation occurrs
	currentValidation = '';

  //Flag to show or not an loader when modal wait
  isModalLoading = false

	//Start this component whith Bootstrap modal and service to get data from API
	constructor(private modalService: NgbModal, private service: AlbumService) { }

	ngOnInit(): void {

		this.service.getAlbums()
			.subscribe((response: any) => {
				// code : number, error : string, message : string, result : string
				const { code, error, message, result } = response

				if (error) {
					console.log("code : ", code)
					console.log("error : ", error)
					console.log("message : ", message)
					console.log("result : ", result)
				} else {
					this.albums = result;
				}

			});

	}

	//Open modal for add an album , the modal is boostrap modal and this is open whit fields empy
	openModalAdd(content: any, index?: number) {
    //desactive loading spinner
    this.isModalLoading = false
    //reset message error validations
		this.currentValidation = ''

		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })

		if (index != null && index >= 0) {
			this.currentAlbum = Album.clone (this.albums[index])
		} else {
      console.log('create new album')
			this.currentAlbum = new Album();
		}
	}

	//Open modal for delete an album , the modal is boostrap
	// Whit index show name of album to user
	openModalDelete(content: any, index?: number) {

		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })

		if (index != null && index >= 0) {
			this.currentAlbum = this.albums[index];
		} else {
			this.currentAlbum = new Album();
		}
	}


	//Save any change on an album, this is edit or Add data
	saveAlbum(modal: NgbActiveModal) {
    //Active loading spinner
    this.isModalLoading = true

    //Save need one validation, a mandatory name
		this.currentValidation = ''
		let isValid = true

		if (!this.currentAlbum.albumName || this.currentAlbum.albumName == "") {
			this.currentValidation = 'El campo "Nombre" no puede estar vacío'
			isValid = false
		}

    //Validate in albums if name exists, only can be one by album
    //First get filter and check if exist
    const albumDuplicated = this.albums.filter( (a : Album) =>
      this.currentAlbum.albumName === a.albumName && this.currentAlbum.albumId !== a.albumId)
    //Then execute if validation
		if ( albumDuplicated.length >= 1 ) {
			this.currentValidation = 'Este nombre ya esta un uso'
			isValid = false
		}

    //if name not null
		if (isValid) {

			//Verify if is edit or new record to add
			if (this.currentAlbum.albumId) {
				//IS EDIT
        //run edit to database
        this.modifyAlbum(modal)
			} else {
				//IS NEW RECORD
        //run add to database
        this.addAlbum(modal)
			}
		}else{
      //desactive loading spinner
      this.isModalLoading = false
    }

	}

  addAlbum (modal : NgbActiveModal){
    //Call Album service for add new record
    this.service.addAlbum(this.currentAlbum).subscribe(({
        next: (response: any) => {
          //assign this value for get ID and refres update item whit data from database
          this.currentAlbum = response.result
          this.closeModal(modal, response)
        },
        //Insert album complete OK
        complete : () => {
          //add item to fisrt whithout reload
          this.albums.unshift(this.currentAlbum)
          this.closeModal(modal, 'success')
        } ,
        error: error => this.closeModal(modal, error)
    }))
  }

  modifyAlbum (modal : NgbActiveModal){
    //Call Album service for edit a record
    this.service.modifyAlbum(this.currentAlbum).subscribe(({
      next: (response: any) => this.closeModal(modal, response),
      //Modify Album complete OK
      complete : () => {
        //Clone Album to update in array and clean reference of current Album
        const modificatedAlbum = Album.clone(this.currentAlbum)
        //Find index to apply update
        const idMatch = (a: Album) => a.albumId == this.currentAlbum.albumId
        const currentIndex = this.albums.findIndex(idMatch)
        this.currentAlbum = new Album()
        this.albums[currentIndex] = modificatedAlbum
        this.closeModal(modal, 'success')
      } ,
      error: error => this.closeModal(modal, error)
  }))
  }

	//Searh mach index by id and delete item from album
	deleteAlbum(modal: NgbActiveModal) {

		this.service.deleteAlbum(this.currentAlbum)
      .subscribe((response: any) => {
        // code : number, error : string, message : string, result : string
        const { code, error, message, result } = response

        if (error) {
          console.log("error check network for more details: ", error)
        } else {
          console.log("message : ", message)
          //Alos delete item from current array
          const idMatch = (a: Album) => a.albumId === this.currentAlbum.albumId;
          const currentIndex = this.albums.findIndex(idMatch)
          this.albums.splice(currentIndex, 1)
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
}
