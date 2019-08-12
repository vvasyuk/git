import { Component, OnInit } from '@angular/core';
import { ImageService } from '../../services/image.service';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss']
})
export class ImageComponent {

  imgUrl: string = 'http://localhost:8080/img';
  //imgUrl: string = 'https://picsum.photos/200/300/?random';
  imageToShow: any;
  isImageLoading: boolean;
  csvToShow: string[][];


  constructor(private imageService: ImageService) {}

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
       this.imageToShow = reader.result;
    }, false);

    if (image) {
       reader.readAsDataURL(image);
    }

    // this.imageToShow = window.URL.createObjectURL(image);
   }

   getImageFromService() {
       this.isImageLoading = true;
       this.imageService.getImage(this.imgUrl).subscribe(data => {
         this.createImageFromBlob(data);
         this.isImageLoading = false;
       }, error => {
         this.isImageLoading = false;
         console.log(error);
       });
   }

   getCsv() {
    this.csvToShow = [
          [ 'First', 'Second', 'Third', 'The last one' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Bold value', 'Val 2', 'Val 3', 'Val 4' ]
        ];
  }

}
