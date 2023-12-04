import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppService } from '../app.service';
import { Partenaire } from '../Partenaire/Partenaire';
import { ToastrService } from 'ngx-toastr';
import { min } from 'rxjs';

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css'],
})
export class SingUpComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private appserver: AppService,
    private toast: ToastrService
  ) {}
  data: any;
  parte: Partenaire = new Partenaire();
  formadata: FormData = new FormData();
  selectedFile: any;

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      Name: ['', Validators.required],
      Email: ['', Validators.required],
      Phone: ['', Validators.required],
      Adresse: ['', Validators.required],
      Password: ['', Validators.required],
      type: ['', Validators.required],
      logo: ['', Validators.required],
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0] as File;
  }

  onSubmit() {


    this.parte.name = this.registerForm.value.Name;
    this.parte.email = this.registerForm.value.Email;
    this.parte.tel = this.registerForm.value.Phone;
    this.parte.adresse = this.registerForm.value.Adresse;
    this.parte.password = this.registerForm.value.Password;
    this.parte.logo = this.selectedFile;
    this.parte.type = this.registerForm.value.type;
    this.parte.etat = 1;

    this.parte.role = 'partenaire';


    if ((!this.parte.name)) {

      console.error('Le champ name est vide.');
    } else {
      this.formadata.append('name', this.parte.name);
    }

    if (!this.registerForm.value.Email) {
      console.error('Le champ Email est vide.');
    } else {
      this.formadata.append('email', this.registerForm.value.Email);
    }

    if (!this.registerForm.value.Phone) {
      console.error('Le champ Phone est vide.');
    } else {
      this.formadata.append('tel', this.registerForm.value.Phone);
    }

    if (!this.registerForm.value.Adresse) {
      console.error('Le champ Adresse est vide.');
    } else {
      this.formadata.append('adresse', this.registerForm.value.Adresse);
    }

    if (!this.registerForm.value.Password) {
      console.error('Le champ Password est vide.');
    } else {
      this.formadata.append('password', this.registerForm.value.Password);
    }


    if (!this.selectedFile) {
      console.error('Aucun fichier sélectionné pour le champ logo.');
    } else {
      this.formadata.append('logo', this.selectedFile);
    }

    // Vérifie si le champ 'type' est vide
    if (!this.registerForm.value.type) {
      console.error('Le champ type est vide.');
    } else {
      this.formadata.append('type', this.registerForm.value.type);
           this.formadata.append('etat', '1');
    this.formadata.append('role', 'partenaire');

    }



console.log(this.formadata.get('logo'));




    this.appserver.savePartenaire(this.formadata).subscribe({
      next:(res:any)=>{
        console.log(res,"response")
        this.toast.success("register successfully")
        setTimeout(() => {
          window.location.href='/';
        }, 3000);


      },
      error:(err:any)=>{
        console
        this.data=err.status;
        // console.log(this.data)
        // console.log(err,"error")

        this.toast.error(err.error.message);
        setTimeout(() => {
          window.location.reload();
        }, 3000);
      }
    })


  }

}
