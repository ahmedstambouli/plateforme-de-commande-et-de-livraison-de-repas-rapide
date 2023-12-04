import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Partenaire } from '../Partenaire';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile-partenaire',
  templateUrl: './profile-partenaire.component.html',
  styleUrls: ['./profile-partenaire.component.css'],
})
export class ProfilePartenaireComponent implements OnInit {
  constructor(
    private service: AppService,
    private formBuilder: FormBuilder,
    private toast: ToastrService
  ) {}
  id: any;
  obj: any;
  uti: any;
  objpart: any;
  registerForm!: FormGroup;
  parte: Partenaire = new Partenaire();
  passwordpartenaire: string[] = [];

  ngOnInit() {
    this.obj = localStorage.getItem('partenaire');
    this.uti = JSON.parse(this.obj);
    this.id = this.uti.id;
    this.getPartenair();

    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', Validators.required],
      adresse: ['', Validators.required],
      tel: ['', Validators.required],
      logo: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  getPartenair() {
    //console.log(this.id);

    this.service.getPartenaireparId(this.id).subscribe({
      next: (response) => {
        this.objpart = response;
        this.passwordpartenaire = this.objpart.password;
        console.log(this.passwordpartenaire);
      },
      error: (e) => console.log(e),
    });
  }

  updateProfile() {
    let pass = this.registerForm.value.password;
    //console.log(this.passwordpartenaire)

    if (this.registerForm.value.password === '') {
      this.parte.name = this.registerForm.value.name;
      this.parte.email = this.registerForm.value.email;
      this.parte.tel = this.registerForm.value.tel;
      this.parte.adresse = this.registerForm.value.adresse;
      this.parte.logo = this.registerForm.value.logo;
      this.parte.role = 'partenaire';
      console.log(this.parte);
      console.log('vide');

      this.service.updatePartenairesonpassword(this.parte, this.id).subscribe({
        next: (data) => {
          alert('Modification effectuée avec succés');
          window.location.reload();
        },
        error: (err) => {
          if (err.status == 202) {
            this.toast.success('modification effectuée avec succés');
          } else {
            this.toast.error('vérifier');
          }
        },
      });
    } else {
      this.parte.name = this.registerForm.value.name;
      this.parte.email = this.registerForm.value.email;
      this.parte.tel = this.registerForm.value.tel;
      this.parte.adresse = this.registerForm.value.adresse;
      this.parte.password = pass;
      this.parte.logo = this.registerForm.value.logo;
      this.parte.role = 'partenaire';
      console.log(this.parte);
      console.log('not vide');
       this.service.updatePartenaire(this.parte, this.id).subscribe({
      next: (response) => {
        alert('modification effectuée avec succés');
        //window.location.reload();
      },
      error: (err) => {
        //console.log(err)
        if (err.status == 202) {
          this.toast.success('modification effectuée avec succés');
        } else {
          this.toast.error('erreur de modification');
        }
      },
    });
    }


  }

  logout() {
    localStorage.removeItem('partenaire');
    window.location.href = '/';
  }
}
