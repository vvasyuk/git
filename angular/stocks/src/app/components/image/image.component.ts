import { Component, OnInit } from '@angular/core';
import { ImageService } from '../../services/image.service';
import * as pdfMake from 'pdfmake/build/pdfmake';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// npm install --save @types/node
// npm install pdfmake --save
// npm install pdfmake-unicode --save
// npm install @types/pdfmake --save-dev
declare var require: any;
const pdfMakeX = require('pdfmake/build/pdfmake.js');
const pdfFontsX = require('pdfmake-unicode/dist/pdfmake-unicode.js');
pdfMakeX.vfs = pdfFontsX.pdfMake.vfs;

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
  text: string;


  constructor(private imageService: ImageService,
    private http: HttpClient) {}

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

   getTextFromService() {
    this.imageService.getText().subscribe(data => {
      this.text = data
      console.log(data);
    }, error => {
      console.log(error);
    });
}

   getCsv() {
    this.csvToShow = [
          [ 'First', 'Second', 'Third', 'The last one' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Value 1', 'Value 2', 'Value 3', 'Value 4' ],
          [ 'Bold value', 'Val 2', 'Val 3', 'Val 4' ]
        ];
  }

  getPdf(){
    const docDefinition = {
      content: [{
          table: {
              headerRows: 1,
              widths: ['*', 'auto', 100, '*'],
              // body: [
              //     ['First', 'Second', 'Third', 'Последняя'],
              //     ['Value 1', 'Value 2', 'Value 3', 'Value 4'],
              //     [{ text: 'Bold value', bold: true }, 'Val 2', 'Val 3', 'Чё']
              // ]
              body: this.csvToShow
          }
      }]
  };
  console.info('1')
  console.info('1')
  console.info('1')
  console.info('1')
  console.info('1')
  console.info('1')
  pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
  console.info('2')
  }

  seq: string = ""
  getSeq() {
    this.http.get('http://www.mocky.io/v2/5d62fb55330000d03e55a71b', {responseType: 'text'})
    .subscribe(a => {
      this.http.get('http://www.mocky.io/v2/5d6302a1330000403c55a729', {responseType: 'text'}).subscribe(b => {
        this.seq = a+b
      });
    });
  }

  seq2: string = ""
  getSeq2() {
    var observable = Observable.create(function(observer) {
      console.info('0,1')
      const docDefinition = {
        content: [{
            table: {
                headerRows: 1,
                widths: ['*', 'auto', 100, '*'],
                body: [
                    ['First', 'Second', 'Third', 'Последняя'],
                    ['Value 1', 'Value 2', 'Value 3', 'Value 4'],
                    [{ text: 'Bold value', bold: true }, 'Val 2', 'Val 3', 'Чё']
                ]
            }
        }]
    };
    pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
      console.info('0,2')
      observer.next(0);
      observer.complete();});

    console.info('1')
    observable.subscribe(() => this.seq2='xyi')

    // let obs = new Observable<void>(observer => observer.complete());
    // obs.subscribe(() => this.seq2='xyi')
    // this.http.get('http://www.mocky.io/v2/5d62fb55330000d03e55a71b', {responseType: 'text'})
    // .subscribe(a => {
    //   obs.subscribe(() => {
    //     this.seq2 = a+'xyi'
    //   });
    // });
  }

}
