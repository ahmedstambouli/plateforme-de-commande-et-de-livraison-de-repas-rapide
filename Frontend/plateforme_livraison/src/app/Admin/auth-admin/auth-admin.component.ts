import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-auth-admin',
  templateUrl: './auth-admin.component.html',
  styleUrls: ['./auth-admin.component.css']
})
export class AuthAdminComponent {
  formulaire: FormGroup;
  submitted = false;
 token!:string;
showAlert: any;
  constructor(private formBuilder: FormBuilder, private http: HttpClient,private authservice:AuthService,private router:Router) {
    this.formulaire = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.formulaire.invalid) {

      return;
    }
    const emailControl = this.formulaire.get('email');
    const passwordControl = this.formulaire.get('password');
    
    if (emailControl && passwordControl) {
       this.authservice.login(this.formulaire.value).subscribe((res:any)=>{
        this.token=res.token;
        localStorage.setItem("token",this.token)
   this.router.navigate(['/HomeAdmin'])
   
       },(error:any)=>{
        this.showAlert = true;
      })
      
    }
    



  }
}


