import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsComponent } from '../products.component';
import { MatDialogRef } from '@angular/material/dialog';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-modaladdproduct',
  templateUrl: './modaladdproduct.component.html',
  styleUrls: ['./modaladdproduct.component.css']
})
export class ModaladdproductComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private dialogRef: MatDialogRef<ProductsComponent>,private service:AppService){}
  registerForm!: FormGroup;
  selectedFile: any;
  formadata:FormData=new FormData();
  obj:any
  uti:any
  id:any





  ngOnInit(): void {
    this.obj = localStorage.getItem('partenaire')
    this.uti=JSON.parse(this.obj)
     this.id=this.uti.id;


    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      quantity: ['', Validators.required],
      categori: ['', Validators.required],
      fileName: ['', Validators.required],
      Price:['', Validators.required],
      file_Name: ['', Validators.required],

    });
  }



  onFileSelected(event: any)
  {
    this.selectedFile = event.target.files[0] as File;
  }
  closeModal(): void {
    this.dialogRef.close('Résultat de la modale');
  }



 ajouterproduit()
 {

  this.formadata.append('name',this.registerForm.value.name)
  this.formadata.append('quantity',this.registerForm.value.quantity)
  this.formadata.append('categori',this.registerForm.value.categori)
  this.formadata.append('price',this.registerForm.value.Price)
  this.formadata.append('image', this.selectedFile);
  this.formadata.append('idp', this.id);
    console.log(this.formadata);


this.service.Ajouterproduit(this.formadata).subscribe({
  next:(res)=>{
    console.log("ajouté")

    location.reload();

  },

  error:(err)=>console.error(err),
})





 }






}
