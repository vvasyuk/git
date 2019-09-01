import { Component, OnInit } from '@angular/core';
import { ImageService } from '../../services/image.service';
import * as pdfMake from 'pdfmake/build/pdfmake';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, Subject, Observer } from 'rxjs';

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


  constructor(private imageService: ImageService,
    private http: HttpClient) {}




  imgUrl: string = 'http://localhost:8080/img';
  //imgUrl: string = 'https://picsum.photos/200/300/?random';
  imageToShow: any;
  isImageLoading: boolean;
  csvToShow: string[][];
  text: string;
  test: string = "this.getGreeting()"

  seq: string = "";

  seq2: string = "";

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
      this.text = data;
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
              body: [
                  [this.getGreeting(), 'Second', 'Third', 'Последняя'],
                  ['Value 1', 'Value 2', 'Value 3', 'Value 4'],
                  [{ text: 'Bold value', bold: true }, 'Val 2', 'Val 3', 'Чё']
              ]
          },
          layout: {
            hLineWidth: function (i, node) {
              return (i === 0 || i === node.table.body.length) ? 2 : 0.1;
            }
          }
      },

    ]
  };
  var x = JSON.stringify(docDefinition)
  console.info(x);
  console.info(docDefinition);
  pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
  console.info('2');
  }

  getF1(){
    return(function (i, node) {
      return (i === 0 || i === node.table.body.length) ? 2 : 0.1;
    })
  }

  getF2(){
    return "function (i, node) {return (i === 0 || i === node.table.body.length) ? 2 : 0.1;}"
  }

  getGreeting() {
    return "howdy";
  }

  evalString(){
    console.log(this.test)
    console.log(eval(this.test))
    console.log(eval("asdfasdf"))
  }

  getPdf2(){
    this.imageService.getPDFData().subscribe(data => {
        console.log(data);
        //working
        //const docDefinition = {content: ['First']}
        //let docDefinition = {}
        //docDefinition = JSON.stringify(data)

        //const docDefinition = JSON.parse(data);

        const docDefinition = JSON.parse(data, function (key, value) {
          if (key == "xyi") {
            return new Date();
          } else {
            return value;
          }
        }
        );
        // console.info(docDefinition);
        // console.info(docDefinition.content.constructor.name);

        // docDefinition.content.forEach( (element) => {
        //   console.info(element.constructor.name)
        //   console.info(element.label)
        // });

        // Object.keys(docDefinition).forEach(e => {
        //   console.log(`key=${e}  value=${docDefinition[e]}`)
        // });

        // Object.entries(docDefinition).forEach(([key, value]) => {
        //   console.log(`${key} ${value}`);
        // });

        const iterate = (obj) => {
          Object.keys(obj).forEach(key => {

          //console.log(`key: ${key}, value: ${obj[key]}`)
          if (`${obj[key]}`.startsWith("this.")){
            console.log(`${obj[key]}`)
            obj[key] = eval(obj[key])
            //IE 11
            //eval('obj[key] = ("' + obj[key] + '")')
            //or
            //obj[key] = this[`${obj[key]}`.replace("this.","")]()

          }

          if (typeof obj[key] === 'object') {
                  iterate(obj[key])
              }
          })
        }

        iterate(docDefinition)

        console.info('1');
        pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
        console.info('2');
    }, error => {
      console.log(error);
    });
  }
  getSeq() {
    this.http.get('http://www.mocky.io/v2/5d62fb55330000d03e55a71b', {responseType: 'text'})
    .subscribe(a => {
      this.http.get('http://www.mocky.io/v2/5d6302a1330000403c55a729', {responseType: 'text'}).subscribe(b => {
        this.seq = a+b;
      });
    });
  }
  getSeq2() {
    //working
    // this.http.get('http://www.mocky.io/v2/5d62fb55330000d03e55a71b', {responseType: 'text'})
    // .subscribe(a => {
    //   var observable = Observable.create(function(observer) {
    //     console.info('0,1')
    //     const docDefinition = {
    //       content: [{
    //           table: {
    //               headerRows: 1,
    //               widths: ['*', 'auto', 100, '*'],
    //               body: [
    //                   ['First', 'Second', 'Third', 'Последняя'],
    //                   [a, 'Value 2', 'Value 3', 'Value 4'],
    //                   [{ text: 'Bold value', bold: true }, 'Val 2', 'Val 3', 'Чё']
    //               ]
    //           }
    //       }]
    //     };
    //     pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
    //     console.info('0,2')
    //     observer.next(0);
    //     observer.complete();
    //   });
    //   observable.subscribe(() => this.seq2='xyi' + a)
    //   });

    //working2
      const url1 = this.http.get('http://www.mocky.io/v2/5d62fb55330000d03e55a71b');
      const url2 = this.http.get('http://www.mocky.io/v2/5d6302a1330000403c55a729');
      var observable;
      forkJoin([url1, url2]).subscribe(results => {

        observable = Observable.create(function(observer) {
        console.info('0,1');
        const docDefinition = {
          content: [{
              table: {
                  headerRows: 1,
                  widths: ['*', 'auto', 100, '*'],
                  body: [
                      ['First', 'Second', 'Third', 'Последняя'],
                      [results[0], results[1], 'Value 3', 'Value 4'],
                      [{ text: 'Bold value', bold: true }, 'Val 2', 'Val 3', 'Чё']
                  ]
              }
          }]
        };
        pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
        console.info('0,2');
        observer.next(0);
        observer.complete();
        });
        observable.subscribe(() => this.seq2='xyi');
      });
    // let obs = new Observable<void>(observer => observer.complete());
    // obs.subscribe(() => this.seq2='xyi')
    // this.http.get('http://www.mocky.io/v2/5d62fb55330000d03e55a71b', {responseType: 'text'})
    // .subscribe(a => {
    //   obs.subscribe(() => {
    //     this.seq2 = a+'xyi'
    //   });
    // });
  }

  getSeq3() {
  const url1 = this.http.get('http://www.mocky.io/v2/5d62fb55330000d03e55a71b');
  const url2 = this.http.get('http://www.mocky.io/v2/5d6302a1330000403c55a729');
  return Observable.create(function(observer) {
    try{
      forkJoin([url1, url2]).subscribe(results => {
        const docDefinition = {
        content: [{
            table: {
                headerRows: 1,
                widths: ['*', 'auto', 100, '*'],
                body: [
                    ['First', 'Second', 'Third', 'Последняя'],
                    [results[0], results[1], 'Value 3', 'Value 4'],
                    [{ text: 'Bold value', bold: true }, 'Val 2', 'Val 3', 'Чё']
                ]
            }
        }]
      };
      pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
      observer.next('true');
      observer.complete();
    });
    } catch(e) {
      observer.error(e);
    }

  });
  }
  getSeq3Demo(){
    this.getSeq3().subscribe(
      data => {console.log('getSeq3Demo');},
      error => {console.log(error);}
      );
  }

  getSeq4() {
    const url1 = this.http.get('https://picsum.photos/id/1036/200/300', {responseType: 'blob'});
    const url2 = this.http.get('https://picsum.photos/id/1036/200/300', {responseType: 'blob'});
    return Observable.create(function(observer) {
      try{
        forkJoin([url1, url2]).subscribe(results => {
          let reader = new FileReader();
          reader.readAsDataURL( results[0] );
          reader.onload = () => {
            const docDefinition = {
              content: [{image: reader.result, fit: [600,600], alignment: 'center'}]
            };
            pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
            observer.next('true');
            observer.complete();
          };
      });
      } catch(e) {
        observer.error(e);
      }

    });
    }
    getSeq4Demo(){
      this.getSeq4().subscribe(
        data => {console.log('getSeq4');},
        error => {console.log(error);}
        );
    }

    // getSeq5() {
    //   const url1 = this.http.get('https://picsum.photos/id/1036/200/300', {responseType: 'blob'});
    //   const url2 = this.http.get('https://picsum.photos/id/1036/200/300', {responseType: 'blob'});
    //   return Observable.create(function(observer) {
    //     try{
    //       forkJoin([url1, url2]).subscribe(results => {
    //         let arr = [];
    //         let idx = -1;
    //         let reader = new FileReader();
    //         reader.readAsDataURL( results[0] );
    //         reader.onloadend = () => {
    //           ++idx
    //           console.log(idx);
    //           if (true){
    //             arr[idx] = reader.result;
    //             const docDefinition = {
    //               content: [{image: arr[0], fit: [600,600], alignment: 'center'},
    //                         {image: arr[0], fit: [600,600], alignment: 'center'}]
    //             }
    //             pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
    //             observer.next('true');
    //             observer.complete();
    //           }
    //         }
    //     })
    //     } catch(e) {
    //       observer.error(e);
    //     }

    //   });
    //   }
    //working
    // fileReaderObs(file: Blob)  {
    //   let reader = new FileReader()
    //   let fileReaderObs = Observable.create(function(observer) {
    //     reader.onloadend = () => {
    //       let r = reader.result;
    //       observer.next(r)
    //       observer.complete()
    //     }
    //   })
    //   reader.readAsDataURL(file)
    //   return fileReaderObs
    // };

    fileReaderObs(file: Blob)  {
      return () => {
        let reader = new FileReader()
        let fileReaderObs = Observable.create(function(observer) {
          reader.onloadend = () => {
            let r = reader.result;
            observer.next(r)
            observer.complete()
          }
        })
        reader.readAsDataURL(file)
        return fileReaderObs
    }
    };

    getSeq5() {
      const url1 = this.http.get('https://picsum.photos/id/1036/200/300', {responseType: 'blob'});
      const url2 = this.http.get('https://picsum.photos/id/1036/200/300', {responseType: 'blob'});
      return Observable.create(function(observer) {
        try{
          forkJoin([url1, url2]).subscribe(results => {
            function readerObs(b: Blob) {
              let reader = new FileReader()
              let fileReaderObs = Observable.create(function(observer) {
                reader.onloadend = () => {
                  let r = reader.result;
                  observer.next(r)
                  observer.complete()
                }
              })
              reader.readAsDataURL(b)
              return fileReaderObs
            }
            forkJoin([readerObs(results[0]), readerObs(results[1])]).subscribe(fRes => {
                const docDefinition = {
                  content: [{image: fRes[0], fit: [600,600], alignment: 'center'},
                            {image: fRes[1], fit: [600,600], alignment: 'center'}]
                }
                pdfMake.createPdf(docDefinition).download('pdfmake.pdf');
                observer.next('true');
                observer.complete();
            });
          });
        } catch(e) {
          observer.error(e);
        }
      });
      }
      getSeq5Demo(){
        this.getSeq5().subscribe(
          data => {console.log('getSeq5');},
          error => {console.log(error);}
          );
      }

      asd(){
        let deck = {
          suits: ["hearts", "spades", "clubs", "diamonds"],
          cards: Array(52),
          createCardPicker: function() {
              //return function() {
              return () => {
                  let pickedCard = Math.floor(Math.random() * 52);
                  let pickedSuit = Math.floor(pickedCard / 13);

                  return {suit: this.suits[pickedSuit], card: pickedCard % 13};
              }
          }
      }

      let cardPicker = deck.createCardPicker();
      let pickedCard = cardPicker();

      alert("card: " + pickedCard.card + " of " + pickedCard.suit);
      }

      getTextFromRest() {
        this.imageService.getTextLocal().subscribe(data => {
          //this.text = data;
          console.log(data);
        }, error => {
          console.log(error);
        });
    }
}
