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
  productImageUrl!: string;
  selectedFile: any;
  formadata: FormData = new FormData();
  formadatap: FormData = new FormData();

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
      type: ['', Validators.required],
    });

    this.getimage(this.id);
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0] as File;
  }




  getPartenair() {
    this.service.getPartenaireparId(this.id).subscribe({
      next: (response) => {
        this.objpart = response;
        console.log(this.objpart);

        this.passwordpartenaire = this.objpart.password;
        console.log(this.passwordpartenaire);
      },
      error: (e) => console.log(e),
    });
  }




  updateProfile() {
    let pass = this.registerForm.value.password;
        //   //avec password
      this.formadatap.append('idp', this.id);
      this.formadatap.append('name', this.parte.name);
      this.formadatap.append('email', this.registerForm.value.email);
      this.formadatap.append('tel', this.registerForm.value.tel);
      this.formadatap.append('adresse', this.registerForm.value.adresse);
      this.formadatap.append('password', this.registerForm.value.password);
      this.formadatap.append('logo', this.selectedFile);
      this.formadatap.append('type', this.registerForm.value.type);
      this.formadatap.append('etat', '1');
      this.formadatap.append('role', 'partenaire');
console.log(      this.formadatap.get("logo")
);

    //console.log(this.passwordpartenaire)

    // if (this.registerForm.value.password === '') {
    //   //sons password
    //   this.formadata.append('name', this.parte.name);
    //   this.formadata.append('email', this.registerForm.value.email);
    //   this.formadata.append('tel', this.registerForm.value.tel);
    //   this.formadata.append('adresse', this.registerForm.value.adresse);
    //   this.formadata.append('logo', this.selectedFile);
    //   this.formadata.append('type', this.registerForm.value.type);
    //   this.formadata.append('etat', '1');
    //   this.formadata.append('role', 'partenaire');
    //   this.formadatap.get("type");


      // this.service
      //   .updatePartenairesonpassword(this.formadata,this.id)
      //   .subscribe({
      //     next: (data) => {
      //       alert('Modification effectuée avec succés');
      //       window.location.reload();
      //     },
      //     error: (err) => {
      //       console.log(err);

      //       if (err.status == 202) {
      //         this.toast.success('modification effectuée avec succés');
      //       } else {
      //         this.toast.error('vérifier');
      //       }
      //     },
      //   });
    // } else {
    //   //avec password
    //   this.formadatap.append('idp', this.id);
    //   this.formadatap.append('name', this.parte.name);
    //   this.formadatap.append('email', this.registerForm.value.email);
    //   this.formadatap.append('tel', this.registerForm.value.tel);
    //   this.formadatap.append('adresse', this.registerForm.value.adresse);
    //   this.formadatap.append('password', this.registerForm.value.password);
    //   this.formadatap.append('logo', this.selectedFile);
    //   this.formadatap.append('type', this.registerForm.value.type);
    //   this.formadatap.append('etat', '1');
    //   this.formadatap.append('role', 'partenaire');
    //   this.formadatap.get("logo");

      // this.service.updatePartenaire(this.formadatap, this.id).subscribe({
      //   next: (response) => {
      //     alert('modification effectuée avec succés');
      //     //window.location.reload();
      //   },
      //   error: (err) => {
      //     console.log(err)
      //     if (err.status == 202) {
      //       this.toast.success('modification effectuée avec succés');
      //     } else {
      //       this.toast.error('erreur de modification');
      //     }
      //   },
      // });
   // }
  }

  async getimage(idpart: any): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      this.service.getimagepartenaire(idpart).subscribe(
        (response) => {
          const reader = new FileReader();
          reader.onloadend = () => {
            this.productImageUrl = reader.result as string;
            resolve(this.productImageUrl);
          };
          reader.readAsDataURL(response);
        },
        (error) => {
          console.error(
            'Erreur lors de la récupération de image du produit',
            error
          );
          reject(error);
        }
      );
    });
  }

  logout() {
    localStorage.removeItem('partenaire');
    window.location.href = '/';
  }
}
