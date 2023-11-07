import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppService } from '../app.service';
import { Partenaire } from '../Partenaire/Partenaire';
<<<<<<< HEAD
import { HttpClient } from '@angular/common/http';
=======
import { ToastrService } from 'ngx-toastr';
import { min } from 'rxjs';

>>>>>>> 6d9d40dc9fbfc36fd49ea54ef8ac385d8b14697f

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {
  registerForm !: FormGroup;
  submitted = false;



<<<<<<< HEAD
  constructor(private formBuilder: FormBuilder,private appserver:AppService ,private http:HttpClient) { }
=======
  constructor(private formBuilder: FormBuilder,private appserver:AppService,private toast:ToastrService ) { }
>>>>>>> 6d9d40dc9fbfc36fd49ea54ef8ac385d8b14697f
  data:any;
  parte:Partenaire=new Partenaire()


  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      Name: ['', Validators.required],
      Email: ['', Validators.required],
      Phone: ['', Validators.required ],
      Adresse: ['', Validators.required],
      Password: ['',Validators.required],
      Role:[''],
    });
    const data={
      "name":"oussema",
      "email": "john.dkkkoe@ssldlsdzddddjjlkdjzsm",
      "password": "secretPassword",
      "address": "t",
      "role":"admin",
      "tel": "555-123-4567"
    }
    this.http.post('http://localhost:8085/public/Partenaire/RegisterPartenaire',data).subscribe(res=>{
      console.log(res)
    })

  }



  onSubmit() {




    this.submitted = true;
    //if (this.registerForm.invalid) {
     //return;
   //}
   // alert('Success');
    /*this.appserver.savePartenaire().subscribe({
      next:(res :any)=>{
        console.log(res)
      },
      error:(err:any)=> {}
    });*/

    this.parte.name=this.registerForm.value.Name;
    this.parte.email=this.registerForm.value.Email;
    this.parte.tel=this.registerForm.value.Phone;
    this.parte.adresse=this.registerForm.value.Adresse;
    this.parte.password=this.registerForm.value.Password;
    this.parte.logo="logo.png";
    this.parte.role="partenaire";
    //console.log(this.parte)





    this.appserver.savePartenaire(this.parte).subscribe({
      next:(res:any)=>{
        //console.log(res,"response")
        this.toast.success("register successfully")

      },
      error:(err:any)=>{
        this.data=err.status;
        console.log(this.data)
        console.log(err,"error")
        if(this.data==400){
          this.toast.error("please verify your input");
        }
        this.toast.error(err.error.message);


      }
    })

    //console.log(this.data);
  }
}
