import { Component, ElementRef, OnInit, ViewChild ,Renderer2,  AfterViewInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  constructor(    private toast: ToastrService,
    private route: ActivatedRoute,private service:AppService,private formBuilder: FormBuilder,private renderer: Renderer2, private elementRef: ElementRef){}
  registerForm!: FormGroup;
  formadata:FormData=new FormData();
  selectedFile: any;
  id:any
  produit:any
  @ViewChild('updateProductModal') updateProductModal!: ElementRef;



  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      quantity: ['', Validators.required],
      categori: ['', Validators.required],
      fileName: ['', Validators.required],
      file_Name: ['', Validators.required],

    });
    setTimeout(() => {
      this.openModal();
    }, 3); // Ouvrir la modal après 3 secondes
       // Récupère le paramètre 'id' de l'URL
      this.route.paramMap.subscribe(params => {
      this.id = params.get('id');

    });

    this.idproduit()
  }



  openModal() {
    const modal = this.elementRef.nativeElement.querySelector('#UpdateProduct');
    if (modal) {
      this.renderer.addClass(modal, 'show'); // Ajoute la classe 'show' pour afficher la modal
      this.renderer.setStyle(modal, 'display', 'block'); // Affiche la modal
      this.renderer.setStyle(document.body, 'padding-right', '0px'); // Empêche le défilement arrière-plan
      this.renderer.setStyle(document.body, 'overflow', 'hidden'); // Empêche le défilement arrière-plan
    }
  }

  onFileSelected(event: any)
  {
    this.selectedFile = event.target.files[0] as File;


  }

  idproduit()
 {
  this.service.getproduitparid(this.id).subscribe({
    next:(res)=>{
      this.produit=res
      console.log(this.produit)

    },
    error:(err)=>{
      console.log(err)
    }

  })
 }

  image:any
  updateproduit()
  {
    this.registerForm.value.fileName=this.selectedFile
    console.log(this.registerForm.value.fileName);

    if(this.registerForm.value.fileName==undefined)
    {
      this.image=this.produit.fileName
      console.log(this.image);

    }
    else{
      this.image=this.selectedFile
      console.log(this.image);


    }

    this.formadata.append('name',this.registerForm.value.name)
    this.formadata.append('quantity',this.registerForm.value.quantity)
    this.formadata.append('categori',this.registerForm.value.categori)
    this.formadata.append('image', this.image);
    console.log(this.image);











this.service.updateproduit(this.id,this.formadata).subscribe({
  next: (data)=>{
    console.log(data);
    //alert("Produit modifié avec succés!");
    this.toast.success("Produit modifié avec succés!")
    window.location.href='/home';
    },
    complete: ()=>{
      //console.log("complete");
      },
      error:(err)=>
      {
        console.log(err);
        this.toast.error("erreur!")


      },
})

   }


}
