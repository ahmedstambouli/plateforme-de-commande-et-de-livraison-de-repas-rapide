import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { AppService } from 'src/app/app.service';
import { ModaladdproductComponent } from './modaladdproduct/modaladdproduct.component';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit{

  constructor(private service:AppService,private formBuilder: FormBuilder,private elRef: ElementRef,public dialog: MatDialog){}
  productImageUrl!: string;
  dialogRef!: MatDialogRef<ModaladdproductComponent> | null;
  registerForm!: FormGroup;
  formadata:FormData=new FormData();
  formadataget:FormData=new FormData();

  selectedFile: any;
  id:any
  idp:any
  produit:any
  obj:any
  uti:any
  objprod:any
  idprod:any
  imageDataUrls: string[] = [];
  getproduit:any
  div:any;
  rep:any


  ngOnInit() {
    this.obj = localStorage.getItem('partenaire')
    this.uti=JSON.parse(this.obj)
     this.id=this.uti.id;

      this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      quantity: ['', Validators.required],
      categori: ['', Validators.required],
      fileName: ['', Validators.required],
      file_Name: ['', Validators.required],

    });

    this.getproduitparidp()


  }


  onFileSelected(event: any)
  {
    this.selectedFile = event.target.files[0] as File;


  }


 ajouterproduit()
 {

  this.formadata.append('name',this.registerForm.value.name)
  this.formadata.append('quantity',this.registerForm.value.quantity)
  this.formadata.append('categori',this.registerForm.value.categori)
  this.formadata.append('image', this.selectedFile);
  this.formadata.append('idp', this.id);

this.service.Ajouterproduit(this.formadata).subscribe({
  next:(res)=>{
    console.log("ajouté")
   this.getproduitparidp()
   this.closeModal()
  },

  error:(err)=>console.error(err),
})



 }

 idproduit(id:any)
 {
  this.service.getproduitparid(id).subscribe({
    next:(res)=>{
      this.produit=res
      console.log(this.produit)

    },
    error:(err)=>{
      console.log(err)
    }

  })
 }


 getproduitparidp()
 {
  console.log(this.uti);
  this.formadataget.append('id', this.uti.id);


  this.service.getproduitparidp(this.id).subscribe({

    next:(data:any)=>{

      this.objprod=data
      console.log(data)

      for (let index = 0; index < this.objprod.length; index++) {
        const element = this.objprod[index];
        console.log(element);


        this.getimage(element.id).then(imagedataurl => {
          this.imageDataUrls [element.id]=imagedataurl
          console.log(this.imageDataUrls);

        })

      }



    },
    error:(err)=>{
      console.log(err);
      if(err.status==500)
      {
        console.log("vide");

      }

    }
  })



 }


 async getimage(idproduit:any): Promise<string>
 {
  return new Promise<string>((resolve, reject) => {
  this.service.getimageproduit(idproduit).subscribe(
    response =>{
      const reader = new FileReader();
      reader.onloadend = () => {
        this.productImageUrl = reader.result as string;
        resolve( this.productImageUrl);

    };
    reader.readAsDataURL(response);



  }, error => {
    console.error('Erreur lors de la récupération de image du produit', error);
    reject(error);

  }
  )


 }


  )}







  openModal(): void {
    const dialogRef = this.dialog.open(ModaladdproductComponent, {
      width: '50%',
      // Autres configurations de la modale ici
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Modal closed with result: ${result}`);
      // Traiter le résultat du modal ici si nécessaire
    });

  }

  closeModal(): void {
    if (this.dialogRef) {
      this.dialogRef.close();
    }
  }



  deleteP(id:any)
  {
    this.service.deleteproduit(id).subscribe({
      next:(value)=>{
        alert("suppression avec succes");
        window.location.reload() ;
        },
        error:(e)=>console.log(e),

    })
  }


}
