import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit{
  productImageUrl!: string;

  constructor(private service:AppService,private formBuilder: FormBuilder){}

  registerForm!: FormGroup;
  formadata:FormData=new FormData();
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
    //this.updateproduit()
    //this.getproduit=JSON.parse('produit')
    //console.log(this.getproduit);


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
    console.log("ajouté")},

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
  this.service.getproduitparidp(this.id).subscribe({

    next:(data:any)=>{

      this.objprod=data

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




image:any
  updateproduit()
  {


    if (this.registerForm.value.fileName === '') {
      console.log('ahmed');
    }


this.formadata.append('name',this.registerForm.value.name)
this.formadata.append('quantity',this.registerForm.value.quantity)
this.formadata.append('categori',this.registerForm.value.categori)
this.formadata.append('image',this.selectedFile)

console.log(this.formadata.get("image"));







this.service.updateproduit(this.produit.id,this.formadata).subscribe({
  next: (data)=>{
    console.log(data);
    alert("Produit modifié avec succés!");
    window.location.reload();
    },
    complete: ()=>{
      //console.log("complete");
      },
      error:(err)=>
      {
        console.log(err);

      },
})

  }

}
